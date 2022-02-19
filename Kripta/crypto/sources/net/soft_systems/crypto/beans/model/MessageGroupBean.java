package net.soft_systems.crypto.beans.model;
import java.beans.PropertyVetoException;
import java.util.*;
import net.soft_systems.crypto.frames.model.MessagesFrame;
import net.soft_systems.integrator.*;
import net.soft_systems.model.message.*;
public class MessageGroupBean extends LeafBean implements EditableBean {
	Vector messages = new Vector();
	public Vector getMessageBeans() {
		Vector beans = new Vector();
		Enumeration en = getMessages().elements();
		Message msg;
		while (en.hasMoreElements()) {
			msg = (Message)en.nextElement();
			if (msg instanceof BeanMessage) {
				BeanMessage beanMessage = (BeanMessage)msg;
				if (!beans.contains(beanMessage.getBean())) { beans.add(beanMessage.getBean()); }
			}
		}
		return beans;
	}
	public Vector getMessages() { return messages; }
	public MessageGroupBean() { super(); }
	private EditFrame editFrame;
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new MessagesFrame(this); }
		return editFrame;
	}
	public void closeEditFrame() {
		if (editFrame != null) {
			try { editFrame.setClosed(true); }
			catch (PropertyVetoException ex) { Debug.warning("PropertyVetoException :" + ex.getMessage()); }
			editFrame = null;
		}
	}
	public String getId() { return "messages"; }
	public void setId(String id) { }
	public void setName(String name) { }
	public String getName() { return "Сообщения"; }
}

