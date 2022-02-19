/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.message;
import java.util.*;
import net.soft_systems.crypto.beans.policy.*;
import net.soft_systems.crypto.ui.Formats;
import net.soft_systems.integrator.Bean;
public class Message {
	private int no;
	public int getNo() { return no; }
	static private int NO = 0;
	protected int getNextNo() {
		NO++;
		return NO;
	}
	private Hashtable params = new Hashtable();
	private int messageType;
	public final static int MODELLING_SERVER_STARTED     = 1;
	public final static int MODELLING_SERVER_SHUTDOWN    = 2;
	public final static int COMMAND_EXECUTED_FROM_SERVER = 3;
	public final static int COMMAND_DENYED_FROM_SERVER   = 4;
	public final static int RIGHT_ENTERED                = 5;
	public final static int RIGHT_REMOVED                = 6;
	private Date messageTime;
	public String getContent() {
		switch (messageType) {
			case MODELLING_SERVER_STARTED:
				return "Сервер моделирования запущен";
			case MODELLING_SERVER_SHUTDOWN:
				return "Сервер моделирования остановлен";
			case COMMAND_EXECUTED_FROM_SERVER: {
					CommandBean command = (CommandBean)getParam("command");
					return "Выполнена команда " + command.getId();
				}
			case COMMAND_DENYED_FROM_SERVER: {
					CommandBean command = (CommandBean)getParam("command");
					return "Команда запрещена " + command.getId();
				}
			case RIGHT_ENTERED: {
					RightBean right = (RightBean)getParam("right");
					return "Делегировано право " + right + " субъекту " + getParam("subject") +
						" при доступе к объекту " + getParam("object");
				}
			case RIGHT_REMOVED: {
					RightBean right = (RightBean)getParam("right");
					return "Разделигировано право " + right + " субъекта " + getParam("subject") +
						" при доступе к объекту " + getParam("object");
				}
			default:
				return null;
		}
	}
	public Message(int messageType) {
		no = getNextNo();
		this.messageType = messageType;
		messageTime = new Date();
	}
	public Date getMessageTime() { return messageTime; }
	public String getMessageTimeStr() { return Formats.format(messageTime); }
	public void setParam(String name, Object value) { params.put(name, value); }
	public Object getParam(String name) { return params.get(name); }
	public int getMessageType() { return messageType; }
	public void setMessageType(int messageType) { this.messageType = messageType; }
	static public Message createCommandMessage(CommandBean command) {
		Message message = new Message(COMMAND_EXECUTED_FROM_SERVER);
		message.setParam("data", command.getCommandString());
		message.setParam("command", command);
		return message;
	}
	static public Message createDenyCommandMessage(CommandBean command) {
		Message message = new Message(COMMAND_DENYED_FROM_SERVER);
		message.setParam("data", command.getCommandString());
		message.setParam("command", command);
		return message;
	}
	static public Message createAddRightMessage(RightBean right, Bean subject, Bean object) {
		Message beanMessage = new Message(RIGHT_ENTERED);
		beanMessage.setParam("right", right);
		beanMessage.setParam("object", object);
		beanMessage.setParam("subject", subject);
		return beanMessage;
	}
	static public Message createRemoveRightMessage(RightBean right, Bean subject, Bean object) {
		Message beanMessage = new Message(RIGHT_REMOVED);
		beanMessage.setParam("right", right);
		beanMessage.setParam("object", object);
		beanMessage.setParam("subject", subject);
		return beanMessage;
	}
}

