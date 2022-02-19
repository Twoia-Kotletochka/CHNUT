package example.swich;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import rnd.Erlang;
import rnd.Negexp;
import rnd.Randomable;
import stat.StatTables;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

public class SwitchUI extends JFrame implements ITestSwitchUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private ChooseRandom chooseRandomFileStream = null;

	private ChooseRandom chooseRandomFileSize = null;

	private Diagram diagram1 = null;

	private JButton jButtonStart = null;

	private TestSwitchModel model = new TestSwitchModel();

	private Diagram diagram2 = null;

	private Erlang erlangForFileSize = null; // @jve:decl-index=0:visual-constraint=""

	private JPanel jPanelParameters = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JPanel jPanel = null;

	private JLabel jLabel2 = null;

	private JTextField jTextFieldNumberOfPort = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextPackageSize = null;

	private JPanel jPanel2 = null;

	private JLabel jLabel4 = null;

	private JTextField jTextFieldSwitchCapacity = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JLabel jLabel5 = null;

	private JTextField jTextFieldFinishTime = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JLabel jLabel6 = null;

	private JTextField jTextFieldBufferSize = null;

	private JPanel jPanelChargeCoeff = null;

	private JLabel jLabelChargeCoeff = null;

	private JPanel jPanelCurrentTime = null;

	private JTextField jTextFieldCurrentTime = null;

	/**
	 * This method initializes chooseRandomTransGen
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomFileStream() {
		if (chooseRandomFileStream == null) {
			Negexp negexp = new Negexp();
			negexp.setM(0.5D);
			chooseRandomFileStream = new ChooseRandom();
			chooseRandomFileStream.setTitle("Випадковий інтервал між файлами (мін)");
			chooseRandomFileStream.setPreferredSize(new java.awt.Dimension(293,
					35));
			chooseRandomFileStream.setRandom(negexp);
			chooseRandomFileStream.setBounds(new Rectangle(11, 26, 239, 52));
		}
		return chooseRandomFileStream;
	}

	/**
	 * This method initializes chooseRandomGetPut
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomFileSize() {
		if (chooseRandomFileSize == null) {
			chooseRandomFileSize = new ChooseRandom();
			chooseRandomFileSize.setTitle("Випадковий розмір файлів (Мбайт)");
			chooseRandomFileSize.setBounds(new Rectangle(12, 89, 239, 52));
			chooseRandomFileSize.setRandom(getErlangForFileSize());
		}
		return chooseRandomFileSize;
	}

	/**
	 * This method initializes diagram1
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram1() {
		if (diagram1 == null) {
			diagram1 = new Diagram();
			diagram1.setTitleText("Заповнення буферу (Мбайт)");
			diagram1.setHorizontalMaxText("600");
			diagram1.setVerticalMaxText("50");
			diagram1.setVerticalMinEnabled(false);
			diagram1.setHorizontalMaxEnabled(false);
			diagram1.setHorizontalMinEnabled(false);
			diagram1.setVerticalMaxEnabled(false);
			diagram1.setPainterColor(java.awt.Color.magenta);

		}
		return diagram1;
	}

	/**
	 * This method initializes jButtonStart
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("Старт");
			jButtonStart.setBounds(new Rectangle(86, 58, 110, 27));
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					start();
				}
			});
		}
		return jButtonStart;
	}

	protected void start() {
		model.setUi(this);
		Thread t = new Thread() {
			public void run() {
//				model.getDispatcher().addChangeTimeListener(
//						new ChangeTimeListener(){
//
//							public void onChangeTime(ChangeTimeEvent evt) {
//								getJTextFieldCurrentTime().setText(String.valueOf(StatTables.roundTo(evt.getCurrentTime(),1)));
//								
//							}
//							
//						}
//						);
				getDiagram1().clear();
				getJButtonStart().setEnabled(false);
//				getJTextFieldFinishTime().setVisible(false);
//				getJTextFieldCurrentTime().setVisible(true);
				getDiagram1().setTitleText("Заповнення буферу (Мбайт)");
				jLabelChargeCoeff.setText(chargeCoeff());
				double max = Double.valueOf(getDiagram2()
						.getHorizontalMaxText());
				model.getHisto().initFromTo(0, max, 25);
				model.start();
				try {
					model.getDispatcher().getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getJButtonStart().setEnabled(true);
//				getJTextFieldFinishTime().setVisible(true);
//				getJTextFieldCurrentTime().setVisible(false);
				float fillCoeff=(float)StatTables.roundTo(model.getHisto().average()/getBufferSize(),0.0001);
				getDiagram1().setTitleText("Коефіцієнт заповнення буферу: "+fillCoeff);
				double[] p = new double[model.getHisto().getBorders().length + 1];
				double[] a = new double[model.getHisto().getBorders().length + 1];
				p[0] = 0;
				for (int i = 1; i < p.length; i++) {
					p[i] = model.getHisto().getBorders()[i - 1];

				}
				a[0] = 1;
				for (int i = 1; i < a.length; i++) {
					a[i] = 1 - model.getHisto().accumFrequency()[i - 1];

				}

				getDiagram2().drawDependency(p, a, Color.blue, false);

			}
		};
		t.start();
	}

	/**
	 * This method initializes diagram2
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram2() {
		if (diagram2 == null) {
			diagram2 = new Diagram();
			diagram2
					.setTitleText("Вирогідність втрати пакету від розміру буфера");
			diagram2.setVerticalMaxText("0.25");
			diagram2.setHorizontalMaxText("50");
			diagram2.setHorizontalMaxEnabled(false);
			diagram2.setHorizontalMinEnabled(false);
			diagram2.setVerticalMinEnabled(false);
			diagram2.setVerticalMaxEnabled(false);
			diagram2.setPainterColor(java.awt.Color.magenta);
		}
		return diagram2;
	}

	/**
	 * This method initializes erlangForFileSize
	 * 
	 * @return rnd.Erlang
	 */
	private Erlang getErlangForFileSize() {
		if (erlangForFileSize == null) {
			erlangForFileSize = new Erlang();
			erlangForFileSize.setK(3);
			erlangForFileSize.setM(10.0D);
		}
		return erlangForFileSize;
	}

	/**
	 * This method initializes jPanelParameters
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelParameters() {
		if (jPanelParameters == null) {
			jPanelParameters = new JPanel();
			jPanelParameters
					.setBorder(javax.swing.BorderFactory
							.createCompoundBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									javax.swing.BorderFactory
											.createTitledBorder(
													null,
													"Параметри потоку файлів на порт",
													javax.swing.border.TitledBorder.CENTER,
													javax.swing.border.TitledBorder.DEFAULT_POSITION,
													new java.awt.Font("Dialog",
															java.awt.Font.BOLD,
															12),
													new java.awt.Color(51, 51,
															51))));
			jPanelParameters.setLayout(null);
			jPanelParameters.add(getChooseRandomFileStream(), null);
			jPanelParameters.add(getChooseRandomFileSize(), null);
			jPanelParameters.add(getJPanel1(), null);
		}
		return jPanelParameters;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setMaximumSize(new java.awt.Dimension(195, 25));
			jLabel2.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel2.setText("Кількість портів:");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel.setBounds(new java.awt.Rectangle(13, 26, 239, 33));
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextFieldNumberOfPort(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNumberOfPort() {
		if (jTextFieldNumberOfPort == null) {
			jTextFieldNumberOfPort = new JTextField();
			jTextFieldNumberOfPort.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldNumberOfPort
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldNumberOfPort.setMaximumSize(new java.awt.Dimension(100,
					21));
			jTextFieldNumberOfPort.setText("8");
			jTextFieldNumberOfPort
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							jLabelChargeCoeff.setText(chargeCoeff());
						}
					});
		}
		return jTextFieldNumberOfPort;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel3.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel3.setText("Розмір пакету (байтів):");
			jLabel3.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS));
			jPanel1.setBounds(new java.awt.Rectangle(11, 148, 239, 33));
			jPanel1.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJTextPackageSize(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextPackageSize
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextPackageSize() {
		if (jTextPackageSize == null) {
			jTextPackageSize = new JTextField();
			jTextPackageSize.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextPackageSize.setText("64");
			jTextPackageSize
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextPackageSize.setMaximumSize(new java.awt.Dimension(100, 21));
		}
		return jTextPackageSize;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel4.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel4.setText("Швидкість(МБіт/сек):");
			jLabel4.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.X_AXIS));
			jPanel2.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel2.setBounds(new java.awt.Rectangle(13, 68, 239, 33));
			jPanel2.add(jLabel4, null);
			jPanel2.add(getJTextFieldSwitchCapacity(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSwitchCapacity() {
		if (jTextFieldSwitchCapacity == null) {
			jTextFieldSwitchCapacity = new JTextField();
			jTextFieldSwitchCapacity.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldSwitchCapacity.setText("100");
			jTextFieldSwitchCapacity
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldSwitchCapacity.setMaximumSize(new java.awt.Dimension(100,
					21));
			jTextFieldSwitchCapacity
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							jLabelChargeCoeff.setText(chargeCoeff());
						}
					});
		}
		return jTextFieldSwitchCapacity;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBounds(0, 151, 259, 96);
			jPanel3.setLayout(null);
			jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(
					new javax.swing.border.SoftBevelBorder(
							javax.swing.border.SoftBevelBorder.RAISED),
					javax.swing.BorderFactory.createTitledBorder(null,
							"Параметри моделювання",
							javax.swing.border.TitledBorder.CENTER,
							javax.swing.border.TitledBorder.DEFAULT_POSITION,
							null, null)));
			jPanel3.add(getJPanel4(), null);
			jPanel3.add(getJButtonStart(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel5.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel5.setText("Час роботи (мін):");
			jLabel5.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel4 = new JPanel();
			jPanel4.setLayout(new BoxLayout(getJPanel4(), BoxLayout.X_AXIS));
			jPanel4.setBounds(new Rectangle(11, 18, 237, 30));
			jPanel4.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel4.add(jLabel5, null);
			jPanel4.add(getJPanelCurrentTime(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldFinishTime() {
		if (jTextFieldFinishTime == null) {
			jTextFieldFinishTime = new JTextField();
			jTextFieldFinishTime.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldFinishTime.setText("600");
			jTextFieldFinishTime
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldFinishTime.setName("jTextFieldFinishTime");
			jTextFieldFinishTime
					.setMaximumSize(new java.awt.Dimension(100, 21));
			jTextFieldFinishTime
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							getDiagram1().setHorizontalMaxText(
									getJTextFieldFinishTime().getText());
						}
					});
		}
		return jTextFieldFinishTime;
	}

	/**
	 * This method initializes jPanel5
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5
					.setBorder(javax.swing.BorderFactory
							.createCompoundBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									javax.swing.BorderFactory
											.createTitledBorder(
													null,
													"Параметри комутатора",
													javax.swing.border.TitledBorder.CENTER,
													javax.swing.border.TitledBorder.DEFAULT_POSITION,
													new java.awt.Font("Dialog",
															java.awt.Font.BOLD,
															12),
													new java.awt.Color(51, 51,
															51))));
			jPanel5.add(getJPanel(), null);
			jPanel5.add(getJPanel2(), null);
			jPanel5.add(getJPanel6(), null);
			jPanel5.add(getJPanel3());
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
			jLabel6.setPreferredSize(new Dimension(95, 20));
			jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel6.setText("Розмір буферу (MБайт):");
			jLabel6.setMaximumSize(new Dimension(195, 25));
			jPanel6 = new JPanel();
			jPanel6.setLayout(new BoxLayout(getJPanel6(), BoxLayout.X_AXIS));
			jPanel6.setBounds(new java.awt.Rectangle(13, 110, 239, 30));
			jPanel6.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jPanel6.add(jLabel6, null);
			jPanel6.add(getJTextFieldBufferSize(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBufferSize() {
		if (jTextFieldBufferSize == null) {
			jTextFieldBufferSize = new JTextField();
			jTextFieldBufferSize.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			jTextFieldBufferSize.setText("50");
			jTextFieldBufferSize.setHorizontalAlignment(JTextField.CENTER);
			jTextFieldBufferSize.setMaximumSize(new Dimension(100, 21));
			jTextFieldBufferSize
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							getDiagram1().setVerticalMaxText(
									getJTextFieldBufferSize().getText());
							getDiagram1().setVerticalMaxText(
									getJTextFieldBufferSize().getText());
							getDiagram2().setHorizontalMaxText(
									getJTextFieldBufferSize().getText());
						}
					});
		}
		return jTextFieldBufferSize;
	}

	/**
	 * This method initializes jPanelChargeCoeff
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelChargeCoeff() {
		if (jPanelChargeCoeff == null) {
			jLabelChargeCoeff = new JLabel();
			jLabelChargeCoeff
					.setText("Коефіціент завантаження системи при таких налаштуваннях дорівнює 0.010666666666666666");
			jLabelChargeCoeff
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabelChargeCoeff.setName("jLabelChargeCoeff");
			jPanelChargeCoeff = new JPanel();
			jPanelChargeCoeff.setLayout(new CardLayout());
			jPanelChargeCoeff.setBorder(javax.swing.BorderFactory
					.createLineBorder(java.awt.Color.gray, 0));
			jPanelChargeCoeff.add(jLabelChargeCoeff, jLabelChargeCoeff
					.getName());
		}
		return jPanelChargeCoeff;
	}

	/**
	 * This method initializes jPanelCurrentTime	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCurrentTime() {
		if (jPanelCurrentTime == null) {
			jPanelCurrentTime = new JPanel();
			jPanelCurrentTime.setLayout(new CardLayout());
			jPanelCurrentTime.setMaximumSize(new java.awt.Dimension(120,21));
			jPanelCurrentTime.add(getJTextFieldFinishTime(), getJTextFieldFinishTime().getName());
			jPanelCurrentTime.add(getJTextFieldCurrentTime(), getJTextFieldCurrentTime().getName());
		}
		return jPanelCurrentTime;
	}

	/**
	 * This method initializes jTextFieldCurrentTime	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldCurrentTime() {
		if (jTextFieldCurrentTime == null) {
			jTextFieldCurrentTime = new JTextField();
			jTextFieldCurrentTime.setName("jTextFieldCurrentTime");
			jTextFieldCurrentTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldCurrentTime.setVisible(false);
		}
		return jTextFieldCurrentTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwitchUI application = new SwitchUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public SwitchUI() {
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
		this.setSize(606, 574);
		this.setContentPane(getJContentPane());
		this.setTitle("Дослідження пропускної спроможності комутатора");

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
			gbl_jContentPane.columnWidths = new int[]{264, 317, 0};
			gbl_jContentPane.rowHeights = new int[]{0, 200, 88, 170, 0, 21, 0};
			gbl_jContentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_jPanelParameters = new GridBagConstraints();
			gbc_jPanelParameters.fill = GridBagConstraints.BOTH;
			gbc_jPanelParameters.insets = new Insets(0, 0, 5, 5);
			gbc_jPanelParameters.gridx = 0;
			gbc_jPanelParameters.gridy = 1;
			jContentPane.add(getJPanelParameters(), gbc_jPanelParameters);
			GridBagConstraints gbc_diagram1 = new GridBagConstraints();
			gbc_diagram1.gridheight = 2;
			gbc_diagram1.fill = GridBagConstraints.BOTH;
			gbc_diagram1.insets = new Insets(0, 0, 5, 0);
			gbc_diagram1.gridx = 1;
			gbc_diagram1.gridy = 0;
			jContentPane.add(getDiagram1(), gbc_diagram1);
			GridBagConstraints gbc_diagram2 = new GridBagConstraints();
			gbc_diagram2.gridheight = 3;
			gbc_diagram2.fill = GridBagConstraints.BOTH;
			gbc_diagram2.insets = new Insets(0, 0, 5, 0);
			gbc_diagram2.gridx = 1;
			gbc_diagram2.gridy = 2;
			jContentPane.add(getDiagram2(), gbc_diagram2);
			GridBagConstraints gbc_jPanel5 = new GridBagConstraints();
			gbc_jPanel5.fill = GridBagConstraints.BOTH;
			gbc_jPanel5.insets = new Insets(0, 0, 5, 5);
			gbc_jPanel5.gridheight = 2;
			gbc_jPanel5.gridx = 0;
			gbc_jPanel5.gridy = 2;
			jContentPane.add(getJPanel5(), gbc_jPanel5);
			GridBagConstraints gbc_jPanelChargeCoeff = new GridBagConstraints();
			gbc_jPanelChargeCoeff.fill = GridBagConstraints.BOTH;
			gbc_jPanelChargeCoeff.gridwidth = 2;
			gbc_jPanelChargeCoeff.gridx = 0;
			gbc_jPanelChargeCoeff.gridy = 5;
			jContentPane.add(getJPanelChargeCoeff(), gbc_jPanelChargeCoeff);
		}
		return jContentPane;
	}

	public Painter getPainter1() {
		return getDiagram1().getPainter();
	}

	public double getFinishTime() {

		return Double.valueOf(getJTextFieldFinishTime().getText());
	}

	public Randomable getRandomFileStream() {
		return getChooseRandomFileStream().getRandom();
	}

	public Randomable getRandomFileSize() {
		return getChooseRandomFileSize().getRandom();
	}

	public double getSwitchCapacity() {
		try {
			return Double.valueOf(getJTextFieldSwitchCapacity().getText());
		} catch (Exception e) {
			return 0;
		}	
	}

	public int getNumberOfPort() {
		try {
			return Integer.valueOf(getJTextFieldNumberOfPort().getText());
		} catch (Exception e) {
			return 0;
		}
	}

	public int getPackageSize() {
		try {
			return Integer.valueOf(getJTextPackageSize().getText());
		} catch (Exception e) {
			return 0;
		}

	}
public double getBufferSize() {
	try {
		return Double.valueOf(getJTextFieldBufferSize().getText());
	} catch (Exception e) {
		return 0;
	}
	
}
	private String chargeCoeff() {
		try {
			double fileSizeInMBit = getChooseRandomFileSize().getRandom()
					.average() * 8;
			double numberFilePerSecond = 1 / getChooseRandomFileStream()
					.getRandom().average() / 60;
			double coeff = getNumberOfPort() * numberFilePerSecond
					* fileSizeInMBit / getSwitchCapacity();
			return "Коефіціент завантаження системи при таких налаштуваннях дорівнює "
					+ String.valueOf(coeff);
		} catch (Exception e) {
			return "";
		}

	}

} // @jve:decl-index=0:visual-constraint="5,20"
