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
 */

import java.io.*;

// The superclass DOES NOT implement serializable
class ReadingMaterial  {
    
    /**
     * We do not make these private because we need the subclass Book to be
     * able to save the state of this superclass.
     * Alternatively, we could make these private and create set and get
     *  functions that would allow access to the Book subclass. 
     * If we failed to do that with private fields, then fields would not
     * be saveable!
     */
    protected String author;
    protected String subject;
    protected int yearwritten;
    
    // other relevant data and methods 
    // .
    // .
    // .
    
    /*
     * a mandatory public no-arg constructor... will be used to reconstruct
     * this non-serializable class.
     */
    public ReadingMaterial() {}

    ReadingMaterial(String auth, String sub, int year) {
	author = auth;
	subject = sub;
	yearwritten = year;
    }
}

 
