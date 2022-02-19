/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
*/

package net.soft_systems.crypto.beans.policy;
import java.util.Vector;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class ConditionBean extends LeafBean {
	private RightBean right;
	private Bean subject;
	private Bean object;
	String id;
	String name;
	public ConditionBean() { }
	public ConditionBean(RightBean right, Bean subject, Bean object) {
		super();
		this.right = right;
		this.subject = subject;
		this.object = object;
	}
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "right", right.getId());
		config.setAttribute(beanElement, "subject", subject.getId());
		config.setAttribute(beanElement, "object", object.getId());
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
	public RightBean getRight() { return right; }
	private void setRight(RightBean right) { this.right = right; }
	public Bean getSubject() { return subject; }
	private void setSubject(Bean subject) { this.subject = subject; }
	public Bean getObject() { return object; }
	private void setObject(Bean object) { this.object = object; }
	public String getId() { return right.getId() + " in M[" + subject.getId() + "," + object.getId() + "]"; }
	public String getName() { return getId(); }
	public void setId(String id) { }
	public void setName(String name) { }
	public boolean isValidSubject(Vector subjects) { return subjects.contains(subject); }
	public boolean isValidObject(Vector subjects, Vector resources) {
		return subjects.contains(object) || resources.contains(object);
	}
	public boolean isValidRight(Vector availRights) { return availRights.contains(right); }
}

