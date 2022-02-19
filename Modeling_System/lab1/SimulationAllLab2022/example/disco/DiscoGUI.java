package example.disco;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import process.Dispatcher;
import rnd.Erlang;
import rnd.Norm;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

public class DiscoGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jContentPane = null;
	ChooseRandom chooseRandomWay = null;
	ChooseRandom chooseRandomDance = null;
	Diagram diagram = null;
	JButton jButtonStartExperiment = null;
	JPanel jPanelWayRnd = null;
	JPanel jPanelDanceRnd = null;
	ChooseData chooseDataNumberOfClients = null;
	ChooseData chooseDataOpenTime = null;
	ChooseData chooseDataDancingTime = null;

	public int getNumberOfClient() {
		return getChooseDataNumberOfClients().getInt();
	}

	/**
	 * This method initializes chooseRandomAdvertising
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomWay() {
		if (chooseRandomWay == null) {
			chooseRandomWay = new ChooseRandom();
			chooseRandomWay.setTitle(" ");
			chooseRandomWay.setRandom(new Erlang(1D, 2));
		}
		return chooseRandomWay;
	}

	/**
	 * This method initializes chooseRandomContact
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomDance() {
		if (chooseRandomDance == null) {
			chooseRandomDance = new ChooseRandom();
			chooseRandomDance.setTitle(" ");
			chooseRandomDance.setRandom(new Norm(2, 0.4));
		}
		return chooseRandomDance;
	}

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	public Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setBounds(new Rectangle(244, 13, 292, 197));
			diagram.setVerticalMaxText("100");
			diagram.setPainterColor(java.awt.Color.magenta);
			diagram.setGridByX(5);
			diagram.setTitleText("Кількість танцюючих");
			diagram.setHorizontalMaxText("5");
		}
		return diagram;
	}

	/**
	 * This method initializes jButtonStartExperiment
	 * 
	 * @return javax.swing.JButton
	 */
	JButton getJButtonStartExperiment() {
		if (jButtonStartExperiment == null) {
			jButtonStartExperiment = new JButton();
			jButtonStartExperiment.setBounds(new Rectangle(333, 234, 83, 26));
			jButtonStartExperiment.setText("Старт");
			jButtonStartExperiment
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							onClickButtonStartExperiment();
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
	JPanel getJPanelIdvertisingRnd() {
		if (jPanelWayRnd == null) {
			jPanelWayRnd = new JPanel();
			jPanelWayRnd.setLayout(new CardLayout());
			jPanelWayRnd.setBounds(new Rectangle(14, 63, 215, 70));
			jPanelWayRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Тривалість шляху до дискотеки",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									null, null));
			jPanelWayRnd.add(getChooseRandomWay(), getChooseRandomWay()
					.getName());
		}
		return jPanelWayRnd;
	}

	/**
	 * This method initializes jPanelContactRnd
	 * 
	 * @return javax.swing.JPanel
	 */
	JPanel getJPanelContactRnd() {
		if (jPanelDanceRnd == null) {
			jPanelDanceRnd = new JPanel();
			jPanelDanceRnd.setLayout(new CardLayout());
			jPanelDanceRnd.setBounds(new Rectangle(17, 142, 210, 70));
			jPanelDanceRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Час перебування на дискотеці",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									null, null));
			jPanelDanceRnd.add(getChooseRandomDance(), getChooseRandomDance()
					.getName());
		}
		return jPanelDanceRnd;
	}

	/**
	 * This method initializes chooseDataNumberOfClients
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataNumberOfClients() {
		if (chooseDataNumberOfClients == null) {
			chooseDataNumberOfClients = new ChooseData();
			chooseDataNumberOfClients.setBounds(new Rectangle(17, 15, 213, 43));
			chooseDataNumberOfClients.setText("100");
			chooseDataNumberOfClients.setHorizontalAlignment(JTextField.CENTER);
			chooseDataNumberOfClients.setTitle("Кількість пар, бажаючих танцювати");
			chooseDataNumberOfClients.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					int n = Integer.parseInt(chooseDataNumberOfClients
							.getText());
					getDiagram().setVerticalMaxText(String.valueOf(2 * n));
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
	public ChooseData getChooseDataOpenTime() {
		if (chooseDataOpenTime == null) {
			chooseDataOpenTime = new ChooseData();
			chooseDataOpenTime.setBounds(new Rectangle(16, 222, 214, 43));
			chooseDataOpenTime.setHorizontalAlignment(JTextField.CENTER);
			chooseDataOpenTime.setTitle("Час до відкриття дискотеки");
			chooseDataOpenTime.setText("1");
			chooseDataOpenTime.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					try {
						double t1 = getChooseDataOpenTime().getDouble();
						double t2 = getChooseDataDancingTime().getDouble();
						String s = String.valueOf(t1 + t2 + 1);
						getDiagram().setHorizontalMaxText(s);
					} catch (Exception e2) {

					}
				}
			});

		}
		return chooseDataOpenTime;
	}

	/**
	 * This method initializes chooseDataFinishTime1
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataDancingTime() {
		if (chooseDataDancingTime == null) {
			chooseDataDancingTime = new ChooseData();
			chooseDataDancingTime.setBounds(new Rectangle(15, 270, 218, 46));
			chooseDataDancingTime.setHorizontalAlignment(JTextField.CENTER);
			chooseDataDancingTime.setTitle("Тривалість роботи дискотеки");
			chooseDataDancingTime.setText("3");
			chooseDataDancingTime.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					try {
						double t1 = getChooseDataOpenTime().getDouble();
						double t2 = getChooseDataDancingTime().getDouble();
						String s = String.valueOf(t1 + t2 + 1);
						getDiagram().setHorizontalMaxText(s);
					} catch (Exception e2) {

					}
				}
			});

		}
		return chooseDataDancingTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiscoGUI application = new DiscoGUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public DiscoGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(558, 372);
		this.setContentPane(getJContentPane());
		this.setTitle("Дослідження завантаження дискотеки");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getDiagram(), null);
			jContentPane.add(getJButtonStartExperiment(), null);
			jContentPane.add(getJPanelIdvertisingRnd(), null);
			jContentPane.add(getJPanelContactRnd(), null);
			jContentPane.add(getChooseDataNumberOfClients(), null);
			jContentPane.add(getChooseDataOpenTime(), null);
			jContentPane.add(getChooseDataDancingTime(), null);
		}
		return jContentPane;
	}

	void onClickButtonStartExperiment() {
		final Dispatcher dispatcher = new Dispatcher();
		dispatcher.setProtocolFileName("Console");
		DiscoModel model = createModel(dispatcher);
		getJButtonStartExperiment().setEnabled(false);
		resetPainters();
		model.startExperiment();
		new Thread() {
			public void run() {
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
	
	protected void resetPainters(){
		getDiagram().getPainter().placeToXY(0, 0);
	}

	protected DiscoModel createModel(Dispatcher d) {

		return new DiscoModel(this, d);
	}

	public JButton getJButtonStart() {
		return getJButtonStartExperiment();
	}
} // @jve:decl-index=0:visual-constraint="66,39"
