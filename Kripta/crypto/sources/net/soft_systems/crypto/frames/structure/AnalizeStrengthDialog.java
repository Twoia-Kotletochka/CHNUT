package net.soft_systems.crypto.frames.structure;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.structure.RelationBean;
import net.soft_systems.crypto.ui.Formats;
import net.soft_systems.integrator.BaseInternalFrame;
public class AnalizeStrengthDialog extends BaseInternalFrame {
	private Vector relations;
	/**
	 * Creates new form JDialog
	 */
	public AnalizeStrengthDialog(Vector relations) {
		super(Run.integrator.messages.getMessage("analize-protection"));
		this.relations = relations;
		initGUI();
	}
	public RelationBean getRelationAt(int index) { return (RelationBean)relations.elementAt(index); }
	public TableModel getTableModel() {
		return new AbstractTableModel() {
			public String getColumnName(int column) {
				switch (column) {
					case 0:
						return "Отношение";
					case 1:
						return "Вероятность появления угрозы";
					case 2:
						return "Величина ущерба";
					case 3:
						return "Сопротивляемость защиты";
					case 4:
						return "Остаточный риск";
					default:
						return "";
				}
			}
			public int getRowCount() { return relations.size(); }
			public int getColumnCount() { return 5; }
			public Object getValueAt(int row, int column) {
				switch (column) {
					case 0:
						return getRelationAt(row).getFullName();
					case 1:
						return (getRelationAt(row).getThreat() != null) ?
							"" + getRelationAt(row).getThreat().getProbability() : "";
					case 2:
						return (getRelationAt(row).getVulnerability() != null) ?
							"" + getRelationAt(row).getVulnerability().getLoss() : "";
					case 3:
						return "" + getRelationAt(row).getProtectionStrength();
					case 4:
						return Formats.format(getRelationAt(row).getBoundaryRisk());
					default:
						return "";
				}
			}
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				switch (columnIndex) {
					case 3:
						return getRelationAt(rowIndex).getProtection() != null;
					case 1:
						return getRelationAt(rowIndex).getThreat() != null;
					case 2:
						return getRelationAt(rowIndex).getVulnerability() != null;
					default:
						return false;
				}
			}
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				try {
					double value;
					value = Double.valueOf(aValue.toString()).doubleValue();
					switch (columnIndex) {
						case 1:
							if (getRelationAt(rowIndex).getThreat() != null) {
								getRelationAt(rowIndex).getThreat().setProbability(value);
							}
						break;
						case 2:
							if (getRelationAt(rowIndex).getVulnerability() != null) {
								getRelationAt(rowIndex).getVulnerability().setLoss(value);
							}
						break;
						case 3:
							if (getRelationAt(rowIndex).getProtection() != null) {
								getRelationAt(rowIndex).getProtection().setStrength(value);
							}
						break;
					}
					updateData();
				}
				catch (Exception ex) { }
			}
		};
	}
	public void updateData() {
		firstValue.setText(Formats.format(Run.infoSystem.getTotalRisk()));
		if (Double.isInfinite(Run.infoSystem.getTotalStrength())) { secondValue.setText("max"); }
		else { secondValue.setText(Formats.format(Run.infoSystem.getTotalStrength())); }
		AbstractTableModel model = (AbstractTableModel)table.getModel();
		model.fireTableDataChanged();
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initGUI() {
		pack();
		addInternalFrameListener(
			new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent evt) { closeDialog(); }
			});
		setBounds(new Rectangle(0, 0, 600, 500));
		GridBagLayout layout = new GridBagLayout();
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		titleLabel.setText("Защищенность барьеров");
		firstLabel.setText("Суммарный риск");
		secondLabel.setText("Защищенность системы");
		//titleLabel
		getContentPane().add(titleLabel,
			new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 1.0, 0.0, GridBagConstraints.NORTH,
			GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		// tableScroll
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setMinimumSize(new Dimension(300, 150));
		tableScroll.setPreferredSize(new Dimension(300, 150));
		getContentPane().add(tableScroll,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 1.0, 100.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(5, 5, 5, 5), 0, 0));
		//first
		getContentPane().add(firstLabel,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.RELATIVE, 1, 3.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
			new Insets(5, 5, 5, 5), 0, 0));
		getContentPane().add(firstValue,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
			new Insets(5, 5, 5, 5), 0, 0));
		//second
		getContentPane().add(secondLabel,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.RELATIVE, 1, 3.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
			new Insets(5, 5, 5, 5), 0, 0));
		getContentPane().add(secondValue,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
			new Insets(5, 5, 5, 5), 0, 0));
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		layout = new GridBagLayout();
		buttonPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 100;
		buttonPanel.add(new JPanel(), c);
		// close button
		JButton button = new JButton(Run.integrator.messages.getMessage("close"));
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) { closeDialog(); }
			});
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		buttonPanel.add(button, c);
		// adding button panel
		c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.SOUTH;
		getContentPane().add(buttonPanel, c);
		setBackground(buttonPanel.getBackground());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(getTableModel());
		updateData();
	}
	private void closeDialog() {
		try { setClosed(true); }
		catch (PropertyVetoException ex) { ex.printStackTrace(); }
	}
	private JLabel titleLabel      = new JLabel();
	private JTable table           = new JTable();
	private JLabel firstLabel      = new JLabel();
	private JTextField firstValue  = new JTextField();
	private JLabel secondLabel     = new JLabel();
	private JTextField secondValue = new JTextField();
}

