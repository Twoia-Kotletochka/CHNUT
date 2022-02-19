package example.disco;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import rnd.Erlang;
import rnd.Norm;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

public class NoOOPDiscoGUI extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ChooseRandom chooseRandomWay = null;
	private ChooseRandom chooseRandomDance = null;
	private Diagram diagram = null;
	private JButton jButtonStartExperiment = null;
	private JPanel jPanelWayRnd = null;
	private JPanel jPanelDanceRnd = null;
	private ChooseData chooseDataNumberOfClients = null;
	private ChooseData chooseDataOpenTime = null;
	private ChooseData chooseDataDancingTime = null;
	

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
			chooseRandomWay.setRandom(new Erlang(1D,2));
		}
		return chooseRandomWay;
	}

	
	public ChooseRandom getChooseRandomDance() {
		if (chooseRandomDance == null) {
			chooseRandomDance = new ChooseRandom();
			chooseRandomDance.setRandom(new Norm(2,0.4));
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
	private JButton getJButtonStartExperiment() {
		if (jButtonStartExperiment == null) {
			jButtonStartExperiment = new JButton();
			jButtonStartExperiment.setBounds(new Rectangle(333, 234, 83, 26));
			jButtonStartExperiment.setText("Старт");
			jButtonStartExperiment.addActionListener(new java.awt.event.ActionListener() {
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
	private JPanel getJPanelIdvertisingRnd() {
		if (jPanelWayRnd == null) {
			jPanelWayRnd = new JPanel();
			jPanelWayRnd.setLayout(new CardLayout());
			jPanelWayRnd.setBounds(new Rectangle(14, 73, 215, 60));
			jPanelWayRnd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.SoftBevelBorder.RAISED), "Тривалість шляху до дискотеки", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			jPanelWayRnd.add(getChooseRandomWay(), getChooseRandomWay().getName());
		}
		return jPanelWayRnd;
	}

	/**
	 * This method initializes jPanelContactRnd	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelContactRnd() {
		if (jPanelDanceRnd == null) {
			jPanelDanceRnd = new JPanel();
			jPanelDanceRnd.setLayout(new CardLayout());
			jPanelDanceRnd.setBounds(new Rectangle(17, 149, 210, 63));
			jPanelDanceRnd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.SoftBevelBorder.RAISED), "Час перебування на дискотеці", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jPanelDanceRnd.add(getChooseRandomDance(), getChooseRandomDance().getName());
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
			chooseDataNumberOfClients.setTitle("Кількість бажаючих танцювати");
			chooseDataNumberOfClients.addCaretListener(new CaretListener() {
				
				public void caretUpdate(CaretEvent e) {
						getDiagram().setVerticalMaxText(chooseDataNumberOfClients.getText());
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
						double t1=getChooseDataOpenTime().getDouble();
						double t2=getChooseDataDancingTime().getDouble();
						String s= String.valueOf(t1+t2+1);
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
						double t1=getChooseDataOpenTime().getDouble();
						double t2=getChooseDataDancingTime().getDouble();
						String s= String.valueOf(t1+t2+1);
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
		NoOOPDiscoGUI application = new NoOOPDiscoGUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public NoOOPDiscoGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(558, 351);
		this.setContentPane(getJContentPane());
		this.setTitle("Дослідження завантаження дискотеки");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
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
	private void onClickButtonStartExperiment(){
		
		getDiagram().getPainter().placeToXY(0,0);
		NoOOP nooop = new NoOOP(this);
		nooop.start();
	}
}  //  @jve:decl-index=0:visual-constraint="66,39"
