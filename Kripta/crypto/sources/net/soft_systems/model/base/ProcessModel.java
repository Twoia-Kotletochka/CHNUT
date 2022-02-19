/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.base;
import java.rmi.*;
import java.util.Vector;
public interface ProcessModel extends Remote {
	public Vector getMethodNames() throws RemoteException;
	public boolean invokeMethod(String methodName) throws RemoteException;
}

