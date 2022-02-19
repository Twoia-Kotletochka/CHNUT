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

import java.io.*;
import java.rmi.*;
import java.rmi.activation.*;
import java.util.Vector;

public class MyPersistentClass extends Activatable
    implements examples.activation.YetAnotherRemoteInterface {

    private Vector transactions;
    private File holder;

    // The constructor for activation and export; this constructor is
    // called by the method ActivationInstantiator.newInstance during
    // activation, to construct the object.
    //
    public MyPersistentClass(ActivationID id, MarshalledObject data) 
	throws RemoteException, ClassNotFoundException, java.io.IOException {

	super(id, 0);

        // Extract the File object from the MarshalledObject that was 
        // passed to the constructor 
	//
	holder = (File)data.get();

	if (holder.exists()) {
	    // Use the MarshalledObject to restore my state
	    //
	    this.restoreState();
	} else {
	    transactions = new Vector(1,1);
	    transactions.addElement("Initializing transaction vector");
	}
    }

    // Define the method declared in AnotherRemoteInterface
    //
    public Vector calltheServer(Vector v) throws RemoteException {

	int limit = v.size();
	for (int i = 0; i < limit; i++) {
	    transactions.addElement(v.elementAt(i));
	}

	// Save this object's data out to file
	//
	this.saveState();
	return transactions;
    }

    public Vector getTransactions() {
	return transactions;
    }


    // If the MarshalledObject that was passed to the constructor was
    // a file, then use it to recover the vector of transaction data
    //
    private void restoreState() throws IOException, ClassNotFoundException {
	File f = holder;
	FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        transactions = (Vector)ois.readObject();
        ois.close();
    }

    private void saveState() {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

	try {
	    File f  = holder;
		try {
	            fos = new FileOutputStream(f);
		} catch (IOException e1) {
		    e1.printStackTrace(); 
	            throw new RuntimeException("Error creating FileOutputStream");
		}
		try {
	            oos = new ObjectOutputStream(fos);
		} catch (IOException e2) {
	            throw new RuntimeException("Error creating ObjectOutputStream");
		}
		try {
	            oos.writeObject(getTransactions());
		} catch (IOException e3) {
	            throw new RuntimeException("Error writing Vector");
		}
		try {
	            oos.close();
		} catch (IOException e3) {
	            throw new RuntimeException("Error closing stream");
		}

	} catch (SecurityException e4) {
	    throw new RuntimeException("Security Problem");
	} catch (Exception e) {
	    throw new RuntimeException("Error saving vector of data");
	}
    }
}
