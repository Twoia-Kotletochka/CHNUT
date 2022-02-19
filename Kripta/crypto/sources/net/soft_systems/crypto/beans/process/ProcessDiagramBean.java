package net.soft_systems.crypto.beans.process;
import java.beans.PropertyVetoException;
import net.soft_systems.crypto.frames.ProcessDiagramFrame;
import net.soft_systems.integrator.*;
public class ProcessDiagramBean extends TopicBean implements EditableBean {
	public ProcessDiagramBean() { super("process-diagram", "Диаграмма взаимодействия процессов"); }
	private EditFrame editFrame;
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new ProcessDiagramFrame(this); }
		return editFrame;
	}
	public void closeEditFrame() {
		if (editFrame != null) {
			try { editFrame.setClosed(true); }
			catch (PropertyVetoException ex) { Debug.warning("PropertyVetoException :" + ex.getMessage()); }
			editFrame = null;
		}
	}
}

