package lab_1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class DlgCommand extends JDialog {

	private static Map<String,Object> map;
	private int idCathedra;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_furniture;
	private JTextField textField_audit;
	private JTextField textField_Corps;

	
	public DlgCommand(Map<String, Object> map) { //Для створення діалогу, що відображає надану інформацію конструктор 
		this();
		textField_Corps.setText((String) map.get("corps"));		
		textField_audit.setText((String) map.get("audience"));		
		textField_furniture.setText(String.valueOf(map.get("furniture")));		
		idCathedra = (int) map.get("id");
	}

	public Map<String, Object> getMap(){ 
		
		return map;
	}
	
	
	public void clear() { //Для очистки полів діалогу створімо метод 
		textField_Corps.setText("");
		textField_audit.setText("");
		textField_furniture.setText("");
		//idCathedra = 0;
		map = null;
	}

	
	public void onCancel() { //Для обробки події натискання на кнопку Cancel 
		map = null;
		setVisible(false);
	}
	
	public Class<?> setVisible() { // метод для відображення діалогу
		DlgCommand window  = new DlgCommand();
		window.setVisible(true);
		return null;
	}
	
	public void onOk() {	 //Для обробки події натискання на кнопку ОК метод
		String name = textField_Corps.getText();
		String chief = textField_Corps.getText();
		String staffTxt = textField_furniture.getText();
		idCathedra++;
		int staff = 0;
		try {
			staff = Integer.parseInt(staffTxt);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog((Component) map, "\""
					+ staffTxt +"\" is not correct number");
			return;
		}
		map = new LinkedHashMap<>();
		map.put("id", idCathedra);
		map.put("corps", name);
		map.put("audience", chief);
		map.put("furniture", staff);

		setVisible(false);
	} 
	

	public DlgCommand() {
		setModal(true);
		setBounds(100, 100, 454, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textField_Corps = new JTextField();
			textField_Corps.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Corps", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textField_Corps.setToolTipText("");
			contentPanel.add(textField_Corps);
			textField_Corps.setColumns(10);
		}
		{
			textField_audit = new JTextField();
			textField_audit.setBorder(new TitledBorder(null, "Audience", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(textField_audit);
			textField_audit.setColumns(10);
		}
		{
			textField_furniture = new JTextField();
			textField_furniture.setBorder(new TitledBorder(null, "Furniture", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(textField_furniture);
			textField_furniture.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOk();
					}
				});
				okButton.setActionCommand("OK");
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

}
