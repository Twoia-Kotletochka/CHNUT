
/****************************************************************
 *  Copyright (c) 1996, 1997, 1998,1999 Phaos Technology Corp. All 
 *  rights reserved.
 ****************************************************************/

import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.applet.*;
import java.rmi.registry.*;

import SSLClientSocketFactory;

/* Applet class needs no security related logic to take advantage of
 * secure communications with an rmi server.  Secure client socket
 * class is automatically downloaded from the codebase and installed
 * into the applet.  
 */
public class SSLHelloApplet extends Applet { 
    
    String message = "blank"; 
    
    /* "obj" is the identifier that we'll use to refer to the remote
     * object that implements the "Hello" interface 
     */
    Hello obj = null;
    
    public void init() {
	try {

            // Load the client socket factory from the applet host.
            // contact a secure rmi registry.
            Registry registry = java.rmi.registry.LocateRegistry.
                getRegistry(getCodeBase().getHost(), 1099,
                            new SSLClientSocketFactory());
            
	    /* obtain a remote reference to the secure server using
	     * jrmp over SSL.
	     */
            obj = (Hello) registry.lookup("/HelloServer"); 

	    // invoke a secure remote method
            message = obj.sayHello(); 
	} catch (Exception e) {
	    System.out.println("HelloApplet exception: " +
			       e.getMessage());
	    e.printStackTrace();
	}
    }
    
    public void paint(Graphics g) {
	g.drawString(message, 25, 50);
    }
}
