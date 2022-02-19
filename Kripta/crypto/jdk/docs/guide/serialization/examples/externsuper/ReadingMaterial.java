/*
 * @(#)ReadingMaterial.java	1.1 98/10/03        
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
 * The Externalizable Superclass:
 *  When the Externalizable Subclass Book implements its writeExternal and
 *  readExternal Methods, it needs to save the state of its superclass using 
 * the superclass's writeExternal and readExternal Methods
 */
public class ReadingMaterial implements Externalizable {

    /* 
     * In order for us to be able to serialize this data, these must be
     * either serializable/externalizable objects or primitive data types. 
     */
    private  String author;
    private  String subject;
    private  int yearwritten;

    // other relevant data and methods 
    // .
    // .
    // .
  
    /* 
     * Must have a public no-arg constructor when implementing Externalizable
     */
    public ReadingMaterial() {}
    
    /** 
     * Initialize the fields
     *
     */
    public ReadingMaterial(String auth, String sub, int year) {
	author = auth;
	subject = sub;
	yearwritten = year;
    }
    
    /**
     * A public field access method, since the data fields are private and 
     * will need to be accessed by the subclass to print them or use them 
     * otherwise.
     */
    public String getAuthor() { 
	return author; }
    /**
     * A field access method, since the data fields are private and will need
     * to be accessed by the subclass to print them or use them otherwise.
     */
    public String getSubject() { 
	return subject; }
    /**
     * A field access method, since the data fields are private and will need
     * to be accessed by the subclass to print them or use them otherwise.
     */
    public int getYearwritten() { 
	return yearwritten; }

    /**
     * Mandatory writeExternal method. 
     * @serialData Write author and subject field as objects and then write
     *             yearwritten field as an integer.
     */
    public void writeExternal(ObjectOutput out) throws IOException {
    
	out.writeObject(author);
	out.writeObject(subject);
	out.writeInt(yearwritten);
  }

    /**
     * Mandatory readExternal method. Will read in the data that we wrote out
     * in the writeExternal method. MUST BE IN THE SAME ORDER and type as we
     * wrote it out. By the time, readExternal is called, an object of this 
     * class has already been created using the public no-arg constructor,
     * so this method is used to restore the data to all of the fields of the 
     * newly created object.
     */
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
   
    author = (String)in.readObject();
    subject = (String)in.readObject();
    yearwritten = in.readInt();
  }
}

