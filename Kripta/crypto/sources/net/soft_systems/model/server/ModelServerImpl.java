package net.soft_systems.model.server;
import java.io.File;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.policy.*;
import net.soft_systems.crypto.beans.process.*;
import net.soft_systems.crypto.frames.model.MessagesFrame;
import net.soft_systems.integrator.*;
import net.soft_systems.model.base.*;
import net.soft_systems.model.message.*;
import net.soft_systems.routines.Config;
public class ModelServerImpl extends UnicastRemoteObject implements ModelServer {
	private class PoolElement {
		Object data;
		String right;
		boolean notified = false;
		ProcessBean srcProcess;
		public ProcessBean getSrcProcess() { return srcProcess; }
		public PoolElement(Object data) { this.data = data; }
		public PoolElement(Object data, String right, ProcessBean process) {
			this.srcProcess = process;
			this.right = right;
			this.data = data;
		}
		public boolean isNotified() { return notified; }
		public void notifyRecieved() { notified = true; }
		public Object getData() { return data; }
		public String getRight() { return right; }
	}
	private Hashtable dataPools = new Hashtable();
	/**
	 * Hashtable containing Vectors of ClientReciever objects with keys which are ids of processes
	 */
	private Hashtable clientRecieverVectors = new Hashtable();
	/**
	 * Adds ClientReciever object to vector with key <code>processId</code>
	 * @param processId id of process which is assosiated with <code>clientReciever</code>
	 * @param clientReciever client reciever of messages from process
	 */
	public void addClientReciever(String processId, ClientReciever clientReciever) {
		Vector recieverVector = (Vector)clientRecieverVectors.get(processId);
		if (recieverVector == null) {
			recieverVector = new Vector();
			clientRecieverVectors.put(processId, recieverVector);
		}
		recieverVector.add(clientReciever);
	}
	/**
	 * Removes ClientReciever object from vector with key <code>processId</code>
	 * @param processId id of process which is assosiated with <code>clientReciever</code>
	 * @param clientReciever client reciever of messages from process
	 */
	public void removeClientReciever(String processId, ClientReciever clientReciever) {
		Vector recieverVector = (Vector)clientRecieverVectors.get(processId);
		if (recieverVector != null) { recieverVector.remove(clientReciever); }
	}
	/**
	 * Sends <code>message</code> to client recievers assosiated with process with id <code>processId</code>
	 * @param procesId id of process
	 * @param message Message sent to client recievers
	 */
	public void doSendMessage(String processId, String message) {
		Vector recieverVector = (Vector)clientRecieverVectors.get(processId);
		if (recieverVector != null) {
			Enumeration enReciever = recieverVector.elements();
			ClientReciever reciever;
			while (enReciever.hasMoreElements()) {
				reciever = (ClientReciever)enReciever.nextElement();
				try { reciever.onMessage(message); }
				catch (RemoteException ex) {
					Debug.error("Ошибка удаленного вызова при отправке сообщения " + ex.getMessage());
				}
			}
		}
	}
	static public ModelServerImpl SERVER = null;
	/**
	 * Url to registry of remote objects sample: rmi://host:port/
	 */
	private String registryURL;
	private String objectName = "CryptoModellingServer";
	private Hashtable processModels = new Hashtable();
	/**
	 * Initializes ModelServer
	 */
	public ModelServerImpl() throws RemoteException { initDataPools(); }
	/**
	 * unregisters client for process with id <code>processId</code>.
	 * @param processId Identifier of process
	 * @return Bound name of remote process object.
	 */
	public void unregisterProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		MalformedURLException {
			ProcessBean processBean = (ProcessBean)BeanUtil.getBeanById(getProcessBeans(), processId);
			if (processBean != null) {
				if (!processBean.isAutoCreate()) {
					try { destroyProcessModel(processBean); }
					catch (NotBoundException ex) { throw new RemoteException(ex.getMessage()); }
				}
				logMessage(new BeanMessage(BeanMessage.PROCESS_UNREGISTERED, processBean));
				removeClientReciever(processId, clientReciever);
			}
	}
	/**
	 * Registers client for process with id <code>processId</code>.
	 * @param processId Identifier of process
	 * @return Bound name of remote process object.
	 */
	public String registerProcess(String processId, ClientReciever clientReciever) throws RemoteException,
		MalformedURLException {
			ProcessBean processBean = (ProcessBean)BeanUtil.getBeanById(getProcessBeans(), processId);
			if (processBean != null) {
				addClientReciever(processId, clientReciever);
				logMessage(new BeanMessage(BeanMessage.PROCESS_REGISTERED, processBean));
				if (!processBean.isAutoCreate()) {
					try { initProcessModel(processBean); }
					catch (Exception e) { throw new RemoteException(e.getMessage()); }
				}
				String name = getProcessBindName(processBean);
				return name;
			}
			else {
				Debug.error("Клиент для процесса " + processId +
					" не удалось зарегистрировать, так как он не найден");
				clientReciever.onMessage("Клиент не зарегистрирован, так как не найден процесс " + processId);
				return null;
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
	public String getObjectName() { return objectName; }
	/**
	 * Sets name of object sample: ModelServer
	 */
	public void setObjectName(String objectName) { this.objectName = objectName; }
	/**
	 * @return Name of remote object ModelServer in registry
	 */
	public String getFullBindName() { return getRegistryURL() + getObjectName(); }
	public void sendData(ProcessBean process, String nodeId, Object data) {
		sendData(process, nodeId, data, null);
/*		NodeBean node = (NodeBean)BeanUtil.getBeanById(process.getNodes(), nodeId);
		if (node != null)
		{
			logMessage(BeanMessage.createDataMessage(BeanMessage.NODE_DATA_SENT, node, data));
			ChannelBean channel = Run.infoSystem.getChannelFrom(node);
			if (channel != null)
			{
				Enumeration en = channel.getDstNodes().elements();
				logMessage(BeanMessage.createDataMessage(BeanMessage.CHANNEL_DATA_TRANSMITTED,
					channel, data));
				NodeBean dstNode;
				ProcessBean dstProcess;
				ProcessModelImpl processModel;
				while (en.hasMoreElements())
				{
					dstNode = (NodeBean)en.nextElement();
					dstProcess = dstNode.getParentProcess();
					processModel = (ProcessModelImpl)getProcessModel(dstProcess.getId());
					pushData(dstNode, data);
					if (processModel != null)
					{
						logMessage(BeanMessage.createDataMessage(BeanMessage.NODE_DATA_RECIEVED,
							dstNode, data));
						processModel.onRecieve(dstNode.getId());
					}
					else
					{
						//process is not created yet
						//onRecieve must be called after creation
					}
				}
			}
		}
		else
		{
			logMessage(BeanMessage.createInfoMessage(process, "Узел " + nodeId + " не найден"));
		}*/
	}
	public boolean checkRight(String right, ProcessBean srcProcess, ProcessBean dstProcess) {
		if (Run.infoSystem.isPolicyEnabled(HRUPolicy.class)) {
			HRUPolicy policy = (HRUPolicy)BeanUtil.getBeanById(Run.infoSystem.getPolicies(), "hru");
			if (policy != null) { return policy.checkRight(right, srcProcess, dstProcess); }
		}
		return true;
	}
	public void sendData(ProcessBean process, String nodeId, Object data, String right) {
		NodeBean node = (NodeBean)BeanUtil.getBeanById(process.getNodes(), nodeId);
		if (node != null) {
			logMessage(BeanMessage.createDataMessage(BeanMessage.NODE_DATA_SENT, node, data, right));
			ChannelBean channel = Run.infoSystem.getChannelFrom(node);
			if (channel != null) {
				Enumeration en = channel.getDstNodes().elements();
				logMessage(BeanMessage.createDataMessage(BeanMessage.CHANNEL_DATA_TRANSMITTED,
					channel, data, right));
				NodeBean dstNode;
				ProcessBean dstProcess;
				ProcessModelImpl processModel;
				while (en.hasMoreElements()) {
					dstNode = (NodeBean)en.nextElement();
					dstProcess = dstNode.getParentProcess();
					processModel = (ProcessModelImpl)getProcessModel(dstProcess.getId());
					if (right == null || checkRight(right, process, dstProcess)) {
						pushData(dstNode, data, right, process);
						if (processModel != null) { processModel.onRecieve(dstNode.getId()); }
						else {
							//process is not created yet
							//onRecieve must be called after creation
						}
					}
					else logMessage(BeanMessage.createInfoMessage(process,
							"Доступ к процессу " + dstProcess + " с правом " + right + " запрещен"));
				}
			}
		}
		else { logMessage(BeanMessage.createInfoMessage(process, "Узел " + nodeId + " не найден")); }
	}
	/**
	 * binds ModelServer
	 */
	public void register() throws RemoteException, MalformedURLException {
		Naming.rebind(getFullBindName(), this);
		logMessage(new Message(Message.MODELLING_SERVER_STARTED));
	}
	static public void grantAllPermissions() {
		String policyFilename = Config.getDefaultDir() + File.separator + "rmi.policy";
		if (new File(policyFilename).exists()) { System.setProperty("java.security.policy", policyFilename); }
		else { Debug.error("Can't locate file " + policyFilename + " with rmi permission"); }
	}
	/**
	 * Creates implementation of model server, registers it in rmi registry service
	 * @param registryUrl Url to registry service <code>rmiregistry</code>
	 */
	static public ModelServerImpl start(String registryUrl) throws ConnectException, RemoteException,
		MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException,
		NoSuchMethodException, InvocationTargetException {
			grantAllPermissions();
			ModelServerImpl modelServer = new ModelServerImpl();
			SERVER = modelServer;
			System.setSecurityManager(new RMISecurityManager());
			modelServer.setRegistryURL(registryUrl);
			modelServer.register();
			modelServer.createProcesses();
			return modelServer;
	}
	/**
	 * @return vector of process beans
	 */
	public Vector getProcessBeans() { return Run.infoSystem.getProcesses(); }
	/**
	 * @return bind name of process
	 * @param process Bean of process
	 */
	public String getProcessBindName(ProcessBean process) { return getRegistryURL() + process.getId(); }
	public String getProcessClassName(ProcessBean process) {
		ProcessGenerator gen = Run.infoSystem.getProcessGenerator();
		String fullClassName = gen.getBaseName() + process.getLatinId();
		String pack          = gen.getPackage();
		return pack + "." + fullClassName;
	}
	public ProcessModelImpl newProcessModel(ProcessBean process) throws ClassNotFoundException,
		InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
			//String className = getProcessClassName(process);
			Class processModelClass = process.reloadClass();
			//Class processModelClass = Class.forName(className);
			Class[] params = new Class[1];
			params[0] = ProcessBean.class;
			Constructor constr = processModelClass.getConstructor(params);
			if (constr != null) {
				Object[] actParams = new Object[1];
				actParams[0] = process;
				ProcessModelImpl processImpl = (ProcessModelImpl)constr.newInstance(actParams);
				logMessage(new BeanMessage(BeanMessage.PROCESS_CREATED, process));
				if (processImpl != null) {
					processImpl.create();
					processPool(processImpl);
					return processImpl;
				}
				else { throw new InstantiationException("Process " + process + " is not instantiated"); }
			}
			else {
				throw new InstantiationException("Nessesary constructor for process " + process + " is not found");
			}
	}
	public void destroyProcessModel(ProcessBean process) throws RemoteException,
		MalformedURLException, NotBoundException {
			ProcessModelImpl processModel = getProcessModel(process.getId());
			if (processModel != null) {
				processModel.onDestroy();
				processModels.remove(process.getId());
				Naming.unbind(getProcessBindName(process));
				logMessage(new BeanMessage(BeanMessage.PROCESS_DESTROYED, process));
			}
	}
	public void initProcessModel(ProcessBean process) throws RemoteException, MalformedURLException,
		ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
		InvocationTargetException {
			ProcessModelImpl processImpl = newProcessModel(process);
			processModels.put(process.getId(), processImpl);
			String name = getProcessBindName(process);
			Naming.rebind(name, processImpl);
	}
	/**
	 * Creates all processes. Binds them. Calls <code>initProcess</code> for each process model
	 * @see initProcesses
	 */
	public void createProcesses() throws RemoteException, MalformedURLException, ClassNotFoundException,
		InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
			Vector processes = getProcessBeans();
			Enumeration enProcesses = processes.elements();
			ProcessBean process;
			while (enProcesses.hasMoreElements()) {
				process = (ProcessBean)enProcesses.nextElement();
				if (process.isAutoCreate()) { initProcessModel(process); }
			}
	}
	public void createProcess(Bean element) {
		ProcessBean process = Run.infoSystem.getProcessByElement(element);
		if (process != null) {
			try { createProcess(process); }
			catch (Exception ex) {
				Debug.error("Ошибка при создании процесса элемента " + element + ": " + ex.getMessage());
			}
		}
		else Debug.error("Не найден процесс элемента " + element);
	}
	public void createProcess(ProcessBean process) throws RemoteException, MalformedURLException,
		ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
		InvocationTargetException {
			// if process is already created - does nothing
			if (!processModels.containsKey(process.getId())) initProcessModel(process);
	}
	public void destroyProcess(Bean element) {
		ProcessBean process = Run.infoSystem.getProcessByElement(element);
		if (process != null) {
			try { destroyProcess(process); }
			catch (Exception ex) {
				Debug.error("Ошибка при уничтожении процесса элемента " + element + ": " + ex.getMessage());
			}
		}
		else Debug.error("Не найден процесс элемента " + element);
	}
	public void destroyProcess(ProcessBean process) throws RemoteException, MalformedURLException {
		try {
			destroyProcessModel(process);
			clearClientRecievers(process);
		}
		catch (NotBoundException ex) { throw new RemoteException(ex.getMessage()); }
	}
	/**
	 * Destroys all processes. Unbinds them.
	 */
	public void destroyProcesses() throws RemoteException, MalformedURLException {
		Vector processes = getProcessBeans();
		Enumeration enProcesses = processes.elements();
		ProcessBean process;
		while (enProcesses.hasMoreElements()) {
			process = (ProcessBean)enProcesses.nextElement();
			destroyProcess(process);
		}
	}
	/**
	 * Checks if pool for nodes of model is not empty and calls onRecieve while there are some data elements
	 * Must be called after creation of every process model
	 */
	protected void processPool(ProcessModelImpl model) {
		ProcessBean process = model.getProcessBean();
		Enumeration en = process.getNodes().elements();
		NodeBean node;
		while (en.hasMoreElements()) {
			node = (NodeBean)en.nextElement();
			Vector pool = getDataPool(node);
			if (pool != null) {
				for (int i = 0; i < pool.size(); i++) {
					PoolElement poolElem = (PoolElement)pool.elementAt(i);
					if (!poolElem.isNotified()) {
						poolElem.notifyRecieved();
						// if (checkRight(poolElem.getRight(),poolElem.getSrcProcess(),process)) {
						model.onRecieve(node.getId());
/*						} else {
							popData(node);
							logMessage(BeanMessage.createInfoMessage(process, "Доступ к процессу "+process+" с правом " + poolElem.getRight() + " запрещен"));
						}
						*/
					}
				}
			}
		}
	}
	/**
	 * Sends message <code>message</code> send by server or by process to client using <code>ClientReciever</code>
	 * and logs it on server
	 */
	public void logMessage(Message message) {
		Run.infoSystem.addMessage(message);
		if (message instanceof BeanMessage) {
			BeanMessage beanMessage = (BeanMessage)message;
			Bean bean = beanMessage.getBean();
			if (bean instanceof ProcessBean) {
				String msg = beanMessage.getContent();
				Object data = beanMessage.getData();
/*
				if (data != null)
				{
					if (data instanceof byte[])
					{
						msg += " " + Binary.toHexString((byte[]) data);
					}
					else
					{
						msg += " " + data;
					}
				}
				*/
				doSendMessage(bean.getId(), msg + " " + MessagesFrame.dataToString(data, 0));
			}
		}
		// Debug.notice("Modelling message: " + message.getContent());
	}
	public Hashtable getProcessModels() { return processModels; }
	public ProcessModelImpl getProcessModel(String processId) {
		return (ProcessModelImpl)processModels.get(processId);
	}
	/**
	 * Initializes vectors of data pools for each node connected by channels
	 */
	protected void initDataPools() {
		Enumeration en = getProcessBeans().elements();
		ProcessBean process;
		Enumeration enNode;
		NodeBean node;
		String key;
		while (en.hasMoreElements()) {
			process = (ProcessBean)en.nextElement();
			enNode = process.getNodes().elements();
			while (enNode.hasMoreElements()) {
				node = (NodeBean)enNode.nextElement();
				if (Run.infoSystem.getChannelWith(node) != null) {
					key = process.getId() + "." + node.getId();
					dataPools.put(key, new Vector());
				}
			}
		}
	}
	public void pushData(NodeBean node, Object data) {
		pushData(node, data);
		/*String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null)
		{
			pool.add(new PoolElement(data));
		}
		else
		{
			Debug.error("Cannot find pool while calling pushData for node " + node);
		}*/
	}
	public void pushData(NodeBean node, Object data, String right, ProcessBean process) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null) { pool.add(new PoolElement(data, right, process)); }
		else { Debug.error("Не удалось найти очередь сообщения для узла " + node); }
	}
	public String getLastRight(NodeBean node) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null) {
			if (pool.size() > 0) {
				PoolElement poolElem = (PoolElement)pool.elementAt(0);
				return poolElem.getRight();
			}
			else {
				logMessage(BeanMessage.createInfoMessage(node.getParentProcess(),
					"Очередь сообщений пуста при вызове getLastRight для узла " + node));
			}
		}
		else { Debug.error("Не удалось найти очередь сообщений при вызове getLastRight для узла " + node); }
		return null;
	}
	public Object popData(NodeBean node) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null) {
			if (pool.size() > 0) {
				PoolElement poolElem = (PoolElement)pool.elementAt(0);
				Object data = poolElem.getData();
				pool.remove(0);
				logMessage(BeanMessage.createDataMessage(BeanMessage.NODE_DATA_RECIEVED, node, data,
					poolElem.getRight()));
				return data;
			}
			else {
				logMessage(BeanMessage.createInfoMessage(node.getParentProcess(),
					"Очередь сообщений пуста при получении данных из узла " + node));
			}
		}
		else { Debug.error("Не удалось найти очередь сообщений при получении данных из узла " + node); }
		return null;
	}
	public boolean isEmptyData(NodeBean node) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null) { return pool.size() > 0; }
		else { Debug.error("Не удалось найти очередь сообщений при вызове isEmptyData из узла " + node); }
		return false;
	}
	protected Vector getDataPool(NodeBean node) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		Vector pool = (Vector)dataPools.get(key);
		if (pool != null) { return pool; }
		else { Debug.error("Не удалось найти очередь сообщений для узла " + node); }
		return null;
	}
	protected void clearPool(NodeBean node) {
		String key = node.getParentProcess().getId() + "." + node.getId();
		dataPools.remove(key);
	}
	protected void clearProcessPools(ProcessBean process) {
		Enumeration en = process.getNodes().elements();
		NodeBean node;
		while (en.hasMoreElements()) {
			node = (NodeBean)en.nextElement();
			clearPool(node);
		}
	}
	protected void clearAllPools() {
		Vector processes = getProcessBeans();
		Enumeration enProcesses = processes.elements();
		ProcessBean process;
		while (enProcesses.hasMoreElements()) {
			process = (ProcessBean)enProcesses.nextElement();
			clearProcessPools(process);
		}
	}
	public Object recvData(ProcessBean process, String nodeId) {
		NodeBean node = (NodeBean)BeanUtil.getBeanById(process.getNodes(), nodeId);
		if (node != null) {
			Object data = popData(node);
			return data;
		}
		else { Debug.notice("Узел " + nodeId + " не найден при получении данных у процесса " + process); }
		return null;
	}
	public boolean isEmptyNode(ProcessBean process, String nodeId) {
		NodeBean node = (NodeBean)BeanUtil.getBeanById(process.getNodes(), nodeId);
		if (node != null) { return isEmptyData(node); }
		else { Debug.notice("Узел " + nodeId + " не найден при проверке данных у процесса " + process); }
		return true;
	}
	public String recvRight(ProcessBean process, String nodeId) {
		NodeBean node = (NodeBean)BeanUtil.getBeanById(process.getNodes(), nodeId);
		if (node != null) { return getLastRight(node); }
		else { Debug.notice("Узел " + nodeId + " не найден при получении права доступа от процесса "
				+ process); }
		return null;
	}
	public Vector getProcessIds() throws RemoteException {
		Vector result           = new Vector();
		Vector processes        = getProcessBeans();
		Enumeration enProcesses = processes.elements();
		ProcessBean process;
		while (enProcesses.hasMoreElements()) {
			process = (ProcessBean)enProcesses.nextElement();
			if (!process.isAutoCreate()) { result.add(process.getId()); }
		}
		return result;
	}
	public void clearClientRecievers(ProcessBean process) throws RemoteException {
		Vector recieverVector = (Vector)clientRecieverVectors.get(process.getId());
		if (recieverVector != null) {
			Enumeration en = recieverVector.elements();
			ClientReciever clientReciever;
			while (en.hasMoreElements()) {
				clientReciever = (ClientReciever)en.nextElement();
				clientReciever.onFinish();
			}
			recieverVector.clear();
			clientReciever = null;
		}
	}
	public void finish() throws RemoteException, MalformedURLException, NotBoundException {
		destroyProcesses();
		clearAllPools();
		Naming.unbind(getFullBindName());
		logMessage(new Message(Message.MODELLING_SERVER_SHUTDOWN));
	}
	public boolean execCommand(String cmd, ProcessBean process) {
		if (Run.infoSystem.isPolicyEnabled(HRUPolicy.class)) {
			HRUPolicy policy = (HRUPolicy)BeanUtil.getBeanById(Run.infoSystem.getPolicies(), "hru");
			if (policy != null) {
				CommandBean command = policy.getCommand(cmd);
				if (command != null) {
					if (policy.execCommand(command)) {
						logMessage(BeanMessage.createCommandMessage(process, command));
						return true;
					}
					else {
						Debug.notice("Команда " + cmd + " не выполнена");
						logMessage(BeanMessage.createDenyCommandMessage(process, command));
					}
				}
				else Debug.notice("Команда " + cmd + " не найдена");
			}
		}
		return false;
	}
	public boolean execCommand(String cmd) {
		if (Run.infoSystem.isPolicyEnabled(HRUPolicy.class)) {
			HRUPolicy policy = (HRUPolicy)BeanUtil.getBeanById(Run.infoSystem.getPolicies(), "hru");
			if (policy != null) {
				CommandBean command = policy.getCommand(cmd);
				if (command != null) {
					if (policy.execCommand(command)) {
						logMessage(Message.createCommandMessage(command));
						return true;
					}
					else {
						Debug.notice("Команда " + cmd + " не выполнена");
						logMessage(Message.createDenyCommandMessage(command));
					}
				}
				else { Debug.notice("Команда " + cmd + " не найдена"); }
			}
		}
		return false;
	}
}

