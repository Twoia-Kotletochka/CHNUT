package net.soft_systems.crypto.frames.process;
import java.awt.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.CryptoBean;
import net.soft_systems.crypto.beans.process.MethodBean;
import net.soft_systems.crypto.frames.CryptoEditFrame;
public class MethodFrame extends CryptoEditFrame {
	JPanel codePanel;
	public MethodFrame(CryptoBean bean) { super(bean); }
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
/*		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(0, 5, 0, 10);
		ProcessBean process = (ProcessBean)getBean();
		autoCreateCheckBox = new JCheckBox("Создавать при запуске", process.isAutoCreate());
		propertiesPanel.add(autoCreateCheckBox, c);
		*/
	}
	JTextPane codePane;
	protected void initCodePanel() {
		MethodBean method = (MethodBean)getBean();
		codePanel = new JPanel();
		codePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Код"),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagLayout layout = new GridBagLayout();
		codePanel.setLayout(layout);
		GridBagConstraints c;
		c = new GridBagConstraints();
		c.weightx = 100;
		c.weighty = 100;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		codePane = new JTextPane();
		codePane.setContentType("text/plain");
		codePane.setEditable(true);
		codePane.setText(method.getCode());
		codePanel.add(new JScrollPane(codePane), c);
	}
	protected void setData() {
		MethodBean bean = (MethodBean)getBean();
		bean.setId(idField.getText());
		bean.setCode(codePane.getText());
		Run.integrator.updateTreeEdit(bean);
	}
	protected void doOk() {
		setData();
		closeFrame();
	}
	protected void init() {
		this.setSize(400, 500);
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
		initCodePanel();
		getContentPane().add(codePanel,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1.0, 100.0, GridBagConstraints.NORTH,
			GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
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

