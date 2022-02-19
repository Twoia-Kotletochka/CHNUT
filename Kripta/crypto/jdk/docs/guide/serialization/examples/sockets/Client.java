/*
 * @(#)Client.java	1.1 98/10/03        
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
 * This example shows how to use sockets and serialization to send 
 * objects that will be received
 * (see class Server to see the receiving part)
 *
 * Compiled and Tested with JDK1.1 & JDK1.2
 */
public class Client {

    public static void main(String args[]) {

    
      try {
	  // Create a socket  
	  Socket soc = new Socket(InetAddress.getLocalHost(), 8020);

	  // Serialize today's date to a outputstream associated to the socket
	  OutputStream o = soc.getOutputStream();
	  ObjectOutput s = new ObjectOutputStream(o);
	  
	  s.writeObject("Today's date");
	  s.writeObject(new Date());
	  s.flush(); 
	  s.close();
      } catch (Exception e) {
	  System.out.println(e.getMessage());
	  System.out.println("Error during serialization");
	  System.exit(1);
      }
  }
}


