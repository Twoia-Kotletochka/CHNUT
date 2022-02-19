/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.message;
import net.soft_systems.crypto.beans.policy.CommandBean;
import net.soft_systems.crypto.beans.process.*;
import net.soft_systems.integrator.Bean;
public class BeanMessage extends Message {
	private Bean bean;
	public final static int METHOD_INVOKED           = 103;
	public final static int PROCESS_DESTROYED        = 102;
	public final static int NODE_DATA_RECIEVED       = 105;
	public final static int CHANNEL_DATA_TRANSMITTED = 106;
	public final static int NODE_DATA_SENT           = 104;
	public final static int PROCESS_CREATED          = 101;
	public final static int PROCESS_INFO             = 107;
	public final static int PROCESS_REGISTERED       = 108;
	public final static int PROCESS_UNREGISTERED     = 109;
	public final static int COMMAND_EXECUTED         = 110;
	public final static int COMMAND_DENYED           = 111;
	public BeanMessage(int messageType, Bean bean) {
		super(messageType);
		this.bean = bean;
	}
	public Bean getBean() { return bean; }
	public void setBean(Bean bean) { this.bean = bean; }
	public String getContent() {
		String content = super.getContent();
		if (content == null) {
			switch (getMessageType()) {
				case PROCESS_CREATED:
					return "Процесс создан";
				case PROCESS_DESTROYED:
					return "Процесс завершен";
				case METHOD_INVOKED: {
						ProcessBean process = (ProcessBean)getBean();
						MethodBean method = (MethodBean)getParam("method");
						return "Запуск метода " + method;
					}
				case NODE_DATA_SENT: {
						ProcessBean process = (ProcessBean)getBean();
						NodeBean node       = (NodeBean)getParam("node");
						String msg          = "Отправка данных с узла " + node;
						if (getParam("right") != null) msg += ". Запрашиваемое право доступа " + getParam("right");
						return msg;
					}
				case NODE_DATA_RECIEVED: {
						ProcessBean process = (ProcessBean)getBean();
						NodeBean node       = (NodeBean)getParam("node");
						String msg          = "Получены данные в узле " + node;
						if (getParam("right") != null) msg += ". Запрашиваемое право доступа " + getParam("right");
						return msg;
					}
				case CHANNEL_DATA_TRANSMITTED: {
						ChannelBean channel = (ChannelBean)getBean();
						return "Передача данных";
					}
				case PROCESS_INFO: { return "" + getParam("info"); }
				case PROCESS_REGISTERED: { return "Регистрация клиента"; }
				case PROCESS_UNREGISTERED: { return "Отключение клиента"; }
				case COMMAND_EXECUTED: {
						CommandBean command = (CommandBean)getParam("command");
						return "Выполнена команда " + command.getId();
					}
				case COMMAND_DENYED: {
						CommandBean command = (CommandBean)getParam("command");
						return "Команда запрещена " + command.getId();
					}
				default:
					return null;
			}
		}
		return content;
	}
	static public BeanMessage createInvokeMethodMessage(MethodBean method) {
		BeanMessage beanMessage = new BeanMessage(METHOD_INVOKED, method.getParentProcess());
		beanMessage.setParam("method", method);
		return beanMessage;
	}
	static public BeanMessage createDataMessage(int messageType, ChannelBean channel, Object data) {
		BeanMessage beanMessage = new BeanMessage(messageType, channel);
		if (data != null) { beanMessage.setParam("data", data); }
		return beanMessage;
	}
	static public BeanMessage createCommandMessage(ProcessBean process, CommandBean command) {
		BeanMessage beanMessage = new BeanMessage(COMMAND_EXECUTED, process);
		beanMessage.setParam("data", command.getCommandString());
		beanMessage.setParam("command", command);
		return beanMessage;
	}
	static public BeanMessage createDenyCommandMessage(ProcessBean process, CommandBean command) {
		BeanMessage beanMessage = new BeanMessage(COMMAND_DENYED, process);
		beanMessage.setParam("data", command.getCommandString());
		beanMessage.setParam("command", command);
		return beanMessage;
	}
	static public BeanMessage createDataMessage(int messageType, ChannelBean channel,
		Object data, String right) {
			BeanMessage beanMessage = new BeanMessage(messageType, channel);
			if (data != null) { beanMessage.setParam("data", data); }
			if (right != null) beanMessage.setParam("right", right);
			return beanMessage;
	}
	static public BeanMessage createDataMessage(int messageType, NodeBean node, Object data) {
		BeanMessage beanMessage = new BeanMessage(messageType, node.getParentProcess());
		beanMessage.setParam("node", node);
		if (data != null) { beanMessage.setParam("data", data); }
		return beanMessage;
	}
	static public BeanMessage createDataMessage(int messageType, NodeBean node, Object data, String right) {
		BeanMessage beanMessage = new BeanMessage(messageType, node.getParentProcess());
		beanMessage.setParam("node", node);
		if (data != null) { beanMessage.setParam("data", data); }
		if (right != null) { beanMessage.setParam("right", right); }
		return beanMessage;
	}
	static public BeanMessage createInfoMessage(Bean bean, String info, Object data) {
		BeanMessage beanMessage = new BeanMessage(PROCESS_INFO, bean);
		beanMessage.setParam("info", info);
		if (data != null) { beanMessage.setParam("data", data); }
		return beanMessage;
	}
	static public BeanMessage createInfoMessage(Bean bean, String info) {
		BeanMessage beanMessage = new BeanMessage(PROCESS_INFO, bean);
		beanMessage.setParam("info", info);
		return beanMessage;
	}
	public Object getData() { return getParam("data"); }
}

