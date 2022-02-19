/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.policy;
import java.util.Vector;
import net.soft_systems.integrator.*;
import net.soft_systems.model.server.ModelServerImpl;
import org.w3c.dom.Element;
public class DestroyObjectOperation extends OperationBean {
	private Bean object;
	public DestroyObjectOperation(Bean object) { this.object = object; }
	public DestroyObjectOperation() { }
	private Bean getObject() { return object; }
	private void setObject(Bean object) { this.object = object; }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "object", object.getId());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		Vector availSubjects  = BeanUtil.getBeanById(config.getRootBean(), "subjects").getChildBeans();
		Vector availResources = BeanUtil.getBeanById(config.getRootBean(), "resources").getChildBeans();
		Vector availObjects   = new Vector();
		availObjects.addAll(availResources);
		availObjects.addAll(availSubjects);
		String objectId = config.getAttribute(beanElement, "object");
		object = BeanUtil.getBeanById(availObjects, objectId);
	}
	public String getOperation() { return "destroy object " + object.getId(); }
	public boolean isValidSubject(Vector subjects) { return true; }
	public boolean isValidObject(Vector subjects, Vector resources) {
		return subjects.contains(object) || resources.contains(object);
	}
	public boolean isValidRight(Vector availRights) { return true; }
	public void exec() { ModelServerImpl.SERVER.destroyProcess(getObject()); }
}

