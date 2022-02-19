/****************************************************************
 *  Copyright (c) 1996, 1997, 1998,1999 Phaos Technology Corp. All 
 *  rights reserved.
 ****************************************************************/

import java.net.*;
import java.io.*;
import java.rmi.server.*;

// Import Phaos SSLava Toolkit APIs
import crysec.SSL.*;

// Extend standard RMISocketFactory to use SSL.
public class SSLServerSocketFactory implements RMIServerSocketFactory, 
    Serializable {

  // SSL context object
  transient protected SSLParams params;
  
  public SSLServerSocketFactory()
  {
    this(new SSLParams());
  }

  public SSLServerSocketFactory(SSLParams p) {
    params = p;
  }

  public ServerSocket createServerSocket(int port) 
      throws IOException {
    return ((ServerSocket) new SSLServerSocket(port, params));
  }
}

