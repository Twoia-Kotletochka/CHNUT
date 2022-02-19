/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.process;
import java.awt.event.*;
import java.awt.geom.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.crypto.frames.model.MessagesFrame;
import net.soft_systems.crypto.frames.process.NodeFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class NodeBean extends CryptoRenderableBean implements DynamicBean {
	public static final int IN  = 1;
	public static final int OUT = 2;
	private int nodeType        = IN;
	public ProcessBean getParentProcess() { return (ProcessBean)getParent().getParent(); };
	public NodeBean() { }
	public String getBaseName() { return "N"; }
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new NodeFrame(this); }
		return editFrame;
	}
	public void showMessages() {
		Run.integrator.addFrameToDesktop(new MessagesFrame(Run.infoSystem.getMessageTopic(), this));
	}
	public void initMenu(JPopupMenu menu) {
		JMenuItem menuItem = new JMenuItem(Run.integrator.messages.getMessage("properties"));
		menuItem.setMnemonic(KeyEvent.VK_ENTER);
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { Run.integrator.addFrameToDesktop(getEditFrame()); }
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
	public void closeEditFrame() {
		if (editFrame != null) {
			try { editFrame.setClosed(true); }
			catch (PropertyVetoException ex) { Debug.warning("PropertyVetoException :" + ex.getMessage()); }
			editFrame = null;
		}
	}
	public String getName()
		{ return getParentProcess().getId() + "." + getId() + " / " + getNodeTypeString(); }
	public void setName(String name) { }
	public int getNodeType() { return nodeType; }
	public void setNodeType(int type) {
		if (type != nodeType) {
			this.nodeType = type;
			Run.infoSystem.removeChannelWith(this);
		}
	}
	public String getTypeName() { return "Узел"; }
	public String getNodeTypeString() {
		switch (nodeType) {
			case IN:
				return "in";
			default:
				return "out";
		}
	}
	/**
	 * Determines x coordinate of point where links are connected
	 * @return Horizontal coordinate relative to X coordinate of sign, which is considered as center of sign
	 */
	public double getCenterX() {
		if (nodeType == IN) { return 3; }
		else { return 15; }
	}
	/**
	 * Determines y coordinate of point where links are connected
	 * @return Vertical coordinate relative to Y coordinate of sign, which is considered as center of sign
	 */
	public double getCenterY() { return 3; }
	public Sign getSign() {
		Sign sign = new Sign();
		if (nodeType == IN) {
			sign.addShape(new Line2D.Double(6, 3, 18, 3));
			sign.addShape(new Ellipse2D.Double(0, 0, 6, 6));
		}
		else {
			sign.addShape(new Line2D.Double(0, 3, 12, 3));
			sign.addShape(new Ellipse2D.Double(12, 0, 6, 6));
		}
		return sign;
	}
	public void moveTo(double x, double y) { }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "type", "" + nodeType);
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		try { nodeType = Integer.valueOf(config.getAttribute(beanElement, "type")).intValue(); }
		catch (NumberFormatException ex) {
			Debug.warning("Incorrect node type " + config.getAttribute(beanElement, "type") +
				" at node " + getId());
		}
	}
	public double getX() { return getParentProcess().getNodeX(this); }
	public double getY() { return getParentProcess().getNodeY(this); }
}

