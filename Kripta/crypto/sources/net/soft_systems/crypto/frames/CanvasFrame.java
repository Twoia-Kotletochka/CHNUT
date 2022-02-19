/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.frames;
import javax.swing.*;
import net.soft_systems.crypto.frames.CryptoCanvas;
import net.soft_systems.integrator.*;
abstract public class CanvasFrame extends EditFrame {
	protected CryptoCanvas canvas;
	public CanvasFrame(Bean bean) {
		super(bean);
		init();
	}
	protected void init() {
		this.setLocation(0, 0);
		this.setSize(500, 500);
		canvas = getCanvas();
		JScrollPane scrollPane = new JScrollPane(canvas);
		canvas.setScrollPane(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
	}
	public void layoutElements(int layoutMode) { canvas.layoutElements(layoutMode); }
	public abstract JToolBar getToolBar();
	public double getScale() { return canvas.getScale(); }
	public void setScale(double scale) { canvas.setScale(scale); }
	public abstract CryptoCanvas getCanvas();
}

