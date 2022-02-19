/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.policy;
import java.util.Vector;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class DeleteRightOperation extends OperationBean {
	private Bean object;
	private Bean subject;
	private RightBean right;
	public DeleteRightOperation(RightBean right, Bean subject, Bean object) {
		this.object = object;
		this.subject = subject;
		this.right = right;
	}
	public DeleteRightOperation() { }
	private Bean getObject() { return object; }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "object", object.getId());
		config.setAttribute(beanElement, "subject", subject.getId());
		config.setAttribute(beanElement, "right", right.getId());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		Vector availRights    = BeanUtil.getBeanById(config.getRootBean(), "rights").getChildBeans();
		Vector availSubjects  = BeanUtil.getBeanById(config.getRootBean(), "subjects").getChildBeans();
		Vector availResources = BeanUtil.getBeanById(config.getRootBean(), "resources").getChildBeans();
		Vector availObjects   = new Vector();
		availObjects.addAll(availResources);
		availObjects.addAll(availSubjects);
		String rightId = config.getAttribute(beanElement, "right");
		right = (RightBean)BeanUtil.getBeanById(availRights, rightId);
		String subjectId = config.getAttribute(beanElement, "subject");
		subject = BeanUtil.getBeanById(availSubjects, subjectId);
		String objectId = config.getAttribute(beanElement, "object");
		object = BeanUtil.getBeanById(availObjects, objectId);
	}
	public String getOperation() {
		return "delete " + right.getId() + " from M[" + subject.getId() + "," + object.getId() + "]";
	}
	private Bean getSubject() { return subject; }
	private RightBean getRight() { return right; }
	public boolean isValidSubject(Vector subjects) { return subjects.contains(subject); }
	public boolean isValidObject(Vector subjects, Vector resources) {
		return subjects.contains(object) || resources.contains(object);
	}
	public boolean isValidRight(Vector availRights) { return availRights.contains(right); }
	public void exec() {
		if (Run.infoSystem.isPolicyEnabled(HRUPolicy.class)) {
			HRUPolicy policy = (HRUPolicy)BeanUtil.getBeanById(Run.infoSystem.getPolicies(), "hru");
			if (policy != null) { policy.removeRight(getRight(), getSubject(), getObject()); }
		}
	}
}

