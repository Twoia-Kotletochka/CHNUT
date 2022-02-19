package net.soft_systems.integrator;
import javax.swing.JToolBar;
import javax.swing.event.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.integrator.BaseInternalFrame;
public class EditFrame extends BaseInternalFrame {
	private Bean bean;
	public Bean getBean() { return bean; }
	public JToolBar getToolBar() { return null; }
	public EditFrame(Bean bean, String title) {
		super(title);
		this.bean = bean;
		addInternalFrameListener(
			new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent e) { }
			});
	}
	public EditFrame(Bean bean) {
		super(bean.getName());
		this.bean = bean;
		addInternalFrameListener(
			new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent e) { }
			});
	}
	protected void closeFrame() {
		EditableBean editableBean = (EditableBean)getBean();
		editableBean.closeEditFrame();
		Run.integrator.removeFrameFromDesktop(editableBean);
	}
}

