/*
 *
 * @(#)OriginalClass.java	1.4 98/10/01        
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
import java.awt.*;

/**
 *
 * This example shows how to use the Serializable Fields API with 
 * Serialization, demonstrating that the class can define fields 
 * other than those already in the class to be serializable. This 
 * differs from just rewriting the writeObject method to customize 
 * the data format (see the Custom Data Format example) because, 
 * in this example, versioning support still holds.
 *
 * Using the Serializable Fields API, this example specifically 
 * changes the internal representation of a rectangle from 
 * x1,y1,x2,y2 implementation (see Original Class) to Point(x1,y1), 
 * Point(x2,y2) (see Evolved Class) while the external representation 
 * still remains x1, y1, x2, y2. This ensures bidirectional compatibility 
 * between the original and evolved representations. 
 *
 * The original rectangle class (in this file) consists of four integers
 * (x1, y1, x2, y2). Instead of four integers, the evolved rectangle class 
 * (in EvolvedClass.java) has two Points as fields. In order for this evolved
 * class to fulfill its contract with the original class, the evolved class 
 * saves its fields as four integers (x1, y1, x2, y2) instead of two points. 
 * By doing this, the evolved class ensures bidirectional compatibility 
 * with the original class.
 *
 * How to Run:
 *           
 * Compile Original Class with JDK1.2: 
 *         javac OriginalClass.java
 * Run Original Class with serialization flag: 
 *         java OriginalClass -s
 * Compile Evolved Class:
 *         javac EvolvedClass.java
 * Run Evolved Class with deserialization flag:
 *         java EvolvedClass -d
 *
 * This tests compatibility in one direction. Do the same in other
 * direction to see  bidirectional compatibility.
 *
 *  Compiled and Tested with JDK1.2
 */

public class OriginalClass {

    /**  
     *  There are two options: either a user can serialize an object or
     *  deserialize it. (using the -s or -d flag). These options allow
     *  for the demonstration of bidirection readability and writeability
     *  between the original and the evolved class. In other words,
     *  one can serialize an object here and deserialize it with the evolved
     *  class or vice versa.
     */
    public static void main(String args[]) {

        ARectangle orgClass = new ARectangle(0, 0, 2, 2);
        ARectangle newClass = null;

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
                so.writeObject(orgClass);
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
                newClass = (ARectangle) si.readObject();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(1);
            }
            System.out.println("Now printing deserialized object: ");
            System.out.println();
	    System.out.println(newClass);
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
 * The original Rectangle Class. Simply consists of 4 integers
 * representing the two diagonal points of a rectangle. There is
 * no writeObject or readObject written, so that the external and the
 * the internal representations are the same (4 integers)
 */
class ARectangle implements java.io.Serializable {

    /**
     * X-coordinate of point 1 of two diagonal points of rectange.
     * @serial 
     */
    int x1;

    /**
     * Y-coordinate of point 1 of two diagonal points of rectange.
     * @serial 
     */
    int y1;

    /**
     * X-coordinate of point 2 of two diagonal points of rectange.
     * @serial 
     */
    int x2;

    /**
     * Y-coordinate of point 2 of two diagonal points of rectange.
     * @serial 
     */
    int y2;

    ARectangle(int xone, int yone, int xtwo, int ytwo) {
 	x1 = xone;
	y1 = yone;
	x2 = xtwo;
	y2 = ytwo;
    }

    public String toString() {
	return("x1: " + x1 + "\ny1: " + y1 + "\nx2: " + x2 + "\ny2: " + y2);
    }
}
 
