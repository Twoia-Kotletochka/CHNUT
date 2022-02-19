package view;

import java.util.Map;
import java.util.List;
import controller.Controller;
import query.QueryCorps;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class DlgQuery1 extends JDialog
{
	public JPanel contentPanel;
    private static JTextField textFieldNumber;
    private static JTextField textFielAudience;
    private static int IdFieldNumber;
    private static String Floor;
    
    public int getIdFieldNumber() {
        return IdFieldNumber;
    }
    
    public String getTeamName() {
        return Floor;
    }
    
    public static void main(String[] args) {
        try {
            DlgQuery1 dialog = new DlgQuery1();
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgQuery1() {
        contentPanel = new JPanel();
        setModal(true);
        setTitle("Query1");
       setBounds(100, 100, 415, 158);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, "Center");
        (textFieldNumber = new JTextField()).setColumns(10);
        textFieldNumber.setColumns(10);
        textFieldNumber.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Floor", 4, 2, null, new Color(0, 0, 0)), new BevelBorder(1, null, null, null, null)));
        contentPanel.add(textFieldNumber);
        (textFielAudience = new JTextField()).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked( MouseEvent e) {
                mouseClickedTextFieldFieldNumber();
            }
        });
        textFielAudience.setEditable(false);
        textFielAudience.setColumns(10);
        textFielAudience.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Corps", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
        contentPanel.add(textFielAudience);
         JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(2));
        getContentPane().add(buttonPane, "South");
         JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 if (IdFieldNumber == 0) {
                     JOptionPane.showMessageDialog(textFielAudience, "Corps not selected");
                     return;
                 }
                  String finishTxt = textFieldNumber.getText();
                 Floor = "";
                 try {
                     Floor = finishTxt;
                 }
                 catch (NumberFormatException e2) {
                     JOptionPane.showMessageDialog(null, "\"" + finishTxt + "\" is not correct number");
                     return;
                 }
                 setVisible(false);
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
         JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
            	Floor = "";
                IdFieldNumber = 0;
                setVisible(false);
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
    

  
    
    protected static void mouseClickedTextFieldFieldNumber() {
    	
        String query = QueryCorps.queryGetAll();
        List<Map<String, Object>> listMap = Controller.executeQuery(query);
        DlgSelect ds = new DlgSelect(listMap);
        ds.setTitle("QueryAudience selection");
        ds.setVisible(true);
        Map map = ds.getMap();
        ds.dispose();
        IdFieldNumber = (int) map.get("id");
        textFielAudience.setText((String) map.get("coprs"));
    }
}
