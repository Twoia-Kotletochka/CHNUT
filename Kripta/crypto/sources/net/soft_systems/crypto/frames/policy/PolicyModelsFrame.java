package net.soft_systems.crypto.frames.policy;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.policy.ClassRefBean;
import net.soft_systems.crypto.frames.*;
import net.soft_systems.integrator.*;
public class PolicyModelsFrame extends BaseInternalFrame {
	protected CheckBoxList list;
	protected JPanel getModelsPanel() {
		JPanel modelsPanel = new JPanel();
		modelsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Модели"),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		JPanel listPanel   = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel listName    = new JLabel();
		list = new CheckBoxList();
		JButton editButton = new JButton();
		JScrollPane scrollListPane = new JScrollPane(list);
		//setting text values
		listName.setText(Run.integrator.messages.getMessage("elem-list"));
		editButton.setText(Run.integrator.messages.getMessage("edit"));
		// adding ui elements
		modelsPanel.setLayout(new GridBagLayout());
		modelsPanel.add(listPanel,
			new GridBagConstraints(0, 0, 1, GridBagConstraints.REMAINDER, 1.0, 1.0, GridBagConstraints.SOUTHWEST,
			GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		modelsPanel.add(buttonPanel,
			new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH,
			new Insets(0, 0, 0, 0), 0, 0));
		listPanel.setLayout(new GridBagLayout());
		listPanel.add(listName,
			new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1.0, 0.0,
			GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		listPanel.add(scrollListPane,
			new GridBagConstraints(0, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER,
			GridBagConstraints.REMAINDER, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(5, 5, 5, 5), 0, 0));
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.add(editButton,
			new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, 1, 1.0, 0.0, GridBagConstraints.SOUTH,
			GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		// adding ActionListeners to buttons
		editButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { doEdit(); }
			});
		list.addMouseListener(
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() > 1) { doEdit(); }
				}
			});
		return modelsPanel;
	}
	protected JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();
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
		return buttonPanel;
	}
	public void closeDialog() {
		try { setClosed(true); }
		catch (PropertyVetoException ex) { ex.printStackTrace(); }
	}
	protected void setPolicies() {
		Vector policies = Run.infoSystem.getPolicies();
		for (int i = 0; i < list.getCheckBoxCount(); i++) {
			ClassRefBean policy = (ClassRefBean)getAvailPolicy(i);
			if (list.isSelected(i)) {
				try { Run.infoSystem.enablePolicy(policy.getClassRef()); }
				catch (ClassNotFoundException ex) {
					Debug.error("Неизвестный класс политики безопасности " + policy.getClassName());
				}
			}
			else {
				try { Run.infoSystem.disablePolicy(policy.getClassRef()); }
				catch (ClassNotFoundException ex) {
					Debug.error("Неизвестный класс политики безопасности " + policy.getClassName());
				}
			}
		}
	}
	protected void doOk() {
		setPolicies();
		closeDialog();
	}
	protected void doCancel() { closeDialog(); }
	protected void doEdit() {
		int index = list.getSelectedIndex();
		if (index >= 0) {
			Bean availPolicy = getAvailPolicy(index);
			Debug.debug("Editing policy " + index + " Policy " + availPolicy);
		}
		else { Debug.debug("Policy is not selected"); }
	}
	public void init() {
		setBounds(new Rectangle(0, 0, 379, 446));
		JPanel modelsPanel = getModelsPanel();
		JPanel buttonPanel = getButtonPanel();
		setBackground(modelsPanel.getBackground());
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 100;
		c.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(modelsPanel, c);
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.SOUTH;
		getContentPane().add(buttonPanel, c);
	}
	protected Bean getAvailPolicy(int index) { return (Bean)Run.infoSystem.getAvailPolicies().get(index); }
	protected void initData() {
		Enumeration en = Run.infoSystem.getAvailPolicies().elements();
		ClassRefBean policy;
		while (en.hasMoreElements()) {
			policy = (ClassRefBean)en.nextElement();
			if (ClassRefBean.class.isAssignableFrom(policy.getClass())) {
				try { list.addCheckBox(policy.toString(), Run.infoSystem.isPolicyEnabled(policy.getClassRef())); }
				catch (ClassNotFoundException ex) {
					Debug.error("Класс политики безопасности " + policy.getClassName() + " не найден");
				}
			}
			else {
				Debug.error("Неверный класс задания политики безопасности в конфигурации. Класс " +
					policy.getClass().getName() + " должен наследовать класс ClassRefBean");
			}
		}
		list.addCheckBoxListener(
			new CheckBoxListListener() {
				public void selected(int index) {
					Debug.debug("Selected check box " + index + "Policy " + getAvailPolicy(index));
				}
				public void checked(int index, boolean checked) {
					Debug.debug("Checked check box " + index + "Policy " + getAvailPolicy(index) +
						" State " + checked);
				}
			});
	}
	public PolicyModelsFrame() {
		super("Модели политики безопасности");
		init();
		initData();
	}
}

