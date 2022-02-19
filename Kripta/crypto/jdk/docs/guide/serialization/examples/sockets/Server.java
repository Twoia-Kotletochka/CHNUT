/*
 * @(#)Server.java	1.1 98/10/03        
 *
 * Copyright (c) 1997 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * 
 */

import java.io.*;
import java.net.*;
import java.util.*;


/**
 * This example shows how to use sockets to send and receive objects.
 * This file contains the class Server, which does the receiving of objects 
 * from class WriteSocket in file WriteSocket.java
 * The Server has to run first and wait for the WriteSocket
 * to send the information.
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class Server {

    /**
     * Create the serversocket and use its stream to receive serialized objects
     */
    public static void main(String args[]) {

      ServerSocket ser = null;
      Socket soc = null;
      String str = null;
      Date d = null;
   
      try {
	ser = new ServerSocket(8020);
	/*
	 * This will wait for a connection to be made to this socket.
         */	
	soc = ser.accept();
	InputStream o = soc.getInputStream();
	ObjectInput s = new ObjectInputStream(o);
	str = (String) s.readObject();
	d = (Date) s.readObject();
	s.close();
	
	// print out what we just received
	System.out.println(str);
	System.out.println(d);
      } catch (Exception e) {
       	  System.out.println(e.getMessage());
	  System.out.println("Error during serialization");
	  System.exit(1);
      }
    }
}
