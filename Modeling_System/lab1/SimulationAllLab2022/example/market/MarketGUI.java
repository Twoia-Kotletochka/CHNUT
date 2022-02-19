package example.market;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import process.Dispatcher;
import rnd.Erlang;
import rnd.Negexp;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

public class MarketGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ChooseRandom chooseRandomAdvertising = null;
	private ChooseRandom chooseRandomContact = null;
	private Diagram diagram = null;
	private JButton jButtonStartExperiment = null;
	private JPanel jPanelAdvertisingRnd = null;
	private JPanel jPanelContactRnd = null;
	private ChooseData chooseDataNumberOfClients = null;
	private ChooseData chooseDataFinishTime = null;

	/**
	 * This method initializes chooseRandomAdvertising
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomAdvertising() {
		if (chooseRandomAdvertising == null) {
			chooseRandomAdvertising = new ChooseRandom();
			chooseRandomAdvertising.setTitle(" ");
			chooseRandomAdvertising.setRandom(new Erlang(5D, 2));
		}
		return chooseRandomAdvertising;
	}

	/**
	 * This method initializes chooseRandomContact
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomContact() {
		if (chooseRandomContact == null) {
			chooseRandomContact = new ChooseRandom();
			chooseRandomContact.setTitle(" ");
			chooseRandomContact.setRandom(new Negexp(1));
		}
		return chooseRandomContact;
	}

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	public Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setVerticalMaxText("500");
			diagram.setPainterColor(java.awt.Color.magenta);
			diagram.setGridByX(6);
			diagram.setTitleText("Кількість залучених");
			diagram.setHorizontalMaxText("12");
		}
		return diagram;
	}

	/**
	 * This method initializes jButtonStartExperiment
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStartExperiment() {
		if (jButtonStartExperiment == null) {
			jButtonStartExperiment = new JButton();
			jButtonStartExperiment.setText("Старт");
			jButtonStartExperiment
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							onClickButtonStart();
						}
					});
		}
		return jButtonStartExperiment;
	}

	/**
	 * This method initializes jPanelIdvertisingRnd
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelIdvertisingRnd() {
		if (jPanelAdvertisingRnd == null) {
			jPanelAdvertisingRnd = new JPanel();
			jPanelAdvertisingRnd.setLayout(new CardLayout());
			jPanelAdvertisingRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Вплив реклами",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.PLAIN, 12),
									new java.awt.Color(51, 51, 51)));
			jPanelAdvertisingRnd.add(getChooseRandomAdvertising(),
					getChooseRandomAdvertising().getName());
		}
		return jPanelAdvertisingRnd;
	}

	/**
	 * This method initializes jPanelContactRnd
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelContactRnd() {
		if (jPanelContactRnd == null) {
			jPanelContactRnd = new JPanel();
			jPanelContactRnd.setLayout(new CardLayout());
			jPanelContactRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Вплив контактів",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.PLAIN, 12),
									new java.awt.Color(51, 51, 51)));			jPanelContactRnd.add(getChooseRandomContact(),
					getChooseRandomContact().getName());
		}
		return jPanelContactRnd;
	}

	/**
	 * This method initializes chooseDataNumberOfClients
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataNumberOfClients() {
		if (chooseDataNumberOfClients == null) {
			chooseDataNumberOfClients = new ChooseData();
			chooseDataNumberOfClients.setText("500");
			chooseDataNumberOfClients.setHorizontalAlignment(JTextField.CENTER);
			chooseDataNumberOfClients.setTitle("Кількість клієнтів");
			chooseDataNumberOfClients.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					if (getDiagram().getVerticalMaxText() != getChooseDataNumberOfClients()
							.getText())
						getDiagram().setVerticalMaxText(
								getChooseDataNumberOfClients().getText());
				}
			});
		}
		return chooseDataNumberOfClients;
	}

	/**
	 * This method initializes chooseDataFinishTime
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataFinishTime() {
		if (chooseDataFinishTime == null) {
			chooseDataFinishTime = new ChooseData();
			chooseDataFinishTime.setHorizontalAlignment(JTextField.CENTER);
			chooseDataFinishTime.setTitle("Час моделювання");
			chooseDataFinishTime.setText("12");
			chooseDataFinishTime.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					if (getDiagram().getHorizontalMaxText() != getChooseDataFinishTime()
							.getText())
						getDiagram().setHorizontalMaxText(
								getChooseDataFinishTime().getText());
				}
			});

		}
		return chooseDataFinishTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarketGUI application = new MarketGUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public MarketGUI() {
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
		this.setSize(558, 366);
		this.setContentPane(getJContentPane());
		this.setTitle("Маркетингове дослідження");
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
			gbl_jContentPane.columnWidths = new int[]{215, 292, 0};
			gbl_jContentPane.rowHeights = new int[]{45, 68, 69, 0, 0, 0, 43, 0};
			gbl_jContentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_chooseDataNumberOfClients = new GridBagConstraints();
			gbc_chooseDataNumberOfClients.fill = GridBagConstraints.HORIZONTAL;
			gbc_chooseDataNumberOfClients.insets = new Insets(0, 0, 5, 5);
			gbc_chooseDataNumberOfClients.gridx = 0;
			gbc_chooseDataNumberOfClients.gridy = 0;
			jContentPane.add(getChooseDataNumberOfClients(), gbc_chooseDataNumberOfClients);
			GridBagConstraints gbc_diagram = new GridBagConstraints();
			gbc_diagram.fill = GridBagConstraints.BOTH;
			gbc_diagram.insets = new Insets(0, 0, 5, 0);
			gbc_diagram.gridheight = 6;
			gbc_diagram.gridx = 1;
			gbc_diagram.gridy = 0;
			jContentPane.add(getDiagram(), gbc_diagram);
			GridBagConstraints gbc_jPanelAdvertisingRnd = new GridBagConstraints();
			gbc_jPanelAdvertisingRnd.fill = GridBagConstraints.BOTH;
			gbc_jPanelAdvertisingRnd.insets = new Insets(0, 0, 5, 5);
			gbc_jPanelAdvertisingRnd.gridx = 0;
			gbc_jPanelAdvertisingRnd.gridy = 1;
			jContentPane.add(getJPanelIdvertisingRnd(), gbc_jPanelAdvertisingRnd);
			GridBagConstraints gbc_jPanelContactRnd = new GridBagConstraints();
			gbc_jPanelContactRnd.fill = GridBagConstraints.BOTH;
			gbc_jPanelContactRnd.insets = new Insets(0, 0, 5, 5);
			gbc_jPanelContactRnd.gridx = 0;
			gbc_jPanelContactRnd.gridy = 2;
			jContentPane.add(getJPanelContactRnd(), gbc_jPanelContactRnd);
			GridBagConstraints gbc_chooseDataFinishTime = new GridBagConstraints();
			gbc_chooseDataFinishTime.fill = GridBagConstraints.HORIZONTAL;
			gbc_chooseDataFinishTime.insets = new Insets(0, 0, 5, 5);
			gbc_chooseDataFinishTime.gridx = 0;
			gbc_chooseDataFinishTime.gridy = 3;
			jContentPane.add(getChooseDataFinishTime(), gbc_chooseDataFinishTime);
			GridBagConstraints gbc_jButtonStartExperiment = new GridBagConstraints();
			gbc_jButtonStartExperiment.gridx = 1;
			gbc_jButtonStartExperiment.gridy = 6;
			jContentPane.add(getJButtonStartExperiment(), gbc_jButtonStartExperiment);
		}
		return jContentPane;
	}

	private void onClickButtonStart() {
		getDiagram().getPainter().placeToXY(0, 0);
		final Dispatcher dispatcher = new Dispatcher();
		MarketModel model = new MarketModel(dispatcher, this);
		model.initForStart();
		getJButtonStartExperiment().setEnabled(false);
		dispatcher.start();
		new Thread(){
			public void run(){
				try {
					dispatcher.getThread().join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getJButtonStartExperiment().setEnabled(true);
			}
		}.start();

	}
} // @jve:decl-index=0:visual-constraint="66,39"
