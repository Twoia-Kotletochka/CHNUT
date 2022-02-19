package net.soft_systems.crypto.beans.structure;
import java.beans.PropertyVetoException;
import net.soft_systems.crypto.frames.StructureFrame;
import net.soft_systems.integrator.*;
public class StructureBean extends TopicBean implements EditableBean {
	public StructureBean() { super("structure-diagram", "Диаграмма статической модели"); }
	private EditFrame editFrame;
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new StructureFrame(this); }
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

