package view;

import java.util.List;
import controller.Controller;
import query.QueryCorps;
import java.util.LinkedHashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JDialog;


public class DlgAudience extends JDialog implements IDlg{

	private final JPanel contentPanel;
	private Map<String, Object> map;
	private int idAudience;
	private int idCorps;
	private JTextField textField_Corps;
	private JTextField textField_Number;
	private JTextField textField_Floor;
	private JTextField textField_area;

	public static void main(String[] args) {
		try {
			DlgAudience dialog = new DlgAudience();
			dialog.setDefaultCloseOperation(2);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public DlgAudience(Map map) {
		this();
		idAudience = (int) map.get("id");
		//idCorps = (int) map.get("idCorps");
		textField_Number.setText(String.valueOf(map.get("number")));
		textField_area.setText((String) map.get("area"));
		textField_Floor.setText(String.valueOf(map.get("floor")));
		textField_Corps.setText((String) map.get("corps"));
	}

	 public Map<String, Object> getMap() {
	        return map;
	    }
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DlgAudience() {
		this.contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 470, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_Corps = new JTextField();
			textField_Corps.setEnabled(false);
			textField_Corps.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					
					String query = QueryCorps.queryGetAll();
					List<Map<String, Object>> listMap = 
							Controller.executeQuery(query);
					DlgSelect ds = new DlgSelect(listMap);
					ds.setTitle("QueryCorps selection");
					ds.setVisible(true);
					Map map = ds.getMap();
					ds.dispose();
					idCorps = (int) map.get("id");
					textField_Corps.setText((String) map.get("coprs"));		
					
				}
			});
			textField_Corps.setBorder(new TitledBorder(null, "Corps", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_Corps.setBounds(10, 78, 115, 49);
			contentPanel.add(textField_Corps);
			textField_Corps.setColumns(10);
		}
		
		textField_Number = new JTextField();
		textField_Number.setBorder(new TitledBorder(null, "Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_Number.setBounds(10, 10, 126, 49);
		contentPanel.add(textField_Number);
		textField_Number.setColumns(10);
		{
			textField_Floor = new JTextField();
			textField_Floor.setBorder(new TitledBorder(null, "Floor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_Floor.setBounds(167, 10, 126, 49);
			contentPanel.add(textField_Floor);
			textField_Floor.setColumns(10);
		}
		{
			textField_area = new JTextField();
			textField_area.setBorder(new TitledBorder(null, "Area sq. m. ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_area.setBounds(320, 10, 126, 49);
			contentPanel.add(textField_area);
			textField_area.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String number = textField_Number.getText();
						String area = textField_area.getText();
						String floor = textField_Floor.getText();

						(map = new LinkedHashMap<String, Object>()).put("id", idAudience);
						map.put("number", number);
						map.put("area", area);
						map.put("floor", floor);
						map.put("idCorps", idCorps);
						setVisible(false);	

						
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
						map = null;
				        setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	   
	   public void clear() {
	        textField_Number.setText("");
	        textField_area.setText("");
	        textField_Floor.setText("");
	        textField_Corps.setText("");
	    }
}
