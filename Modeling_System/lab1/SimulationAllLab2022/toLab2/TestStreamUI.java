package toLab2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import process.Dispatcher;
import process.QueueForTransactions;
import qusystem.FinishDevice;
import qusystem.TransactionsGenerator;
import rnd.Norm;
import rnd.Uniform;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

public class TestStreamUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Diagram diagram = null;
	private ChooseRandom chooseRandom = null;
	private TransactionsGenerator transactionsGenerator = null;  //  @jve:decl-index=0:visual-constraint="14,270"
	private QueueForTransactions queueForTransactions = null;  //  @jve:decl-index=0:visual-constraint="189,271"
	private FinishDevice finishDevice = null;  //  @jve:decl-index=0:visual-constraint="483,270"
	private Painter painter = null;  //  @jve:decl-index=0:visual-constraint="381,273"
	private Norm norm = null;  //  @jve:decl-index=0:visual-constraint="507,312"
	private JButton jButton = null;
	private Uniform uniform = null;  //  @jve:decl-index=0:visual-constraint="634,182"
	private Dispatcher dispatcher=null;
	/**
	 * This method initializes diagram	
	 * 	
	 * @return paint.Diagram	
	 */
	private Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setHorizontalMaxText("20");
			diagram.setGridColor(Color.LIGHT_GRAY);
			diagram.setGridByX(20);
			diagram.setVerticalMaxText("2");
			diagram.setTitleText("Випадковий потік подій");
		}
		return diagram;
	}

	/**
	 * This method initializes chooseRandom	
	 * 	
	 * @return rnd.ChooseRandom	
	 */
	private ChooseRandom getChooseRandom() {
		if (chooseRandom == null) {
			chooseRandom = new ChooseRandom();
			chooseRandom.setMinimumSize(new java.awt.Dimension(303,35));
			chooseRandom.setMaximumSize(new java.awt.Dimension(303,35));
			chooseRandom.setRandom(getUniform());
		}
		return chooseRandom;
	}

	/**
	 * This method initializes transactionsGenerator	
	 * 	
	 * @return qusystem.TransactionsGenerator	
	 */
	private TransactionsGenerator getTransactionsGenerator() {
		if (transactionsGenerator == null) {
			transactionsGenerator = new TransactionsGenerator();
			transactionsGenerator.setFinishTime(20.0D);
			transactionsGenerator.setRandom(getChooseRandom());
			transactionsGenerator.setOutputQueue(getQueueForTransactions());
		}
		return transactionsGenerator;
	}

	/**
	 * This method initializes queueForTransactions	
	 * 	
	 * @return qusystem.QueueForTransactions	
	 */
	private QueueForTransactions getQueueForTransactions() {
		if (queueForTransactions == null) {
			queueForTransactions = new QueueForTransactions();
			queueForTransactions.setDispatcher(getDispatcher());
			queueForTransactions.setPainter(getPainter());
		}
		return queueForTransactions;
	}

	/**
	 * This method initializes finishDevice	
	 * 	
	 * @return qusystem.FinishDevice	
	 */
	private FinishDevice getFinishDevice() {
		if (finishDevice == null) {
			finishDevice = new FinishDevice();
			finishDevice.setFinishTime(20.0D);
			finishDevice.setRandom(getNorm());
			finishDevice.setInputQueue(getQueueForTransactions());
		}
		return finishDevice;
	}

	/**
	 * This method initializes painter	
	 * 	
	 * @return paint.Painter	
	 */
	private Painter getPainter() {
		if (painter == null) {
			painter = new Painter(getDiagram());
			painter.setColor(java.awt.Color.red);
		}
		return painter;
	}

	/**
	 * This method initializes norm	
	 * 	
	 * @return rnd.Norm	
	 */
	private Norm getNorm() {
		if (norm == null) {
			norm = new Norm();
			norm.setSigma(0.0D);
			norm.setM(0.0D);
		}
		return norm;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Run");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onRun();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes uniform	
	 * 	
	 * @return rnd.Uniform	
	 */
	private Uniform getUniform() {
		if (uniform == null) {
			uniform = new Uniform();
			uniform.setMax(1.0D);
			uniform.setMin(1.0D);
		}
		return uniform;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TestStreamUI application = new TestStreamUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public TestStreamUI() {
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
		this.setSize(595, 246);
		this.setContentPane(getJContentPane());
		this.setTitle("Знайомство з випадковими потоками подій");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new java.awt.Insets(5,114,1,1);
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 103;
			gridBagConstraints3.ipady = 9;
			gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHEAST;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new java.awt.Insets(0,0,0,114);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = -103;
			gridBagConstraints2.ipady = 6;
			gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(0,2,1,0);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 339;
			gridBagConstraints1.ipady = -19;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.weightx = 1.0D;
			gridBagConstraints1.weighty = 1.0D;
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.SOUTH;
			gridBagConstraints1.gridwidth = 2;
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWeights = new double[]{1.0, 0.0};
			gbl_jContentPane.columnWidths = new int[]{260, 0};
			jContentPane.setLayout(gbl_jContentPane);
			jContentPane.add(getDiagram(), gridBagConstraints1);
			jContentPane.add(getChooseRandom(), gridBagConstraints2);
			jContentPane.add(getJButton(), gridBagConstraints3);
		}
		return jContentPane;
	}

	public Dispatcher getDispatcher() {
		if (dispatcher==null) dispatcher=new Dispatcher();
		return dispatcher;
	}

	private void onRun() {
		getJButton().setEnabled(false);
		String s=getDiagram().getHorizontalMaxText();
		double finishTime=Double.parseDouble(s);
		getFinishDevice().setFinishTime(finishTime);
		getTransactionsGenerator().setFinishTime(finishTime);
		getDispatcher().addStartingActor(getTransactionsGenerator());
		getDispatcher().addStartingActor(getFinishDevice());
		getQueueForTransactions().init();
		getDiagram().clear();
		getDispatcher().setProtocolFileName("");
		getDispatcher().start();
		try {
			getDispatcher().getThread().join();
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		getJButton().setEnabled(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,19"
