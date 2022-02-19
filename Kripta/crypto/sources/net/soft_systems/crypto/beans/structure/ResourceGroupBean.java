/*
 * ResourceGroupBean.java
 *
 * Created on неділя, 25, серпня 2002, 16:09
 */

package net.soft_systems.crypto.beans.structure;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.*;
/**
 * ----------------------------------------------------------------------------------------------------------
 * @author andrew
 * @version ---------------------------------------------------------------------------------------------------------
 */
public class ResourceGroupBean extends TopicBean implements ParentDynamicBean {
	public ResourceGroupBean() { super("resources", "Защищаемая область O"); }
	public String getChildName() { return "Ресурс"; }
	public Class getChildClass() { return ResourceBean.class; }
	public void removeBean(String id) {
		ResourceBean b = (ResourceBean)BeanUtil.getBeanById(getChildBeans(), id);
		if (b != null) {
			Run.infoSystem.delRelations(b);
			super.removeBean(id);
		}
	}
}

