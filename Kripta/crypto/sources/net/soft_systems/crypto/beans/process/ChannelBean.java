/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.process;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.structure.RelationSign;
import net.soft_systems.crypto.frames.model.MessagesFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.*;
public class ChannelBean extends LeafBean implements RenderableBean, PopupMenuBean {
	static private Vector processes;
	static protected Vector getProcesses(BeanConfig config) {
		if (processes == null) {
			processes = BeanUtil.getBeanById(config.getRootBean(), "processes").getChildBeans();
		}
		return processes;
	}
	static protected NodeBean getNode(String processId, String nodeId, BeanConfig config) {
		ProcessBean processBean = (ProcessBean)BeanUtil.getBeanById(getProcesses(config), processId);
		if (processBean != null) { return (NodeBean)BeanUtil.getBeanById(processBean.getNodes(), nodeId); }
		return null;
	}
	public Vector getDstNodes() { return dstNodes; }
	public ChannelBean(NodeBean src) { this.srcNode = src; }
	public ChannelBean() { }
	public boolean existsDstNode(NodeBean dst) { return dstNodes.contains(dst); }
	public boolean addDstNode(NodeBean dst) {
		if (!existsDstNode(dst)) {
			dstNodes.add(dst);
			return true;
		}
		else { return false; }
	}
	private NodeBean srcNode;
	/**
	 * @associates NodeBean
	 */
	private Vector dstNodes = new Vector();
	public NodeBean getSrcNode() { return srcNode; }
	protected Vector getBeans() {
		Vector beans = new Vector();
		beans.add(srcNode);
		beans.addAll(dstNodes);
		return beans;
	}
	public Sign getSign() {
		RelationSign sign = new RelationSign();
		sign.setColor(Color.gray);
		Enumeration en = dstNodes.elements();
		double x, y;
		Line2D.Double line;
		AffineTransform transform;
		float W = 12;
		float H = 5;
		while (en.hasMoreElements()) {
			Bean bean = (Bean)en.nextElement();
			if (RenderableBean.class.isAssignableFrom(bean.getClass())) {
				RenderableBean rBean = (RenderableBean)bean;
				x = rBean.getX() + rBean.getCenterX() - getX();
				y = rBean.getY() + rBean.getCenterY() - getY();
				line = new Line2D.Double(0, 0, x, y);
				sign.addShape(line);
				double angle = sign.getAngle(line);
				GeneralPath path = new GeneralPath();
				path.moveTo((float)line.getX2() - W, (float)line.getY2() - H);
				path.lineTo((float)line.getX2(), (float)line.getY2());
				path.lineTo((float)line.getX2() - W, (float)line.getY2() + H);
				path.lineTo((float)line.getX2() - W, (float)line.getY2() - H);
				transform = AffineTransform.getRotateInstance(angle + Math.PI * 2, line.getX2(), line.getY2());
				sign.addShape(transform.createTransformedShape(path));
			}
		}
		return sign;
	}
	public double getX() { return getSrcNode().getX() + getSrcNode().getCenterX(); }
	public double getY() { return getSrcNode().getY() + getSrcNode().getCenterY(); }
	public double getCenterX() { return 0; }
	public double getCenterY() { return 0; }
	public void moveTo(double X, double Y) { }
	public String getId() {
		String id = getSrcNode().getName() + " -> [";
		Enumeration en = dstNodes.elements();
		NodeBean n;
		while (en.hasMoreElements()) {
			n = (NodeBean)en.nextElement();
			id += n.getName();
			if (en.hasMoreElements()) { id += ","; }
			else { id += "]"; }
		}
		return id;
	}
	public void setId(String id) { }
	public void setName(String name) { }
	public String getName() { return getId(); }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		Element srcElement = config.addElement(beanElement, "src-node");
		config.setAttribute(srcElement, "process", srcNode.getParentProcess().getId());
		config.setAttribute(srcElement, "node", srcNode.getId());
		Enumeration en = dstNodes.elements();
		NodeBean dstNode;
		while (en.hasMoreElements()) {
			dstNode = (NodeBean)en.nextElement();
			Element dstElement = config.addElement(beanElement, "dst-node");
			config.setAttribute(dstElement, "process", dstNode.getParentProcess().getId());
			config.setAttribute(dstElement, "node", dstNode.getId());
		}
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		Element dstElement;
		String processId, nodeId;
		for (Node obj = beanElement.getFirstChild(); obj != null; obj = obj.getNextSibling()) {
			if ((obj.getNodeType() == Node.ELEMENT_NODE) && obj.getNodeName() == "src-node") {
				Element srcElement = (Element)obj;
				processId = config.getAttribute(srcElement, "process");
				nodeId = config.getAttribute(srcElement, "node");
				srcNode = getNode(processId, nodeId, config);
			}
			else if ((obj.getNodeType() == Node.ELEMENT_NODE) && obj.getNodeName() == "dst-node") {
				dstElement = (Element)obj;
				processId = config.getAttribute(dstElement, "process");
				nodeId = config.getAttribute(dstElement, "node");
				addDstNode(getNode(processId, nodeId, config));
			}
		}
	}
	public boolean containsNode(NodeBean node) { return (srcNode == node || dstNodes.contains(node)); }
	public boolean containsProcess(ProcessBean process) {
		if (srcNode.getParentProcess() == process) { return true; }
		else {
			Enumeration en = dstNodes.elements();
			NodeBean n;
			while (en.hasMoreElements()) {
				n = (NodeBean)en.nextElement();
				if (n.getParentProcess() == process) { return true; }
			}
		}
		return false;
	}
	public void showMessages() {
		Run.integrator.addFrameToDesktop(new MessagesFrame(Run.infoSystem.getMessageTopic(), this));
	}
	public void initMenu(JPopupMenu menu) {
		JMenuItem menuItem = new JMenuItem("Сообщения");
		menuItem.setMnemonic(KeyEvent.VK_M);
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { showMessages(); }
			});
		menu.add(menuItem);
	}
}

