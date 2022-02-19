/*
 * @(#)Book.java	1.1 98/10/03        
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
 * The subclass that implements Externalizable.. responsible for
 * saving the state of its non-Externalizable super class
 */
class Book extends ReadingMaterial implements Externalizable {

    int numpages;
    String name;
    boolean ishardcover;

    // other relevant information and methods
    // .
    // .
    // .
   
    /*
     * mandatory public no-arg constructor
     * if the superclass did not have a no-arg constructor, we would have to
     * give the regular constructor default values.
     */
    public Book() { super(); }
    
    Book(int pages, String n, boolean hardcover, String author,
	 String subject, int yearwritten) {

	super(author, subject, yearwritten);
	numpages = pages;
	name = n;
	ishardcover = hardcover;
    }

    /** 
     * Mandatory writeExernal method. 
     *
     * @serialData Expicitly saves the supertype's fields and then saves this
     *             class fields. Writes superclass fields in following order:
     *             author as an object, subject as an object, yearwritten
     *             as an integer. Writes class fields in following order:
     *             numpages as an int, name as an Object and ishardcover 
     *             as a boolean.
     */
    public void writeExternal(ObjectOutput out) throws IOException  {

	/* 
	 * Since the superclass does not implement the Serializable interface
	 * we explicitly do the saving... Since these fields are not private
	 * we can access them directly. If they were private, the superclass
	 * would have to implement get methods that would allow the
	 * subclass this necessary access for proper saving.
	 */
	out.writeObject(author);
	out.writeObject(subject);
	out.writeInt(yearwritten);
	   
	// now we take care of this subclass's fields
	out.writeInt(numpages);
	out.writeObject(name);
	out.writeBoolean(ishardcover);
    }

    /** 
     * Mandatory readExternal method. Explicitly restores the supertype's 
     * fields and then restores
     *  this class's fields
     */
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

	/* 
	 * Since the superclass does not implement the Serializable interface
	 * we explicitly do the restoring... Since these fields are not private
	 * we can access them directly. If they were private, the superclass
	 * would have to implement set methods that would allow the
	 * subclass this necessary access for proper restoring.
	 */
	author = (String) in.readObject();
	subject = (String) in.readObject();
	yearwritten = in.readInt();

	// now we take care of the subclass's fields
	numpages = in.readInt();
	name = (String) in.readObject();
	ishardcover = in.readBoolean();
    }

    /** 
     * Prints out the fields. used for testing!
     */
    public String toString() {
        return("Name: " + name + "\n" + "Author: " + author + "\n" + "Pages: "
+ numpages + "\n" + "Subject: " + subject + "\n" + "Year: " + yearwritten + "\n");
    }
}

  


