package net.soft_systems.model.server;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Vector;
import net.soft_systems.model.base.ClientReciever;
public interface ModelServer extends Remote {
	public String registerProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		MalformedURLException;
	public void unregisterProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		MalformedURLException;
	public Vector getProcessIds() throws RemoteException;
}

