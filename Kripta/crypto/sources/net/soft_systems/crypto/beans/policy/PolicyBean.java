package net.soft_systems.crypto.beans.policy;
import net.soft_systems.integrator.*;
abstract public class PolicyBean extends TopicBean implements EditableBean, Cloneable {
	public void setId(String id) { }
	public void setName(String id) { }
	public Object clone() throws CloneNotSupportedException { return super.clone(); }
	/**
	 * Initialises new policy. Must call this method if policy is created,
	 * not loaded from storage. It adds rights group and so on
	 */
	abstract public void initNewPolicy();
}

