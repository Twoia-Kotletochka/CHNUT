/*
 * ThreatGroupBean.java
 *
 * Created on неділя, 25, серпня 2002, 15:48
 */

package net.soft_systems.crypto.beans.structure;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.*;
public class ThreatGroupBean extends TopicBean implements ParentDynamicBean {
	/**
	 * Creates new ThreatGroupBean
	 */
	public ThreatGroupBean() { super("threats", "Область угроз T"); }
	public String getChildName() { return "Угроза"; }
	public Class getChildClass() { return ThreatBean.class; }
	public void removeBean(String id) {
		ThreatBean b = (ThreatBean)BeanUtil.getBeanById(getChildBeans(), id);
		removeBean(b);
	}
	public void removeBean(Bean b) {
		if (b != null) {
			Run.infoSystem.delThreatFromRelations((ThreatBean)b);
			super.removeBean(b);
		}
	}
}

