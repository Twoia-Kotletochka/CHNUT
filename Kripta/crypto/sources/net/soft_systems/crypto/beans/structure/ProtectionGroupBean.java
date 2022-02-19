/*
 * ProtectionGroupBean.java
 *
 * Created on неділя, 25, серпня 2002, 16:06
 */

package net.soft_systems.crypto.beans.structure;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.*;
public class ProtectionGroupBean extends TopicBean implements ParentDynamicBean {
	public ProtectionGroupBean() { super("protections", "Системные защиты M"); }
	public String getChildName() { return "Защита"; }
	public Class getChildClass() { return ProtectionBean.class; }
	public void removeBean(String id) {
		Bean b = BeanUtil.getBeanById(getChildBeans(), id);
		removeBean(b);
	}
	public void removeBean(Bean b) {
		if (b != null) {
			Run.infoSystem.delProtectionFromRelations((ProtectionBean)b);
			super.removeBean(b);
		}
	}
}

