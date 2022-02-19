package view;

import java.util.List;
import controller.Controller;
import query.QueryAudience;
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


public class DlgFurniture extends JDialog implements IDlg{

	private int id;
	private int price;
	private int amount_of_furniture;
	private int weight;
	
	
	private final JPanel contentPanel;
	private Map<String, Object> map;
	private int idFurniture;
	private int idAudience;
	private JTextField textField_price;
	private JTextField textField_amount_of_furniture;
	private JTextField textField_weight;
	
	private JTextField textField_Audience;

	public static void main(String[] args) {
		try {
			DlgFurniture dialog = new DlgFurniture();
			dialog.setDefaultCloseOperation(2);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public DlgFurniture(Map map) {
		this();
		idFurniture = (int) map.get("id");
		
		textField_price.setText(String.valueOf(map.get("price")));
		textField_weight.setText(String.valueOf(map.get("weight")));
		textField_amount_of_furniture.setText(String.valueOf(map.get("amount_of_furniture")));
		textField_Audience.setText((String) map.get("audience"));
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
	public DlgFurniture() {
		this.contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 470, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_Audience = new JTextField();
			textField_Audience.setEnabled(false);
			textField_Audience.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					
					String query = QueryAudience.queryGetAll();
					List<Map<String, Object>> listMap = 
							Controller.executeQuery(query);
					DlgSelect ds = new DlgSelect(listMap);
					ds.setTitle("QueryAudience selection");
					ds.setVisible(true);
					Map map = ds.getMap();
					ds.dispose();
					idAudience = (int) map.get("id");
					textField_Audience.setText((String) map.get("audience"));		
					
				}
			});
			textField_Audience.setBorder(new TitledBorder(null, "Audience", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_Audience.setBounds(10, 78, 115, 49);
			contentPanel.add(textField_Audience);
			textField_Audience.setColumns(10);
		}
		
		textField_price = new JTextField();
		textField_price.setBorder(new TitledBorder(null, "Price", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_price.setBounds(10, 10, 126, 49);
		contentPanel.add(textField_price);
		textField_price.setColumns(10);
		{
			textField_amount_of_furniture = new JTextField();
			textField_amount_of_furniture.setBorder(new TitledBorder(null, "Amount of Furniture", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_amount_of_furniture.setBounds(167, 10, 126, 49);
			contentPanel.add(textField_amount_of_furniture);
			textField_amount_of_furniture.setColumns(10);
		}
		{
			textField_weight = new JTextField();
			textField_weight.setBorder(new TitledBorder(null, "Weight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			textField_weight.setBounds(320, 10, 126, 49);
			contentPanel.add(textField_weight);
			textField_weight.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int price = Integer.parseInt(textField_price.getText());
						int weight = Integer.parseInt(textField_weight.getText());
						int amount_of_furniture = Integer.parseInt(textField_amount_of_furniture.getText());
						
						(map = new LinkedHashMap<String, Object>()).put("id", idFurniture);
						map.put("price", price);
						map.put("amount_of_furniture", amount_of_furniture);
						map.put("weight", weight);
						map.put("idAudience", idAudience);
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
	        textField_price.setText("");
	        textField_weight.setText("");
	        textField_amount_of_furniture.setText("");
	        textField_Audience.setText("");
	    }
}
