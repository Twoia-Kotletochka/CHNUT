//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\beans\\ResourceBean.java

package net.soft_systems.crypto.beans.structure;
import java.awt.geom.Ellipse2D;
import net.soft_systems.crypto.base.CryptoRenderableBean;
import net.soft_systems.integrator.*;
public class ResourceBean extends CryptoRenderableBean implements DynamicBean {
	public ResourceBean() {
		super();
		//super(Run.infoSystem.newResource(""));
	}
	public ResourceBean(String id) {
		//super(Run.infoSystem.newResource(id));
		super(id);
	}
	public String getBaseName() { return "O"; }
	public Sign getSign() {
		Sign sign = new Sign();
		sign.addShape(new Ellipse2D.Double(0, 0, 50, 50));
		return sign;
	}
	public double getCenterX() { return 25; }
	public double getCenterY() { return 25; }
	public String getTypeName() { return "Ресурс"; }
}

