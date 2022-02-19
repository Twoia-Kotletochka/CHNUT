/*
 * BoundaryBean.java
 *
 * Created on νεδ³λÿ, 25, ρεπονÿ 2002, 20:41
 */

package net.soft_systems.crypto.beans.structure;
import java.awt.geom.GeneralPath;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.crypto.frames.structure.BoundaryEditFrame;
import net.soft_systems.integrator.*;
/**
 * ----------------------------------------------------------------------------------------------------------
 * @author andrew
 * @version ---------------------------------------------------------------------------------------------------------
 */
public class BoundaryBean extends CryptoRenderableBean {
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * Creates new BoundaryBean -----------------------------------------------------------------------------------------------------
	 */
	public BoundaryBean() {
		// super(Run.infoSystem.newBoundary(""));
		super();
	}
	public BoundaryBean(String id) {
		// super(Run.infoSystem.newBoundary(id));
		super(id);
	}
	public Sign getSign() {
		Sign sign = new Sign();
		GeneralPath path = new GeneralPath();
		path.moveTo(0, 20);
		path.lineTo(30, 0);
		path.lineTo(30, 40);
		path.lineTo(0, 60);
		path.lineTo(0, 20);
		sign.addShape(path);
		return sign;
	}
	public double getCenterX() { return 15; }
	public double getCenterY() { return 30; }
	public String getBaseName() { return "B"; }
	public String getTypeName() { return "Αΰπϊεπ"; }
	public double getRisk() { return risk; }
	public void evalRisk(double threatProbability, double vulnerabilityLoss, double protectionStrength) {
		risk = threatProbability * vulnerabilityLoss * (1 - protectionStrength);
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------
	 */
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new BoundaryEditFrame(this); }
		return editFrame;
	}
	private double risk;
}

