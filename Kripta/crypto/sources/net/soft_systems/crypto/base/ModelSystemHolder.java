/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.base;
import java.util.Vector;
import net.soft_systems.crypto.beans.model.MessageGroupBean;
import net.soft_systems.integrator.*;
public class ModelSystemHolder extends ProcessSystemHolder {
	private Vector messages;
	public void init(Bean rootBean) {
		super.init(rootBean);
		if (rootBean != null) {
			try {
				MessageGroupBean msgGroup = (MessageGroupBean)BeanUtil.getBeanById(rootBean, "messages");
				messages = msgGroup.getMessages();
			}
			catch (NullPointerException ex) { Debug.critical("Ошибка в иерархии элементов"); }
		}
		else { Debug.critical("Ошибка в иерархии элементов"); }
	}
	static public ModelSystemHolder getNewModelSystemHolder(Bean rootBean) {
		ModelSystemHolder sh = new ModelSystemHolder();
		sh.init(rootBean);
		return sh;
	}
	public Vector getMessages() { return messages; }
	public Bean getMessageTopic() { return BeanUtil.getBeanById(rootBean, "messages"); }
}

