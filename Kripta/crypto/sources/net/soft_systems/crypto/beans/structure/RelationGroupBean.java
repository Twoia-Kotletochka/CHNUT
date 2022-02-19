package net.soft_systems.crypto.beans.structure;
import net.soft_systems.integrator.*;
public class RelationGroupBean extends TopicBean {
	public RelationGroupBean() { super("relations", "Связи"); }
	public void removeBean(String id) {
		Bean b = BeanUtil.getBeanById(getChildBeans(), id);
		removeBean(b);
	}
	public void removeBean(Bean b) {
		if (b != null) {
			RelationBean relation = (RelationBean)b;
			relation.setProtection(null);
			relation.setThreat(null);
			super.removeBean(b);
		}
	}
}

