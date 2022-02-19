/*
 * CryptoBean.java
 *
 * Created on νεδ³λÿ, 25, ρεπονÿ 2002, 19:32
 */

package net.soft_systems.crypto.base;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.frames.CryptoEditFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
abstract public class CryptoBean implements EditableBean, RelationEnabledBean, BaseNameEnabled {
	/**
	 * Creates new CryptoBean
	 */
	public CryptoBean() { }
	public CryptoBean(String id) { this.id = id; }
	public void setName(String id) { setId(id); }
	public String getName() { return getId(); }
	public Vector getChildBeans() { return null; }
	public void removeBean(String id) { }
	public void removeBean(Bean bean) { }
	public String toString() { return getName(); }
	public void addBean(Bean bean) { }
	public void initMenu(JPopupMenu menu) {
		JMenuItem menuItem = new JMenuItem(Run.integrator.messages.getMessage("properties"));
		menuItem.setMnemonic(KeyEvent.VK_ENTER);
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { Run.integrator.addFrameToDesktop(getEditFrame()); }
			});
		menu.add(menuItem);
	}
	public void closeEditFrame() {
		if (editFrame != null) {
			try { editFrame.setClosed(true); }
			catch (PropertyVetoException ex) { Debug.warning("PropertyVetoException :" + ex.getMessage()); }
			editFrame = null;
		}
	}
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new CryptoEditFrame(this); }
		return editFrame;
	}
	protected EditFrame editFrame;
	private String id;
	/**
	 * Saves bean configuration to xml storage
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to set element data
	 */
	public void store(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultStore(this, beanElement, config); }
	/**
	 * Loads bean configuration from xml element
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to get data from element
	 */
	public void load(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultLoad(this, beanElement, config); }
	public Vector getAccessedObjects() {
		Debug.debug("getAccessedObjects is not implemented");
		return null;
	}
	public void setAccessedObjects(Vector objects) { Debug.debug("setAccessedObjects is not implemented"); }
	public Vector getAvailObjects() {
		Debug.debug("getAvailObjects is not implemented");
		return null;
	}
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getAvailObjectsName() { return "not implemented"; }
	public String getAccessedObjectsName() { return "not implemented"; }
	public String getRelationInfo() { return "not implemented"; }
	/**
	 * @return String representation of element type
	 */
	abstract public String getTypeName();
	private Bean parent;
	/**
	 * @return parent bean or null if bean is <code>rootBean</code>
	 */
	public Bean getParent() { return parent; }
	/**
	 * Sets parent bean. Must be called in realization of addBean of parent bean class
	 * @param parent parent Bean
	 */
	public void setParent(Bean parent) { this.parent = parent; }
}

