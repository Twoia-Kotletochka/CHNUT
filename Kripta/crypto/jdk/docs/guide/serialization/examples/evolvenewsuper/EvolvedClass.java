/*
 * @(#)EvolvedClass.java	1.2 98/10/01        
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
 * This example shows how to use versioning in serialization to evolve
 * the class to have a new superclass. 
 * 
 * For example if initially the class structure was:
 *
 * class A {...};
 * class C extends A {...};
 *
 * and the evolved clss structure looks like:
 * 
 * class A {...};
 * class B extends A {...};
 * class C extends B {...};
 *
 * then it should be possible to read the old version of C with
 * the new version of C and vice versa.
 *
 * This example demonstrates this. 
 *
 * NOTE: In this example, the superclasses (A and B in the above example)
 * implement the Serializable interface. If they did not, then it would
 * be the responsibility of the subclass C to save and restore the fields of
 * A and B. See the relevant example called: 
 * "Serialization with a NonSerializable Superclass" 
 *
 * If you want to run this: see file OriginalClass.java
 *
 *
 * Compiled and Tested with JDK1.2
 * This file contains the evolved class.
 * The original class is in file called OriginalClass.java
 */
 
public class EvolvedClass {

    /** 
     *  There are two options: either a user can serialize an object or
     *  deserialize it. (using the -s or -d flag). These options allow
     *  for the demonstration of bidirection readability and writeability
     *  between the original and the evolved class. In other words,
     *  one can serialize an object here and deserialize it with the evolved
     *  class or vice versa.
     */
    public static void main(String args[]) {
	ASubClass corg = new ASubClass(1, "SerializedByEvolvedClass", 'a');
	ASubClass cnew = null;
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

	if (serialize) {
	    // Serialize the original class object
	    try {
		FileOutputStream fo = new FileOutputStream("tmp");
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(corg);
		so.flush();
	    } catch (Exception e) {
		System.out.println(e);
		System.exit(1);
	    }
	}

	if (deserialize) {
	    // Deserialize in to new class object
	    try {
		FileInputStream fi = new FileInputStream("tmp");
		ObjectInputStream si = new ObjectInputStream(fi);  
		cnew = (ASubClass) si.readObject();
	    } catch (Exception e) {
		System.out.println(e);
		System.exit(1);
	    }
	    System.out.println();
	    System.out.println("Printing the deserialized class: ");
	    System.out.println();
	    System.out.println(cnew);
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
 * A simple superclass that implements serializable
 */
class ASuperClass implements Serializable {

    /**
     * @serial
     */
    String name;
    
    /* 
     * mandatory field obtained using the command SerialVer on the
     * original "ASuperClass"
     */
    static final long serialVersionUID = 8904068738375916775L;

    ASuperClass(String name) {
	this.name = name;
    }

    public String toString() {
	return("Name: " + name);
    }
}

/** 
 * The new superclass that has been added - also implements
 * Serializable
 */
class AMiddleSuperClass extends ASuperClass implements Serializable {

    /**
     * @serial
     */
    char ch;

    AMiddleSuperClass(String name, char ch) {
	super(name);
	this.ch = ch;
    }

    public String toString() {
	return(super.toString() + "\nCh:   " + ch);
    }
}

/** 
 * The subclass that extends the new super class and implements
 * serializable
 *
 * Again note, if the superclasses were not serializable, then it would
 * be the responsibility of this subclass to save and restore the
 * superclass's fields. See example called "Serialization with a
 * NonSerializable Superclass" for more details.
 */
class ASubClass extends AMiddleSuperClass implements Serializable {
    
    /**
     * @serial
     */
    int num;

    /* 
     * mandatory field obtained using the command SerialVer on the
     * original "ASubClass"
     */
    static final long serialVersionUID = 1099529196579429023L;

    ASubClass(int num, String name, char c) {
	super(name, c);
	this.num = num;
    }

    public String toString() {
	return (super.toString() + "\nNum:  " + num);
    }
}
 









