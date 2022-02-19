package buldo2022;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

import process.Dispatcher;
import process.IModelFactory;
import rnd.Erlang;
import rnd.Norm;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.experiments.ExperimentManager;
import widgets.stat.StatisticsManager;
import widgets.trans.TransProcessManager;

public class BuldGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagramHeepSize = null;

	private Diagram diagramQueueToLoader = null;

	private Diagram diagramLorryOnRoad = null;

	private JButton jButtonStart = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanelTest = null;

	private JScrollPane jScrollPaneTz = null;

	private JPanel jPanelTransient = null;

	private TransProcessManager transProcessManager = null;

	private JPanel jPanelModelParameters = null;

	private Dispatcher dispatcher = null;

	private JCheckBox jCheckBox = null;

	private JPanel jPanelStat = null;

	private ChooseRandom rndBuldo = null;

	private ChooseRandom rndLoader = null;

	private ChooseData chooseDataNLorry = null;

	private ChooseData chooseDataBodySize = null;

	private ChooseRandom rndLorry = null;

	private ChooseData chooseDataHeapMaxSize = null;

	private ChooseData chooseDataFinishTime = null;

	private JTextPane jTextPane = null;
	private StatisticsManager statisticsManager;
	private JPanel jPanelRegres;

	public BuldGUI() throws HeadlessException {
		super();
		initialize();
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
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startTest();
				}
			});
		}
		return jButtonStart;
	}

	private void startTest() {
		getJButtonStart().setEnabled(false);
		getDiagramHeepSize().clear();
		getDiagramQueueToLoader().clear();
		getDiagramLorryOnRoad().clear();
		Dispatcher dispatcher = new Dispatcher();		
		dispatcher.addDispatcherFinishListener(
				()->getJButtonStart().setEnabled(true));
		IModelFactory factory = (d)-> new BuldModel(d, this);
		BuldModel model =(BuldModel) factory.createModel(dispatcher);
		model.initForTest();
		dispatcher.start();
	}

	


	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setName("");
			jTabbedPane.setVisible(true);
			jTabbedPane.setFont(new java.awt.Font("Courier New",
					java.awt.Font.PLAIN, 14));
			jTabbedPane.addTab("Tz", null, getJScrollPaneTz(), null);
			jTabbedPane.addTab("Test", null, getJPanelTest(), null);
			jTabbedPane.addTab("Stat", null, getJPanelStat(),
					"Статистичні характеристики");
			jTabbedPane.addTab("Regres", null, getJPanelRegres(), null);
			jTabbedPane.addTab("Transient", null, getJPanelTransient(), null);

		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanelTest
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelTest() {
		if (jPanelTest == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new java.awt.Insets(4, 8, 3, 2);
			gridBagConstraints13.gridy = 4;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new Insets(2, 3, 0, 4);
			gridBagConstraints12.gridy = 4;
			gridBagConstraints12.ipadx = 10;
			gridBagConstraints12.ipady = -1;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(1, 4, 1, 4);
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.ipadx = 183;
			gridBagConstraints11.ipady = -86;
			gridBagConstraints11.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints11.weightx = 1.0D;
			gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints11.gridwidth = 4;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(0, 3, 0, 4);
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.ipadx = 183;
			gridBagConstraints10.ipady = -86;
			gridBagConstraints10.weightx = 1.0D;
			gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints10.gridwidth = 4;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(4, 3, 0, 4);
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.ipadx = 183;
			gridBagConstraints8.ipady = -86;
			gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints8.weightx = 1.0D;
			gridBagConstraints8.weighty = 2.0D;
			gridBagConstraints8.gridwidth = 4;
			jPanelTest = new JPanel();
			jPanelTest.setLayout(new GridBagLayout());
			jPanelTest.add(getDiagramHeepSize(), gridBagConstraints8);
			jPanelTest.add(getDiagramQueueToLoader(), gridBagConstraints10);
			jPanelTest.add(getDiagramLorryOnRoad(), gridBagConstraints11);
			jPanelTest.add(getJButtonStart(), gridBagConstraints12);
			jPanelTest.add(getJCheckBox(), gridBagConstraints13);
			jPanelTest
			.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentShown(
						java.awt.event.ComponentEvent e) {
					// Штучно формуємо подію CaretUpdate,
					// щоб ооновити налаштування діаграм
					getChooseDataNLorry().select(0,0);
					getChooseDataFinishTime().select(0,0);
					getChooseDataHeapMaxSize().select(0,0);
				}
			});
		}
		return jPanelTest;
	}

	/**
	 * This method initializes jScrollPaneTz
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneTz() {
		if (jScrollPaneTz == null) {
			jScrollPaneTz = new JScrollPane();
			jScrollPaneTz.setName("jScrollPaneTz");
			jScrollPaneTz.setViewportView(getJTextPane());
			jScrollPaneTz
					.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPaneTz;
	}

	/**
	 * This method initializes jPanelTransient
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelTransient() {
		if (jPanelTransient == null) {
			jPanelTransient = new JPanel();
			jPanelTransient.setLayout(new CardLayout(0, 0));
			jPanelTransient.add(getTransProcessManager(), "name_34492577955736");

		}
		return jPanelTransient;
	}

	/**
	 * This method initializes transMonitorView
	 * 
	 * @return transProcess.TransMonitorView
	 */
	private TransProcessManager getTransProcessManager() {
		if (transProcessManager == null) {
			transProcessManager = new TransProcessManager();
			transProcessManager.setNumberOfInterval("20");
			transProcessManager.setTextInterval("5");
			transProcessManager.setFactory((d)-> new BuldModel(d, this));
//			transProcessManager.setFactory(new  IModelFactory() {
//				@Override
//				public Object createModel(Dispatcher d) {
//					return new BuldModel(d,BuldGUI.this);
//				}
//			});

		}
		return transProcessManager;
	}

	/**
	 * This method initializes jPanelModelParameters
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelModelParameters() {
		if (jPanelModelParameters == null) {
			jPanelModelParameters = new JPanel();
			jPanelModelParameters.setLayout(null);
			jPanelModelParameters
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									javax.swing.BorderFactory
											.createBevelBorder(javax.swing.border.BevelBorder.RAISED),
									"Параметри системи що досліджується",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(51, 51, 51)));
			jPanelModelParameters.setPreferredSize(new Dimension(262, 436));
			jPanelModelParameters.setMinimumSize(new Dimension(262, 436));
			jPanelModelParameters.add(getRndBuldo(), null);
			jPanelModelParameters.add(getRndLoader(), null);
			jPanelModelParameters.add(getChooseDataNLorry(), null);
			jPanelModelParameters.add(getChooseDataBodySize(), null);
			jPanelModelParameters.add(getRndLorry(), null);
			jPanelModelParameters.add(getChooseDataHeapMaxSize(), null);
			jPanelModelParameters.add(getChooseDataFinishTime(), null);
		}
		return jPanelModelParameters;
	}

	/**
	 * This method initializes jCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	public JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox
					.setActionCommand("\u0412\u044b\u0432\u043e\u0434 \u043f\u0440\u043e\u0442\u043e\u043a\u043e\u043b\u0430 \u043d\u0430 \u043a\u043e\u043d\u0441\u043e\u043b\u044c");
			jCheckBox.setText("Протокол на консоль");
			jCheckBox.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelStat() {
		if (jPanelStat == null) {
			jPanelStat = new JPanel();
			jPanelStat.setLayout(new CardLayout(0, 0));
			jPanelStat.add(getStatisticsManager(), "name_131147950583608");
		}
		return jPanelStat;
	}

	/**
	 * This method initializes rndBuldo	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getRndBuldo() {
		if (rndBuldo == null) {
			rndBuldo = new ChooseRandom();
			rndBuldo.setRandom(new Erlang(3,3));
			rndBuldo.setTitle("Продуктивність бульдозера");
			rndBuldo.setBounds(new Rectangle(3, 21, 231, 52));
		}
		return rndBuldo;
	}


	/**
	 * This method initializes rndLoader	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getRndLoader() {
		if (rndLoader == null) {
			rndLoader = new ChooseRandom();
			rndLoader.setRandom(new Norm(1,0.2));
			rndLoader.setTitle("Продуктивність навантажувача");
			rndLoader.setBounds(new Rectangle(3, 81, 231, 52));
		}
		return rndLoader;
	}


	/**
	 * This method initializes chooseDataNLorry	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataNLorry() {
		if (chooseDataNLorry == null) {
			chooseDataNLorry = new ChooseData();
			chooseDataNLorry.setBounds(new Rectangle(3, 200, 231, 53));
			chooseDataNLorry.setTitle("Кількість самоскидів");
			chooseDataNLorry.setText("5");
			chooseDataNLorry
			.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					if (getJPanelTest().isShowing()){
						getDiagramLorryOnRoad().setVerticalMaxText(
								chooseDataNLorry.getText());
						getDiagramQueueToLoader().setVerticalMaxText(
								chooseDataNLorry.getText());
					}
				}
			});

		}
		return chooseDataNLorry;
	}


	/**
	 * This method initializes chooseDataBodySize	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataBodySize() {
		if (chooseDataBodySize == null) {
			chooseDataBodySize = new ChooseData();
			chooseDataBodySize.setBounds(new Rectangle(4, 263, 231, 53));
			chooseDataBodySize.setTitle("Місткість кузову");
			chooseDataBodySize.setText("2");
		}
		return chooseDataBodySize;
	}


	/**
	 * This method initializes rndLorry	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getRndLorry() {
		if (rndLorry == null) {
			rndLorry = new ChooseRandom();
			rndLorry.setBounds(new Rectangle(3, 142, 231, 52));
			rndLorry.setRandom(new Erlang(20, 4));
			rndLorry.setTitle("Перебування самоскида у дорозі");
		}
		return rndLorry;
	}


	/**
	 * This method initializes chooseDataHeapMaxSize	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataHeapMaxSize() {
		if (chooseDataHeapMaxSize == null) {
			chooseDataHeapMaxSize = new ChooseData();
			chooseDataHeapMaxSize.setBounds(new Rectangle(1, 326, 231, 53));
			chooseDataHeapMaxSize.setTitle("Найбільший розмір купи");
			chooseDataHeapMaxSize.setText("50");
			chooseDataHeapMaxSize.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					if (getJPanelTest().isShowing())
						getDiagramHeepSize().setVerticalMaxText(
								chooseDataHeapMaxSize.getText());
				}
			});

		}
		return chooseDataHeapMaxSize;
	}


	/**
	 * This method initializes chooseDataFinishTime	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataFinishTime() {
		if (chooseDataFinishTime == null) {
			chooseDataFinishTime = new ChooseData();
			chooseDataFinishTime.setBounds(new Rectangle(3, 385, 231, 53));
			chooseDataFinishTime.setTitle("Час моделювання");
			chooseDataFinishTime.setText("500");
			chooseDataFinishTime
			.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					if (getJPanelTest().isShowing()) {
						getDiagramHeepSize().setHorizontalMaxText(
								chooseDataFinishTime.getText());
						getDiagramQueueToLoader().setHorizontalMaxText(
								chooseDataFinishTime.getText());
						getDiagramLorryOnRoad().setHorizontalMaxText(
								chooseDataFinishTime.getText());
					}
				}
			});

		}
		return chooseDataFinishTime;
	}


	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			String str="/buldo2022/tz.htm";
			URL url = getClass().getResource(str);
			try {
				jTextPane.setPage(url);
			} catch (IOException e33) {
				System.err
						.println("Problems with file "+str);
			}

		}
		return jTextPane;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuldGUI application = new BuldGUI();
		application.setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(949, 499);
		this.setContentPane(getJContentPane());
		this
				.setTitle("Дослідження грунтовидобувних робіт шляхом імітаційного моделювання");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(9, 10, 7, 2);
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.ipadx = -21;
			gridBagConstraints7.ipady = 13;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = -96;
			gridBagConstraints6.ipady = -294;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(8, 3, 6, 7);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTabbedPane(), gridBagConstraints6);
			jContentPane.add(getJPanelModelParameters(), gridBagConstraints7);
		}
		return jContentPane;
	}

	// /////////////////////////////////////////////////
	// ///Реалізація інтерфейсу IBuldoGUI
	// //////////////////////////////////////////////////


	/*
	 * @see buldo2009.IBuldoGUI Метод повертає стан перемикача управління
	 *      виведенням протоколу на консоль
	 */
	public Boolean getProtocolToConcole() {
		return getJCheckBox().isSelected();
	}


	/**
	 * This method initializes diagramHeepSize
	 * 
	 * @see buldo2011.IBuldoGUI
	 * @return paint.Diagram
	 */
	public Diagram getDiagramHeepSize() {
		if (diagramHeepSize == null) {
			diagramHeepSize = new Diagram();
			diagramHeepSize.setHorizontalMaxText("500");
			diagramHeepSize.setTitleText("Розмір купи грунту");
			diagramHeepSize.setVerticalMaxText("50");
			diagramHeepSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramHeepSize;
	}

	/**
	 * This method initializes diagramQueueToLoader
	 * 
	 * @see buldo2011.IBuldoGUI
	 * @return paint.Diagram
	 */
	public Diagram getDiagramQueueToLoader() {
		if (diagramQueueToLoader == null) {
			diagramQueueToLoader = new Diagram();
			diagramQueueToLoader.setHorizontalMaxText("500");
			diagramQueueToLoader.setTitleText("Черга до навантажувача");
			diagramQueueToLoader.setVerticalMaxText("6");
			diagramQueueToLoader.setPainterColor(java.awt.Color.magenta);
		}
		return diagramQueueToLoader;
	}

	/**
	 * This method initializes diagram
	 * 
	 * @see buldo2011.IBuldoGUI
	 * @return paint.Diagram
	 */
	public Diagram getDiagramLorryOnRoad() {
		if (diagramLorryOnRoad == null) {
			diagramLorryOnRoad = new Diagram();
			diagramLorryOnRoad.setHorizontalMaxText("500");
			diagramLorryOnRoad
					.setTitleText("Самоскиди у дорозі");
			diagramLorryOnRoad.setVerticalMaxText("6");
			diagramLorryOnRoad.setPainterColor(java.awt.SystemColor.activeCaption);
		}
		return diagramLorryOnRoad;
	}

	public Dispatcher getDispatcher() {
		if (dispatcher == null)
			dispatcher = new Dispatcher();
		return dispatcher;
	}


	private StatisticsManager getStatisticsManager() {
		if (statisticsManager == null) {
			statisticsManager = new StatisticsManager();
			statisticsManager.setFactory((d)-> new BuldModel(d, this));
		}
		return statisticsManager;
	}
	private JPanel getJPanelRegres() {
		if (jPanelRegres == null) {
			jPanelRegres = new JPanel();
			jPanelRegres.setLayout(new CardLayout(0, 0));		
			ExperimentManager em = new ExperimentManager();
			em.getChooseDataFactors().setTitle("Кількість самоскидів");
			em.getComboBox().setPreferredSize(new Dimension(328, 20));
			em.getComboBox().setMinimumSize(new Dimension(328, 20));
			em.getDiagram().setHorizontalMaxText("15");
			em.getDiagram().setVerticalMaxText("500");
			em.getChooseDataFactors().setText("1 3 5 7 9 12 14");
			em.setFactory((d)-> new BuldModel(d, this));
			jPanelRegres.add(em, "name_111625814938837");
		}
		return jPanelRegres;
	}
}  //  @jve:decl-index=0:visual-constraint="42,-8"
// @jve:decl-index=0:visual-constraint="10,8"
