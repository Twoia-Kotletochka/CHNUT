/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.process;
import net.soft_systems.crypto.base.CryptoBean;
import net.soft_systems.crypto.frames.process.MethodFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class MethodBean extends CryptoBean implements DynamicBean {
	public String getLatinId() { return getId(); }
	private String code = "";
	public ProcessBean getParentProcess() { return (ProcessBean)getParent().getParent(); };
	public MethodBean() { }
	public MethodBean(String id) {
		super();
		setId(id);
	}
	public String getBaseName() { return "OnEvent_"; }
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new MethodFrame(this); }
		return editFrame;
	}
	public String getName() { return getId(); }
	public void setName(String name) { }
	public String getTypeName() { return "Метод"; }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setElementValue(beanElement, getCode());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		code = config.getElementValue(beanElement);
	}
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
}

