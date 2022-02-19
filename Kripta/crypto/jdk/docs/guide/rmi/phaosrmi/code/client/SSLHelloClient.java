
/****************************************************************
 *  Copyright (c) 1996, 1997, 1998, 1999 Phaos Technology Corp. All 
 *  rights reserved.
 ****************************************************************/

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import Hello;

// Version of HelloClient that uses SSL
public class SSLHelloClient {
    public static void main(String args[]) {
	try {
	    if (args.length < 1) {
		System.err.println("Usage: <hostName>");
		System.exit(1);
	    }

	    // This example will not work without a security manager.
	    System.setSecurityManager(new RMISecurityManager());

	    // Create a secure rmiregistry
	    Registry registry = LocateRegistry.
		getRegistry(args[0], 1099, 
			    new SSLClientSocketFactory());

	    // Obtain a reference to the HelloObject
	    Hello hello = (Hello) registry.lookup("/HelloServer");
	    System.out.println("Message: " + hello.sayHello());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
