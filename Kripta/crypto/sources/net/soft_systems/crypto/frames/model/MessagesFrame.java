package net.soft_systems.crypto.frames.model;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.algo.Binary;
import net.soft_systems.crypto.beans.model.MessageGroupBean;
import net.soft_systems.integrator.*;
import net.soft_systems.model.message.*;
public class MessagesFrame extends EditFrame {
	Object filter      = null;
	JLabel filterLabel = new JLabel();
	JTable table       = new JTable();
	JComboBox filterComboBox;
	JComboBox formatComboBox;
	JTextPane dataLabel = new JTextPane();
	protected JComboBox getFormatComboBox() {
		Vector formats = new Vector();
		formats.add("Шестнадцатеричный");
		formats.add("Строковый");
		formats.add("Бинарный");
		JComboBox c = new JComboBox(formats);
		c.setSelectedIndex(0);
		c.addItemListener(
			new ItemListener() {
				public void itemStateChanged(ItemEvent e) { updateSelectedMessageData(); }
			});
		return c;
	}
	public MessagesFrame(Bean bean) {
		super(bean);
		init();
	}
	public MessagesFrame(Bean bean, Bean filter) {
		super(bean);
		this.filter = filter;
		init();
	}
	protected void doOk() { closeFrame(); }
	protected Vector getMessages() {
		filter = filterComboBox.getSelectedItem();
		MessageGroupBean msgGroup = (MessageGroupBean)getBean();
		Vector messages = null;
		if (filter == null) { messages = new Vector(); }
		else if (filter.equals("Все")) { messages = msgGroup.getMessages(); }
		else if (filter.equals("Сервер")) {
			messages = new Vector();
			Enumeration en = msgGroup.getMessages().elements();
			Message msg;
			while (en.hasMoreElements()) {
				msg = (Message)en.nextElement();
				if (!(msg instanceof BeanMessage)) { messages.add(msg); }
			}
		}
		else {
			messages = new Vector();
			Enumeration en = msgGroup.getMessages().elements();
			Message msg;
			while (en.hasMoreElements()) {
				msg = (Message)en.nextElement();
				if (msg instanceof BeanMessage) {
					BeanMessage bMsg = (BeanMessage)msg;
					if (bMsg.getBean().equals(filter)) { messages.add(msg); }
				}
			}
		}
		return messages;
	}
	class UpdatableTableModel extends AbstractTableModel {
		public UpdatableTableModel() { fireTableDataChanged(); }
		Vector messages;
		public void fireTableDataChanged() {
			messages = getMessages();
			super.fireTableDataChanged();
		}
		public String getColumnName(int column) {
			switch (column) {
				case 0:
					return "Номер";
				case 1:
					return "Время";
				case 2:
					return "Объект системы";
				case 3:
					return "Сообщение";
				default:
					return "";
			}
		}
		public int getRowCount() { return messages.size(); }
		public int getColumnCount() { return 4; }
		public Object getValueAt(int row, int column) {
			Message msg = (Message)messages.elementAt(row);
			if (msg != null) {
				switch (column) {
					case 0:
						return "" + msg.getNo();
					case 1:
						return msg.getMessageTimeStr();
					case 2: {
							if (msg instanceof BeanMessage) {
								BeanMessage bmsg = (BeanMessage)msg;
								return bmsg.getBean().toString();
							}
							else { return ""; }
						}
					case 3:
						return msg.getContent();
					default:
						return "";
				}
			}
			else { return ""; }
		}
		public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }
	};
	public TableModel getTableModel() { return new UpdatableTableModel(); }
	protected void updateSelected() {
		filter = filterComboBox.getSelectedItem();
		AbstractTableModel model = (AbstractTableModel)table.getModel();
		model.fireTableDataChanged();
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
		updateSelectedMessageData();
	}
	protected void updateTableModel() {
		AbstractTableModel model = (AbstractTableModel)table.getModel();
		model.fireTableDataChanged();
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
		updateSelectedMessageData();
		// need to update beans combo box
	}
	private class UpdatableComboBoxModel extends AbstractListModel implements ComboBoxModel {
		private Vector items;
		private Object selected;
		public UpdatableComboBoxModel(Vector items, Object selected) {
			this.items = items;
			if (items.size() > 0 && selected == null) { this.selected = items.elementAt(0); }
			this.selected = selected;
		}
		public void setSelectedItem(Object obj) {
			this.selected = obj;
			updateSelected();
		}
		public Object getSelectedItem() { return selected; }
		public int getSize() { return items.size(); }
		public Object getElementAt(int i) { return items.elementAt(i); }
	}
	protected void updateFilter(Object filter) {
		MessageGroupBean messageGroup = (MessageGroupBean)getBean();
		Vector beans = messageGroup.getMessageBeans();
		beans.insertElementAt("Все", 0);
		beans.insertElementAt("Сервер", 1);
		if (filter != null && !beans.contains(filter)) { beans.add(filter); }
		filterComboBox.setModel(new UpdatableComboBoxModel(beans, filter));
		if (filter != null) { filterComboBox.setSelectedItem(filter); }
	}
	protected void init() {
		addInternalFrameListener(
			new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent evt) { closeDialog(); }
			});
		setBounds(new Rectangle(0, 0, 600, 500));
		GridBagLayout layout = new GridBagLayout();
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		filterLabel.setText("Фильтр");
		//filterLabel
		getContentPane().add(filterLabel,
			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
			new Insets(5, 5, 0, 5), 0, 0));
		filterComboBox = new JComboBox();
		updateFilter(filter);
		getContentPane().add(filterComboBox,
			new GridBagConstraints(GridBagConstraints.RELATIVE, 0, GridBagConstraints.RELATIVE, 1, 0.0, 0.0,
			GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		JButton updateButton = new JButton("Обновить");
		updateButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateFilter(filterComboBox.getSelectedItem());
					updateTableModel();
				}
			});
		getContentPane().add(updateButton,
			new GridBagConstraints(GridBagConstraints.RELATIVE, 0, GridBagConstraints.REMAINDER, 1, 0.0, 0.0,
			GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		// tableScroll
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setMinimumSize(new Dimension(300, 150));
		tableScroll.setPreferredSize(new Dimension(300, 150));
		getContentPane().add(tableScroll,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 1.0, 100.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(5, 5, 5, 5), 0, 0));
		//data label
		JLabel lab1 = new JLabel("Данные");
		getContentPane().add(lab1,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.RELATIVE, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
			new Insets(5, 5, 5, 0), 0, 0));
		dataLabel.setContentType("text/html");
		dataLabel.setMinimumSize(new Dimension(300, 100));
		dataLabel.setPreferredSize(new Dimension(300, 100));
		// формат
		formatComboBox = getFormatComboBox();
		getContentPane().add(formatComboBox,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
			new Insets(5, 5, 5, 5), 0, 0));
		JScrollPane pane = new JScrollPane(dataLabel);
		pane.setVerticalScrollBarPolicy(pane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(pane,
			new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
			GridBagConstraints.REMAINDER, 1, 100.0, 100.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
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
		table.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) { updateSelectedMessageData(); }
			});
