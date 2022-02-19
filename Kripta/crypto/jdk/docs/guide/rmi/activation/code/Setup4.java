/*
 * Copyright (c) 1998, 1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */
package examples.activation; 

import java.io.File;
import java.rmi.*;
import java.rmi.activation.*;
import java.util.Properties;

public class Setup4 {

    // This class registers information about the MyPersistentClass
    // class with rmid and the rmiregistry
    //
    public static void main(String[] args) throws Exception {
	
	System.setSecurityManager(new RMISecurityManager());
	
	// Because of the 1.2 security model, a security policy should 
	// be specified for the ActivationGroup VM. The first argument
	// to the Properties put method, inherited from Hashtable, is 
	// the key and the second is the value 
	// 
	Properties props = new Properties(); 
	props.put("java.security.policy", 
	   "/home/rmi_tutorial/activation/policy"); 
	
	ActivationGroupDesc.CommandEnvironment ace = null; 
	ActivationGroupDesc exampleGroup = new ActivationGroupDesc(props, ace);
	
	// Once the ActivationGroupDesc has been created, register it 
	// with the activation system to obtain its ID
	//
	ActivationGroupID agi = 
	   ActivationGroup.getSystem().registerGroup(exampleGroup);
	
	// Now explicitly create the group
	//
	ActivationGroup.createGroup(agi, exampleGroup, 0);

	// Don't forget the trailing slash at the end of the URL
	// or your classes won't be found
	//	
	String location = "file:/home/rmi_tutorial/activation/";

	// Pass the file that we want to persist to as the Marshalled
	// object
	MarshalledObject data =  new MarshalledObject (new File(
	    "/home/rmi_tutorial/activation/persistentObjectStore.ser"));

	// The second argument to the ActivationDesc constructor will be used 
	// to uniquely identify this class; it's location is relative to the  
	// URL-formatted String, location.
	//
	ActivationDesc desc = new ActivationDesc 
	    (agi, "examples.activation.MyPersistentClass", location, data);
	
	YetAnotherRemoteInterface yari = 
	    (YetAnotherRemoteInterface)Activatable.register(desc);
	System.out.println("Got the stub for MyPersistentClass");

	// Bind the stub to a name in the registry running on 1099
	//
	Naming.rebind("MyPersistentClass", yari);
	System.out.println("Exported MyPersistentClass");

	System.exit(0);
    }

}
