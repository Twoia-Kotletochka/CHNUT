package net.soft_systems.crypto.frames.process;
import java.awt.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoBean;
import net.soft_systems.crypto.beans.process.NodeBean;
import net.soft_systems.crypto.frames.CryptoEditFrame;
public class NodeFrame extends CryptoEditFrame {
	public NodeFrame(CryptoBean bean) { super(bean); }
	JComboBox typeComboBox;
	protected void initPropertiesPanel() {
		propertiesPanel = new JPanel();
		setBackground(propertiesPanel.getBackground());
		propertiesPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Свойства"),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagLayout layout = new GridBagLayout();
		propertiesPanel.setLayout(layout);
		GridBagConstraints c;
		c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.insets = new Insets(0, 10, 0, 0);
		propertiesPanel.add(new JLabel("Идентификатор:"), c);
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(0, 5, 0, 10);
		idField = new JTextField(getBean().getId());
		propertiesPanel.add(idField, c);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.insets = new Insets(5, 10, 0, 0);
		propertiesPanel.add(new JLabel("Тип:"), c);
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(5, 5, 0, 10);
		typeComboBox = initTypeComboBox();
		propertiesPanel.add(typeComboBox, c);
	}
	protected JComboBox initTypeComboBox() {
		NodeBean editableBean = (NodeBean)getBean();
		JComboBox cb = new JComboBox();
		cb.addItem("in");
		cb.addItem("out");
		switch (editableBean.getNodeType()) {
			case NodeBean.IN:
				cb.setSelectedIndex(0);
			break;
			case NodeBean.OUT:
				cb.setSelectedIndex(1);
			break;
		}
		return cb;
	}
	protected int getType() {
		int typeIdx = typeComboBox.getSelectedIndex();
		switch (typeIdx) {
			case 0:
				return NodeBean.IN;
			default:
				return NodeBean.OUT;
		}
	}
	protected void setData() {
		NodeBean editableBean = (NodeBean)getBean();
		editableBean.setId(idField.getText());
		editableBean.setNodeType(getType());
		Run.integrator.updateTreeEdit(editableBean);
	}
	protected void doOk() {
		setData();
		closeFrame();
	}
	protected void init() {
		initPropertiesPanel();
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.NORTH;
		getContentPane().add(propertiesPanel, c);
		initButtonPanel();
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.SOUTH;
		getContentPane().add(buttonPanel, c);
	}
}

