package net.soft_systems.model.client;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Vector;
import net.soft_systems.model.base.*;
import net.soft_systems.model.server.ModelServer;
import net.soft_systems.routines.Config;
public class ModelClient {
	/**
	 * Url to registry of remote objects sample: rmi://host:port/
	 */
	private String registryURL;
	private String serverName = "CryptoModellingServer";
	private ProcessModel processModel;
	private ModelServer server;
	public void list(String rmiURL) {
		try {
			String[] names = Naming.list(rmiURL);
			for (int i = 0; i < names.length; i++) { System.out.println("Registered objects :" + names[i]); }
		}
		catch (Exception e) { Debug.error("Exception: " + e.getMessage()); }
	}
	public boolean invokeMethod(String methodName) throws RemoteException, NotBoundException,
		MalformedURLException {
			if (getProcessModel() != null) { return getProcessModel().invokeMethod(methodName); }
			else { return false; }
	}
	static public void grantAllPermissions() {
		String policyFilename = Config.getDefaultDir() + File.separator + "rmi.policy";
		if (new File(policyFilename).exists()) { System.setProperty("java.security.policy", policyFilename); }
		else { Debug.error("Не удалось обнаружить файл " + policyFilename + " с правами rmi"); }
	}
	static public ModelClient start(String registryUrl) throws NotBoundException,
		MalformedURLException, RemoteException {
			grantAllPermissions();
			if (System.getSecurityManager() == null) { System.setSecurityManager(new RMISecurityManager()); }
			ModelClient client = new ModelClient();
			client.setRegistryURL(registryUrl);
			Debug.debug("Поиск сервера моделирования :" + client.getFullServerName());
			client.lookupServer();
			return client;
	}
	public static void main(String[] args) {
		try {
			ModelClient client = ModelClient.start("rmi://localhost:1234/");
			if (client != null) {
/*				client.registerProcess("P1");
				Vector methodNames = client.getProcessModel().getMethodNames();
				Debug.notice("" + methodNames.size());
				Enumeration enMethods = methodNames.elements();
				String method;
				Debug.debug("" + client.getProcessModel().getClass());
				Method methods[] = client.getProcessModel().getClass().getMethods();
				for (int i = 0; i < methods.length; i++)
				{
					Method meth = (Method)methods[i];
					Debug.notice("Method " + meth);
				}
				while (enMethods.hasMoreElements())
				{
					method = (String)enMethods.nextElement();
					Debug.notice("Invoking " + method);
					if (client.invokeMethod(method))
					{
						Debug.notice("Method " + method + " was successfully invoked");
					}
					else
					{
						Debug.notice("Error. Method " + method + " was not invoked");
					}
				}
				*/
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			Debug.error("Ошибка: " + ex.getMessage());
		}
	}
	/**
	 * @return url of registry service
	 */
	public String getRegistryURL() { return registryURL; }
	/**
	 * Sets url to registry service
	 */
	public void setRegistryURL(String registryURL) { this.registryURL = registryURL; }
	/**
	 * @return name of object
	 */
	public String getServerName() { return serverName; }
	/**
	 * Sets name of object sample: ModelServer
	 */
	public void setServerName(String serverName) { this.serverName = serverName; }
	/**
	 * @return Name of remote object ModelServer in registry
	 */
	public String getFullServerName() { return getRegistryURL() + getServerName(); }
	public void registerProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		NotBoundException, MalformedURLException {
			String processName = server.registerProcess(processId, clientReciever);
			if (processName != null) {
				Debug.notice("Процесс " + processId + " зарегистрирован с именем " + processName);
				processModel = (ProcessModel)Naming.lookup(processName);
				Debug.notice("Процесс " + processId + " обнаружен по имени " + processName);
			}
	}
	public void unregisterProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		NotBoundException, MalformedURLException {
			server.unregisterProcess(processId, clientReciever);
			Debug.notice("Процесс " + processId + " отключен");
	}
	public void lookupServer() throws NotBoundException, MalformedURLException, RemoteException {
		server = (ModelServer)Naming.lookup(getFullServerName());
		Debug.notice("Сервер моделирования обнаружен по адресу " + getFullServerName());
	}
	public ProcessModel getProcessModel() { return processModel; }
	public Vector getProcessIds() throws RemoteException { return server.getProcessIds(); }
	public Vector getMethodNames() {
		if (processModel != null) {
			try { return processModel.getMethodNames(); }
			catch (Exception ex) {
				Debug.error("Ошибка при получении методов процесса " + ex.getMessage());
				return new Vector();
			}
		}
		else { return new Vector(); }
	}
}

