/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.base;
import java.lang.reflect.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import net.soft_systems.crypto.beans.process.*;
import net.soft_systems.integrator.*;
import net.soft_systems.model.message.BeanMessage;
import net.soft_systems.model.server.ModelServerImpl;
public class ProcessModelImpl extends UnicastRemoteObject implements ProcessModel {
	private ProcessBean processBean;
	private boolean created = false;
	public ProcessBean getProcessBean() { return processBean; }
	public Vector getMethodNames() throws RemoteException {
		Vector methods = getProcessBean().getMethods();
		Enumeration enMethods = methods.elements();
		MethodBean method;
		Vector result = new Vector();
		while (enMethods.hasMoreElements()) {
			method = (MethodBean)enMethods.nextElement();
			if (!method.getId().equals("onCreate") && !method.getId().equals("onDestroy") &&
				!method.getId().equals("onRecieve")) {
					result.add(method.getId());
			}
		}
		return result;
	}
	public boolean invokeMethod(String methodName) throws RemoteException {
		try {
			MethodBean methodBean = (MethodBean)BeanUtil.getBeanById(getProcessBean().getMethods(), methodName);
			if (methodBean != null) {
				Method method = this.getClass().getMethod(methodName, null);
				if (method != null) {
					ModelServerImpl.SERVER.logMessage(BeanMessage.createInvokeMethodMessage(methodBean));
					method.invoke(this, null);
					return true;
				}
				else { return false; }
			}
			else { return false; }
		}
		catch (NoSuchMethodException ex) {
			Debug.error("No such method " + ex.getMessage());
			return false;
		}
		catch (IllegalAccessException ex) {
			Debug.error("Illegal access exception  " + ex.getMessage());
			return false;
		}
		catch (InvocationTargetException ex) {
			Debug.error("InvocationTargetException " + ex.getMessage());
			return false;
		}
	}
	public void logMessage(String message) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message, null));
	}
	public void logDataMessage(String message, Object data) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message, data));
	}
	public void logDataMessage(String message, int data) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message,
			new Integer(data)));
	}
	public void logDataMessage(String message, long data) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message,
			new Long(data)));
	}
	public void logDataMessage(String message, float data) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message,
			new Float(data)));
	}
	public void logDataMessage(String message, double data) {
		ModelServerImpl.SERVER.logMessage(BeanMessage.createInfoMessage(getProcessBean(), message,
			new Double(data)));
	}
	public ProcessModelImpl(ProcessBean processBean) throws RemoteException { this.processBean = processBean; }
	public void send(String node, Object data) {
		// Debug.notice("Sending data " + data + " to node " + node);
		ModelServerImpl.SERVER.sendData(getProcessBean(), node, data);
	}
	public void send(String node, Object data, String right) {
		// Debug.notice("Sending data " + data + " to node " + node);
		ModelServerImpl.SERVER.sendData(getProcessBean(), node, data, right);
	}
	public String recvRight(String node) { return ModelServerImpl.SERVER.recvRight(getProcessBean(), node); }
	public Object recv(String node) {
		Object data = ModelServerImpl.SERVER.recvData(getProcessBean(), node);
		// Debug.notice("Recieved from node " + node + " data " + data);
		return data;
	}
	public boolean hasMoreData(String node)
		{ return ModelServerImpl.SERVER.isEmptyNode(getProcessBean(), node); }
	public String getProcessId() { return processBean.getId(); }
	public boolean isAutoCreate() { return processBean.isAutoCreate(); }
	public void onCreate() { }
	public void onDestroy() { }
	public void onRecieve(String nodeId) { }
	public boolean isCreated() { return created; }
	public void create() {
		if (!isCreated()) {
			created = true;
			onCreate();
		}
	}
	public void execCommand(String command) { ModelServerImpl.SERVER.execCommand(command, getProcessBean()); }
}

