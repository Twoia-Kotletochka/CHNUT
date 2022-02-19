package toLab8_testGradient;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

public class PidControllerView extends javax.swing.JPanel implements
		IController {
	private javax.swing.JLabel ivjJLabel1 = null;

	private javax.swing.JLabel ivjJLabel2 = null;

	private javax.swing.JLabel ivjJLabel3 = null;

	private javax.swing.JLabel ivjJLabel31 = null;

	private javax.swing.JLabel ivjJLabel32 = null;

	private javax.swing.JTextField ivjJTFDifer = null;

	private javax.swing.JTextField ivjJTFIntegr = null;

	private javax.swing.JTextField ivjJTFProportional = null;

	private javax.swing.JTextField ivjJTFTarget = null;

	private javax.swing.JLabel ivjJLabel4 = null;

	private PidController ivjPidController = null;

	private javax.swing.JLabel ivjJLabel11 = null;

	private javax.swing.JTextField ivjJTFOutput0 = null;

	/**
	 * PidControllerView constructor comment.
	 */
	public PidControllerView() {
		super();
		initialize();
	}

	/**
	 * PidControllerView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 */
	public PidControllerView(java.awt.LayoutManager layout) {
		super(layout);
	}

	/**
	 * PidControllerView constructor comment.
	 * 
	 * @param layout
	 *            java.awt.LayoutManager
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public PidControllerView(java.awt.LayoutManager layout,
			boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * PidControllerView constructor comment.
	 * 
	 * @param isDoubleBuffered
	 *            boolean
	 */
	public PidControllerView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
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
				ivjJLabel1.setAlignmentX(Component.RIGHT_ALIGNMENT);
				ivjJLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel1.setHorizontalAlignment(SwingConstants.LEFT);
				ivjJLabel1.setName("JLabel1");
				ivjJLabel1.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel1.setText("Задане значення  ");
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
				ivjJLabel11.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel11.setHorizontalAlignment(SwingConstants.LEFT);
				ivjJLabel11.setName("JLabel11");
				ivjJLabel11.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel11.setText("Стартове знач. на виході");
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
	 * Return the JLabel2 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel2() {
		if (ivjJLabel2 == null) {
			try {
				ivjJLabel2 = new javax.swing.JLabel();
				ivjJLabel2.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel2.setName("JLabel2");
				ivjJLabel2.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJLabel2.setText("Налаштування складових");
				ivjJLabel2
						.setHorizontalAlignment(SwingConstants.LEFT);
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
	 * Return the JLabel3 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel3() {
		if (ivjJLabel3 == null) {
			try {
				ivjJLabel3 = new javax.swing.JLabel();
				ivjJLabel3.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel3.setHorizontalAlignment(SwingConstants.LEFT);
				ivjJLabel3.setName("JLabel3");
				ivjJLabel3.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel3.setText("Пропорційна");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel3;
	}

	/**
	 * Return the JLabel31 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel31() {
		if (ivjJLabel31 == null) {
			try {
				ivjJLabel31 = new javax.swing.JLabel();
				ivjJLabel31.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel31.setHorizontalAlignment(SwingConstants.LEFT);
				ivjJLabel31.setName("JLabel31");
				ivjJLabel31.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel31.setText("Інтегральна");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel31;
	}

	/**
	 * Return the JLabel32 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel32() {
		if (ivjJLabel32 == null) {
			try {
				ivjJLabel32 = new javax.swing.JLabel();
				ivjJLabel32.setHorizontalTextPosition(SwingConstants.LEFT);
				ivjJLabel32.setHorizontalAlignment(SwingConstants.LEFT);
				ivjJLabel32.setName("JLabel32");
				ivjJLabel32.setFont(new java.awt.Font("dialog", 0, 12));
				ivjJLabel32.setText("Диференціальна");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel32;
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
				ivjJLabel4.setFont(new java.awt.Font("sansserif", 1, 12));
				ivjJLabel4.setText("ПІД-РЕГУЛЯТОР");
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
	 * Return the JTFDifer property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFDifer() {
		if (ivjJTFDifer == null) {
			try {
				ivjJTFDifer = new javax.swing.JTextField();
				ivjJTFDifer.setName("JTFDifer");
				ivjJTFDifer.setText("2");
				ivjJTFDifer
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFDifer;
	}

	/**
	 * Return the JTFIntegr property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFIntegr() {
		if (ivjJTFIntegr == null) {
			try {
				ivjJTFIntegr = new javax.swing.JTextField();
				ivjJTFIntegr.setName("JTFIntegr");
				ivjJTFIntegr.setText("2");
				ivjJTFIntegr
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFIntegr;
	}

	/**
	 * Return the JTFOutput0 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFOutput0() {
		if (ivjJTFOutput0 == null) {
			try {
				ivjJTFOutput0 = new javax.swing.JTextField();
				ivjJTFOutput0.setName("JTFOutput0");
				ivjJTFOutput0.setText("20");
				ivjJTFOutput0
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFOutput0;
	}

	/**
	 * Return the JTFProportional property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFProportional() {
		if (ivjJTFProportional == null) {
			try {
				ivjJTFProportional = new javax.swing.JTextField();
				ivjJTFProportional.setName("JTFProportional");
				ivjJTFProportional.setText("2");
				ivjJTFProportional
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFProportional;
	}

	/**
	 * Return the JTFTarget property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTFTarget() {
		if (ivjJTFTarget == null) {
			try {
				ivjJTFTarget = new javax.swing.JTextField();
				ivjJTFTarget.setName("JTFTarget");
				ivjJTFTarget.setText("20");
				ivjJTFTarget
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTFTarget;
	}

	/**
	 * setPidParameters method comment.
	 */
	public double[] getParameters() {
		double[] pid = new double[3];
		pid[0] = Double.parseDouble(getJTFProportional().getText());
		pid[1] = Double.parseDouble(getJTFIntegr().getText());
		pid[2] = Double.parseDouble(getJTFDifer().getText());
		return pid;
	}

	/**
	 * Return the PidController property value.
	 * 
	 * @return toLab8_testGradient.PidController
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private PidController getPidController() {
		if (ivjPidController == null) {
			try {
				ivjPidController = new toLab8_testGradient.PidController();
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjPidController;
	}

	/**
	 * getResult method comment.
	 */
	public double getResult() {
		return getPidController().getResult();
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
	 * init method comment.
	 */
	public void init() {
		double[] pid = new double[3];
		pid[0] = Double.parseDouble(getJTFProportional().getText());
		pid[1] = Double.parseDouble(getJTFIntegr().getText());
		pid[2] = Double.parseDouble(getJTFDifer().getText());
		getPidController().setParameters(pid);
		getPidController().setTarget(
				Double.parseDouble(getJTFTarget().getText()));
		getPidController().setOutput(
				Double.parseDouble(getJTFOutput0().getText()));
		getPidController().init();
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("PidControllerView");
			setBorder(new javax.swing.border.EtchedBorder());
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 160, 0};
			setLayout(gridBagLayout);
			setSize(199, 188);

			java.awt.GridBagConstraints constraintsJLabel2 = new java.awt.GridBagConstraints();
			constraintsJLabel2.gridx = 1;
			constraintsJLabel2.gridy = 4;
			constraintsJLabel2.gridwidth = 2;
			add(getJLabel2(), constraintsJLabel2);

			java.awt.GridBagConstraints constraintsJLabel1 = new java.awt.GridBagConstraints();
			constraintsJLabel1.anchor = GridBagConstraints.EAST;
			constraintsJLabel1.gridx = 1;
			constraintsJLabel1.gridy = 2;
			add(getJLabel1(), constraintsJLabel1);

			java.awt.GridBagConstraints constraintsJLabel3 = new java.awt.GridBagConstraints();
			constraintsJLabel3.gridx = 1;
			constraintsJLabel3.gridy = 5;
			add(getJLabel3(), constraintsJLabel3);

			java.awt.GridBagConstraints constraintsJLabel31 = new java.awt.GridBagConstraints();
			constraintsJLabel31.gridx = 1;
			constraintsJLabel31.gridy = 6;
			add(getJLabel31(), constraintsJLabel31);

			java.awt.GridBagConstraints constraintsJLabel32 = new java.awt.GridBagConstraints();
			constraintsJLabel32.gridx = 1;
			constraintsJLabel32.gridy = 7;
			add(getJLabel32(), constraintsJLabel32);

			java.awt.GridBagConstraints constraintsJTFDifer = new java.awt.GridBagConstraints();
			constraintsJTFDifer.gridx = 2;
			constraintsJTFDifer.gridy = 7;
			constraintsJTFDifer.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFDifer.weightx = 1.0;
			constraintsJTFDifer.ipadx = 28;
			constraintsJTFDifer.insets = new java.awt.Insets(3, 3, 7, 9);
			add(getJTFDifer(), constraintsJTFDifer);

			java.awt.GridBagConstraints constraintsJTFIntegr = new java.awt.GridBagConstraints();
			constraintsJTFIntegr.gridx = 2;
			constraintsJTFIntegr.gridy = 6;
			constraintsJTFIntegr.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFIntegr.weightx = 1.0;
			constraintsJTFIntegr.ipadx = 28;
			constraintsJTFIntegr.insets = new java.awt.Insets(3, 3, 2, 9);
			add(getJTFIntegr(), constraintsJTFIntegr);

			java.awt.GridBagConstraints constraintsJTFProportional = new java.awt.GridBagConstraints();
			constraintsJTFProportional.gridx = 2;
			constraintsJTFProportional.gridy = 5;
			constraintsJTFProportional.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFProportional.weightx = 1.0;
			constraintsJTFProportional.ipadx = 28;
			constraintsJTFProportional.insets = new java.awt.Insets(0, 3, 2, 9);
			add(getJTFProportional(), constraintsJTFProportional);

			java.awt.GridBagConstraints constraintsJTFTarget = new java.awt.GridBagConstraints();
			constraintsJTFTarget.gridx = 2;
			constraintsJTFTarget.gridy = 2;
			constraintsJTFTarget.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFTarget.weightx = 1.0;
			constraintsJTFTarget.ipadx = 28;
			constraintsJTFTarget.insets = new java.awt.Insets(1, 3, 2, 9);
			add(getJTFTarget(), constraintsJTFTarget);

			java.awt.GridBagConstraints constraintsJLabel4 = new java.awt.GridBagConstraints();
			constraintsJLabel4.gridx = 1;
			constraintsJLabel4.gridy = 1;
			constraintsJLabel4.gridwidth = 2;
			add(getJLabel4(), constraintsJLabel4);

			java.awt.GridBagConstraints constraintsJLabel11 = new java.awt.GridBagConstraints();
			constraintsJLabel11.anchor = GridBagConstraints.EAST;
			constraintsJLabel11.gridx = 1;
			constraintsJLabel11.gridy = 3;
			constraintsJLabel11.insets = new Insets(0, 0, 0, 8);
			add(getJLabel11(), constraintsJLabel11);

			java.awt.GridBagConstraints constraintsJTFOutput0 = new java.awt.GridBagConstraints();
			constraintsJTFOutput0.gridx = 2;
			constraintsJTFOutput0.gridy = 3;
			constraintsJTFOutput0.fill = java.awt.GridBagConstraints.HORIZONTAL;
			constraintsJTFOutput0.weightx = 1.0;
			constraintsJTFOutput0.ipadx = 28;
			constraintsJTFOutput0.insets = new java.awt.Insets(3, 3, 1, 9);
			add(getJTFOutput0(), constraintsJTFOutput0);
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
			PidControllerView aPidControllerView;
			aPidControllerView = new PidControllerView();
			frame.setContentPane(aPidControllerView);
			frame.setSize(aPidControllerView.getSize());
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
	 * onChangeTime method comment.
	 */
	public void onChangeTime() {
		getPidController().onChangeTime();
	}

	/**
	 * Method generated to support the promotion of the IOperatingObject
	 * attribute.
	 * 
	 * @param arg1
	 *            toLab8_testGradient.IOperatingObject
	 */
	public void setIOperatingObject(IOperatingObject arg1) {
		getPidController().setIOperatingObject(arg1);
	}

	/**
	 * Method generated to support the promotion of the IStepTimer attribute.
	 * 
	 * @param arg1
	 *            toLab8_testGradient.IStepTimer
	 */
	public void setIStepTimer(IStepTimer arg1) {
		getPidController().setIStepTimer(arg1);
	}

	/**
	 * Method generated to support the promotion of the Painter attribute.
	 * 
	 * @param arg1
	 *            paint.Painter
	 */
	public void setPainter(widgets.Painter arg1) {
		getPidController().setPainter(arg1);
	}

	/**
	 * setPidParameters method comment.
	 */
	public void setParameters(double[] pid) {
		getJTFProportional().setText(String.valueOf(pid[0]));
		getJTFProportional().select(0, 0);
		getJTFIntegr().setText(String.valueOf(pid[1]));
		getJTFIntegr().select(0, 0);
		getJTFDifer().setText(String.valueOf(pid[2]));
		getJTFDifer().select(0, 0);
	}
}
