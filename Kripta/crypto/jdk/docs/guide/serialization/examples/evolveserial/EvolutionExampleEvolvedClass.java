/*
 * @(#)EvolutionExampleEvolvedClass.java	1.1 98/10/03        
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
 * This example demonstratres some of the compatible changes that Serialization
 * handles without class-specific methods
 *
 * For directions of How to Run: see the Original Class file.
 *
 * Compiled and Tested with JDK1.2
 * This file contains the evolved class.
 * The original class is in file called EvolutionExampleOriginalClass.java
 */
public class EvolutionExampleEvolvedClass {

    /** 
     *  There are two options: either a user can serialize an object or
     *  deserialize it. (using the -s or -d flag). These options allow
     *  for the demonstration of bidirection readability and writeability
     *  between the original and the evolved class. In other words,
     *  one can serialize an object here and deserialize it with the evolved
     *  class or vice versa.
     */
    public static void main(String args[]) {

	AClass serializeclass = new AClass(20, "serializedByEvolvedClass");
	AClass deserializeclass = null;

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


    /** 
     * Evolved Class      
     * Has the following compatible changes 
     * 1) add a field
     * 2) change a access to a field (for example -> public to private)
     * 3) Remove the writeObject/readObject methods
     * 4) change a static field to non-static - this is equivalent to adding
     *    a field.
      
     * Compatible changes that are NOT demonstrated by this example
     * 1) Adding classes/Removing classes
     * 2) Adding writeObject/readObject methods
     */
class AClass implements Serializable {

    // mandatory suid field (gotten using serialver on the original Aclass)
    static final long serialVersionUID = -6756364686697947626L;
    
    // Change: removed the private
    /**
     * @serial
     */
    int num;
    
    // Change: added this field
    /**
     * @serial
     */
    boolean b;
    
    /**
     * @serial
     */
    String name;
    
    // Change: removed the static.. so this field will now be serialized
    // equivalent to adding a field
    /**
     * @serial
     */
    Hashtable ht = new Hashtable();
    // ...
    // ...
    // ...
    
    AClass(int n, String s) {
	num = n;
	name = s;
	boolean b = true;
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



