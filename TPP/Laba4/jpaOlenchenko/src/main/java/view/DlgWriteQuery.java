package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class DlgWriteQuery extends JDialog
{
    private final JPanel contentPanel;
    private JTextArea textArea;
    private String query;
    
    public static void main(final String[] args) {
        try {
            final DlgWriteQuery dialog = new DlgWriteQuery();
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DlgWriteQuery() {
        this.contentPanel = new JPanel();
        this.setTitle("Enter SQL sentence");
        this.setModal(true);
        this.setBounds(100, 100, 243, 126);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, "Center");
        this.contentPanel.setLayout(new CardLayout(0, 0));
        (this.textArea = new JTextArea()).setLineWrap(true);
        this.textArea.setFont(new Font("Courier New", 0, 12));
        this.textArea.setText("select c.id, c.audience, c.corps, c.furniture from Corps c");
        this.contentPanel.add(this.textArea, "name_2507468788000");
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(2));
        this.getContentPane().add(buttonPane, "South");
        final JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                DlgWriteQuery.this.onOk();
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        this.getRootPane().setDefaultButton(okButton);
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
    
    protected void onOk() {
        this.query = this.textArea.getText();
        this.setVisible(false);
    }
    
    protected void onCancel() {
        this.query = null;
        this.setVisible(false);
    }
    
    public String getQuery() {
        return this.query;
    }
    
    public JTextArea getTextArea() {
        return this.textArea;
    }
}