/*		table.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				updateSelectedMessageData();
			}
		});
		*/
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(getTableModel());
		table.getSelectionModel().addListSelectionListener(
			new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) { updateSelectedMessageData(); }
			});
		table.sizeColumnsToFit(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	protected void initPropertiesPanel() { }
	private void closeDialog() { Run.integrator.removeFrameFromDesktop(this); }
	static public String dataToString(Object d, int byteArrFormat) {
		String m;
		if (d != null) {
			if (d instanceof byte[]) {
				byte db[] = (byte[]) d;
				switch (byteArrFormat) {
					case 0:
						m = Binary.toHexString(db);
					break;
					case 2:
						m = Binary.toBinString(db);
					break;
					case 1:
						m = Binary.toString(db);
					break;
					default:
						m = Binary.toHexString(db);
					break;
				}
			}
			else if (d instanceof int[]) {
				int id[] = (int[]) d;
				m = "";
				for (int i = 0; i < id.length; i++) {
					m += id[i];
					if (i != id.length - 1) m += ", ";
				}
			}
			else if (d instanceof long[]) {
				long id[] = (long[]) d;
				m = "";
				for (int i = 0; i < id.length; i++) {
					m += id[i];
					if (i != id.length - 1) m += ", ";
				}
			}
			else { m = "" + d; }
		}
		else { m = ""; }
		return m;
	}
	protected void updateSelectedMessageData() {
		if (table.getSelectedRow() >= 0 && table.getSelectedRow() < getMessages().size()) {
			Message msg = (Message)getMessages().elementAt(table.getSelectedRow());
			if (msg instanceof BeanMessage) {
				BeanMessage bmsg = (BeanMessage)msg;
				Object d = bmsg.getData();
				setMessageData(dataToString(d, formatComboBox.getSelectedIndex()));
			}
			else { setMessageData(""); }
		}
		else { setMessageData(""); }
	}
	protected void setMessageData(String data) {
		if (!dataLabel.getText().equals(data)) {
			dataLabel.setText(data);
			dataLabel.setCaretPosition(0);
		}
	}
}

