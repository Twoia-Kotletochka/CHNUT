package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class InfoFrame extends JFrame
{
    private JPanel contentPane;
    private JTextArea textArea;
    private JPanel panelPhoto;
    
    public InfoFrame() {
        this.setBounds(100, 100, 265, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        final GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[2];
        gbl_contentPane.rowHeights = new int[3];
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        this.contentPane.setLayout(gbl_contentPane);
        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = 1;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        this.contentPane.add(this.getPanelPhoto(), gbc_panel);
        final GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.fill = 1;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 1;
        this.contentPane.add(this.getTextArea(), gbc_textArea);
    }
    
    public JTextArea getTextArea() {
        if (this.textArea == null) {
            (this.textArea = new JTextArea()).setFont(new Font("Monospaced", 0, 12));
            this.textArea.setText("Developer Olenchenko Illya\nGroup KIt-211\nIndividual plan number 14\nE-mail olenchenko.ilya243@gmail.com\ntel. 380689987766");
        }
        return this.textArea;
    }
    
    private JPanel getPanelPhoto() {
        if (this.panelPhoto == null) {
            this.panelPhoto = new JPanel() {
                public void paintComponent(final Graphics g) {
                    super.paintComponent(g);
                    final Graphics2D g2d = (Graphics2D)g;
                    final URL url = this.getClass().getResource("/resource/IMG_3097.jpg");
                    BufferedImage img;
                    try {
                        img = ImageIO.read(url);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    final double k = img.getHeight() / (double)img.getWidth();
                    int width = this.getWidth();
                    int height = this.getHeight();
                    if (height / (double)width > k) {
                        height = (int)(width * k);
                    }
                    else {
                        width = (int)(height / k);
                    }
                    final Image scaledImg = img.getScaledInstance(width, height, 4);
                    g2d.drawImage(scaledImg, 0, 0, null);
                }
            };
        }
        return this.panelPhoto;
    }
}
