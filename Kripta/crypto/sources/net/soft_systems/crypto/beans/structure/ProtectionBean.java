//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\beans\\ProtectionBean.java

package net.soft_systems.crypto.beans.structure;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.crypto.frames.structure.ProtectionEditFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class ProtectionBean extends CryptoRenderableBean implements DynamicBean {
	public ProtectionBean() {
		//super(Run.infoSystem.newBoundary(""));
		super();
	}
	public ProtectionBean(String id) {
		//super(Run.infoSystem.newBoundary(id));
		super(id);
	}
	public String getBaseName() { return "M"; }
	public Sign getSign() {
		Sign sign = new Sign();
		sign.addShape(new RoundRectangle2D.Double(0, 0, 30, 50, 10, 10));
		return sign;
	}
	public double getCenterX() { return 15; }
	public double getCenterY() { return 25; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------
	 */
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new ProtectionEditFrame(this); }
		return editFrame;
	}
	public Vector getRelations() { return Run.infoSystem.getProtectionRelations(this); }
	public Vector getAccessedObjects() { return getRelations(); }
	public void setAccessedObjects(Vector accessedObjects) {
		Enumeration en = accessedObjects.elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			relation.setProtection(this);
		}
		en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (!accessedObjects.contains(relation)) { relation.setProtection(null); }
		}
	}
	public Vector getAvailObjects() {
		Vector availObjects = new Vector();
		Vector relations    = getRelations();
		Enumeration en      = Run.infoSystem.getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getProtection() == null && relation.getVulnerability() != null) //
			// !relations.contains(relation)
			{ availObjects.add(relation); }
		}
		return availObjects;
	}
	public String getAvailObjectsName() { return "Связи с уязвимостями"; }
	public String getAccessedObjectsName() { return "Связь под защитой"; }
	public String getRelationInfo() {
		return "<p align=justify>В связи под <strong>защитой</strong> указывается случай доступа <strong>субъекта</strong> к <strong>объекту</strong>, при котором возникает <strong>уязвимость</strong>, перекрываемая средством защиты</p>";
	}
	public String getTypeName() { return "Средство защиты"; }
	public double getStrength() { return strength; }
	public void setStrength(double strength) {
		if (strength >= 0 && strength <= 1 && this.strength != strength) {
			this.strength = strength;
			doChangeParams();
		}
	}
	protected void doChangeParams() { Run.infoSystem.evalBoundaryRisks(this); }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "strength", "" + getStrength());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		strength = config.getDoubleAttribute(beanElement, "strength", 0);
	}
	private double strength;
}

