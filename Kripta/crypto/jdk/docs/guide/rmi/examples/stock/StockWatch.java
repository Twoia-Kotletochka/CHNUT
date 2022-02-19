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

/**
 * The StockWatch remote interface is used to register interest in
 * receiving stock updates.
 */
public interface StockWatch extends java.rmi.Remote {

    /**
     * Request notification of stock updates.
     * @param stock the stock name
     * @param obj the remote object to be notified
     * @return the latest update of the stock
     * @exception StockNotFoundException if stock is not known
     * @exception RemoteException if some communication failure occurs
     */
    Stock watch(String stock, StockNotify obj)
	throws StockNotFoundException, RemoteException;

    /**
     * Cancel request for stock updates for a particular stock.
     * @param stock the stock name
     * @param obj the remote object canceling the notification
     * @exception RemoteException if some communication failure occurs
     */
    void cancel(String stock, StockNotify obj) throws RemoteException;

    /**
     * Returns an array of stock update information for the stocks
     * already registered by the remote object.
     * @param obj the remote object
     * @return the list of stocks, or null if obj is not watching any
     *  stocks
     * @exception RemoteException if some communication failure occurs
     */
    Stock[] list(StockNotify obj) throws RemoteException;

    /**
     * Cancel all requests for stock updates for the remote object.
     * @param obj the remote object canceling the request
     * @exception RemoteException if some communication failure occurs
     */
    void cancelAll(StockNotify obj) throws RemoteException;
}

    
