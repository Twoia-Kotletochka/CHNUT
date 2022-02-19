package example.testTickerForMultiplication;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import process.ChangeTimeListener;
import process.Dispatcher;
import rnd.Negexp;
import rnd.Norm;
import rnd.Triangular;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

public class QSystemExampleUI extends JFrame implements IQSystemExampleUI
		 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private ChooseRandom chooseRandomTransGen = null;

	private ChooseRandom chooseRandomGetPut = null;

	private ChooseRandom chooseRandomFinish = null;

	private JTextField jTextFieldNClone1 = null;

	private JTextField jTextFieldNClone2 = null;

	private JTextField jTextFieldFinishTime = null;

	private Diagram diagram1 = null;

	private Diagram diagram2 = null;

	private JButton jButtonStart = null;

	private JLabel jLabelTransGen = null;

	private JLabel jLabelGetPut = null;

	private JLabel jLabelFinishDevice = null;

	private JLabel jLabelNGetPut = null;

	private JLabel jLabelNFinishDevice = null;

	private JLabel jLabelFinishTime = null;

	private JLabel jLabelCurrentTime = null;

	private JTextField jTextFieldCurrentTime = null;

	private Dispatcher dispatcher = null;

	private Dispatcher getDispatcher() {
		if (dispatcher == null)
			dispatcher = new Dispatcher();
		return dispatcher;
	}

	/**
	 * This method initializes chooseRandomTransGen
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomTransGen() {
		if (chooseRandomTransGen == null) {
			chooseRandomTransGen = new ChooseRandom();
			chooseRandomTransGen.setRandom(new Negexp(1));
		}
		return chooseRandomTransGen;
	}

	/**
	 * This method initializes chooseRandomGetPut
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomGetPut() {
		if (chooseRandomGetPut == null) {
			chooseRandomGetPut = new ChooseRandom();
			chooseRandomGetPut.setRandom(new Norm(0.8,0.1));
		}
		return chooseRandomGetPut;
	}

	/**
	 * This method initializes chooseRandomFinish
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomFinish() {
		if (chooseRandomFinish == null) {
			chooseRandomFinish = new ChooseRandom();
			chooseRandomFinish.setRandom(new Triangular(0.5, 0.8, 1));
		}
		return chooseRandomFinish;
	}

	/**
	 * This method initializes jTextFieldNClone1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNClone1() {
		if (jTextFieldNClone1 == null) {
			jTextFieldNClone1 = new JTextField();
			jTextFieldNClone1
					.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jTextFieldNClone1.setText("2");
		}
		return jTextFieldNClone1;
	}

	/**
	 * This method initializes jTextFieldNClone2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNClone2() {
		if (jTextFieldNClone2 == null) {
			jTextFieldNClone2 = new JTextField();
			jTextFieldNClone2
					.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jTextFieldNClone2.setText("3");
		}
		return jTextFieldNClone2;
	}

	/**
	 * This method initializes jTextFieldFinishTime
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldFinishTime() {
		if (jTextFieldFinishTime == null) {
			jTextFieldFinishTime = new JTextField();
			jTextFieldFinishTime
					.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jTextFieldFinishTime.setText("100");
			jTextFieldFinishTime
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							getDiagram1().setHorizontalMaxText(
									getJTextFieldFinishTime().getText());
							getDiagram2().setHorizontalMaxText(
									getJTextFieldFinishTime().getText());
						}
					});
		}
		return jTextFieldFinishTime;
	}

	/**
	 * This method initializes diagram1
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram1() {
		if (diagram1 == null) {
			diagram1 = new Diagram();
			diagram1.setTitleText("����� �� ��������� �������");
			diagram1.setHorizontalMaxText("100");
			diagram1.setPainterColor(java.awt.Color.magenta);
		}
		return diagram1;
	}

	/**
	 * This method initializes diagram2
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram2() {
		if (diagram2 == null) {
			diagram2 = new Diagram();
			diagram2.setTitleText("����� �� ������ �������");
			diagram2.setHorizontalMaxText("100");
			diagram2.setPainterColor(java.awt.Color.magenta);
		}
		return diagram2;
	}

	/**
	 * This method initializes jButtonStart
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("�����");
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					start();
				}
			});
		}
		return jButtonStart;
	}

	private void start() {
		new Thread() {
			public void run() {
				getJButtonStart().setEnabled(false);
				getDiagram1().clear();
				getDiagram2().clear();
				QSystemModel model = new QSystemModel();
				model.setUi(QSystemExampleUI.this);
				model.setDispatcher(getDispatcher());
				model.initForStart();
				model.componentsToStartList();
				getDispatcher().start();
				//��������� ������� ��䳿 ChangeTime ��� ��������� ���������� ����
				getDispatcher().addChangeTimeListener(new ChangeTimeListener() {
					public void onChangeTime(EventObject evt) {
						getJTextFieldCurrentTime().setText(
								String.valueOf(((Dispatcher)evt.getSource()).getCurrentTime()));
					}
				});
				//�������� �� ���� �����������
				try {
					model.getDispatcher().getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getJButtonStart().setEnabled(true);
			}
		}.start();
	}

	/**
	 * This method initializes jTextFieldCurrentTime
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCurrentTime() {
		if (jTextFieldCurrentTime == null) {
			jTextFieldCurrentTime = new JTextField();
			jTextFieldCurrentTime.setFont(new Font("Courier New", Font.PLAIN, 11));
			jTextFieldCurrentTime
					.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		}
		return jTextFieldCurrentTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		QSystemExampleUI application = new QSystemExampleUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public QSystemExampleUI() {
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
		this.setSize(612, 376);
		this.setContentPane(getJContentPane());
		this.setTitle("����������� ������� �������� ��������������");

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.gridx = 3;
			gridBagConstraints16.gridy = 9;
			gridBagConstraints16.ipadx = 161;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.insets = new java.awt.Insets(14, 6, 15, 12);
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new java.awt.Insets(15, 8, 18, 5);
			gridBagConstraints15.gridy = 9;
			gridBagConstraints15.ipadx = 11;
			gridBagConstraints15.gridx = 2;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.insets = new java.awt.Insets(6, 16, 8, 3);
			gridBagConstraints14.gridy = 8;
			gridBagConstraints14.ipadx = 79;
			gridBagConstraints14.gridx = 0;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new java.awt.Insets(7, 16, 7, 3);
			gridBagConstraints13.gridy = 7;
			gridBagConstraints13.ipadx = 23;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new java.awt.Insets(8, 16, 7, 3);
			gridBagConstraints12.gridy = 6;
			gridBagConstraints12.ipadx = 7;
			gridBagConstraints12.gridx = 0;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new java.awt.Insets(0, 16, 0, 6);
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 4;
			gridBagConstraints11.ipadx = 61;
			gridBagConstraints11.gridwidth = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new java.awt.Insets(3, 16, 1, 6);
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 2;
			gridBagConstraints10.ipadx = 34;
			gridBagConstraints10.gridwidth = 2;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.insets = new java.awt.Insets(7, 16, 0, 6);
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 0;
			gridBagConstraints9.ipadx = 56;
			gridBagConstraints9.gridwidth = 2;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new java.awt.Insets(5, 55, 13, 36);
			gridBagConstraints8.gridy = 9;
			gridBagConstraints8.ipadx = 49;
			gridBagConstraints8.ipady = 5;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new java.awt.Insets(0, 6, 7, 12);
			gridBagConstraints7.gridwidth = 2;
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 4;
			gridBagConstraints7.ipadx = 91;
			gridBagConstraints7.ipady = -39;
			gridBagConstraints7.gridheight = 5;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new java.awt.Insets(7, 6, 0, 13);
			gridBagConstraints6.gridwidth = 2;
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = 90;
			gridBagConstraints6.ipady = -56;
			gridBagConstraints6.gridheight = 4;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 8;
			gridBagConstraints5.ipadx = 7;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new java.awt.Insets(5, 4, 5, 6);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 7;
			gridBagConstraints4.ipadx = 21;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new java.awt.Insets(5, 4, 5, 6);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 6;
			gridBagConstraints3.ipadx = 21;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new java.awt.Insets(7, 4, 4, 6);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new java.awt.Insets(1, 16, 7, 6);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 5;
			gridBagConstraints2.gridwidth = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.insets = new java.awt.Insets(2, 17, 15, 6);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 3;
			gridBagConstraints1.ipadx = -98;
			gridBagConstraints1.gridwidth = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.insets = new java.awt.Insets(0, 17, 3, 7);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = -99;
			gridBagConstraints.gridwidth = 2;
			jLabelCurrentTime = new JLabel();
			jLabelCurrentTime.setText("�������� ��������� ���");
			jLabelFinishTime = new JLabel();
			jLabelFinishTime
					.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			jLabelFinishTime.setText("��� �����������");
			jLabelNFinishDevice = new JLabel();
			jLabelNFinishDevice
					.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			jLabelNFinishDevice.setText("ʳ������ ������� �������");
			jLabelNGetPut = new JLabel();
			jLabelNGetPut
					.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			jLabelNGetPut.setText("ʳ������ ��������� �������");
			jLabelFinishDevice = new JLabel();
			jLabelFinishDevice
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabelFinishDevice.setText("��������� ������ �������");
			jLabelGetPut = new JLabel();
			jLabelGetPut
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabelGetPut.setText("��������� ���������� �������");
			jLabelTransGen = new JLabel();
			jLabelTransGen
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabelTransGen.setText("��������� ������ ����������");
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_jContentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
			jContentPane.setLayout(gbl_jContentPane);
			jContentPane.add(getChooseRandomTransGen(), gridBagConstraints);
			jContentPane.add(getChooseRandomGetPut(), gridBagConstraints1);
			jContentPane.add(getChooseRandomFinish(), gridBagConstraints2);
			jContentPane.add(getJTextFieldNClone1(), gridBagConstraints3);
			jContentPane.add(getJTextFieldNClone2(), gridBagConstraints4);
			jContentPane.add(getJTextFieldFinishTime(), gridBagConstraints5);
			jContentPane.add(getDiagram1(), gridBagConstraints6);
			jContentPane.add(getDiagram2(), gridBagConstraints7);
			jContentPane.add(getJButtonStart(), gridBagConstraints8);
			jContentPane.add(jLabelTransGen, gridBagConstraints9);
			jContentPane.add(jLabelGetPut, gridBagConstraints10);
			jContentPane.add(jLabelFinishDevice, gridBagConstraints11);
			jContentPane.add(jLabelNGetPut, gridBagConstraints12);
			jContentPane.add(jLabelNFinishDevice, gridBagConstraints13);
			jContentPane.add(jLabelFinishTime, gridBagConstraints14);
			jContentPane.add(jLabelCurrentTime, gridBagConstraints15);
			jContentPane.add(getJTextFieldCurrentTime(), gridBagConstraints16);
		}
		return jContentPane;
	}

	public Painter getPainter1() {
		return getDiagram1().getPainter();
	}

	public Painter getPainter2() {
		return getDiagram2().getPainter();
	}

	public double getFinishTime() {

		return Double.valueOf(getJTextFieldFinishTime().getText());
	}

	public int getNClones1() {

		return Integer.valueOf(getJTextFieldNClone1().getText());
	}

	public int getNClones2() {
		return Integer.valueOf(getJTextFieldNClone2().getText());
	}


} // @jve:decl-index=0:visual-constraint="74,28"
