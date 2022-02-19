/*
 * @(#)EvolutionExampleOriginalClass.java	1.3 98/10/01        
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
 * When Java objects use serialization to store objects, the potential arises
 * that the version of the class reading the data is different from the version
 * of class that wrote that data

 *
 * This example demonstrates some of the compatible changes that Serialization
 * handles without class-specific methods

 * How to Run:
 * 
 * Compile Original Class: 
 *         javac EvolutionExampleOriginalClass.java
 * Run Original Class with serialization flag: 
 *         java EvolutionExampleOriginalClass -s
 * Compile Evolved Class:
 *         javac EvolutionExampleEvolvedClass.java
 * Run Evolved Class with deserialization flag:
 *         java EvolutionExampleEvolvedClass -d
 *
 * This tests compatibility in one direction only. Perform the same steps in 
 * the other direction to see bidirectional compatibility.
 *

 * Compiled and Tested with JDK1.1.4 & JDK1.2
 * This file contains the original class.
 * The evolved class is in file called EvolutionExampleEvolvedClass.java
 */
public class EvolutionExampleOriginalClass {


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
		
	AClass serializeclass = new AClass(10, "serializedByOriginalClass");
	AClass deserializeclass = null;

	/* 
	 * Serialize the original class if that's the option chosen
	 */
	if (serialize) {
	    try {
		FileOutputStream fo = new FileOutputStream("evolve.tmp");
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(serializeclass);
		so.flush();
	    } catch (Exception e) {
		System.out.println(e);
		System.exit(1);
	    }
	}
    
	/* 
	 * Deserialize, if that's the option chosen and print the name
	 * of the object, which will allow us to see who serialized the
	 * object, the original class or the evolved class file
	 */
	if (deserialize) {
	    try {
		FileInputStream fi = new FileInputStream("evolve.tmp");
		ObjectInputStream si = new ObjectInputStream(fi);  
		deserializeclass = (AClass) si.readObject();
	    } catch (Exception e) {
		System.out.println(e);
		System.exit(1);
	    }
	    /* 
	     * Print out to see that it is indeed the same object as it was
	     * when it was serialized (depending on whether it was the original
	     * class that serialized it or the evolved class)
	     */
	    System.out.println("Now printing deserialized object's name: ");
	    System.out.println();
	    System.out.println("name: " + deserializeclass.name);
	    System.out.println();
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
 * Original Class
 * will evolve later (see the other file)
 */
class AClass implements Serializable {
    
    // some data fields.

    /** 
     * @serial
     */
    private int num;

    /** 
     * @serial
     */
    String name;

    static Hashtable ht = new Hashtable();
 
    // ...
    // ...
    // ...
    
    AClass(int n, String s) {
	num = n;
	name = s;
    }
   
    // some methods...
    // ...
    // ...

    /** 
     * These writeObject and readObject merely defaultwriteObject and
     * defaultreadObject - so they don't do anything.. but they are placed
     * here so that we can show that we can remove them in the evolved 
     * class without any effect.
     *
     * @serialData Write serializable fields. No optional data written.
     */
    private void writeObject(ObjectOutputStream s)
	throws IOException {    
	s.defaultWriteObject();
    }
  
    /** 
     * readObject - just calls defaultreadObject()
     *
     * @serialData Read serializable fields. No optional data read.
     */
    private void readObject(ObjectInputStream s)
	throws IOException, ClassNotFoundException {
	    s.defaultReadObject();
    }
}
