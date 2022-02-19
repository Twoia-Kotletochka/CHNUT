/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.server;
import java.rmi.RemoteException;
import java.util.Vector;
import net.soft_systems.crypto.beans.process.*;
public class ModelServerImpl_Test {
	private Vector processes;
	private Vector channels;
	private Vector logEvents = new Vector();
	private Vector dataPools;
	public ModelServerImpl_Test() { }
	public Vector getProcesses() { return processes; }
	public Vector getChannels() { return channels; }
	public Vector getProcessNames() { return null; }
	public byte[] getProcessClass() { return null; }
	public void loadProcesses() { }
	public String registerProcess(String processId) throws RemoteException { return null; }
	public Vector getLogEvents() { return logEvents; }
	public Vector getDataPools() { return dataPools; }
	public Object recvData(String processId, String nodeId) { return null; }
	public void sendData(String processId, String nodeId, Object data) { }
	public boolean isCorrectWithPolicy(ProcessBean process, NodeBean node) { return true; }
}

