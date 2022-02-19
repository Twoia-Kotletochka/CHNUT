package example.cafe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import process.Dispatcher;
import rnd.Erlang;
import rnd.Negexp;
import widgets.ChooseDataH;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

public class CoffeeHouseUI extends JFrame implements ICoffeeHouseUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private ChooseRandom chooseRandomClient = null;

	private ChooseRandom chooseRandomGen = null;

	private JButton jButtonStart = null;

	private Diagram diagramCaffee = null;

	private Diagram diagramHisto = null;

	private JScrollPane jScrollPane = null;

	private JTextArea jTextArea = null;

	private Dispatcher dispatcher = null;  //  @jve:decl-index=0:visual-constraint="608,121"

	private ChooseDataH chooseDataHFinishTime = null;

	/**
	 * This method initializes chooseRandomClient
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomClient() {
		if (chooseRandomClient == null) {
			chooseRandomClient = new ChooseRandom();
			chooseRandomClient.setTitle("Час перебування у кафе");
			chooseRandomClient.setRandom(new Erlang(10,3,true));
		}
		return chooseRandomClient;
	}

	/**
	 * This method initializes chooseRandomGen
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomGen() {
		if (chooseRandomGen == null) {
			
			chooseRandomGen = new ChooseRandom();
			chooseRandomGen.setTitle("Інтервал появи клієнтів");
			chooseRandomGen.setRandom(new Negexp(1,true));
		}
		return chooseRandomGen;
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
					start();
				}
			});
		}
		return jButtonStart;
	}

	protected void start() {
		(new Thread() {
			public void run() {
				CoffeeHouseModel model = new CoffeeHouseModel();
				model.setUi(CoffeeHouseUI.this);
				getDispatcher().setProtocolFileName("");
				model.setDispatcher(getDispatcher());
				getJButtonStart().setEnabled(false);
				getDiagramCaffee().clear();
				getDiagramHisto().clear();
				getJTextArea().setText("");
				model.initForStart();
				model.componentsToStartList();
				getDispatcher().start();
				try {
					getDispatcher().getThread().join();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				model.getDiscretHisto().showRelFrec(getDiagramHisto());
				getJTextArea().setText(model.getDiscretHisto().toString());
				getJTextArea().select(0, 0);
				getJButtonStart().setEnabled(true);
			}
		}).start();
	}

	/**
	 * This method initializes diagramCaffee
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagramCaffee() {
		if (diagramCaffee == null) {
			diagramCaffee = new Diagram();
			diagramCaffee.setHorizontalMaxText("750");
			diagramCaffee.setPainterColor(java.awt.Color.magenta);
			diagramCaffee.setVerticalMaxText("25");
			diagramCaffee.setTitleText("Діаграма заповнення кафе у часі");
		}
		return diagramCaffee;
	}

	/**
	 * This method initializes diagramHisto
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagramHisto() {
		if (diagramHisto == null) {
			diagramHisto = new Diagram();
			diagramHisto.setPainterColor(java.awt.Color.magenta);
			diagramHisto.setTitleText("Гістограма заповнення кафе");
		}
		return diagramHisto;
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
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setText("Стат.характеристики заповнення кафе");
		}
		return jTextArea;
	}



	/**
	 * This method initializes dispatcher	
	 * 	
	 * @return process.Dispatcher	
	 */
	private Dispatcher getDispatcher() {
		if (dispatcher == null) {
			dispatcher = new Dispatcher();
			
		}
		return dispatcher;
	}

	/**
	 * This method initializes chooseDataHFinishTime	
	 * 	
	 * @return widgets.ChooseDataH	
	 */
	private ChooseDataH getChooseDataHFinishTime() {
		if (chooseDataHFinishTime == null) {
			chooseDataHFinishTime = new ChooseDataH();
			chooseDataHFinishTime.setTitle("Час моделювання");
			chooseDataHFinishTime.setDividerLocation(140);
			chooseDataHFinishTime.setText("600");
		}
		return chooseDataHFinishTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CoffeeHouseUI application = new CoffeeHouseUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public CoffeeHouseUI() {
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
		this.setSize(566, 413);
		this.setContentPane(getJContentPane());
		this.setTitle("Моделювання роботи кафе");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.ipadx = 5;
			gridBagConstraints6.ipady = 7;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(4, 40, 5, 29);
			
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(4, 5, 5, 5);
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridheight = 4;
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 30;
			gridBagConstraints4.ipady = 162;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.insets = new Insets(5, 3, 11, 15);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(6, 3, 5, 14);
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 8;
			gridBagConstraints3.ipady = -41;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(6, 6, 5, 5);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 31;
			gridBagConstraints2.ipady = -41;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(3, 94, 16, 84);
			gridBagConstraints1.gridy = 4;
			gridBagConstraints1.ipadx = 42;
			gridBagConstraints1.ipady = -3;
			gridBagConstraints1.gridx = 0;
			
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(4, 5, 5, 5);
			gridBagConstraints.gridy = 2;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getChooseRandomClient(), gridBagConstraints);
			jContentPane.add(getJButtonStart(), gridBagConstraints1);
			jContentPane.add(getDiagramCaffee(), gridBagConstraints2);
			jContentPane.add(getDiagramHisto(), gridBagConstraints3);
			jContentPane.add(getJScrollPane(), gridBagConstraints4);
			jContentPane.add(getChooseRandomGen(), gridBagConstraints5);
			jContentPane.add(getChooseDataHFinishTime(), gridBagConstraints6);
		}
		return jContentPane;
	}

	/**
	 * Возвращаем ChooseRandom для клиента. Это позолит менять законы не меняя ссылки
	 * 
	 * @see rgr.transGenExample.ICoffeeHouseUI#getRndClient()
	 */
	public ChooseRandom getRndClient() {
		return getChooseRandomClient();
	}

	/**
	 * Возвращаем ChooseRandom для генератора. Это позолит менять законы не меняя ссылки
	 * 
	 * @see rgr.transGenExample.ICoffeeHouseUI#getRndGen()
	 */
	public ChooseRandom getRndGen() {
		return getChooseRandomGen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rgr.transGenExample.ICoffeeHouseUI#getFinishTime()
	 */
	public double getFinishTime() {
		return getChooseDataHFinishTime().getDouble();
	}

	public Painter getCaffeeHousePainter() {

		return getDiagramCaffee().getPainter();
	}
} // @jve:decl-index=0:visual-constraint="10,10"
