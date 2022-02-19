package net.soft_systems.crypto.frames;
import javax.swing.JToolBar;
import net.soft_systems.integrator.Bean;
public class StructureFrame extends CanvasFrame {
	protected JToolBar toolBar;
	public StructureFrame(Bean bean) { super(bean); }
	public JToolBar getToolBar() {
		if (toolBar == null) { toolBar = new StructureToolBar(this); }
		return toolBar;
	}
	public CryptoCanvas getCanvas() { return new StructureCanvas(); }
}

