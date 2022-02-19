package gg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.*;

public class Info extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPhoto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Info() {
		setTitle("Developer information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panelPhoto = getPanelPhoto();
		
		GridBagConstraints gbc_panelPhoto = new GridBagConstraints();
		gbc_panelPhoto.gridwidth = 2;
		gbc_panelPhoto.gridheight = 3;
		gbc_panelPhoto.insets = new Insets(0, 0, 5, 0);
		gbc_panelPhoto.fill = GridBagConstraints.BOTH;
		gbc_panelPhoto.gridx = 0;
		gbc_panelPhoto.gridy = 0;
		contentPane.add(panelPhoto, gbc_panelPhoto);
		
		JTextArea txtrDeveloperIllyaOlenchenko = new JTextArea();
		txtrDeveloperIllyaOlenchenko.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		txtrDeveloperIllyaOlenchenko.setText("Developer Illya Olenchenko\r\nDepartament FBI\r\nIndividual plan number 14\r\nE-mail olenchenko.ilya243@gmail.com\r\ntel. 380689987766");
		GridBagConstraints gbc_txtrDeveloperIllyaOlenchenko = new GridBagConstraints();
		gbc_txtrDeveloperIllyaOlenchenko.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtrDeveloperIllyaOlenchenko.anchor = GridBagConstraints.NORTH;
		gbc_txtrDeveloperIllyaOlenchenko.gridwidth = 2;
		gbc_txtrDeveloperIllyaOlenchenko.gridx = 0;
		gbc_txtrDeveloperIllyaOlenchenko.gridy = 4;
		contentPane.add(txtrDeveloperIllyaOlenchenko, gbc_txtrDeveloperIllyaOlenchenko);
	}

	private JPanel getPanelPhoto() {	
		if (panelPhoto == null) {
			//Create panel as anonymous class object
			panelPhoto = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				//override method paintComponent in anonymous class
				public void paintComponent(Graphics g){
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					BufferedImage img;
					// set path to photo
					URL url = getClass().getResource("/resource/IMG_3097.jpg");
					try {
						//reading photo to image
						img = ImageIO.read(url);
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}

					//image scaling according to panel size
					double k = (double)img.getHeight() / img.getWidth();
					int width = getWidth();
					int height = getHeight();
					if((double)height / width > k)
						height = (int) (width *k);
					else
						width = (int) (height /k);
					Image scaledImg = img.getScaledInstance(
							width, height, Image.SCALE_SMOOTH);
					//show photo
					g2d.drawImage(scaledImg,0,0,null);
				};
			};
		}
		return panelPhoto;
	}
}
