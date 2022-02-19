/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;
import net.soft_systems.model.base.ClientReciever;
public class ClientRecieverImpl extends UnicastRemoteObject implements ClientReciever {
	JTextArea messagesArea;
	public void onMessage(String message) throws RemoteException {
		messagesArea.append(message + "\n");
		messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
	}
	public ClientRecieverImpl() throws RemoteException { }
	public void setMessagesArea(JTextArea messagesArea) { this.messagesArea = messagesArea; }
	public void onFinish() throws RemoteException {
		messagesArea.append("Server finished" + "\n");
		messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
	}
}

