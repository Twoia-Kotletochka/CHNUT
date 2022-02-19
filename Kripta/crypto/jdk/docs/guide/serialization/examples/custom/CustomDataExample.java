/*
 * @(#)CustomDataExample.java	1.4 98/10/01        
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
 * This example shows how to use writeObject and readObject to encode
 * custom data format. When the persistent data is unwieldy, it is suitable
 * to store it in a more convenient, condensed format. 
 * 
 * Specifically, this example considers the case of a triangular array. A
 * triangular array is simply a 2-dimensional array that is symmetric. So when
 * serializing it, it is desirable to save only 1/2 of the 2-dimensional array
 * rather than the whole.
 *
 * This is different from the Serialization and Serializable Fields API 
 * Example in that this example does not support versioning.  This example
 * differs from using an Externalizable interface in that this example does
 * not have to worry about superclasses.
 *
 * Complied and tested on JDK 1.1 & the Java 2 SDK, v1.2.
 *
 * How to run this example:
 *                         Compile this file: javac CustomDataExample.java
 *                         Then run:          java CustomDataExample
 *
 *  This will print out two arrays: one from before serialization and the
 *  other from after deserialization.
 */
public class CustomDataExample implements Serializable {

    transient int dimension;
    transient int thearray[][];

    /**
     * Create the triangular array of dimension dim and initialize it
     */
    CustomDataExample (int dim) {
  	dimension = dim;
        thearray = new int[dim][dim];
	arrayInit();
    }

    /**
     * Create an CustomDataExample object, serialize it, deserialize it and 
     * see that they are the same. So, basically test that this custom
     * data example's serialization works.
     */
    public static void main(String args[]) {
	CustomDataExample corg = new CustomDataExample(4);
	CustomDataExample cnew = null;
	
	// Serialize the original class object
	try {
	    FileOutputStream fo = new FileOutputStream("cde.tmp");
	    ObjectOutputStream so = new ObjectOutputStream(fo);
	    so.writeObject(corg);
	    so.flush();
	    so.close();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	
	// Deserialize in to new class object
	try {
	    FileInputStream fi = new FileInputStream("cde.tmp");
	    ObjectInputStream si = new ObjectInputStream(fi);  
	    cnew = (CustomDataExample) si.readObject();
            si.close();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    
	// Print out to check the correctness
	System.out.println();
	System.out.println("Printing the original array...");
	System.out.println(corg);
	System.out.println();
	System.out.println("Printing the new array...");
	System.out.println(cnew);
	System.out.println();
	System.out.println("The original and new arrays should be the same!");
	System.out.println();
   }
    
    /** 
     * Write out the dimension and 1/2 of the 2-dimensional array to the
     * ObjectOutputStream s. readObject depends on this data format.
     *
     * @serialData Write serializable fields, if any exist.
     *             Write out the integer Dimension of the symetrical,
     *             two-dimensional array. Write out the integers composing
     *             1/2 of the 2-dimensional array. 
     *             
     */
    private void writeObject(ObjectOutputStream s)
	throws IOException {
	    // Call even if there is no default serializable fields.
	    s.defaultWriteObject();

	    // save the dimension
	    s.writeInt(dimension);

	    // write out only 1/2 of the 2-dimensional array
	    for (int i = 0; i < dimension; i++) {
		for (int j = 0; j <= i; j++) {
		    s.writeInt(thearray[i][j]);
		}
	    }	    
    }
  
    /** 
     * Read in the dimension and 1/2 of the 2-dimensional array from the
     * ObjectInputStream s. Was written to by writeObject. Also, copy the
     * 1/2 array to the other half to completely fill the symmetric array.
     *
     * @serialData Read serializable fields, if any exist.
     *             Read optional data consisting of an integer indicating
     *             both dimensions of the 2-dimensional array. Read in
     *             1/2 of the 2-dimensional array. 
     */
    private void readObject(ObjectInputStream s) 
	throws IOException, ClassNotFoundException  {
	    /*  Call even if there is no default serializable fields.
	     *  Enables default serializable fields to be added in future versions
             *  and skipped by this version which has no default serializable fields.
	     */
	    s.defaultReadObject();

	    // restore the dimension
	    dimension = s.readInt();
	    
	    // allocate space for the array
	    thearray = new int[dimension][dimension];
	    
	    // first restore 1/2 the 2-dimensional array
	    for (int i = 0; i < dimension; i++) {
		for (int j = 0; j <= i; j++) {
		    thearray[i][j] = s.readInt();
		}
	    }
	    
	    // copy over to the other side
	    for (int i = 0; i < dimension; i++) {
		for (int j = dimension - 1; j > i; j--) {
		    thearray[i][j] = thearray[j][i];
		}
	    }
    }

    /**
     * Initialize the array to some numbers starting from 0 - make it 
     * symmetrical 
     */ 
    void arrayInit() {
       	int x = 0;
	for (int i = 0; i < dimension; i++) { 
	    for (int j = 0; j <= i; j++) {
		thearray[i][j] = x;
		thearray[j][i] = x;
		x++;
	    }
	}       
    }

    /** 
     * Print the 2-dimensional array. Useful for testing.
     */
    public String toString() {

	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < dimension; i++) { 
	    for (int j = 0; j < dimension; j++) {
		sb.append(Integer.toString(thearray[i][j])+ " ");
	    }
	    sb.append("\n");
	}
	return(sb.toString());
    }
}



 









