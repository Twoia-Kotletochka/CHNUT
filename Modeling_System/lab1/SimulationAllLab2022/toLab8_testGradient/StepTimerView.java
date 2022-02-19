package toLab8_testGradient;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

public class StepTimerView extends javax.swing.JPanel implements IStepTimer {
	private javax.swing.JButton ivjJButton1 = null;

	private javax.swing.JLabel ivjJLabel1111 = null;

	private javax.swing.JLabel ivjJLabel11111 = null;

	private javax.swing.JTextField ivjJTFStep = null;

	private javax.swing.JLabel ivjJLabel1 = null;

	private javax.swing.JLabel ivjJLabel111 = null;

	private javax.swing.JTextField ivjJTFDynError = null;

	private javax.swing.JTextField ivjJTFFinishTime = null;

	private StepTimer ivjStepTimer = null;

	IvjEventHandler ivjEventHandler = new IvjEventHandler();

	protected transient toLab8_testGradient.StepTimerViewListener fieldStepTimerViewListenerEventMulticaster = null;

	class IvjEventHandler implements java.awt.event.ActionListener,
			javax.swing.event.CaretListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == StepTimerView.this.getJButton1())
				StepTimerView.this.test();
		};

		public void caretUpdate(javax.swing.event.CaretEvent e) {
			if (e.getSource() == StepTimerView.this.getJTFFinishTime())
				StepTimerView.this
						.fireFinishTime_caretUpdate(new java.util.EventObject(
								this));
		};
	};

	/**
	 * GradientMonitorView constructor comment.
	 */
	public StepTimerView() {
		super();
		initialize();
	}

	/**
	 * GradientMonitorView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 */
	public StepTimerView(java.awt.LayoutManager layout) {
		super(layout);
	}

	/**
	 * GradientMonitorView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public StepTimerView(java.awt.LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * GradientMonitorView constructor comment.
	 * 
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public StepTimerView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * 
	 * @param newListener
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public void addStepTimerViewListener(
			toLab8_testGradient.StepTimerViewListener newListener) {
		fieldStepTimerViewListenerEventMulticaster = toLab8_testGradient.StepTimerViewListenerEventMulticaster
				.add(fieldStepTimerViewListenerEventMulticaster, newListener);
		return;
	}

	/**
	 * Method to support listener events.
	 * 
	 * @param newEvent
	 *            java.util.EventObject
	 */
	protected void fireFinishTime_caretUpdate(java.util.EventObject newEvent) {
		if (fieldStepTimerViewListenerEventMulticaster == null) {
			return;
		}
		;
		fieldStepTimerViewListenerEventMulticaster
				.FinishTime_caretUpdate(newEvent);
	}

	/**
	 * 
	 * @return double
	 */
	public double getCurrentTime() {
		return getStepTimer().getCurrentTime();
	}

	/**
	 * Method generated to support the promotion of the FinishTimeText
	 * attribute.
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getFinishTimeText() {
		return getJTFFinishTime().getText();
	}

	/**
	 * Return the JButton1 property value.
	 * 
	 * @return javax.swing.JButton
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JButton getJButton1() {
		if (ivjJButton1 == null) {
			try {
				ivjJButton1 = new javax.swing.JButton();
				ivjJButton1.setName("JButton1");
				ivjJButton1.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJButton1.setText("Тест");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJButton1;
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
				ivjJLabel1.setFont(new java.awt.Font("dialog", 1, 12));
				ivjJLabel1.setText("МОНІТОР");
				ivjJLabel1
						.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
	 * Return the JLabel111 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel111() {
		if (ivjJLabel111 == null) {
			try {
				ivjJLabel111 = new javax.swing.JLabel();
				ivjJLabel111.setHorizontalAlignment(SwingConstants.RIGHT);
				ivjJLabel111.setName("JLabel111");
				ivjJLabel111.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel111.setText("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442 \u0442\u0435\u0441\u0442\u0443");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel111;
	}

	/**
	 * Return the JLabel1111 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel1111() {
		if (ivjJLabel1111 == null) {
			try {
				ivjJLabel1111 = new javax.swing.JLabel();
				ivjJLabel1111.setHorizontalAlignment(SwingConstants.RIGHT);
				ivjJLabel1111.setName("JLabel1111");
				ivjJLabel1111.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel1111.setText("\u0427\u0430\u0441 \u043C\u043E\u0434\u0435\u043B\u044E\u0432\u0430\u043D\u043D\u044F");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel1111;
	}

	/**
	 * Return the JLabel11111 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel11111() {
		if (ivjJLabel11111 == null) {
			try {
				ivjJLabel11111 = new javax.swing.JLabel();
				ivjJLabel11111.setHorizontalAlignment(SwingConstants.RIGHT);
				ivjJLabel11111.setName("JLabel11111");
				ivjJLabel11111.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel11111.setText("\u041A\u0440\u043E\u043A \u0456\u043D\u0442\u0435\u0433\u0440\u0443\u0432\u0430\u043D\u043D\u044F");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel11111;
	}

	/**
	 * Return the JTFError property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFDynError() {
		if (ivjJTFDynError == null) {
			try {
				ivjJTFDynError = new javax.swing.JTextField();
				ivjJTFDynError.setName("JTFDynError");
				ivjJTFDynError
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFDynError;
	}

	/**
	 * Return the JTFTime property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFFinishTime() {
		if (ivjJTFFinishTime == null) {
			try {
				ivjJTFFinishTime = new javax.swing.JTextField();
				ivjJTFFinishTime.setName("JTFFinishTime");
				ivjJTFFinishTime.setText("5");
				ivjJTFFinishTime.setMinimumSize(new java.awt.Dimension(18, 18));
				ivjJTFFinishTime
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFFinishTime;
	}

	/**
	 * Return the JTFStep property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFStep() {
		if (ivjJTFStep == null) {
			try {
				ivjJTFStep = new javax.swing.JTextField();
				ivjJTFStep.setName("JTFStep");
				ivjJTFStep.setText("0.1");
				ivjJTFStep.setMinimumSize(new java.awt.Dimension(21, 18));
				ivjJTFStep.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				ivjJTFStep.setMaximumSize(new java.awt.Dimension(21, 18));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFStep;
	}

	/**
	 * 
	 * @return double
	 */
	public double getStep() {
		return getStepTimer().getStep();
	}

	/**
	 * Return the StepTimer property value.
	 * 
	 * @return toLab8_testGradient.StepTimer
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private StepTimer getStepTimer() {
		if (ivjStepTimer == null) {
			try {
				ivjStepTimer = new toLab8_testGradient.StepTimer();
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjStepTimer;
	}

	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* Uncomment the following lines to print uncaught exceptions to stdout */
		System.out.println("--------- UNCAUGHT EXCEPTION ---------");
		exception.printStackTrace(System.out);
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
		getJButton1().addActionListener(ivjEventHandler);
		getJTFFinishTime().addCaretListener(ivjEventHandler);
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("GradientMonitorView");
			setBorder(new javax.swing.border.EtchedBorder());
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0};
			setLayout(gridBagLayout);
			setSize(464, 91);

			java.awt.GridBagConstraints constraintsJLabel1111 = new java.awt.GridBagConstraints();
			constraintsJLabel1111.gridx = 4;
			constraintsJLabel1111.gridy = 0;
			constraintsJLabel1111.ipadx = 3;
			constraintsJLabel1111.insets = new Insets(5, 5, 5, 5);
			add(getJLabel1111(), constraintsJLabel1111);

			java.awt.GridBagConstraints constraintsJTFFinishTime = new java.awt.GridBagConstraints();
			constraintsJTFFinishTime.gridx = 5;
			constraintsJTFFinishTime.gridy = 0;
			constraintsJTFFinishTime.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFFinishTime.weightx = 1.0;
			constraintsJTFFinishTime.ipadx = 21;
			constraintsJTFFinishTime.insets = new Insets(5, 0, 5, 0);
			add(getJTFFinishTime(), constraintsJTFFinishTime);

			java.awt.GridBagConstraints constraintsJLabel11111 = new java.awt.GridBagConstraints();
			constraintsJLabel11111.gridx = 1;
			constraintsJLabel11111.gridy = 0;
			constraintsJLabel11111.gridwidth = 2;
			constraintsJLabel11111.ipadx = 7;
			constraintsJLabel11111.insets = new Insets(5, 2, 5, 5);
			add(getJLabel11111(), constraintsJLabel11111);

			java.awt.GridBagConstraints constraintsJTFStep = new java.awt.GridBagConstraints();
			constraintsJTFStep.gridx = 3;
			constraintsJTFStep.gridy = 0;
			constraintsJTFStep.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFStep.weightx = 1.0;
			constraintsJTFStep.ipadx = 38;
			constraintsJTFStep.insets = new Insets(5, 1, 5, 5);
			add(getJTFStep(), constraintsJTFStep);

			java.awt.GridBagConstraints constraintsJButton1 = new java.awt.GridBagConstraints();
			constraintsJButton1.gridx = 1;
			constraintsJButton1.gridy = 2;
			constraintsJButton1.ipadx = 23;
			constraintsJButton1.insets = new Insets(5, 5, 5, 5);
			add(getJButton1(), constraintsJButton1);

			java.awt.GridBagConstraints constraintsJTFDynError = new java.awt.GridBagConstraints();
			constraintsJTFDynError.gridx = 4;
			constraintsJTFDynError.gridy = 2;
			constraintsJTFDynError.gridwidth = 2;
			constraintsJTFDynError.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFDynError.weightx = 1.0;
			constraintsJTFDynError.ipadx = 172;
			constraintsJTFDynError.insets = new Insets(5, 2, 5, 0);
			add(getJTFDynError(), constraintsJTFDynError);

			java.awt.GridBagConstraints constraintsJLabel111 = new java.awt.GridBagConstraints();
			constraintsJLabel111.fill = GridBagConstraints.HORIZONTAL;
			constraintsJLabel111.gridx = 2;
			constraintsJLabel111.gridy = 2;
			constraintsJLabel111.gridwidth = 2;
			constraintsJLabel111.ipadx = 8;
			constraintsJLabel111.insets = new Insets(5, 4, 5, 5);
			add(getJLabel111(), constraintsJLabel111);

			java.awt.GridBagConstraints constraintsJLabel1 = new java.awt.GridBagConstraints();
			constraintsJLabel1.gridwidth = 2;
			constraintsJLabel1.gridx = 0;
			constraintsJLabel1.gridy = 1;
			constraintsJLabel1.ipadx = 16;
			constraintsJLabel1.insets = new Insets(1, 1, 1, 5);
			add(getJLabel1(), constraintsJLabel1);
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
			StepTimerView aStepTimerView;
			aStepTimerView = new StepTimerView();
			frame.setContentPane(aStepTimerView);
			frame.setSize(aStepTimerView.getSize());
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

	/**
	 * 
	 * @param newListener
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public void removeStepTimerViewListener(
			toLab8_testGradient.StepTimerViewListener newListener) {
		fieldStepTimerViewListenerEventMulticaster = toLab8_testGradient.StepTimerViewListenerEventMulticaster
				.remove(fieldStepTimerViewListenerEventMulticaster, newListener);
		return;
	}

	/**
	 * Insert the method's description here. Creation date: (20.04.2006
	 * 22:19:14)
	 * 
	 * @param param
	 *            toLab8_testGradient.IOperatingObject
	 */
	public void setIController(IController param) {
		getStepTimer().setIController(param);
	}

	/**
	 * Insert the method's description here. Creation date: (20.04.2006
	 * 22:19:14)
	 * 
	 * @param param
	 *            toLab8_testGradient.IOperatingObject
	 */
	public void setIOperatingObject(IOperatingObject param) {
		getStepTimer().setIOperatingObject(param);
	}

	/**
	 * Comment
	 */
	public void test() {
		// Настройка монитора
		getJButton1().setEnabled(false);
		double finishTime = Double.parseDouble(getJTFFinishTime().getText());
		double step = Double.parseDouble(getJTFStep().getText());
		getStepTimer().setFinishTime(finishTime);
		getStepTimer().setStep(step);
		// Запуск процесса моделирования
		getStepTimer().test();
		// Вывод результата моделирования
		double dinError = getStepTimer().getController().getResult();
		getJTFDynError().setText(String.valueOf(dinError));
		getJButton1().setEnabled(true);
	}
}
