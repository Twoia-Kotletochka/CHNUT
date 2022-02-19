//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\integrator\\BaseInternalFrame.java

package net.soft_systems.integrator;
import java.awt.event.*;
import java.util.*;
import javax.swing.JInternalFrame;
import javax.swing.event.*;
public class BaseInternalFrame extends JInternalFrame {
	protected Vector closeListeners = new Vector();
	public void addCloseListener(ActionListener closeListener) { this.closeListeners.add(closeListener); }
	public void removeCloseListener(ActionListener closeListener)
		{ this.closeListeners.remove(closeListener); }
	public void doClose() {
		Enumeration en = this.closeListeners.elements();
		while (en.hasMoreElements()) {
			ActionListener l = (ActionListener)en.nextElement();
			l.actionPerformed(new ActionEvent(this, 0, ""));
		}
	}
	static int openFrameCount = 0;
	static final int yOffset  = 20;
	static final int xOffset  = 20;
	public BaseInternalFrame(String title) {
		super(title, true, true, true, true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		openFrameCount++;
		setSize(300, 300);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		if (openFrameCount >= 10) { openFrameCount = 0; }
		addInternalFrameListener(
			new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent e) { doClose(); }
			});
	}
}

