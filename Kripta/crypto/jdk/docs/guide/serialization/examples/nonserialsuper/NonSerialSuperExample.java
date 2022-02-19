/*
 * @(#)NonSerialSuperExample.java	1.1 98/10/03        
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
 */

import java.io.*;

/** This example shows how to serialize a subclass whose superclass is not
 * serializable.

 * When a superclass of a particular is not serializable, the subclass is
 * responsible for saving the state of its supertype (in its writeObject)
 *
 * Compiled and Tested on JDK1.1 & JDK1.2
 *
 * How to run this example:
 *                         Compile this file: javac NonSerialSuperExample.java
 *                         Then run:          java NonSerialSuperExample
 *
 * This will print out a book object before and after serialization.
 */
public class NonSerialSuperExample {

    /**
     * Creates a book object, serializes it, deserializes it and then prints 
     * out to test that the serialization did work.
     */
    public static void main(String args[]) {

	// create a Book object 
	Book bookorg = new Book(100, "How to Serialize", true, "R.R", "Serialization", 1997);
	Book booknew = null;
	
	// serialize the Book
	try {
	    FileOutputStream fo = new FileOutputStream("tmp");
	    ObjectOutputStream so = new ObjectOutputStream(fo);
	    so.writeObject(bookorg);
	    so.flush();
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}
	
	// deserialize the Book
	try {
	    FileInputStream fi = new FileInputStream("tmp");
	    ObjectInputStream si = new ObjectInputStream(fi);  
	    booknew = (Book) si.readObject();
	}catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}
	
    // The books should be the same if we did everything correctly!
	System.out.println();
	System.out.println("Printing original book...");
	System.out.println(bookorg);
	System.out.println("Printing new book... ");
	System.out.println(booknew);
	System.out.println("The original and new should be the same!");
	System.out.println();
    }  
}



  


