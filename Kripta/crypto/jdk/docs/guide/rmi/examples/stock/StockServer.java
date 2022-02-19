/*
 * Copyright (c) 1996,1997,1998,1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package examples.stock;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class StockServer extends UnicastRemoteObject
	implements StockWatch, Runnable
{
    /** table that maps StockNotify objects to a vector of stocks */
    private Hashtable notifyTable = new Hashtable();
    /** table that maps stock names to stock update info */
    private Hashtable stockTable = new Hashtable();
    /** thread for notifying watchers of stock updates */
    private Thread notifier = null;

    private static String name[] = { "Sun", "HP", "Microsoft", "Compaq", "Novell",
				     "IBM", "Apple", "AOL", "Inprise", "SGI"};
    /**
     * Construct the stock server
     * @exception RemoteException if remote object cannot be exported
     */
    public StockServer() throws RemoteException 
    {
	for (int i=0; i<name.length; i++) {
	    stockTable.put(name[i], new Stock(name[i]));
	}
    }

    /**
     * Request notification of stock updates.
     * @param stock the stock name
     * @param obj the remote object to be notified
     * @return the latest update of the stock
     * @exception StockNotFoundException if stock is not known
     */
    public synchronized Stock watch(String stock, StockNotify obj)
	throws StockNotFoundException
    {
	System.out.println("StockServer.watch: " + stock );
	
	if (!stockTable.containsKey(stock)) {
	    throw new StockNotFoundException(stock);
	}
	
	Vector stocks = (Vector)notifyTable.get(obj);

	// register interested party...
	if (stocks == null) {
	    stocks = new Vector();
	    notifyTable.put(obj, stocks);
	}

	// add stock to list
	if (!stocks.contains(stock)) {
	    stocks.addElement(stock);
	}
	
	// start thread to notify watchers...
	if (notifier == null) {
	    notifier = new Thread(this, "StockNotifier");
	    notifier.start();
	}
	return (Stock)stockTable.get(stock);
    }

    /**
     * Cancel request for stock updates for a particular stock.
     * @param stock the stock name
     * @param obj the remote object canceling the notification
     */
    public void cancel(String stock, StockNotify obj)
    {
	Vector stocks = (Vector)notifyTable.get(obj);
	stocks.removeElement(stock);
    }

   /**
     * Returns an array of stock update information for the stocks
     * already registered by the remote object.
     * @param obj the remote object
     * @return the list of stocks, or null if obj is not watching any
     *  stocks
     * @exception RemoteException if some communication failure occurs
     */
    public Stock[] list(StockNotify obj)
    {
	Vector stocks = (Vector)notifyTable.get(obj);
	Stock[] stockList = null;
	
	if (stocks != null) {
	    Enumeration enum = stocks.elements();
	    stockList = new Stock[stocks.size()];
	    int i=0;
	    // collect updates to the stocks this watcher is
	    // interested in
	    while (enum.hasMoreElements()) {
		String stockname = (String)enum.nextElement();
		stockList[i++] = (Stock)stockTable.get(stockname);
	    }
	}
	return stockList;
    }

    /**
     * Cancel all requests for stock updates for the remote object.
     * @param obj the remote object canceling the request
     * @exception RemoteException if some communication failure occurs
     */
    public synchronized void cancelAll(StockNotify obj)
    {
	notifyTable.remove(obj);
    }

    /**
     * Private method to generate random stock updates
     */
    private void generateUpdates() 
    {
	Enumeration enum = stockTable.elements();
	while (enum.hasMoreElements()) {
	    Stock stock = (Stock)enum.nextElement();
	    stock.update();
	}
    }

    /**
     * The run method (called from the notifier thread) sends out stock
     * updates periodically to those remote objects that have
     * registered interest in being notified.
     */
    public void run() 
    {
	boolean done = false;

	do {
  
	    try {
		// wait for a few seconds between updates
		Thread.currentThread().sleep(2000);
	    } catch (InterruptedException e) {
	    }

	    Date date = new Date();
	    // update stocks in table
	    generateUpdates();
	    
	    // enumerate through each watcher...
	    Enumeration enum = notifyTable.keys();
	    while (enum.hasMoreElements()) {
		StockNotify obj = (StockNotify)enum.nextElement();
		Stock[] stockList = list(obj);
		if (stockList != null) {
		    // send update
		    try {
			System.out.println("StockServer.run: sending update " +
					   date);
			obj.update(date, stockList);
		    } catch (RemoteException e) {
			// can't reach watcher; cancel notification request
			System.out.println("StockServer.run: exception");
			e.printStackTrace();
			cancelAll(obj);
		    }
		}
	    }

	    // check to see if the update thread should exit
	    synchronized (this) {
		if (notifyTable.isEmpty()) {
		    notifier = null;
		    done = true;
		}
	    }

	} while (!done);
    }

    /**
     * Start up the stock server; also creates a registry so that the
     * StockApplet can lookup the server.
     */
    public static void main(String args[]) 
    {
	// Create and install the security manager
	System.setSecurityManager(new RMISecurityManager());

	try {
	    System.out.println("StockServer.main: creating registry");
	    LocateRegistry.createRegistry(2005);
	    System.out.println("StockServer.main: creating server");
	    StockServer server = new StockServer();
	    System.out.println("StockServer.main: binding server ");
	    Naming.rebind("//:2005/example.stock.StockServer", server);
	    System.out.println("StockServer.main: done");

	    // Note: this application will exit when all remote stock
	    // watchers drop their references to the remote server
	    // object and unregister their interest in watching stock
	    // reports.

	} catch (Exception e) {
	    System.out.println("StockServer.main: an exception occurred: " +
			       e.getMessage());
	    e.printStackTrace();
	}
    }
}
