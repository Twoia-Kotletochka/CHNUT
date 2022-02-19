/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
*/

package net.soft_systems.crypto.beans.policy;
import java.util.Vector;
import net.soft_systems.integrator.LeafBean;
abstract public class OperationBean extends LeafBean {
	String id;
	public OperationBean() { }
	public String getName() { return getId(); }
	public void setName(String name) { }
	abstract public String getOperation();
	public String getId() { return getOperation(); }
	public void setId(String id) { }
	abstract public boolean isValidSubject(Vector subjects);
	abstract public boolean isValidObject(Vector subjects, Vector resources);
	abstract public boolean isValidRight(Vector availRights);
	abstract public void exec();
}

