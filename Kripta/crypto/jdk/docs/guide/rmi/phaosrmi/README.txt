
/****************************************************************
 *  Copyright (c) 1996, 1997, 1998, 1999 Phaos Technology Corp. All 
 *  rights reserved.
 ****************************************************************/

This example illustrates how to use RMI over an SSL transport layer, 
using the SSLava Toolkit.

The HelloWorld example was changed to work with SSL, but the changes
are relatively minor. They involve initializing and using SSLSockets
where appropriate, and creating a client and server socket factory. The
changes include:

  SSLHelloApplet.java - Client applet program.
  SSLHelloClient.java - Client application program.  

  SSLHelloImpl.java -  Changed to use SSL client and server socket
      factories and initialize SSL connection parameters.
   
  SSLClientSocketFactory.java - Needed to implement SSL client socket factory.

  SSLServerSocketFactory.java - Needed to implement SSL server socket factory. 

The file SETUP.txt briefly describes how to setup a secure RMI client,
server and registry.

Please submit any questions to tech@phaos.com
