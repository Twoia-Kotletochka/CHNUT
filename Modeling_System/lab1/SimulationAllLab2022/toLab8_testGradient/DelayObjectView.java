package toLab8_testGradient;
import java.awt.GridBagConstraints;

public class DelayObjectView extends javax.swing.JPanel implements
		IOperatingObject {
	private javax.swing.JLabel ivjJLabel1 = null;

	private javax.swing.JLabel ivjJLabel11 = null;

	private javax.swing.JLabel ivjJLabel21 = null;

	private javax.swing.JLabel ivjJLabel311 = null;

	private javax.swing.JLabel ivjJLabel321 = null;

	private javax.swing.JLabel ivjJLabel33 = null;

	private javax.swing.JLabel ivjJLabel111 = null;

	private javax.swing.JTextField ivjJTFDelay = null;

	private javax.swing.JTextField ivjJTFKAmplifier = null;

	private javax.swing.JTextField ivjJTFTimeConst = null;

	private javax.swing.JTextField ivjJTFDisturb = null;

	private javax.swing.JTextField ivjJTFOuput0 = null;

	private DelayObject ivjDelayObject = null;

	/**
	 * ControlObjectView constructor comment.
	 */
	public DelayObjectView() {
		super();
		initialize();
	}

	/**
	 * ControlObjectView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 */
	public DelayObjectView(java.awt.LayoutManager layout) {
		super(layout);
	}

	/**
	 * ControlObjectView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public DelayObjectView(java.awt.LayoutManager layout,
			boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * ControlObjectView constructor comment.
	 * 
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public DelayObjectView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * Return the ControlObject property value.
	 * 
	 * @return toLab8_testGradient.ControlObject
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private DelayObject getDelayObject() {
		if (ivjDelayObject == null) {
			try {
				ivjDelayObject = new toLab8_testGradient.DelayObject();
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjDelayObject;
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
				ivjJLabel1.setText("ОБ'ЕКТ КЕРУВАННЯ");
				ivjJLabel1
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
	 * Return the JLabel11 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel11() {
		if (ivjJLabel11 == null) {
			try {
				ivjJLabel11 = new javax.swing.JLabel();
				ivjJLabel11.setName("JLabel11");
				ivjJLabel11.setFont(new java.awt.Font("sansserif", 0, 12));
				ivjJLabel11.setText("Збурення");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel11;
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
				ivjJLabel111.setName("JLabel111");
				ivjJLabel111.setFont(new java.awt.Font("sansserif", 0, 12));
				ivjJLabel111.setText("Початковий стан");
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
	 * Return the JLabel21 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel21() {
		if (ivjJLabel21 == null) {
			try {
				ivjJLabel21 = new javax.swing.JLabel();
				ivjJLabel21.setName("JLabel21");
				ivjJLabel21.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJLabel21.setText("Параметри об'екту");
				ivjJLabel21
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel21;
	}

	/**
	 * Return the JLabel311 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel311() {
		if (ivjJLabel311 == null) {
			try {
				ivjJLabel311 = new javax.swing.JLabel();
				ivjJLabel311.setName("JLabel311");
				ivjJLabel311.setFont(new java.awt.Font("sansserif", 0, 12));
				ivjJLabel311.setText("Стала часу");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel311;
	}

	/**
	 * Return the JLabel321 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel321() {
		if (ivjJLabel321 == null) {
			try {
				ivjJLabel321 = new javax.swing.JLabel();
				ivjJLabel321.setName("JLabel321");
				ivjJLabel321.setToolTipText("");
				ivjJLabel321.setFont(new java.awt.Font("sansserif", 0, 12));
				ivjJLabel321.setText("Затримка реакції");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel321;
	}

	/**
	 * Return the JLabel33 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel33() {
		if (ivjJLabel33 == null) {
			try {
				ivjJLabel33 = new javax.swing.JLabel();
				ivjJLabel33.setName("JLabel33");
				ivjJLabel33.setFont(new java.awt.Font("sansserif", 0, 12));
				ivjJLabel33.setText("Коеф. підсилення");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel33;
	}

	/**
	 * Return the JTFDelay property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFDelay() {
		if (ivjJTFDelay == null) {
			try {
				ivjJTFDelay = new javax.swing.JTextField();
				ivjJTFDelay.setName("JTFDelay");
				ivjJTFDelay.setText("0.5");
				ivjJTFDelay
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFDelay;
	}

	/**
	 * Return the JTFHidranse property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFDisturb() {
		if (ivjJTFDisturb == null) {
			try {
				ivjJTFDisturb = new javax.swing.JTextField();
				ivjJTFDisturb.setName("JTFDisturb");
				ivjJTFDisturb.setText("-20");
				ivjJTFDisturb
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFDisturb;
	}

	/**
	 * Return the JTFKAmplifier property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFKAmplifier() {
		if (ivjJTFKAmplifier == null) {
			try {
				ivjJTFKAmplifier = new javax.swing.JTextField();
				ivjJTFKAmplifier.setName("JTFKAmplifier");
				ivjJTFKAmplifier.setText("1");
				ivjJTFKAmplifier
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFKAmplifier;
	}

	/**
	 * Return the JTFOuput0 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFOuput0() {
		if (ivjJTFOuput0 == null) {
			try {
				ivjJTFOuput0 = new javax.swing.JTextField();
				ivjJTFOuput0.setName("JTFOuput0");
				ivjJTFOuput0.setText("20");
				ivjJTFOuput0
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFOuput0;
	}

	/**
	 * Return the JTFTimeConst property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFTimeConst() {
		if (ivjJTFTimeConst == null) {
			try {
				ivjJTFTimeConst = new javax.swing.JTextField();
				ivjJTFTimeConst.setName("JTFTimeConst");
				ivjJTFTimeConst.setText("1");
				ivjJTFTimeConst
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFTimeConst;
	}

	/**
	 * getOutput method comment.
	 */
	public double getOutput() {
		return getDelayObject().getOutput();
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
	 * Insert the method's description here. Creation date: (11.04.2006
	 * 10:39:34)
	 */
	public void init() {
		getDelayObject().setDelay(Double.parseDouble(getJTFDelay().getText()));
		getDelayObject().setKAmpf(
				Double.parseDouble(getJTFKAmplifier().getText()));
		getDelayObject().setTConst(
				Double.parseDouble(getJTFTimeConst().getText()));
		getDelayObject().setDisturb(
				Double.parseDouble(getJTFDisturb().getText()));
		getDelayObject()
				.setOutput(Double.parseDouble(getJTFOuput0().getText()));
		getDelayObject().init();
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("ControlObjectView");
			setBorder(new javax.swing.border.EtchedBorder());
			setLayout(new java.awt.GridBagLayout());
			setSize(234, 202);

			java.awt.GridBagConstraints constraintsJLabel11 = new java.awt.GridBagConstraints();
			constraintsJLabel11.anchor = GridBagConstraints.EAST;
			constraintsJLabel11.gridx = 1;
			constraintsJLabel11.gridy = 2;
			add(getJLabel11(), constraintsJLabel11);

			java.awt.GridBagConstraints constraintsJTFDisturb = new java.awt.GridBagConstraints();
			constraintsJTFDisturb.gridx = 2;
			constraintsJTFDisturb.gridy = 2;
			constraintsJTFDisturb.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFDisturb.weightx = 1.0;
			constraintsJTFDisturb.ipadx = 28;
			constraintsJTFDisturb.insets = new java.awt.Insets(2, 1, 1, 8);
			add(getJTFDisturb(), constraintsJTFDisturb);

			java.awt.GridBagConstraints constraintsJLabel21 = new java.awt.GridBagConstraints();
			constraintsJLabel21.gridx = 1;
			constraintsJLabel21.gridy = 4;
			constraintsJLabel21.gridwidth = 2;
			add(getJLabel21(), constraintsJLabel21);

			java.awt.GridBagConstraints constraintsJLabel33 = new java.awt.GridBagConstraints();
			constraintsJLabel33.anchor = GridBagConstraints.EAST;
			constraintsJLabel33.gridx = 1;
			constraintsJLabel33.gridy = 5;
			add(getJLabel33(), constraintsJLabel33);

			java.awt.GridBagConstraints constraintsJLabel311 = new java.awt.GridBagConstraints();
			constraintsJLabel311.anchor = GridBagConstraints.EAST;
			constraintsJLabel311.gridx = 1;
			constraintsJLabel311.gridy = 6;
			add(getJLabel311(), constraintsJLabel311);

			java.awt.GridBagConstraints constraintsJLabel321 = new java.awt.GridBagConstraints();
			constraintsJLabel321.anchor = GridBagConstraints.EAST;
			constraintsJLabel321.gridx = 1;
			constraintsJLabel321.gridy = 7;
			add(getJLabel321(), constraintsJLabel321);

			java.awt.GridBagConstraints constraintsJTFKAmplifier = new java.awt.GridBagConstraints();
			constraintsJTFKAmplifier.gridx = 2;
			constraintsJTFKAmplifier.gridy = 5;
			constraintsJTFKAmplifier.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFKAmplifier.weightx = 1.0;
			constraintsJTFKAmplifier.ipadx = 28;
			constraintsJTFKAmplifier.insets = new java.awt.Insets(2, 1, 2, 8);
			add(getJTFKAmplifier(), constraintsJTFKAmplifier);

			java.awt.GridBagConstraints constraintsJTFTimeConst = new java.awt.GridBagConstraints();
			constraintsJTFTimeConst.gridx = 2;
			constraintsJTFTimeConst.gridy = 6;
			constraintsJTFTimeConst.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFTimeConst.weightx = 1.0;
			constraintsJTFTimeConst.ipadx = 28;
			constraintsJTFTimeConst.insets = new java.awt.Insets(2, 1, 2, 8);
			add(getJTFTimeConst(), constraintsJTFTimeConst);

			java.awt.GridBagConstraints constraintsJTFDelay = new java.awt.GridBagConstraints();
			constraintsJTFDelay.gridx = 2;
			constraintsJTFDelay.gridy = 7;
			constraintsJTFDelay.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFDelay.weightx = 1.0;
			constraintsJTFDelay.ipadx = 28;
			constraintsJTFDelay.insets = new java.awt.Insets(2, 1, 4, 8);
			add(getJTFDelay(), constraintsJTFDelay);

			java.awt.GridBagConstraints constraintsJLabel1 = new java.awt.GridBagConstraints();
			constraintsJLabel1.gridx = 1;
			constraintsJLabel1.gridy = 1;
			constraintsJLabel1.gridwidth = 2;
			add(getJLabel1(), constraintsJLabel1);

			java.awt.GridBagConstraints constraintsJLabel111 = new java.awt.GridBagConstraints();
			constraintsJLabel111.anchor = GridBagConstraints.EAST;
			constraintsJLabel111.gridx = 1;
			constraintsJLabel111.gridy = 3;
			add(getJLabel111(), constraintsJLabel111);

			java.awt.GridBagConstraints constraintsJTFOuput0 = new java.awt.GridBagConstraints();
			constraintsJTFOuput0.gridx = 2;
			constraintsJTFOuput0.gridy = 3;
			constraintsJTFOuput0.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFOuput0.weightx = 1.0;
			constraintsJTFOuput0.ipadx = 28;
			constraintsJTFOuput0.insets = new java.awt.Insets(2, 1, 1, 8);
			add(getJTFOuput0(), constraintsJTFOuput0);
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
			DelayObjectView aDelayObjectView;
			aDelayObjectView = new DelayObjectView();
			frame.setContentPane(aDelayObjectView);
			frame.setSize(aDelayObjectView.getSize());
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
	 * setInput method comment.
	 */
	public void onSetInput(double u) {
		getDelayObject().onSetInput(u);
	}

	/**
	 * Method generated to support the promotion of the delayObjectStepTimer
	 * attribute.
	 * 
	 * @param arg1
	 *            toLab8_testGradient.IStepTimer
	 */
	public void setIStepTimer(IStepTimer arg1) {
		getDelayObject().setIStepTimer(arg1);
	}

	/**
	 * Method generated to support the promotion of the delayObjectPainter
	 * attribute.
	 * 
	 * @param arg1
	 *            paint.Painter
	 */
	public void setPainter(widgets.Painter arg1) {
		getDelayObject().setPainter(arg1);
	}
}
