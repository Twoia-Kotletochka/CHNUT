package toLab1;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import stat.Histo;
import widgets.Diagram;

public class HomeWorkForLab1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagram = null;

	private Histo histo = null; // @jve:decl-index=0:visual-constraint="300,343"

	private JPanel jPanel = null;

	private JTextField jTextFieldV = null;

	private JButton jButtonHisto = null;

	private JTextArea jTextArea = null;

	private JScrollPane jScrollPane = null;

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			GridBagLayout gridBagLayout = (GridBagLayout) diagram.getLayout();
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
			diagram.setTitleText("\u0413\u0456\u0441\u0442\u043E\u0433\u0440\u0430\u043C\u0430");
		}
		return diagram;
	}

	/**
	 * This method initializes histo
	 * 
	 * @return stat.Histo
	 */
	private Histo getHisto() {
		if (histo == null) {
			histo = new Histo();
		}
		return histo;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new CardLayout());
			jPanel.setBorder(new TitledBorder(null, "\u041E\u0431\u0441\u044F\u0433 \u0432\u0438\u0431\u0456\u0440\u043A\u0438", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			jPanel.add(getJTextFieldV(), getJTextFieldV().getName());
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextFieldV
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldV() {
		if (jTextFieldV == null) {
			jTextFieldV = new JTextField();
			jTextFieldV.setName("jTextFieldV");
			jTextFieldV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldV.setText("1000");
		}
		return jTextFieldV;
	}

	/**
	 * This method initializes jButtonHisto
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonHisto() {
		if (jButtonHisto == null) {
			jButtonHisto = new JButton();
			jButtonHisto.setActionCommand("");
			jButtonHisto.setText("Отримати гістограму");
			jButtonHisto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int v = Integer.parseInt(getJTextFieldV().getText());
					getHisto().init();
					for (int i = 0; i < v; i++)
						getHisto().add(Math.random());
					getHisto().showRelFrec(getDiagram());
					getJTextArea().setText(getHisto().toString());
					getJTextArea().select(0, 0);
				}
			});
		}
		return jButtonHisto;
	}

	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setMargin(new Insets(0, 20, 0, 0));
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Автоматически созданная заглушка метода
		HomeWorkForLab1 application = new HomeWorkForLab1();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public HomeWorkForLab1() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(590, 319);
		this.setContentPane(getJContentPane());
		this.setTitle("\u0422\u0435\u0441\u0442\u0443\u0432\u0430\u043D\u043D\u044F Math.random()");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{266, 246, 0};
			gbl_jContentPane.rowHeights = new int[]{169, 44, 24, 0};
			gbl_jContentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_diagram = new GridBagConstraints();
			gbc_diagram.fill = GridBagConstraints.BOTH;
			gbc_diagram.insets = new Insets(5, 5, 5, 5);
			gbc_diagram.gridx = 0;
			gbc_diagram.gridy = 0;
			jContentPane.add(getDiagram(), gbc_diagram);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.insets = new Insets(5, 5, 5, 5);
			gbc_jScrollPane.gridx = 1;
			gbc_jScrollPane.gridy = 0;
			jContentPane.add(getJScrollPane(), gbc_jScrollPane);
			GridBagConstraints gbc_jPanel = new GridBagConstraints();
			gbc_jPanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_jPanel.insets = new Insets(0, 170, 5, 170);
			gbc_jPanel.gridwidth = 2;
			gbc_jPanel.gridx = 0;
			gbc_jPanel.gridy = 1;
			jContentPane.add(getJPanel(), gbc_jPanel);
			GridBagConstraints gbc_jButtonHisto = new GridBagConstraints();
			gbc_jButtonHisto.fill = GridBagConstraints.VERTICAL;
			gbc_jButtonHisto.gridwidth = 2;
			gbc_jButtonHisto.gridx = 0;
			gbc_jButtonHisto.gridy = 2;
			jContentPane.add(getJButtonHisto(), gbc_jButtonHisto);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="10,2"
