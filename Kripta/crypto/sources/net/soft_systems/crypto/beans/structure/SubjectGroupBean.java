package net.soft_systems.crypto.beans.structure;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.*;
/**
 * ----------------------------------------------------------------------------------------------------------
 * @author andrew
 * @version ---------------------------------------------------------------------------------------------------------
 */
public class SubjectGroupBean extends TopicBean implements ParentDynamicBean {
	public SubjectGroupBean() { super("subjects", "Субъекты S"); }
	public String getChildName() { return "Субъект"; }
	public Class getChildClass() { return SubjectBean.class; }
	public void removeBean(String id) {
		SubjectBean b = (SubjectBean)BeanUtil.getBeanById(getChildBeans(), id);
		if (b != null) {
			Run.infoSystem.delRelations(b);
			super.removeBean(id);
		}
	}
}

