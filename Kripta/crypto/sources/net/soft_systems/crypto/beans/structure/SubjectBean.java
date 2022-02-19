//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\beans\\ResourceBean.java

package net.soft_systems.crypto.beans.structure;
import java.awt.geom.*;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.crypto.frames.structure.SubjectEditFrame;
import net.soft_systems.integrator.*;
public class SubjectBean extends CryptoRenderableBean implements DynamicBean {
	public SubjectBean() {
		// super(Run.infoSystem.newSubject(""));
		super();
	}
	public SubjectBean(String id) {
		// super(Run.infoSystem.newSubject(id));
		super(id);
	}
	public String getBaseName() { return "S"; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------
	 */
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new SubjectEditFrame(this); }
		return editFrame;
	}
	public Sign getSign() {
		Sign sign = new Sign();
		sign.addShape(new Ellipse2D.Double(0, 0, 25, 25));
		sign.addShape(new Line2D.Double(12.5, 25, 12.5, 50));
		sign.addShape(new Line2D.Double(0, 35, 25, 35));
		sign.addShape(new Line2D.Double(12.5, 50, 0, 60));
		sign.addShape(new Line2D.Double(12.5, 50, 25, 60));
		return sign;
	}
	public double getCenterX() { return 12.5; }
	public double getCenterY() { return 30; }
	public Vector getRelations() { return Run.infoSystem.getSubjectRelations(this); }
	public Vector getAccessedObjects() {
		Vector accessedObjects = new Vector();
		Vector relations       = getRelations();
		Enumeration en         = relations.elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			accessedObjects.add(relation.getResource());
		}
		return accessedObjects;
	}
	public void setAccessedObjects(Vector objects) {
		Enumeration en = objects.elements();
		while (en.hasMoreElements()) {
			ResourceBean resource = (ResourceBean)en.nextElement();
			Run.infoSystem.addRelation(this, resource);
		}
		en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (!objects.contains(relation.getResource())) { Run.infoSystem.delRelation(relation); }
		}
	}
	public Vector getAvailObjects() {
		Vector availObjects = new Vector();
		Vector relations    = getRelations();
		Enumeration en      = Run.infoSystem.getResources().elements();
		while (en.hasMoreElements()) {
			ResourceBean resource = (ResourceBean)en.nextElement();
			if (!Run.infoSystem.containsResource(relations, resource)) { availObjects.add(resource); }
		}
		return availObjects;
	}
	public String getAvailObjectsName() { return "Доступные ресурсы"; }
	public String getAccessedObjectsName() { return "Используемые ресурсы"; }
	public String getRelationInfo() {
		return "<p align=justify>В используемых ресурсах указываются все <strong>ресурсы</strong>, к которым осуществляет доступ <strong>субъект</strong></p>";
	}
	public String getTypeName() { return "Субъект"; }
}

