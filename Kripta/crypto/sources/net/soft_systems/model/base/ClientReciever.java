/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.base;
import java.rmi.*;
public interface ClientReciever extends Remote {
	public void onMessage(String message) throws RemoteException;
	public void onFinish() throws RemoteException;
}

