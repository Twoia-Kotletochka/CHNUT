package example.shans;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import rnd.Negexp;
import rnd.Norm;
import rnd.Randomable;
import rnd.Uniform;
import stat.Histo;
import widgets.ChooseRandom;
import widgets.Diagram;

public class ShansUI extends JFrame implements IShansVisualPart {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagram = null;

	private ChooseRandom chooseRandomBus = null;

	private JButton jButton = null;

	private JTextPane jTextPane = null;

	private JScrollPane jScrollPane = null;

	private ChooseRandom chooseRandomPas = null;

	private ChooseRandom chooseRandomShans = null;

	private JTextField jTextFieldPriz = null;

	private JTextField jTextFieldN = null;

	private Norm norm = null; // @jve:decl-index=0:visual-constraint="382,434"

	private Negexp negexp = null; // @jve:decl-index=0:visual-constraint="534,432"

	private Uniform uniform = null; // @jve:decl-index=0:visual-constraint="471,432"

	private Histo histo = null; // @jve:decl-index=0:visual-constraint="316,429"

	private JCheckBox jCheckBox = null;

	private Model model = null; // @jve:decl-index=0:visual-constraint="183,429"

	

	/**
	 * This is the default constructor
	 */
	public ShansUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(594, 450);
		this.setContentPane(getJContentPane());
		this.setTitle("Мистер Шанс");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new java.awt.Insets(4, 8, 5, 13);
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.ipadx = -97;
			gridBagConstraints8.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new java.awt.Insets(13, 8, 4, 13);
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.ipadx = -140;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 5;
			gridBagConstraints4.ipadx = 132;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new java.awt.Insets(4, 50, 14, 56);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.ipadx = 140;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new java.awt.Insets(19, 50, 8, 56);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 4;
			gridBagConstraints2.ipadx = 148;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new java.awt.Insets(9, 50, 21, 56);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(5, 8, 4, 13);
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.ipadx = -59;
			gridBagConstraints1.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getChooseRandomShans(), null);
			jContentPane.add(getJTextFieldPriz(), null);
			jContentPane.add(getJTextFieldN(), null);
			jContentPane.add(getDiagram(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getChooseRandomBus(), null);
			jContentPane.add(getChooseRandomPas(), null);
			jContentPane.add(getJCheckBox(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setBounds(new java.awt.Rectangle(18, 13, 285, 189));
			diagram.setTitleText("Розподіл обсягу прибутку");
		}
		return diagram;
	}

	/**
	 * This method initializes chooseRandom
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomBus() {
		if (chooseRandomBus == null) {
			chooseRandomBus = new ChooseRandom();
			chooseRandomBus
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									null,
									"Час до появи автобуса",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									null, null));
			chooseRandomBus.setBounds(new java.awt.Rectangle(322, 13, 245, 57));
			chooseRandomBus.setRandom(getNorm());
		}
		return chooseRandomBus;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jButton.setBounds(new java.awt.Rectangle(363,356,160,39));
			jButton.setText("Запуск моделі");

			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					run();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setFont(new java.awt.Font("Lucida Console",
					java.awt.Font.PLAIN, 12));
		}
		return jTextPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new java.awt.Rectangle(16,210,285,187));
			jScrollPane.setViewportView(getJTextPane());
			jScrollPane
					.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes chooseRandom1
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomPas() {
		if (chooseRandomPas == null) {
			chooseRandomPas = new ChooseRandom();
			chooseRandomPas.setBounds(new java.awt.Rectangle(319, 77, 252, 57));
			chooseRandomPas.setRandom(getNegexp());
			chooseRandomPas
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									null,
									"Інтервал появи пасажирів",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(51, 51, 51)));
		}
		return chooseRandomPas;
	}

	/**
	 * This method initializes chooseRandom2
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomShans() {
		if (chooseRandomShans == null) {
			chooseRandomShans = new ChooseRandom();
			chooseRandomShans.setBounds(new java.awt.Rectangle(319, 145, 252,
					57));
			chooseRandomShans.setRandom(getUniform());
			chooseRandomShans
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									null,
									"Час відповіді автомата",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(51, 51, 51)));
		}
		return chooseRandomShans;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPriz() {
		if (jTextFieldPriz == null) {
			jTextFieldPriz = new JTextField();
			jTextFieldPriz
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									null,
									"Розмір виграшу",
									javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(51, 51, 51)));
			jTextFieldPriz.setText("10");
			jTextFieldPriz
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldPriz.setBounds(new Rectangle(352, 213, 192, 45));
			jTextFieldPriz.setFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 14));
		}
		return jTextFieldPriz;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldN() {
		if (jTextFieldN == null) {
			jTextFieldN = new JTextField();
			jTextFieldN.setBorder(javax.swing.BorderFactory.createTitledBorder(
					null, "Кількість запусків моделі",
					javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					new java.awt.Color(51, 51, 51)));
			jTextFieldN.setText("500");
			jTextFieldN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldN.setBounds(new Rectangle(352, 272, 192, 45));
			jTextFieldN.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD,
					14));
		}
		return jTextFieldN;
	}

	/**
	 * This method initializes norm
	 * 
	 * @return rnd.Norm
	 */
	private Norm getNorm() {
		if (norm == null) {
			norm = new Norm();
			norm.setM(300.0D);
			norm.setSigma(50.0D);
			norm.setNextAsRound(true);
		}
		return norm;
	}

	/**
	 * This method initializes negexp
	 * 
	 * @return rnd.Negexp
	 */
	private Negexp getNegexp() {
		if (negexp == null) {
			negexp = new Negexp();
			negexp.setM(60.0D);
			negexp.setNextAsRound(true);
		}
		return negexp;
	}

	/**
	 * This method initializes uniform
	 * 
	 * @return rnd.Uniform
	 */
	private Uniform getUniform() {
		if (uniform == null) {
			uniform = new Uniform();
			uniform.setMax(20.0D);
			uniform.setNextAsRound(true);
			uniform.setMin(10.0D);
		}
		return uniform;
	}

	/**
	 * This method initializes histo1
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
	 * This method initializes jCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setText("Протокол работи на консоль");
			jCheckBox.setSelected(false);
			jCheckBox.setBounds(new java.awt.Rectangle(343,324,201,21));
			jCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); 
					if (getJCheckBox().isSelected()) {
						getModel().getDispatcher().setProtocolFileName(
								"Console");
					} else {
						getModel().getDispatcher().setProtocolFileName("");
					}
				}
			});
		}
		return jCheckBox;
	}

	/**
	 * This method initializes model
	 * 
	 * @return example.shans.Model
	 */
	private Model getModel() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}


	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		ShansUI application = new ShansUI();
		application.setVisible(true);
	}

	public void run() {
		getJButton().setEnabled(false);
		int n = Integer.parseInt(getJTextFieldN().getText());
		getModel().setUi(this);
		getHisto().init();
		getDiagram().clear();
		for (int i = 0; i < n; i++) {
			getModel().runModel();
			try {
				getModel().getDispatcher().getThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getHisto().add(getModel().getProfit());
			if (i==50)histo.showRelFrec(getDiagram(), Color.MAGENTA, 0.9, 0.05, true);
			if(i>20 & i%50==0){
				getDiagram().clear();
				histo.showRelFrec(getDiagram(), Color.MAGENTA, 0.9, 0.05, false);
			}

		}
		getDiagram().clear();
		histo.showRelFrec(getDiagram(), Color.MAGENTA, 0.9, 0.05, false);
		getJTextPane().setText(histo.toString());
		getJTextPane().select(0,0);
		getJButton().setEnabled(true);
	}

	public Randomable getBusRandom() {
		
		return getChooseRandomBus().getRandom();
	}

	public Randomable getPassengerRandom() {
		
		return getChooseRandomPas().getRandom();
	}

	public Randomable getSlotRandom() {
		return getChooseRandomShans().getRandom();
	}

	public double getPrizeSize() {
		return Double.parseDouble(getJTextFieldPriz().getText());
	}

} // @jve:decl-index=0:visual-constraint="17,-45"
