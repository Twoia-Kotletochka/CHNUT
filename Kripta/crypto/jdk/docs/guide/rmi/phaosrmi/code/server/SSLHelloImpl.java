
/****************************************************************
 *  Copyright (c) 1996, 1997, 1998, 1999 Phaos Technology Corp. All 
 *  rights reserved.
 ****************************************************************/

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
import java.rmi.registry.*;

// Import Phaos SSLava Toolkit APIs
import crysec.*;
import crysec.SSL.*;

// Extend to use SSL
public class SSLHelloImpl extends UnicastRemoteObject implements Hello {
  private String name;

  public SSLHelloImpl(SSLServerSocketFactory ssf, String s) 
      throws RemoteException {

      /* pass a client ssl socketfactory and a server ssl socket
       * factory to allow secure rmi communication.  
       *
       * SSLClientSocketFactory is downloaded to client transparently.  
       */
      super(0, new SSLClientSocketFactory(), ssf);
      
      name = s;
  }

  public String sayHello() throws RemoteException {
    return "SSL Hello World!";
  }

  public static void main(String args[]) {
    try {
	// initialize server certificate
	SSLCertificate cert = new SSLCertificate();
	cert.certificateList = new Vector();
	cert.certificateList.addElement(new X509(new File("server-cert.der")));
	cert.certificateList.addElement(new X509(new File("ca-cert.der")));

	// initialize SSL context object
	SSLParams params = new SSLParams();
	params.setServerCert(cert);

	// require client authentication
	params.setRequestClientCert(true);

	System.setSecurityManager(new RMISecurityManager());
	
	// secure server socket factory to use in remote objects
	SSLServerSocketFactory ssf = new SSLServerSocketFactory(params);

	// create a secure rmiregistry
	Registry registry = LocateRegistry.createRegistry(1099,
			   new SSLClientSocketFactory(), ssf);

	// create a remote object that will use a secure client/server socket pair
	SSLHelloImpl o = new 
	    SSLHelloImpl(ssf, "HelloServer");

	registry.rebind("/HelloServer", o);
	System.out.println("HelloServer bound in registry");

      } catch (Exception e) {
	  System.err.println(e.getMessage());
	  e.printStackTrace();
      }
  }
}
