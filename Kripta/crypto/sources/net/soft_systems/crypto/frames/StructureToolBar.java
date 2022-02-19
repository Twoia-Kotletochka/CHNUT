package net.soft_systems.crypto.frames;
import java.awt.event.*;
import javax.swing.*;
public class StructureToolBar extends JToolBar {
	protected CanvasFrame frame;
	public StructureToolBar(CanvasFrame frame) {
		super(frame.getBean().getName());
		this.frame = frame;
		init();
	}
	protected void doLayoutElements(int layoutMode) { frame.layoutElements(layoutMode); }
	protected void updateSelected(JComboBox scaleCombo) {
		double scale = frame.getScale();
		switch ((int)(scale * 100)) {
			case 500:
				scaleCombo.setSelectedIndex(0);
			break;
			case 300:
				scaleCombo.setSelectedIndex(1);
			break;
			case 200:
				scaleCombo.setSelectedIndex(2);
			break;
			case 150:
				scaleCombo.setSelectedIndex(3);
			break;
			case 100:
				scaleCombo.setSelectedIndex(4);
			break;
			case 75:
				scaleCombo.setSelectedIndex(5);
			break;
			case 50:
				scaleCombo.setSelectedIndex(6);
			break;
			case 25:
				scaleCombo.setSelectedIndex(7);
			break;
			default:
				scaleCombo.setSelectedItem("" + (int)(scale * 100) + "%");
			break;
		}
	}
	protected void init() {
		JComboBox scaleCombo = new JComboBox();
		scaleCombo.setEditable(true);
		scaleCombo.addItem("500%");
		scaleCombo.addItem("300%");
		scaleCombo.addItem("200%");
		scaleCombo.addItem("150%");
		scaleCombo.addItem("100%");
		scaleCombo.addItem("75%");
		scaleCombo.addItem("50%");
		scaleCombo.addItem("25%");
		updateSelected(scaleCombo);
		scaleCombo.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					int index = cb.getSelectedIndex();
					if (index < 0) {
						String s = (String)cb.getSelectedItem();
						s = s.replace('%', ' ').trim();
						try {
							int scaleInt = Integer.valueOf(s).intValue();
							frame.setScale(scaleInt / 100d);
							updateSelected(cb);
						}
						catch (NumberFormatException ex) { updateSelected(cb); }
					}
					else {
						switch (index) {
							case 0:
								frame.setScale(5d);
							break;
							case 1:
								frame.setScale(3d);
							break;
							case 2:
								frame.setScale(2d);
							break;
							case 3:
								frame.setScale(1.5d);
							break;
							case 4:
								frame.setScale(1d);
							break;
							case 5:
								frame.setScale(0.75d);
							break;
							case 6:
								frame.setScale(0.5d);
							break;
							case 7:
								frame.setScale(0.25d);
							break;
						}
					}
				}
			});
		add(scaleCombo);
		JButton layoutButton = new JButton("Расположить (гориз)");
		layoutButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { doLayoutElements(CryptoCanvas.HORIZONTAL_LAYOUT); }
			});
		add(layoutButton);
		layoutButton = new JButton("Расположить (верт)");
		layoutButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { doLayoutElements(CryptoCanvas.VERTICAL_LAYOUT); }
			});
		add(layoutButton);
	}
}

