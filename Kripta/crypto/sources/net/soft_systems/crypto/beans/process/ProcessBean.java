/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.process;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.*;
import net.soft_systems.crypto.beans.process.NodeGroupBean;
import net.soft_systems.crypto.beans.structure.SubjectBean;
import net.soft_systems.crypto.frames.model.MessagesFrame;
import net.soft_systems.crypto.frames.process.ProcessFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class ProcessBean extends CryptoRenderableBean implements PopupMenuBean {
	public String getLatinId() { return getId(); }
	static private SystemHolder systemInfo;
	static protected SystemHolder getSystemInfo(BeanConfig config) {
		if (systemInfo == null) { systemInfo = SystemHolder.getNewSystemHolder(config.getRootBean()); }
		return systemInfo;
	}
	private CryptoBean element;
	private Vector nodes = null;
	private Vector methods = null;
	private String vars;
	public String getVars() { return vars; }
	public void setVars(String vars) {
		if (vars == null) vars = "";
		this.vars = vars;
	}
	/**
	 * @return vector of nodes of this process
	 */
	public Vector getNodes() {
		if (nodes == null) {
			Bean nodeGroupBean = BeanUtil.getBeanById(getChildBeans(), "nodes");
			nodes = nodeGroupBean.getChildBeans();
		}
		return nodes;
	}
	/**
	 * @return vector of methods of this process
	 */
	public Vector getMethods() {
		if (methods == null) {
			Bean groupBean = BeanUtil.getBeanById(getChildBeans(), "methods");
			methods = groupBean.getChildBeans();
		}
		return methods;
	}
	public void addBean(Bean bean) {
		children.add(bean);
		bean.setParent(this);
	}
	public void removeBean(String id) {
		for (int i = 0; i < children.size(); i++) {
			Bean b = (Bean)children.elementAt(i);
			if (b.getId().equals(id)) {
				children.remove(i);
				break;
			}
		}
	}
	/**
	 * @associates Bean
	 */
	Vector children = new Vector();
	private boolean autoCreate;
	public ProcessBean(CryptoBean element, String id) {
		super();
		this.element = element;
		setId(id);
		addBean(new NodeGroupBean());
		MethodGroupBean methodGroup = MethodGroupBean.createWithStandartMethods();
		addBean(methodGroup);
		vars = "";
	}
	public ProcessBean() { vars = ""; }
	/**
	 * @return string representing type of element. i. e. <strong>'subject'</strong> or
	 * <strong>'resource'</strong> or <strong>null</strong> if element is null
	 */
	public String getElementTypeName() {
		if (element != null) {
			if (SubjectBean.class.isAssignableFrom(element.getClass())) { return "subject"; }
			else { return "resource"; }
		}
		else { return null; }
	}
	/**
	 * Determines x coordinate of point where links are connected
	 * @return Horizontal coordinate relative to X coordinate of sign, which is considered as center of sign
	 */
	public double getCenterX() { return 15; }
	/**
	 * Determines y coordinate of point where links are connected
	 * @return Vertical coordinate relative to Y coordinate of sign, which is considered as center of sign
	 */
	public double getCenterY() { return 25; }
	/**
	 * @return start of identifier. It is concatenated with number of element which is not used.
	 */
	public String getBaseName() { return "P"; }
	/**
	 * @return String representation of element type
	 */
	public String getTypeName() { return "Процесс"; }
	/**
	 * @return subject or resource bean which corresponds to the process
	 */
	public Bean getElement() { return element; }
	/**
	 * Sets subject or resource bean which corresponds to the process
	 * @param element object of class SubjectBean or ResourceBean
	 */
	public void setElement(CryptoBean element) { this.element = element; }
	/**
	 * @return True if <code>element</code> of the process is contained in any vector <code>subjects</code>
	 * or <code>resources</code>
	 * @see getElement
	 */
	public boolean isContainedIn(Vector subjects, Vector resources) {
		if (element != null) { return subjects.contains(element) || resources.contains(element); }
		else { return false; }
	}
	/**
	 * Saves bean configuration to xml storage
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to set element data
	 */
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, getElementTypeName(), element.getId());
		config.setAttribute(beanElement, "auto-create", "" + this.isAutoCreate());
		Element varsElem = config.addElement(beanElement, "vars");
		config.setElementValue(varsElem, getVars());
	}
	/**
	 * Loads bean configuration from xml element
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to get data from element
	 */
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		String subjectId = config.getAttribute(beanElement, "subject");
		if (subjectId != null) {
			element = (CryptoBean)BeanUtil.getBeanById(getSystemInfo(config).getSubjects(), subjectId);
		}
		else {
			String resourceId = config.getAttribute(beanElement, "resource");
			element = (CryptoBean)BeanUtil.getBeanById(getSystemInfo(config).getResources(), resourceId);
		}
		String autoCreateStr = config.getAttribute(beanElement, "auto-create");
		if (autoCreateStr != null && autoCreateStr.toUpperCase().equals("TRUE")) { autoCreate = true; }
		Element varsElem = config.getChildElementByTag(beanElement, "vars");
		if (varsElem != null) { setVars(config.getElementValue(varsElem)); }
	}
	public void setName(String id) { }
	public Sign getSign() {
		Sign sign = new Sign();
		sign.addShape(new Rectangle2D.Double(0, 0, 30, 40 + (getMaxNodeCount() - 1) * 20));
		return sign;
	}
	public String getName() { return getId() + " / " + element.getTypeName() + " " + element.getId(); }
	public String toString() { return getName(); }
	public Vector getChildBeans() { return children; }
	public double getNodeX(NodeBean node) {
		if (node.getNodeType() == NodeBean.IN) { return getX() - 15 - node.getCenterX(); }
		else { return getX() + 30; }
	}
	public double getNodeY(NodeBean node) {
		int index = getNodeTypeIndex(node);
		return getY() + 20 + index * 20 - node.getCenterY();
	}
	/**
	 * @return Index of node among nodes of the same type as type of <code>node</code> in vector of nodes
	 * @see getNodes
	 */
	public int getNodeTypeIndex(NodeBean node) {
		int type = node.getNodeType();
		int index = 0;
		NodeBean currNode;
		for (int i = 0; i < getNodes().size(); i++) {
			currNode = (NodeBean)getNodes().elementAt(i);
			if (currNode == node) { return index; }
			if (currNode.getNodeType() == type) { index++; }
		}
		return -1;
	}
	/**
	 * @return maximum count of nodes of different types (IN,OUT)
	 */
	public int getMaxNodeCount() {
		int inCount    = 0;
		int outCount   = 0;
		Vector nodes   = getNodes();
		Enumeration en = nodes.elements();
		NodeBean node;
		while (en.hasMoreElements()) {
			node = (NodeBean)en.nextElement();
			if (node.getNodeType() == NodeBean.IN) { inCount++; }
			else if (node.getNodeType() == NodeBean.OUT) { outCount++; }
		}
		return (inCount > outCount) ? inCount : outCount;
	}
	public boolean isAutoCreate() { return autoCreate; }
	public void setAutoCreate(boolean autoCreate) { this.autoCreate = autoCreate; }
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new ProcessFrame(this); }
		return editFrame;
	}
	class ProcessClassLoader extends ClassLoader {
		String classFileName;
		public ProcessClassLoader(String classFileName) { this.classFileName = classFileName; }
		public synchronized Class loadClass(String name, boolean resolve) {
			try {
				System.out.println("CLASSFILENAME " + classFileName);
				System.out.println("CLASSNAME " + name);
				RandomAccessFile file = new RandomAccessFile(classFileName, "r");
				// int len = in.available();
				// byte data[] = new byte[len];
				// in.read(data, 0, len);
				// in.close();
				// RandomAccessFile file = new RandomAccessFile(" test/" + name + ". class", "r");
				byte data[] = new byte[(int)file.length()];
				file.readFully(data);
				Class c = defineClass(name, data, 0, (int)file.length());
				if (resolve) resolveClass(c);
				return c;
			}
			catch (IOException e) {
				Debug.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			return null;
		}
	}
	public Class reloadClass() {
		try {
			File dir = Run.infoSystem.getProcessGenerator().getClassDir();
			System.out.println("Dir " + dir.toURL().toString());
			URL[] urls = null;
			try {
				// Convert the file object to a URL
				URL url = dir.toURL(); // file:/c:/almanac1.4/examples/
				urls = new URL[] { url };
			}
			catch (MalformedURLException e) { }
			try {
				// Create a new class loader with the directory
				ClassLoader cl = new URLClassLoader(urls);
				// Load in the class
				System.out.println("Class " + Run.infoSystem.getProcessGenerator().getClassName(this).toString());
				Class cls = cl.loadClass(Run.infoSystem.getProcessGenerator().getClassName(this));
				// Create a new instance of the new class
				return cls;
			}
			catch (ClassNotFoundException e) { }
			return null;
/*
		try{
			ClassLoader.getSystemClassLoader().loadClass("net.soft_systems.model.base.ProcessModelImpl");
			String classFileName   = Run.infoSystem.getProcessGenerator().getClassFilename(this);
			String className=Run.infoSystem.getProcessGenerator().getClassName(this);
/*			FileInputStream in = new FileInputStream(className);
			int len            = in.available();
			byte data[]        = new byte[len];
			in.read(data, 0, len);
			in.close();
			ClassLoader.getSystemClassLoader().defineClass(null, data, 0, len);
			*/
/*			ClassLoader cl=new ProcessClassLoader(classFileName);
			return cl.loadClass(className);*/
		}
		catch (Exception ex) { Debug.error("Ошибка ввода/вывода " + ex.getMessage()); }
		return null;
		}
		public void gen() {
			try { Run.infoSystem.getProcessGenerator().gen(this); }
			catch (IOException ex) { Debug.error("Ошибка ввода/вывода " + ex.getMessage()); }
		}
		public void showMessages() {
			Run.integrator.addFrameToDesktop(new MessagesFrame(Run.infoSystem.getMessageTopic(), this));
		}
		public void initMenu(JPopupMenu menu) {
			super.initMenu(menu);
			JMenuItem menuItem = new JMenuItem("Генерировать");
			menuItem.setMnemonic(KeyEvent.VK_G);
			menuItem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) { gen(); }
				});
			menu.add(menuItem);
			menuItem = new JMenuItem("Сообщения");
			menuItem.setMnemonic(KeyEvent.VK_M);
			menuItem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) { showMessages(); }
				});
			menu.add(menuItem);
		}
	}

