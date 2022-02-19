
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
public class SSLClientSocketFactory 
    implements RMIClientSocketFactory, Serializable {

  public Socket createSocket(String host, int port) 
      throws IOException {
    return ((Socket) new SSLSocket(host, port, new SSLParams()));
  }
}
