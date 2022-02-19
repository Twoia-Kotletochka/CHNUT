/*
 * @(#)Substitute.java	1.2 98/10/01        
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
 * This example shows how to use the readResolve method.
 * This method resolves the object
 * read from the stream before it is returned to the caller.
 * The writeReplacea method allows an object to nominate its 
 * own replacement in the stream before the object is written.  
 *
 * This example creates a symbol class for which only a single instance of
 * each symbol binding exists. The Symbol class defines the
 * readResolve method. A symbol is created from outside using the 
 * symbollookup method, which finds and returns a symbol if one already 
 * exists and creates one, if one does not. This assures uniqueness within
 * one VM. Then, when readResolve is called when the symbol is being read, 
 * a preexisting equivalent Symbol object is substituted from the hashtable 
 * to maintain the unique identity constraint, if such a symbol exists. 
 * Otherwise, the new symbol is added to the hashtable and returned. This 
 * assures uniqueness when we are dealing with more than one VM.
 *
 *
 * How to Run:
 *             Compile this file: javac Substitute.java
 *             Run this file:     java Substitute
 *
 * This will print out a confirmation that the two symbols that were
 * serialized separately but had the same name are indeed the same symbol.
 *
 *
 * Compiled and Tested with JDK1.2
 */

public class Substitute {
   
    /** 
     * Basically, serialize and deserialize two symbols with the same
     *  name and show that they are actually the same symbol.
     */
    public static void main(String args[]) {
	
	// create a few symbols to be serialized
	Symbol s1 = Symbol.symbolLookup("blue");
	Symbol s2 = Symbol.symbolLookup("pink");
	Symbol s3 = Symbol.symbolLookup("blue");

	// use these to deserialize the symbols
	Symbol obj1 = null, obj2 = null, obj3 = null;


	// serialize the symbols
	try {
 	    FileOutputStream fo = new FileOutputStream("symbol.tmp");
	    ObjectOutputStream so = new ObjectOutputStream(fo);
	    so.writeObject(s1);
	    so.writeObject(s2);
	    so.writeObject(s3);
	    so.flush();
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}
	
	// deserialize the symbols
	try {
	    FileInputStream fi = new FileInputStream("symbol.tmp");
	    ObjectInputStream si = new ObjectInputStream(fi);
	    obj1 = (Symbol) si.readObject();
	    obj2 = (Symbol) si.readObject();
	    obj3 = (Symbol) si.readObject();
	} catch (Exception e) {
	    System.out.println(e);
	    System.exit(1);
	}

	// show the uniqueness 
	if (obj1 == obj3) {
	    System.out.println("Symbol1 and Symbol3 are the same!");
	}
    }
}


/** 
 * The class implementing the readResolve method.
 */
class Symbol implements Serializable {

    /**
     * @serial
     */
    String symbolname;

    /* 
     * Hashtable is **static** because we need to use the same one for
     * all symbol objects. 
     */
    static Hashtable ht = new Hashtable();
    
    /** 
     * This method serves as the constructor. It looks in the hashtable and
     * if that symbol exists, will return that symbol... otherwise, will 
     * create a symbol with that name and will add it to the hashtable. This
     * will assure that the symbols are always unique.
     */
    static Symbol symbolLookup(String symname) {
	if (ht.containsKey(symname)) {
	    return (Symbol)(ht.get(symname));
	}
	else {
	    Symbol newSym = new Symbol(symname);
	    ht.put(symname, newSym);
	    return(newSym);
	}
    }

    /** 
     * Private constructor because we want "outsiders" to use 
     *  symbolLookup instead to force uniqueness.
     */
    private Symbol (String name) {
	symbolname = name;
    }

    /** 
     * Deals with the issue of uniqueness when we are dealing with more
     * than one VM by adding the read symbol to the hash table, if it
     * isn't already there.
     */
    public Object readResolve() throws ObjectStreamException {
	if (!ht.containsKey(symbolname))
	    ht.put(symbolname, this);
	return (Symbol) (ht.get(symbolname));
    }
}









