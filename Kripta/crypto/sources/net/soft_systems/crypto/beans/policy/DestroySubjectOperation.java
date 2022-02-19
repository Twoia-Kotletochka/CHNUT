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
public class DestroySubjectOperation extends OperationBean {
	private Bean subject;
	public DestroySubjectOperation(Bean subject) { this.subject = subject; }
	public DestroySubjectOperation() { }
	private Bean getSubject() { return subject; }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "subject", subject.getId());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		Vector availSubjects = BeanUtil.getBeanById(config.getRootBean(), "subjects").getChildBeans();
		String subjectId = config.getAttribute(beanElement, "subject");
		subject = BeanUtil.getBeanById(availSubjects, subjectId);
	}
	public String getOperation() { return "destroy subject " + subject.getId(); }
	public boolean isValidSubject(Vector subjects) { return subjects.contains(subject); }
	public boolean isValidObject(Vector subjects, Vector resources) { return true; }
	public boolean isValidRight(Vector availRights) { return true; }
	public void exec() { ModelServerImpl.SERVER.destroyProcess(getSubject()); }
}

