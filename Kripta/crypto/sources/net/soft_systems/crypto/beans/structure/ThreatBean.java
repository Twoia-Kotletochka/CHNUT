//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\beans\\ThreatBean.java

package net.soft_systems.crypto.beans.structure;
import java.awt.geom.CubicCurve2D;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.crypto.frames.structure.ThreatEditFrame;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class ThreatBean extends CryptoRenderableBean implements DynamicBean {
	public ThreatBean() {
		// super(Run.infoSystem.newBoundary(""));
		super();
	}
	public ThreatBean(String id) {
		// super(Run.infoSystem.newBoundary(id));
		super(id);
	}
	public Sign getSign() {
		Sign sign = new Sign();
		sign.setOpaqueColor();
		sign.addShape(new CubicCurve2D.Double(20, 0, 0, 20, 20, 40, 0, 60));
		sign.addShape(new CubicCurve2D.Double(30, 0, 10, 20, 30, 40, 10, 60));
		return sign;
	}
	public double getProbability() { return probability; }
	public void setProbability(double probability) {
		if (probability >= 0 && probability <= 1 && this.probability != probability) {
			this.probability = probability;
			doChangeParams();
		}
	}
	protected void doChangeParams() { Run.infoSystem.evalBoundaryRisks(this); }
	public double getCenterX() { return 10; }
	public double getCenterY() { return 30; }
	public String getBaseName() { return "T"; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------
	 */
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new ThreatEditFrame(this); }
		return editFrame;
	}
	public Vector getRelations() { return Run.infoSystem.getThreatRelations(this); }
	public Vector getAccessedObjects() { return getRelations(); }
	public void setAccessedObjects(Vector accessedObjects) {
		Enumeration en = accessedObjects.elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			relation.setThreat(this);
		}
		en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (!accessedObjects.contains(relation)) { relation.setThreat(null); }
		}
	}
	public Vector getAvailObjects() {
		Vector availObjects = new Vector();
		Vector relations    = getRelations();
		Enumeration en      = Run.infoSystem.getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getThreat() == null) // !relations.contains(relation)
			{ availObjects.add(relation); }
		}
		return availObjects;
	}
	public String getAvailObjectsName() { return "Связи без угроз"; }
	public String getAccessedObjectsName() { return "Связь под угрозой"; }
	public String getRelationInfo() {
		return "<p align=justify>В связи под <strong>угрозой</strong> указывается случай доступа <strong>субъекта</strong> к <strong>объекту</strong>, при котором возникает <strong>угроза</strong></p>";
	}
	private double probability;
	public String getTypeName() { return "Угроза"; }
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		config.setAttribute(beanElement, "probability", "" + getProbability());
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		probability = config.getDoubleAttribute(beanElement, "probability", 0);
	}
}

