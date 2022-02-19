/*
 * CryptoEditFrame.java
 *
 * Created on понеділок, 26, серпня 2002, 13:01
 */

package net.soft_systems.crypto.frames;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoBean;
import net.soft_systems.integrator.*;
/**
 * ----------------------------------------------------------------------------------------------------------
 * @author andrew
 * @version ---------------------------------------------------------------------------------------------------------
 */
public class CryptoEditFrame extends EditFrame {
	protected JTextField idField;
	protected JPanel propertiesPanel;
	protected JPanel buttonPanel;
	/**
	 * Creates new CryptoEditFrame
	 */
	public CryptoEditFrame(CryptoBean bean) {
		super(bean, bean.getTypeName() + " " + bean.getName());
		init();
	}
	protected void setId() {
		EditableBean editableBean = (EditableBean)getBean();
		editableBean.setId(idField.getText());
		Run.integrator.updateTreeEdit(editableBean);
	}
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
	}
	protected void initButtonPanel() {
		buttonPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		buttonPanel.setLayout(layout);
		JButton button = new JButton("OK");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) { doOk(); }
			});
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 10;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 100;
		c.insets = new Insets(5, 0, 5, 0);
		c.anchor = GridBagConstraints.EAST;
		buttonPanel.add(button, c);
		button = new JButton("Отмена");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) { doCancel(); }
			});
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		buttonPanel.add(button, c);
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
		c.weighty = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.SOUTH;
		getContentPane().add(buttonPanel, c);
	}
	protected void doOk() {
		setId();
		closeFrame();
	}
	protected void doCancel() {
		EditableBean editableBean = (EditableBean)getBean();
		editableBean.closeEditFrame();
	}
}

