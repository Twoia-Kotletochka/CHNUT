 
/*
 * @(#)Nonexternsuper.java	1.2 99/11/23        
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

/** 
 * When using the Externalizable Interface, an externalizable object
 * must implement a writeExternal method to save the state of the object.
 * It must also explicitly coordinate with its supertype to save its state.

 * This simple example shows how to do this for an object whose supertype
 * is NOT externalizable.
 *
 *
 *   How to Run:
 *             Compile the file:  javac Nonexternsuper.java
 *             Then run: java Nonexternsuper.java
 *
 * This should print out a book object before and after serialization.
 *
 *
 * Tested and compiled on JDK 1.1 & Java 2 SDK, v1.2.
 */

public class Nonexternsuper {
   
    /**
     * Create an Book (subclass of reading material) object, serialize it, 
     * deserialize it and see that they are the same. So, basically test that 
     * this Externalizable example's works
     */
    public static void main(String args[]) {

	// create a Book object 
	Book bookorg = new Book(100, "How to Serialize", true, "R.R", "Serialization", 97);
	Book booknew = null;
	
	//serialize the book
	try {
	    FileOutputStream fo = new FileOutputStream("tmp");
	    ObjectOutputStream so = new ObjectOutputStream(fo);
	    so.writeObject(bookorg);
	    so.flush();
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}

	// de-serialize the Book
	try {
	    FileInputStream fi = new FileInputStream("tmp");
	    ObjectInputStream si = new ObjectInputStream(fi);  	    
	    booknew = (Book) si.readObject();
	}
	catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}

	/* 
	 * Print out the original and new book information
	 * It should be the same if we did everything correctly!
	 */
	System.out.println();
	System.out.println("Printing original book...");
	System.out.println(bookorg);
	System.out.println("Printing new book... ");
        System.out.println(booknew);
	System.out.println("Both original and new should be the same!");
	System.out.println();
    }
}
    



















    




  




 
