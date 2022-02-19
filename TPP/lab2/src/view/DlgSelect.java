package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DlgSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Map<String, Object> map;
	private DbTableView dbTableView;

	public DlgSelect(List<Map<String, Object>> mapList) {
		this();	
		dbTableView.setDbTableModel(mapList);
	}
	
	public Map<String, Object> getMap() {
		return map;
}


	protected  void onSelect() {
		map = dbTableView.getSelectedRowMap();
		setVisible(false);
}
	
	protected  void onCancel() {
		map = null;
		setVisible(false);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSelect dialog = new DlgSelect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSelect() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_4661904534900");
			{
				dbTableView = new DbTableView();
				scrollPane.setViewportView(dbTableView);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onSelect();
					}
				});
				okButton.setActionCommand("Select");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTable dbTtableview() {
		return dbTableView;
	}
}
