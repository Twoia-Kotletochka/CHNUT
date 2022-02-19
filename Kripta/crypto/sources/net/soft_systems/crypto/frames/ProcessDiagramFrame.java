package net.soft_systems.crypto.frames;
import javax.swing.JToolBar;
import net.soft_systems.integrator.Bean;
public class ProcessDiagramFrame extends CanvasFrame {
	protected JToolBar toolBar;
	public ProcessDiagramFrame(Bean bean) { super(bean); }
	public JToolBar getToolBar() {
		if (toolBar == null) { toolBar = new StructureToolBar(this); }
		return toolBar;
	}
	public CryptoCanvas getCanvas() { return new ProcessesCanvas(); }
}

