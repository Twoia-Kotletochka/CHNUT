package toLab8_testGradient;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class OptimizerView extends javax.swing.JPanel {
	private javax.swing.JComboBox ivjJComboBox1 = null;

	private javax.swing.JLabel ivjJLabel4 = null;

	private javax.swing.DefaultComboBoxModel ivjDefaultComboBoxModel = null;

	private javax.swing.JLabel ivjJLabel1 = null;

	private javax.swing.JLabel ivjJLabel2 = null;

	private Optimizer ivjOptimizer = null;

	private javax.swing.JTextField ivjJTFSearchStep = null;

	IvjEventHandler ivjEventHandler = new IvjEventHandler();

	private javax.swing.JButton ivjJButtonTune = null;

	private javax.swing.JCheckBox ivjJCheckBox = null;

	class IvjEventHandler implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == OptimizerView.this.getJButtonTune())
				OptimizerView.this.tune();
		};
	};

	/**
	 * OptimizerView constructor comment.
	 */
	public OptimizerView() {
		super();
		initialize();
	}

	/**
	 * OptimizerView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 */
	public OptimizerView(java.awt.LayoutManager layout) {
		super(layout);
	}

	/**
	 * OptimizerView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public OptimizerView(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * OptimizerView constructor comment.
	 * 
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public OptimizerView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * connEtoC1: (JButton2.action.actionPerformed(java.awt.event.ActionEvent)
	 * --> OptimizerView.tune()V)
	 * 
	 * @param arg1
	 *            java.awt.event.ActionEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	/*
	 * private void connEtoC1(java.awt.event.ActionEvent arg1) { try { // user
	 * code begin {1} // user code end this.tune(); // user code begin {2} //
	 * user code end } catch (java.lang.Throwable ivjExc) { // user code begin
	 * {3} // user code end handleException(ivjExc); } }
	 */
	/**
	 * connPtoP1SetTarget: (DefaultComboBoxModel.this <--> JComboBox1.model)
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	/*
	 * private void connPtoP1SetTarget() { try {
	 * getJComboBox1().setModel(getDefaultComboBoxModel()); // user code begin
	 * {1} // user code end } catch (java.lang.Throwable ivjExc) { // user code
	 * begin {3} // user code end handleException(ivjExc); } }
	 */
	/**
	 * Return the DefaultComboBoxModel property value.
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.DefaultComboBoxModel getDefaultComboBoxModel() {
		if (ivjDefaultComboBoxModel == null) {
			try {
				ivjDefaultComboBoxModel = new javax.swing.DefaultComboBoxModel();
				// user code begin {1}
				ivjDefaultComboBoxModel.addElement("Повний");
				ivjDefaultComboBoxModel.addElement("1/2 репліка");
				ivjDefaultComboBoxModel.addElement("-1/2 репліка");
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjDefaultComboBoxModel;
	}

	/**
	 * Return the JButton2 property value.
	 * 
	 * @return javax.swing.JButton
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JButton getJButtonTune() {
		if (ivjJButtonTune == null) {
			try {
				ivjJButtonTune = new javax.swing.JButton();
				ivjJButtonTune.setName("JButtonTune");
				ivjJButtonTune.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJButtonTune.setText("Пошук");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJButtonTune;
	}

	/**
	 * Return the JCheckBox2 property value.
	 * 
	 * @return javax.swing.JCheckBox
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JCheckBox getJCheckBox() {
		if (ivjJCheckBox == null) {
			try {
				ivjJCheckBox = new javax.swing.JCheckBox();
				ivjJCheckBox.setName("JCheckBox");
				ivjJCheckBox.setFont(new java.awt.Font("dialog", 1, 10));
				ivjJCheckBox.setText("Протокол пошуку");
				ivjJCheckBox
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJCheckBox;
	}

	/**
	 * Return the JComboBox1 property value.
	 * 
	 * @return javax.swing.JComboBox
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JComboBox getJComboBox1() {
		if (ivjJComboBox1 == null) {
			try {
				ivjJComboBox1 = new javax.swing.JComboBox();
				ivjJComboBox1.setName("JComboBox1");
				ivjJComboBox1.setToolTipText("");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJComboBox1;
	}

	/**
	 * Return the JLabel1 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel1() {
		if (ivjJLabel1 == null) {
			try {
				ivjJLabel1 = new javax.swing.JLabel();
				ivjJLabel1.setName("JLabel1");
				ivjJLabel1.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel1.setText("Крок пошуку");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel1;
	}

	/**
	 * Return the JLabel2 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel2() {
		if (ivjJLabel2 == null) {
			try {
				ivjJLabel2 = new javax.swing.JLabel();
				ivjJLabel2.setName("JLabel2");
				ivjJLabel2.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJLabel2.setText("ОПТИМІЗАТОР");
				ivjJLabel2
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel2;
	}

	/**
	 * Return the JLabel4 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel4() {
		if (ivjJLabel4 == null) {
			try {
				ivjJLabel4 = new javax.swing.JLabel();
				ivjJLabel4.setName("JLabel4");
				ivjJLabel4.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel4.setText("План експерименту");
				ivjJLabel4
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel4;
	}

	/**
	 * Return the JTextField1 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFSearchStep() {
		if (ivjJTFSearchStep == null) {
			try {
				ivjJTFSearchStep = new javax.swing.JTextField();
				ivjJTFSearchStep.setName("JTFSearchStep");
				ivjJTFSearchStep.setText("0.05");
				ivjJTFSearchStep
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFSearchStep;
	}

	/**
	 * Return the Optimizer property value.
	 * 
	 * @return toLab8_testGradient.Optimizer
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private Optimizer getOptimizer() {
		if (ivjOptimizer == null) {
			try {
				ivjOptimizer = new toLab8_testGradient.Optimizer();
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjOptimizer;
	}

	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* Uncomment the following lines to print uncaught exceptions to stdout */
		// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
		// exception.printStackTrace(System.out);
	}

	/**
	 * Initializes connections
	 * 
	 * @exception java.lang.Exception
	 *                The exception description.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initConnections() throws java.lang.Exception {
		// user code begin {1}
		// user code end
		getJButtonTune().addActionListener(ivjEventHandler);
		getJComboBox1().setModel(getDefaultComboBoxModel());
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(3, 22, 2, 22);
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = 39;
			gridBagConstraints6.gridwidth = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 3;
			gridBagConstraints5.ipadx = 19;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(3, 4, 2, 22);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(4, 22, 5, 4);
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.ipadx = 6;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(4, 30, 4, 30);
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 5;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.gridwidth = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(2, 22, 3, 22);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 12;
			gridBagConstraints2.gridwidth = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(3, 22, 3, 22);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 4;
			gridBagConstraints1.ipadx = 54;
			gridBagConstraints1.gridwidth = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagConstraints.ipadx = 94;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.insets = new Insets(4, 22, 2, 22);
			setName("OptimizerView");
			setBorder(new javax.swing.border.EtchedBorder());
			setLayout(new GridBagLayout());
			setSize(173, 165);

			this.add(getJComboBox1(), gridBagConstraints);
			this.add(getJButtonTune(), gridBagConstraints1);
			this.add(getJLabel4(), gridBagConstraints2);
			this.add(getJCheckBox(), gridBagConstraints3);
			this.add(getJLabel1(), gridBagConstraints4);
			this.add(getJTFSearchStep(), gridBagConstraints5);
			this.add(getJLabel2(), gridBagConstraints6);
			initConnections();
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}
		// user code end
	}

	/**
	 * mainForAllLab entrypoint - starts the part when it is run as an application
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(java.lang.String[] args) {
		try {
			javax.swing.JFrame frame = new javax.swing.JFrame();
			OptimizerView aOptimizerView;
			aOptimizerView = new OptimizerView();
			frame.setContentPane(aOptimizerView);
			frame.setSize(aOptimizerView.getSize());
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				};
			});
			frame.show();
			java.awt.Insets insets = frame.getInsets();
			frame.setSize(frame.getWidth() + insets.left + insets.right, frame
					.getHeight()
					+ insets.top + insets.bottom);
			frame.setVisible(true);
		} catch (Throwable exception) {
			System.err
					.println("Exception occurred in mainForAllLab() of javax.swing.JPanel");
			exception.printStackTrace(System.out);
		}
	}

	public void setIController(IController newController) {
		getOptimizer().setIController(newController);
	}

	public void setIStepTimer(IStepTimer newStepTimer) {
		getOptimizer().setIStepTimer(newStepTimer);
	}

	public void setPainter(widgets.Painter newPainter) {
		getOptimizer().setPainter(newPainter);
	}

	public void tune() {
		// Настройка оптимизатора в соответствии с панелью управления
		getJButtonTune().setEnabled(false);
		double searhStep = Double.parseDouble(getJTFSearchStep().getText());
		getOptimizer().setSearchStep(searhStep);
		getOptimizer().setPlan(getJComboBox1().getSelectedIndex());
		getOptimizer().setPrintSearchProtocol(getJCheckBox().isSelected());
		// Вызов метода автоматической настройки
		getOptimizer().avtoSet();
		getJButtonTune().setEnabled(true);
	}
}
