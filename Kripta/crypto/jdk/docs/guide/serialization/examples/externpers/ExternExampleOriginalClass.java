/*
 * @(#)ExternExampleOriginalClass.java	1.3 98/10/01        
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
import java.util.*;

/** 
 * This example shows how to evolve a persistent format using the
 * Externalizable interface. A class using the Externalizable interface
 * is responsible for saving its own state, for saving the state of its 
 * supertype, and for versioning including skipping over data. 
 * This example specifically deals with 
 * versioning - (To see how to save the state of the supertype, which can
 * Externalizable or not, see relevant examples)... 
 *
 * How to Run:
 * 
 * Compile Original Class: 
 *         javac ExternExampleOriginalClass.java
 * Run Original Class with serialization flag: 
 *         java ExternExampleOriginalClass -s
 * Compile Evolved Class:
 *         javac ExternExampleEvolvedClass.java
 * Run Evolved Class with deserialization flag:
 *         java ExternExampleEvolvedClass -d
 *
 * This tests compatibility in one direction. Do the same in other
 * direction to see  bidirectional compatibility.
 *
 * Compiled and Tested with JDK1.1.4 & JDK1.2
 */

public class ExternExampleOriginalClass {

    /** 
     *  There are two options: either a user can serialize an object or
     *  deserialize it. (using the -s or -d flag). These options allow
     *  for the demonstration of bidirection readability and writeability
     *  between the original and the evolved class. In other words,
     *  one can serialize an object here and deserialize it with the evolved
     *  class or vice versa.
     */
    public static void main(String args[]) {  
	
	boolean serialize = false;
	boolean deserialize = false;
	
	ExternVersioningClass wobj = new ExternVersioningClass(2, "oldclass");
	ExternVersioningClass robj = null;
	/* 
	 * see if we are serializing or deserializing.
	 * The ability to deserialize or serialize allows
	 * us to see the bidirectional readability and writeability
	 */
	if (args.length == 1) {
	    if (args[0].equals("-d")) {
		deserialize = true;
	    } else if (args[0].equals("-s")) {
		serialize = true;
	    } else {
                usage();
                System.exit(0);
	    }
	} else {
	    usage();
	    System.exit(0);
     }
	
	/* 
	 * serialize, if that's the chosen option
	 */
	if (serialize) {
	    try {
		FileOutputStream fo = new FileOutputStream("evolve.tmp");
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(wobj);
		so.close();
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	}
	
	/* 
	 * deserialize, if that's the chosen option
	 */
	if (deserialize) {
	    try {
		FileInputStream fi = new FileInputStream("evolve.tmp");
		ObjectInputStream si = new ObjectInputStream(fi);
		robj = (ExternVersioningClass) si.readObject();
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	}
    }
    /** 
     * Prints out the usage
     */
    static void usage() {
        System.out.println("Usage:");
        System.out.println("      -s (in order to serialize)");
        System.out.println("      -d (in order to deserialize)");
    }
}

/*
 * The original ExternVersioningClass
 */
class ExternVersioningClass implements Externalizable  {

  // some data that we will explicitly save
  int dimension;
  int array[];
  String name;

  public static final int version = 1;
 
  // ***need to have a public-no-arg constructor*** with Exernalizable
  public ExternVersioningClass() {}

  ExternVersioningClass(int dim, String n) {
    // initialize 
    dimension = dim;
    array = new int[dimension];
    name = n;
  }

    /** 
     * Mandatory writeExternal method. It writes out the class version
     * number and then writes out the fields.
     * 
     * @serialData Writes out an int "class version number". Next, the
     *             dimension field is written as an int. Followed by
     *             the array field written as an object. Finally, the
     *             name field is written as an object.
     */
    public void writeExternal(ObjectOutput out) throws IOException {
	
	// we first write out the class version number (ie. # 1)
	out.writeInt(version);
	
	// now we write out the data
	out.writeInt(dimension);
	out.writeObject(array);
	out.writeObject(name);
    }

    /** 
     * Mandatory readExternal method. Just reads in the fields for this
     * class. Assumption is that later version of the class make only
     * compatible changes and that their additional data is appended.
     *
     * @serialData Read a class version number as an int, the dimension field 
     *             as an int, array field as an Object and name field as an 
     *             object.
     */
    public void readExternal(ObjectInput in) throws IOException,
	java.lang.ClassNotFoundException {
	
	int version = in.readInt();
	System.out.println("Reading an object written by Version #: " + version);
	dimension = in.readInt();
    
	// need to allocate memory for the array we will read in
	array = (int[]) in.readObject();
	name = (String) in.readObject();
    }
}











