package toLab2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import stat.StatTables;

/**
 * Insert the type's description here. Creation date: (03.11.2005 12:44:47)
 * 
 * @author: Administrator
 */
public class TestRandomUI extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private javax.swing.JPanel ivjJFrameContentPane = null;

	private javax.swing.JPanel ivjJPanel1 = null;

	private javax.swing.JLabel ivjJLabel1 = null;

	private javax.swing.JLabel ivjJLabel2 = null;

	private javax.swing.JLabel ivjJLabel3 = null;

	private javax.swing.JLabel ivjJLabel4 = null;

	private javax.swing.JButton ivjJButtonStand = null;

	private javax.swing.JTextField ivjJTextFieldAdd = null;

	private javax.swing.JTextField ivjJTextFieldMask = null;

	private javax.swing.JTextField ivjJTextFieldMult = null;

	IvjEventHandler ivjEventHandler = new IvjEventHandler();

	private javax.swing.JLabel ivjJLabel5 = null;

	private javax.swing.JPanel ivjJPanel2 = null;

	private javax.swing.JLabel ivjJLabel6 = null;

	private javax.swing.JScrollPane ivjJScrollPane1 = null;

	private javax.swing.JScrollPane ivjJScrollPane2 = null;

	private javax.swing.JLabel ivjJLabel7 = null;

	private javax.swing.JLabel ivjJLabel8 = null;

	private javax.swing.JPanel ivjJPanel3 = null;

	private javax.swing.JPanel ivjJPanel4 = null;

	private javax.swing.JPanel ivjJPanel5 = null;

	private javax.swing.JLabel ivjJLabel10 = null;

	private javax.swing.JLabel ivjJLabel101 = null;

	private javax.swing.JLabel ivjJLabel1011 = null;

	private javax.swing.JLabel ivjJLabel9 = null;

	private javax.swing.JTextField ivjJTextFieldInterval = null;

	private javax.swing.JTextField ivjJTextFieldParametr = null;

	private javax.swing.JTextField ivjJTextFieldShift = null;

	private javax.swing.JTextArea ivjJTextAreaResult = null;

	private javax.swing.JButton ivjJButtonTest = null;

	private javax.swing.JList ivjJListTests = null;

	private javax.swing.JTextField ivjJTextFieldVViborki = null;

	private javax.swing.DefaultListModel ivjDefaultListModel1 = null;

	private javax.swing.JLabel ivjJLabel12 = null;

	private javax.swing.JLabel ivjJLabel13 = null;

	private javax.swing.JLabel ivjJLabel14 = null;

	private javax.swing.JLabel ivjJLabel15 = null;

	private widgets.Diagram ivjDiagram = null;

	class IvjEventHandler implements java.awt.event.ActionListener,
			java.awt.event.MouseListener, java.awt.event.WindowListener,
			javax.swing.event.CaretListener,
			javax.swing.event.ListSelectionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == TestRandomUI.this.getJTextFieldVViborki())
				connEtoC5(e);
			if (e.getSource() == TestRandomUI.this.getJButtonTest())
				connEtoM1(e);
			if (e.getSource() == TestRandomUI.this.getJButtonTest())
				connEtoC4(e);
		};

		public void caretUpdate(javax.swing.event.CaretEvent e) {
			if (e.getSource() == TestRandomUI.this.getJTextFieldShift())
				connEtoC6(e);
			if (e.getSource() == TestRandomUI.this.getJTextFieldVViborki())
				connEtoC3(e);
		};

		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getSource() == TestRandomUI.this.getJButtonStand())
				connEtoC2(e);
		};

		public void mouseEntered(java.awt.event.MouseEvent e) {
		};

		public void mouseExited(java.awt.event.MouseEvent e) {
		};

		public void mousePressed(java.awt.event.MouseEvent e) {
		};

		public void mouseReleased(java.awt.event.MouseEvent e) {
		};

		public void valueChanged(javax.swing.event.ListSelectionEvent e) {
			if (e.getSource() == TestRandomUI.this.getJListTests())
				connEtoC8(e);
		};

		public void windowActivated(java.awt.event.WindowEvent e) {
		};

		public void windowClosed(java.awt.event.WindowEvent e) {
		};

		public void windowClosing(java.awt.event.WindowEvent e) {
		};

		public void windowDeactivated(java.awt.event.WindowEvent e) {
		};

		public void windowDeiconified(java.awt.event.WindowEvent e) {
		};

		public void windowIconified(java.awt.event.WindowEvent e) {
		};

		public void windowOpened(java.awt.event.WindowEvent e) {
			if (e.getSource() == TestRandomUI.this)
				connEtoC1(e);
			if (e.getSource() == TestRandomUI.this)
				connEtoC7(e);
		};
	};

	/**
	 * TestRandomVeiw constructor comment.
	 */
	public TestRandomUI() {
		super();
		initialize();
	}

	/**
	 * TestRandomVeiw constructor comment.
	 * 
	 * @param title
	 *            java.lang.String
	 */
	public TestRandomUI(String title) {
		super(title);
	}

	/**
	 * Insert the method's description here. Creation date: (28.11.2005
	 * 22:02:16)
	 * 
	 * @param item
	 *            java.lang.String
	 */
	public void changeItem(String item) {

		getJLabel10().setText("Обсяг вибірки");
		getJTextFieldInterval().setVisible(false);
		getJLabel101().setVisible(false);

		getJLabel1011().setVisible(false);
		getJTextFieldParametr().setVisible(false);
		getJTextFieldShift().setVisible(false);

		if (item == " на період") {
			getJLabel10().setText("Пропустити");
			getJLabel101().setVisible(true);
			getJLabel101().setText("Час чекання");
			getJTextFieldInterval().setVisible(true);
			getJTextFieldInterval().setText("10000");
		}
		if (item == " на рівномірність") {
			getJLabel101().setVisible(true);
			getJTextFieldInterval().setVisible(true);
			getJLabel101().setText("Кільк. інтервалів");
			getJTextFieldInterval().setText("10");

		}
		if (item == " на стохостичність") {
			getJLabel1011().setVisible(true);
			getJTextFieldShift().setVisible(false);
			getJTextFieldParametr().setVisible(true);
		}
		if (item == " на незалежність") {
			getJLabel1011().setVisible(true);
			getJTextFieldParametr().setVisible(false);
			getJTextFieldShift().setVisible(true);

		}
	}

	/**
	 * connEtoC1:
	 * (TestRandomVeiw.window.windowOpened(java.awt.event.WindowEvent) -->
	 * TestRandomVeiw.createStandadGenerator()V)
	 * 
	 * @param arg1
	 *            java.awt.event.WindowEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC1(java.awt.event.WindowEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.setStandadParameters();
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC2: (JButtonStand.mouse.mouseClicked(java.awt.event.MouseEvent)
	 * --> TestRandomVeiw.createStandadGenerator()V)
	 * 
	 * @param arg1
	 *            java.awt.event.MouseEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC2(java.awt.event.MouseEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.setStandadParameters();
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC3:
	 * (JTextFieldVViborki.caret.caretUpdate(javax.swing.event.CaretEvent) -->
	 * TestRandomUI.setIntervalFor(I)V)
	 * 
	 * @param arg1
	 *            javax.swing.event.CaretEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC3(javax.swing.event.CaretEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.setIntervalFor(Integer.parseInt(getJTextFieldVViborki()
					.getText()));
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC4:
	 * (JButtonTest.action.actionPerformed(java.awt.event.ActionEvent) -->
	 * TestRandomVeiw.testNamed(Ljava.lang.String;Ljava.lang.String;Ljava.lang.String;Ljava.lang.String;)V)
	 * 
	 * @param arg1
	 *            java.awt.event.ActionEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC4(java.awt.event.ActionEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.testNamed((String) getJListTests().getSelectedValue(),
					getJTextFieldVViborki().getText(), getJTextFieldInterval()
							.getText(), getJTextFieldParametr().getText());
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC5:
	 * (JTextFieldVViborki.action.actionPerformed(java.awt.event.ActionEvent)
	 * --> TestRandomVeiw.setIntervalFor(I)V)
	 * 
	 * @param arg1
	 *            java.awt.event.ActionEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC5(java.awt.event.ActionEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.setIntervalFor(Integer.parseInt(getJTextFieldVViborki()
					.getText()));
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC6:
	 * (JTextFieldShift.caret.caretUpdate(javax.swing.event.CaretEvent) -->
	 * TestRandomVeiw.onUndependParameter(I)V)
	 * 
	 * @param arg1
	 *            javax.swing.event.CaretEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC6(javax.swing.event.CaretEvent arg1) {
		try {
			// user code begin {1}
			// user code end

			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC7:
	 * (TestRandomVeiw.window.windowOpened(java.awt.event.WindowEvent) -->
	 * TestRandomVeiw.changeItem(Ljava.lang.String;)V)
	 * 
	 * @param arg1
	 *            java.awt.event.WindowEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC7(java.awt.event.WindowEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.changeItem(new java.lang.String());
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoC8:
	 * (JListTests.listSelection.valueChanged(javax.swing.event.ListSelectionEvent)
	 * --> TestRandomVeiw.changeItem(Ljava.lang.String;)V)
	 * 
	 * @param arg1
	 *            javax.swing.event.ListSelectionEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoC8(javax.swing.event.ListSelectionEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			this.changeItem((String) getJListTests().getSelectedValue());
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connEtoM1:
	 * (JButtonTest.action.actionPerformed(java.awt.event.ActionEvent) -->
	 * Diagram.clear()V)
	 * 
	 * @param arg1
	 *            java.awt.event.ActionEvent
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connEtoM1(java.awt.event.ActionEvent arg1) {
		try {
			// user code begin {1}
			// user code end
			getDiagram().clear();
			// user code begin {2}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * connPtoP1SetTarget: (DefaultListModel1.this <--> JListTests.model)
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void connPtoP1SetTarget() {
		/* Set the target from the source */
		try {
			getJListTests().setModel(getDefaultListModel1());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {3}
			// user code end
			handleException(ivjExc);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (03.11.2005
	 * 21:19:04)
	 */
	public Randoms createGenerator(boolean isDelay) {
		long anAdd = Long.decode(getJTextFieldAdd().getText()).longValue();
		long aMult = Long.decode(getJTextFieldMult().getText()).longValue();
		int aBits = Integer.decode(getJTextFieldMask().getText()).intValue();
		Randoms rnd = new Randoms(aBits, anAdd, aMult, isDelay);
		return rnd;
	}

	/**
	 * Вывод графической зависимости между случайными последовательностями
	 * Creation date: (05.11.2005 23:18:02)
	 * 
	 * @param anInt
	 *            int
	 */
	public void drawDependency(java.util.ArrayList a1, java.util.ArrayList a2) {
		getDiagram().clear();
		getDiagram().setHorizontalMinText(Double.toString(0.0));
		getDiagram().setHorizontalMaxText(Double.toString(1.0));
		getDiagram().setVerticalMinText(Double.toString(0.0));
		getDiagram().setVerticalMaxText(Double.toString(1.0));

		/* Вывод графика */
		double x = ((Double) a1.get(0)).doubleValue();
		double y = ((Double) a2.get(0)).doubleValue();
		getDiagram().getPainter().placeToXY((float) x, (float) y);
		getDiagram().setPainterColor(java.awt.Color.magenta);
		for (int i = 1; i < a1.size(); i++) {
			x = ((Double) a1.get(i)).doubleValue();
			y = ((Double) a2.get(i)).doubleValue();
			getDiagram().getPainter().drawToXY((float) x, (float) y);
		}
	}

	/**
	 * Return the DefaultListModel1 property value.
	 * 
	 * @return javax.swing.DefaultListModel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.DefaultListModel getDefaultListModel1() {
		if (ivjDefaultListModel1 == null) {
			try {
				ivjDefaultListModel1 = new javax.swing.DefaultListModel();
				// user code begin {1}
				ivjDefaultListModel1.addElement(" на період");
				ivjDefaultListModel1.addElement(" на рівномірність");
				ivjDefaultListModel1.addElement(" на стохостичність");
				ivjDefaultListModel1.addElement(" на незалежність");
				ivjDefaultListModel1.addElement(" виведення значень");
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjDefaultListModel1;
	}

	/**
	 * Return the Diagram1 property value.
	 * 
	 * @return paint.Diagram
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private widgets.Diagram getDiagram() {
		if (ivjDiagram == null) {
			try {
				ivjDiagram = new widgets.Diagram();
				ivjDiagram.setName("Diagram");
				ivjDiagram.setHorizontalMinEnabled(false);
				ivjDiagram.setVerticalMaxEnabled(false);
				ivjDiagram.setHorizontalMaxText("1");
				ivjDiagram.setGridColor(java.awt.Color.darkGray);
				ivjDiagram.setTitleText("Результати тесту");
				ivjDiagram.setVerticalMinEnabled(false);
				ivjDiagram.setVerticalMaxText("1");
				ivjDiagram.setHorizontalMaxEnabled(false);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjDiagram;
	}

	/**
	 * Return the JButton1 property value.
	 * 
	 * @return javax.swing.JButton
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JButton getJButtonStand() {
		if (ivjJButtonStand == null) {
			try {
				ivjJButtonStand = new javax.swing.JButton();
				ivjJButtonStand.setName("JButtonStand");
				ivjJButtonStand.setText("Повернути стандартні константи");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJButtonStand;
	}

	/**
	 * Return the JButton1 property value.
	 * 
	 * @return javax.swing.JButton
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JButton getJButtonTest() {
		if (ivjJButtonTest == null) {
			try {
				ivjJButtonTest = new javax.swing.JButton();
				ivjJButtonTest.setName("JButtonTest");
				ivjJButtonTest.setText("Виконати тест");
				ivjJButtonTest.setMaximumSize(new java.awt.Dimension(227, 25));
				ivjJButtonTest.setMargin(new java.awt.Insets(2, 2, 2, 2));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJButtonTest;
	}

	/**
	 * Return the JFrameContentPane property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {
			try {
				ivjJFrameContentPane = new javax.swing.JPanel();
				ivjJFrameContentPane.setName("JFrameContentPane");
				ivjJFrameContentPane.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsJPanel1 = new java.awt.GridBagConstraints();
				constraintsJPanel1.gridx = 0;
				constraintsJPanel1.gridy = 1;
				constraintsJPanel1.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJPanel1.weightx = 1.0;
				constraintsJPanel1.weighty = 1.0;
				constraintsJPanel1.ipadx = -5;
				constraintsJPanel1.ipady = -4;
				constraintsJPanel1.insets = new Insets(3, 6, 5, 5);
				getJFrameContentPane().add(getJPanel1(), constraintsJPanel1);

				java.awt.GridBagConstraints constraintsJPanel2 = new java.awt.GridBagConstraints();
				constraintsJPanel2.gridx = 1;
				constraintsJPanel2.gridy = 1;
				constraintsJPanel2.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJPanel2.weightx = 1.0;
				constraintsJPanel2.weighty = 1.0;
				constraintsJPanel2.ipadx = -4;
				constraintsJPanel2.ipady = -4;
				constraintsJPanel2.insets = new Insets(3, 4, 5, 5);
				getJFrameContentPane().add(getJPanel2(), constraintsJPanel2);

				java.awt.GridBagConstraints constraintsJPanel3 = new java.awt.GridBagConstraints();
				constraintsJPanel3.gridx = 0;
				constraintsJPanel3.gridy = 3;
				constraintsJPanel3.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJPanel3.weightx = 1.0;
				constraintsJPanel3.weighty = 1.0;
				constraintsJPanel3.ipadx = -89;
				constraintsJPanel3.ipady = -5;
				constraintsJPanel3.insets = new Insets(4, 6, 9, 5);
				getJFrameContentPane().add(getJPanel3(), constraintsJPanel3);

				java.awt.GridBagConstraints constraintsJPanel4 = new java.awt.GridBagConstraints();
				constraintsJPanel4.gridx = 1;
				constraintsJPanel4.gridy = 3;
				constraintsJPanel4.gridwidth = 2;
				constraintsJPanel4.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJPanel4.weightx = 1.0;
				constraintsJPanel4.weighty = 1.0;
				constraintsJPanel4.ipadx = 31;
				constraintsJPanel4.ipady = 38;
				constraintsJPanel4.insets = new Insets(4, 3, 9, 0);
				getJFrameContentPane().add(getJPanel4(), constraintsJPanel4);

				java.awt.GridBagConstraints constraintsJPanel5 = new java.awt.GridBagConstraints();
				constraintsJPanel5.gridx = 2;
				constraintsJPanel5.gridy = 1;
				constraintsJPanel5.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJPanel5.weightx = 1.0;
				constraintsJPanel5.weighty = 1.0;
				constraintsJPanel5.ipadx = -5;
				constraintsJPanel5.ipady = -4;
				constraintsJPanel5.insets = new Insets(0, 3, 5, 6);
				getJFrameContentPane().add(getJPanel5(), constraintsJPanel5);

				java.awt.GridBagConstraints constraintsJLabel6 = new java.awt.GridBagConstraints();
				constraintsJLabel6.gridx = 1;
				constraintsJLabel6.gridy = 0;
				constraintsJLabel6.gridwidth = 2;
				constraintsJLabel6.ipadx = 63;
				constraintsJLabel6.insets = new Insets(11, 19, 5, 124);
				getJFrameContentPane().add(getJLabel6(), constraintsJLabel6);

				java.awt.GridBagConstraints constraintsJLabel9 = new java.awt.GridBagConstraints();
				constraintsJLabel9.gridx = 2;
				constraintsJLabel9.gridy = 0;
				constraintsJLabel9.ipadx = 26;
				constraintsJLabel9.insets = new Insets(10, 5, 5, 8);
				getJFrameContentPane().add(getJLabel9(), constraintsJLabel9);

				java.awt.GridBagConstraints constraintsJLabel8 = new java.awt.GridBagConstraints();
				constraintsJLabel8.gridx = 1;
				constraintsJLabel8.gridy = 2;
				constraintsJLabel8.gridwidth = 2;
				constraintsJLabel8.ipadx = 100;
				constraintsJLabel8.insets = new Insets(4, 7, 5, 7);
				getJFrameContentPane().add(getJLabel8(), constraintsJLabel8);

				java.awt.GridBagConstraints constraintsJLabel7 = new java.awt.GridBagConstraints();
				constraintsJLabel7.gridx = 0;
				constraintsJLabel7.gridy = 2;
				constraintsJLabel7.ipadx = 202;
				constraintsJLabel7.insets = new Insets(3, 11, 5, 5);
				getJFrameContentPane().add(getJLabel7(), constraintsJLabel7);

				java.awt.GridBagConstraints constraintsJLabel1 = new java.awt.GridBagConstraints();
				constraintsJLabel1.gridx = 0;
				constraintsJLabel1.gridy = 0;
				constraintsJLabel1.ipadx = 178;
				constraintsJLabel1.insets = new Insets(10, 9, 5, 5);
				getJFrameContentPane().add(getJLabel1(), constraintsJLabel1);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJFrameContentPane;
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
				ivjJLabel1.setText("Налаштування генератора");
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
	 * Return the JLabel10 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel10() {
		if (ivjJLabel10 == null) {
			try {
				ivjJLabel10 = new javax.swing.JLabel();
				ivjJLabel10.setName("JLabel10");
				ivjJLabel10.setFont(new java.awt.Font("dialog", 1, 12));
				ivjJLabel10.setText("Обсяг вибірки");
				ivjJLabel10
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel10;
	}

	/**
	 * Return the JLabel101 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel101() {
		if (ivjJLabel101 == null) {
			try {
				ivjJLabel101 = new javax.swing.JLabel();
				ivjJLabel101.setName("JLabel101");
				ivjJLabel101.setText("Кільк. інтервалів");
				ivjJLabel101
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel101;
	}

	/**
	 * Return the JLabel1011 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel1011() {
		if (ivjJLabel1011 == null) {
			try {
				ivjJLabel1011 = new javax.swing.JLabel();
				ivjJLabel1011.setName("JLabel1011");
				ivjJLabel1011.setText("Параметр тесту");
				ivjJLabel1011
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel1011;
	}

	/**
	 * Return the JLabel12 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel12() {
		if (ivjJLabel12 == null) {
			try {
				ivjJLabel12 = new javax.swing.JLabel();
				ivjJLabel12.setName("JLabel12");
				ivjJLabel12.setText("");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel12;
	}

	/**
	 * Return the JLabel13 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel13() {
		if (ivjJLabel13 == null) {
			try {
				ivjJLabel13 = new javax.swing.JLabel();
				ivjJLabel13.setName("JLabel13");
				ivjJLabel13.setText("");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel13;
	}

	/**
	 * Return the JLabel14 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel14() {
		if (ivjJLabel14 == null) {
			try {
				ivjJLabel14 = new javax.swing.JLabel();
				ivjJLabel14.setName("JLabel14");
				ivjJLabel14.setText(" ");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel14;
	}

	/**
	 * Return the JLabel15 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel15() {
		if (ivjJLabel15 == null) {
			try {
				ivjJLabel15 = new javax.swing.JLabel();
				ivjJLabel15.setName("JLabel15");
				ivjJLabel15.setText("");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel15;
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
				ivjJLabel2.setText("Адитивна константа");
				ivjJLabel2
						.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
				ivjJLabel3.setName("JLabel3");
				ivjJLabel3.setText("Мультипліктивна константа");
				ivjJLabel3
						.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
				ivjJLabel4.setText("Кількість біт у масці");
				ivjJLabel4
						.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
	 * Return the JLabel5 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel5() {
		if (ivjJLabel5 == null) {
			try {
				ivjJLabel5 = new javax.swing.JLabel();
				ivjJLabel5.setName("JLabel5");
				ivjJLabel5.setText("Тести");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel5;
	}

	/**
	 * Return the JLabel6 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel6() {
		if (ivjJLabel6 == null) {
			try {
				ivjJLabel6 = new javax.swing.JLabel();
				ivjJLabel6.setName("JLabel6");
				ivjJLabel6.setText("Тестування");
				ivjJLabel6
						.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel6;
	}

	/**
	 * Return the JLabel7 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel7() {
		if (ivjJLabel7 == null) {
			try {
				ivjJLabel7 = new javax.swing.JLabel();
				ivjJLabel7.setName("JLabel7");
				ivjJLabel7.setText("Результати тесту");
				ivjJLabel7
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel7;
	}

	/**
	 * Return the JLabel8 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel8() {
		if (ivjJLabel8 == null) {
			try {
				ivjJLabel8 = new javax.swing.JLabel();
				ivjJLabel8.setName("JLabel8");
				ivjJLabel8.setText("Графічне представлення");
				ivjJLabel8
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel8;
	}

	/**
	 * Return the JLabel9 property value.
	 * 
	 * @return javax.swing.JLabel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JLabel getJLabel9() {
		if (ivjJLabel9 == null) {
			try {
				ivjJLabel9 = new javax.swing.JLabel();
				ivjJLabel9.setName("JLabel9");
				ivjJLabel9.setText("Налаштування тестів");
				ivjJLabel9
						.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel9;
	}

	/**
	 * Return the JList1 property value.
	 * 
	 * @return javax.swing.JList
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JList getJListTests() {
		if (ivjJListTests == null) {
			try {
				ivjJListTests = new javax.swing.JList();
				ivjJListTests.setName("JListTests");
				ivjJListTests.setToolTipText("");
				ivjJListTests.setFont(new java.awt.Font("dialog", 1, 12));
				ivjJListTests.setBounds(2, 0, 155, 95);
				ivjJListTests.setSelectedIndex(-1);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJListTests;
	}

	/**
	 * Return the JPanel1 property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJPanel1() {
		if (ivjJPanel1 == null) {
			try {
				ivjJPanel1 = new javax.swing.JPanel();
				ivjJPanel1.setName("JPanel1");
				ivjJPanel1.setToolTipText("");
				ivjJPanel1.setBorder(new javax.swing.border.EtchedBorder());
				ivjJPanel1.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsJLabel2 = new java.awt.GridBagConstraints();
				constraintsJLabel2.gridx = 0;
				constraintsJLabel2.gridy = 0;
				constraintsJLabel2.ipadx = 55;
				constraintsJLabel2.insets = new Insets(5, 9, 6, 5);
				getJPanel1().add(getJLabel2(), constraintsJLabel2);

				java.awt.GridBagConstraints constraintsJLabel3 = new java.awt.GridBagConstraints();
				constraintsJLabel3.gridx = 0;
				constraintsJLabel3.gridy = 1;
				constraintsJLabel3.ipadx = 8;
				constraintsJLabel3.insets = new java.awt.Insets(5, 9, 6, 5);
				getJPanel1().add(getJLabel3(), constraintsJLabel3);

				java.awt.GridBagConstraints constraintsJLabel4 = new java.awt.GridBagConstraints();
				constraintsJLabel4.gridx = 0;
				constraintsJLabel4.gridy = 2;
				constraintsJLabel4.ipadx = 48;
				constraintsJLabel4.insets = new java.awt.Insets(5, 9, 8, 5);
				getJPanel1().add(getJLabel4(), constraintsJLabel4);

				java.awt.GridBagConstraints constraintsJButtonStand = new java.awt.GridBagConstraints();
				constraintsJButtonStand.gridx = 0;
				constraintsJButtonStand.gridy = 3;
				constraintsJButtonStand.gridwidth = 2;
				constraintsJButtonStand.ipadx = 69;
				constraintsJButtonStand.insets = new Insets(5, 8, 0, 9);
				getJPanel1().add(getJButtonStand(), constraintsJButtonStand);

				java.awt.GridBagConstraints constraintsJTextFieldAdd = new java.awt.GridBagConstraints();
				constraintsJTextFieldAdd.gridx = 1;
				constraintsJTextFieldAdd.gridy = 0;
				constraintsJTextFieldAdd.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldAdd.weightx = 1.0;
				constraintsJTextFieldAdd.ipadx = 90;
				constraintsJTextFieldAdd.insets = new Insets(5, 5, 5, 11);
				getJPanel1().add(getJTextFieldAdd(), constraintsJTextFieldAdd);

				java.awt.GridBagConstraints constraintsJTextFieldMult = new java.awt.GridBagConstraints();
				constraintsJTextFieldMult.gridx = 1;
				constraintsJTextFieldMult.gridy = 1;
				constraintsJTextFieldMult.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldMult.weightx = 1.0;
				constraintsJTextFieldMult.ipadx = 90;
				constraintsJTextFieldMult.insets = new Insets(3, 5, 5, 11);
				getJPanel1()
						.add(getJTextFieldMult(), constraintsJTextFieldMult);

				java.awt.GridBagConstraints constraintsJTextFieldMask = new java.awt.GridBagConstraints();
				constraintsJTextFieldMask.gridx = 1;
				constraintsJTextFieldMask.gridy = 2;
				constraintsJTextFieldMask.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldMask.weightx = 1.0;
				constraintsJTextFieldMask.ipadx = 90;
				constraintsJTextFieldMask.insets = new java.awt.Insets(3, 5, 5,
						11);
				getJPanel1()
						.add(getJTextFieldMask(), constraintsJTextFieldMask);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel1;
	}

	/**
	 * Return the JPanel2 property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJPanel2() {
		if (ivjJPanel2 == null) {
			try {
				ivjJPanel2 = new javax.swing.JPanel();
				ivjJPanel2.setName("JPanel2");
				ivjJPanel2.setBorder(new javax.swing.border.EtchedBorder());
				ivjJPanel2.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsJButtonTest = new java.awt.GridBagConstraints();
				constraintsJButtonTest.gridx = 0;
				constraintsJButtonTest.gridy = 2;
				constraintsJButtonTest.ipadx = 19;
				constraintsJButtonTest.insets = new Insets(3, 5, 3, 5);
				getJPanel2().add(getJButtonTest(), constraintsJButtonTest);

				java.awt.GridBagConstraints constraintsJLabel5 = new java.awt.GridBagConstraints();
				constraintsJLabel5.gridx = 0;
				constraintsJLabel5.gridy = 0;
				constraintsJLabel5.ipadx = 82;
				constraintsJLabel5.insets = new Insets(5, 12, 3, 11);
				getJPanel2().add(getJLabel5(), constraintsJLabel5);

				java.awt.GridBagConstraints constraintsJScrollPane1 = new java.awt.GridBagConstraints();
				constraintsJScrollPane1.gridx = 0;
				constraintsJScrollPane1.gridy = 1;
				constraintsJScrollPane1.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJScrollPane1.weightx = 1.0;
				constraintsJScrollPane1.weighty = 1.0;
				constraintsJScrollPane1.ipadx = 113;
				constraintsJScrollPane1.ipady = 75;
				constraintsJScrollPane1.insets = new Insets(2, 5, 2, 5);
				getJPanel2().add(getJScrollPane1(), constraintsJScrollPane1);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel2;
	}

	/**
	 * Return the JPanel3 property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJPanel3() {
		if (ivjJPanel3 == null) {
			try {
				ivjJPanel3 = new javax.swing.JPanel();
				ivjJPanel3.setName("JPanel3");
				ivjJPanel3.setAutoscrolls(true);
				ivjJPanel3.setBorder(new javax.swing.border.EtchedBorder());
				ivjJPanel3.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsJScrollPane2 = new java.awt.GridBagConstraints();
				constraintsJScrollPane2.gridx = 0;
				constraintsJScrollPane2.gridy = 0;
				constraintsJScrollPane2.fill = java.awt.GridBagConstraints.BOTH;
				constraintsJScrollPane2.weightx = 1.0;
				constraintsJScrollPane2.weighty = 1.0;
				constraintsJScrollPane2.ipadx = 360;
				constraintsJScrollPane2.ipady = 196;
				constraintsJScrollPane2.insets = new java.awt.Insets(5, 6, 8, 7);
				getJPanel3().add(getJScrollPane2(), constraintsJScrollPane2);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel3;
	}

	/**
	 * Return the JPanel4 property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJPanel4() {
		if (ivjJPanel4 == null) {
			try {
				ivjJPanel4 = new javax.swing.JPanel();
				ivjJPanel4.setName("JPanel4");
				ivjJPanel4.setBorder(new javax.swing.border.EtchedBorder());
				ivjJPanel4.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsDiagram = new java.awt.GridBagConstraints();
				constraintsDiagram.gridx = 0;
				constraintsDiagram.gridy = 0;
				constraintsDiagram.fill = java.awt.GridBagConstraints.BOTH;
				constraintsDiagram.weightx = 1.0;
				constraintsDiagram.weighty = 1.0;
				constraintsDiagram.insets = new java.awt.Insets(4, 4, 4, 4);
				getJPanel4().add(getDiagram(), constraintsDiagram);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel4;
	}

	/**
	 * Return the JPanel5 property value.
	 * 
	 * @return javax.swing.JPanel
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JPanel getJPanel5() {
		if (ivjJPanel5 == null) {
			try {
				ivjJPanel5 = new javax.swing.JPanel();
				ivjJPanel5.setName("JPanel5");
				ivjJPanel5.setBorder(new javax.swing.border.EtchedBorder());
				GridBagLayout gbl_ivjJPanel5 = new GridBagLayout();
				gbl_ivjJPanel5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				gbl_ivjJPanel5.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
				ivjJPanel5.setLayout(gbl_ivjJPanel5);

				java.awt.GridBagConstraints constraintsJLabel10 = new java.awt.GridBagConstraints();
				constraintsJLabel10.anchor = GridBagConstraints.SOUTH;
				constraintsJLabel10.gridx = 0;
				constraintsJLabel10.gridy = 0;
				constraintsJLabel10.ipadx = 20;
				constraintsJLabel10.insets = new Insets(8, 7, 0, 8);
				getJPanel5().add(getJLabel10(), constraintsJLabel10);

				java.awt.GridBagConstraints constraintsJLabel101 = new java.awt.GridBagConstraints();
				constraintsJLabel101.anchor = GridBagConstraints.SOUTH;
				constraintsJLabel101.gridx = 0;
				constraintsJLabel101.gridy = 2;
				constraintsJLabel101.ipadx = 9;
				constraintsJLabel101.insets = new Insets(3, 4, 0, 4);
				getJPanel5().add(getJLabel101(), constraintsJLabel101);

				java.awt.GridBagConstraints constraintsJLabel1011 = new java.awt.GridBagConstraints();
				constraintsJLabel1011.anchor = GridBagConstraints.SOUTH;
				constraintsJLabel1011.gridx = 0;
				constraintsJLabel1011.gridy = 4;
				constraintsJLabel1011.ipadx = 25;
				constraintsJLabel1011.ipady = 3;
				constraintsJLabel1011.insets = new Insets(3, 7, 0, 6);
				getJPanel5().add(getJLabel1011(), constraintsJLabel1011);

				java.awt.GridBagConstraints constraintsJTextFieldVViborki = new java.awt.GridBagConstraints();
				constraintsJTextFieldVViborki.gridx = 0;
				constraintsJTextFieldVViborki.gridy = 1;
				constraintsJTextFieldVViborki.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldVViborki.weightx = 1.0;
				constraintsJTextFieldVViborki.ipadx = 78;
				constraintsJTextFieldVViborki.ipady = 4;
				constraintsJTextFieldVViborki.insets = new Insets(2, 24, 5, 23);
				getJPanel5().add(getJTextFieldVViborki(),
						constraintsJTextFieldVViborki);

				java.awt.GridBagConstraints constraintsJTextFieldInterval = new java.awt.GridBagConstraints();
				constraintsJTextFieldInterval.gridx = 0;
				constraintsJTextFieldInterval.gridy = 3;
				constraintsJTextFieldInterval.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldInterval.weightx = 1.0;
				constraintsJTextFieldInterval.ipadx = 78;
				constraintsJTextFieldInterval.ipady = 3;
				constraintsJTextFieldInterval.insets = new Insets(0, 24, 5, 23);
				getJPanel5().add(getJTextFieldInterval(),
						constraintsJTextFieldInterval);

				java.awt.GridBagConstraints constraintsJTextFieldShift = new java.awt.GridBagConstraints();
				constraintsJTextFieldShift.gridx = 0;
				constraintsJTextFieldShift.gridy = 5;
				constraintsJTextFieldShift.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldShift.weightx = 1.0;
				constraintsJTextFieldShift.ipadx = 78;
				constraintsJTextFieldShift.ipady = 4;
				constraintsJTextFieldShift.insets = new Insets(0, 24, 15, 23);
				getJPanel5().add(getJTextFieldShift(),
						constraintsJTextFieldShift);

				java.awt.GridBagConstraints constraintsJTextFieldParametr = new java.awt.GridBagConstraints();
				constraintsJTextFieldParametr.gridx = 0;
				constraintsJTextFieldParametr.gridy = 5;
				constraintsJTextFieldParametr.fill = java.awt.GridBagConstraints.HORIZONTAL;
				constraintsJTextFieldParametr.weightx = 1.0;
				constraintsJTextFieldParametr.ipadx = 78;
				constraintsJTextFieldParametr.ipady = 4;
				constraintsJTextFieldParametr.insets = new java.awt.Insets(3,
						24, 15, 23);
				getJPanel5().add(getJTextFieldParametr(),
						constraintsJTextFieldParametr);

				java.awt.GridBagConstraints constraintsJLabel12 = new java.awt.GridBagConstraints();
				constraintsJLabel12.anchor = GridBagConstraints.SOUTH;
				constraintsJLabel12.gridx = 0;
				constraintsJLabel12.gridy = 4;
				constraintsJLabel12.ipadx = 78;
				constraintsJLabel12.ipady = 17;
				constraintsJLabel12.insets = new Insets(3, 25, 5, 26);
				getJPanel5().add(getJLabel12(), constraintsJLabel12);

				java.awt.GridBagConstraints constraintsJLabel13 = new java.awt.GridBagConstraints();
				constraintsJLabel13.gridx = 0;
				constraintsJLabel13.gridy = 5;
				constraintsJLabel13.ipadx = 81;
				constraintsJLabel13.ipady = 23;
				constraintsJLabel13.insets = new java.awt.Insets(3, 24, 15, 24);
				getJPanel5().add(getJLabel13(), constraintsJLabel13);

				java.awt.GridBagConstraints constraintsJLabel14 = new java.awt.GridBagConstraints();
				constraintsJLabel14.gridx = 0;
				constraintsJLabel14.gridy = 3;
				constraintsJLabel14.ipadx = 78;
				constraintsJLabel14.ipady = 8;
				constraintsJLabel14.insets = new Insets(3, 24, 5, 24);
				getJPanel5().add(getJLabel14(), constraintsJLabel14);

				java.awt.GridBagConstraints constraintsJLabel15 = new java.awt.GridBagConstraints();
				constraintsJLabel15.anchor = GridBagConstraints.SOUTH;
				constraintsJLabel15.gridx = 0;
				constraintsJLabel15.gridy = 2;
				constraintsJLabel15.ipadx = 78;
				constraintsJLabel15.ipady = 14;
				constraintsJLabel15.insets = new Insets(3, 25, 5, 26);
				getJPanel5().add(getJLabel15(), constraintsJLabel15);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel5;
	}

	/**
	 * Return the JScrollPane1 property value.
	 * 
	 * @return javax.swing.JScrollPane
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JScrollPane getJScrollPane1() {
		if (ivjJScrollPane1 == null) {
			try {
				ivjJScrollPane1 = new javax.swing.JScrollPane();
				ivjJScrollPane1.setName("JScrollPane1");
				ivjJScrollPane1.setToolTipText("");
				ivjJScrollPane1
						.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				ivjJScrollPane1
						.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getJScrollPane1().setViewportView(getJListTests());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJScrollPane1;
	}

	/**
	 * Return the JScrollPane2 property value.
	 * 
	 * @return javax.swing.JScrollPane
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JScrollPane getJScrollPane2() {
		if (ivjJScrollPane2 == null) {
			try {
				ivjJScrollPane2 = new javax.swing.JScrollPane();
				ivjJScrollPane2.setName("JScrollPane2");
				ivjJScrollPane2.setAutoscrolls(false);
				ivjJScrollPane2
						.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				ivjJScrollPane2
						.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getJScrollPane2().setViewportView(getJTextAreaResult());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJScrollPane2;
	}

	/**
	 * Return the JTextArea2 property value.
	 * 
	 * @return javax.swing.JTextArea
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextArea getJTextAreaResult() {
		if (ivjJTextAreaResult == null) {
			try {
				ivjJTextAreaResult = new javax.swing.JTextArea();
				ivjJTextAreaResult.setName("JTextAreaResult");
				ivjJTextAreaResult.setLineWrap(true);
				ivjJTextAreaResult.setFont(new java.awt.Font("monospaced", 0,
						12));
				ivjJTextAreaResult.setWrapStyleWord(true);
				ivjJTextAreaResult.setBounds(0, 0, 378, 224);
				ivjJTextAreaResult.setMargin(new java.awt.Insets(2, 2, 2, 8));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextAreaResult;
	}

	/**
	 * Return the JTextField1 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldAdd() {
		if (ivjJTextFieldAdd == null) {
			try {
				ivjJTextFieldAdd = new javax.swing.JTextField();
				ivjJTextFieldAdd.setName("JTextFieldAdd");
				ivjJTextFieldAdd
						.setFont(new java.awt.Font("dialoginput", 0, 12));
				ivjJTextFieldAdd.setText("11");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldAdd;
	}

	/**
	 * Return the JTextField2 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldInterval() {
		if (ivjJTextFieldInterval == null) {
			try {
				ivjJTextFieldInterval = new javax.swing.JTextField();
				ivjJTextFieldInterval.setName("JTextFieldInterval");
				ivjJTextFieldInterval.setFont(new java.awt.Font("dialoginput",
						0, 12));
				ivjJTextFieldInterval.setText("10");
				ivjJTextFieldInterval
						.setMargin(new java.awt.Insets(0, 0, 0, 4));
				ivjJTextFieldInterval
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldInterval;
	}

	/**
	 * Return the JTextField3 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldMask() {
		if (ivjJTextFieldMask == null) {
			try {
				ivjJTextFieldMask = new javax.swing.JTextField();
				ivjJTextFieldMask.setName("JTextFieldMask");
				ivjJTextFieldMask.setFont(new java.awt.Font("dialoginput", 0,
						12));
				ivjJTextFieldMask.setText("31");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldMask;
	}

	/**
	 * Return the JTextField2 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldMult() {
		if (ivjJTextFieldMult == null) {
			try {
				ivjJTextFieldMult = new javax.swing.JTextField();
				ivjJTextFieldMult.setName("JTextFieldMult");
				ivjJTextFieldMult.setFont(new java.awt.Font("dialoginput", 0,
						12));
				ivjJTextFieldMult.setText("25214903917");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldMult;
	}

	/**
	 * Return the JTextField4 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldParametr() {
		if (ivjJTextFieldParametr == null) {
			try {
				ivjJTextFieldParametr = new javax.swing.JTextField();
				ivjJTextFieldParametr.setName("JTextFieldParametr");
				ivjJTextFieldParametr.setFont(new java.awt.Font("dialoginput",
						0, 12));
				ivjJTextFieldParametr.setText("0.5");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldParametr;
	}

	/**
	 * Return the JTextField3 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldShift() {
		if (ivjJTextFieldShift == null) {
			try {
				ivjJTextFieldShift = new javax.swing.JTextField();
				ivjJTextFieldShift.setName("JTextFieldShift");
				ivjJTextFieldShift.setFont(new java.awt.Font("dialoginput", 0,
						12));
				ivjJTextFieldShift.setText("100");
				ivjJTextFieldShift.setMargin(new java.awt.Insets(0, 0, 0, 4));
				ivjJTextFieldShift
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldShift;
	}

	/**
	 * Return the JTextField1 property value.
	 * 
	 * @return javax.swing.JTextField
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private javax.swing.JTextField getJTextFieldVViborki() {
		if (ivjJTextFieldVViborki == null) {
			try {
				ivjJTextFieldVViborki = new javax.swing.JTextField();
				ivjJTextFieldVViborki.setName("JTextFieldVViborki");
				ivjJTextFieldVViborki.setFont(new java.awt.Font("dialoginput",
						0, 12));
				ivjJTextFieldVViborki.setText("500");
				ivjJTextFieldVViborki
						.setMargin(new java.awt.Insets(0, 0, 0, 4));
				ivjJTextFieldVViborki
						.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldVViborki;
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
		this.addWindowListener(ivjEventHandler);
		getJButtonStand().addMouseListener(ivjEventHandler);
		getJTextFieldVViborki().addActionListener(ivjEventHandler);
		getJTextFieldShift().addCaretListener(ivjEventHandler);
		getJListTests().addListSelectionListener(ivjEventHandler);
		getJTextFieldVViborki().addCaretListener(ivjEventHandler);
		getJButtonTest().addActionListener(ivjEventHandler);
		connPtoP1SetTarget();
	}

	/**
	 * Initialize the class.
	 */
	/* WARNING: THIS METHOD WILL BE REGENERATED. */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("TestRandomVeiw");
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setSize(698, 494);
			setVisible(true);
			setTitle("Дослідження генератора рівномірно розподілених випадкових чисел");
			setContentPane(getJFrameContentPane());
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
			TestRandomUI aTestRandomView;
			aTestRandomView = new TestRandomUI();
			aTestRandomView
					.addWindowListener(new java.awt.event.WindowAdapter() {
						public void windowClosing(java.awt.event.WindowEvent e) {
							System.exit(0);
						};
					});
			aTestRandomView.setVisible(true);
			java.awt.Insets insets = aTestRandomView.getInsets();
			aTestRandomView.setSize(aTestRandomView.getWidth() + insets.left
					+ insets.right, aTestRandomView.getHeight() + insets.top
					+ insets.bottom);
			aTestRandomView.setVisible(true);
		} catch (Throwable exception) {
			System.err
					.println("Exception occurred in mainForAllLab() of javax.swing.JFrame");
			exception.printStackTrace(System.out);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (05.11.2005
	 * 23:18:02)
	 * 
	 * @param anInt
	 *            int
	 */

	/**
	 * Insert the method's description here. Creation date: (09.11.2005
	 * 20:07:32)
	 * 
	 * @param size
	 *            int
	 */
	public void periodTest(int size, int waitTime) {
		String text = "Тест на період \n";
		text = text + "Чекаємо не більш ніж " + waitTime + " мілісекунд... \n";
		getJTextAreaResult().setText(text);
		// Этот класс определяет период в отдельном потоке,
		// что позволяет вывести текст "Ждем не более... " раньше, чем будет
		// найден период
		class PeriodFinder implements Runnable {
			private int size;

			private int waitTime;

			private javax.swing.JTextArea area;

			PeriodFinder(int newSize, int newWaitTime,
					javax.swing.JTextArea newArea) {
				super();
				size = newSize;
				waitTime = newWaitTime;
				area = newArea;
			}

			// Метод для поиск периода в отдельном потоке
			public void run() {
				(TestRandomUI.this).setCursor(new java.awt.Cursor(
						java.awt.Cursor.WAIT_CURSOR));
				Randoms rnd = (TestRandomUI.this).createGenerator(false);

				/* Пропускаем size начальных чисел */
				for (int i = 1; i < size; i++) {
					rnd.next();
				}

				/* Запоминаем число в последовательности и время */
				long first = rnd.next();
				long clock = System.currentTimeMillis();

				/*
				 * Запускаем счетчик до совпадения с началом (first), но ждем не
				 * более waitTime миллисекунд
				 */
				int cnt = 1;
				int next;
				while (((next = rnd.next()) != first)
						&& (System.currentTimeMillis() < (clock + waitTime))) {
					cnt++;
				}

				/* Вывод результата */
				String txt = area.getText();
				if (next != first) {
					txt = txt + "Період больш ніж " + cnt;
				} else
					txt = txt + "Період дорівнює " + cnt;
				area.setText(txt);

				/* Восстанавливаем курсор */
				TestRandomUI.this.setCursor(java.awt.Cursor
						.getDefaultCursor());
			}
		}
		// Создаем и запускаем поток вычисления периода
		PeriodFinder pf = new PeriodFinder(size, waitTime, getJTextAreaResult());
		new Thread(pf).start();
	}

	/**
	 * Insert the method's description here. Creation date: (05.11.2005
	 * 23:18:02)
	 * 
	 * @param anInt
	 *            int
	 */
	public void setIntervalFor(int n) {
		String test = (String) getJListTests().getSelectedValue();
		if (test == " на рівномірність") {
			if (n > 0) {
				int ni = (int) Math.floor(Math.log((double) n) * 1.5 + 1);
				getJTextFieldInterval().setText(String.valueOf(ni));
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (03.11.2005
	 * 13:59:54)
	 */
	public void setStandadParameters() {
		getJTextFieldAdd().setText(Long.toString(Randoms.getAddendStand()));
		getJTextFieldMult()
				.setText(Long.toString(Randoms.getMultiplierStand()));
		getJTextFieldMask().setText(Integer.toString(Randoms.getBitsStand()));
	}

	/**
	 * Выводит случайные числа на экран. Creation date: (05.11.2005 15:12:03)
	 * 
	 * @param size
	 *            int - количество выводимых чисел
	 */
	public void showNumberFor(int size) {

		int n = size;
		if (n > 100)
			n = 100;

		/* Создаем генератор в соответствии с визуальной частью */
		Randoms rnd = this.createGenerator(false);

		/* Максимальная длина числа */
		int len = rnd.getBits() / 3 + 1;

		/* Готовим и настраиваем область вывода */
		getJTextAreaResult().setTabSize(len + 1);
		StringBuffer text = new StringBuffer("Випадкові числа:");

		// Формируем случайные числа и строку для вывода
		for (int i = 0; i < n; i++)
			text.append("\t").append(StatTables.format(rnd.next(), len));

		// Выводим результат
		getJTextAreaResult().setText(text.toString());
		getJTextAreaResult().select(0, 0);
	}

	/**
	 * Создание и проверка последовательности на стохастичность. Creation date:
	 * (21.11.2005 20:42:07)
	 * 
	 * @param size
	 *            int - длина последовательности.
	 * @param p
	 *            double - параметр теста из диапазона ]0..1[
	 */
	public void stohosTest(int size, double p) {
		/* Создание генератора в соответствии с визуальной частью */
		Randoms rnd = this.createGenerator(false);
		/* Формирование сумки длин серий и выбор максимальной */
		java.util.ArrayList<Integer> bag = new java.util.ArrayList<Integer>();
		int k = 0; // счетчик случайных чисел
		int ls; // длина серии
		int m = 0; // макс. длина серии
		while (k < size) {
			ls = 0;
			while ((k++ < size) && (rnd.nextFloat() < p)) {
				ls++;	
			}
			bag.add(ls);
			
			if (ls > m)
				m = ls;
		}

		/* Создаем массив для длин серий от нулевой до максимальной */
		double a[] = new double[m + 1];
		/* Определяем количество повторений каждой длины серии */
		for (int j = 0; j < bag.size(); j++) {
			int index = ((Integer) bag.get(j)).intValue();
			a[index]++;
		}
		/* Превращаем частоты в относительные */
		for (int j = 0; j < m + 1; j++)
			a[j] /= bag.size();
		/* Вычисление теоретических частот */
		double t[] = new double[m + 1];
		double x[] = new double[m + 1];
		t[0] = 1 - p; x[0] = 0;
		for (int j = 1; j < m + 1; j++){
			t[j] = t[j - 1] * p;
			x[j] = j;
		}
//		/* Создаем массивы накопленных частот */
//		for (int j = 1; j < m + 1; j++) {
//			t[j] += t[j - 1];
//			arr[j] += arr[j - 1];
//		}
//		// Вывод результатов тестирования
//		/* Создаем массив условных интервалов (значений частот) для диаграммы */
//		double b[] = new double[m + 2];
//		for (int j = 1; j < m + 2; j++)
//			b[j] += (double) j;
//		// Диаграмма теоретических частот
//		getDiagram().getPainter().setColor(java.awt.Color.blue);
////		getDiagram().drawBarsDiagram(b, t, 0.85, 0.1, true);
//		getDiagram().drawNeedleDiagram( x, t, 0.25, true);
//		// Диаграмма экспериментальных частот
//		getDiagram().getPainter().setColor(java.awt.Color.orange);
////		getDiagram().drawBarsDiagram(b, arr, 0.5, 0.4, false);
//		getDiagram().drawNeedleDiagram( x, arr, 0.1, false);
//		// Текстовое сообщение
//		getJTextAreaResult().setText("Тест на стохостичність.\n");
//		
//		// Вычисляем max разницу между экcп. и теор. накопл. частотами
//		double max = stat.StatTables.kolmSmirnovResult(arr, t, b,
//				getJTextAreaResult());
//		// Находим табличное значение критической разности
//		double maxT = (stat.StatTables.kolmogorovSmirnov05(size));
//		String text = "Максимальне відхілення = "
//				+ stat.StatTables.format(max, 1, 2)
//				+ ".\nКритичне відхилення = "
//				+ stat.StatTables.format(maxT, 1, 2)
//				+ "\nГіпотезу про стохостичність генератора";
//		if (max < maxT)
//			text += "\nможна прийняти.";
//		else
//			text += "\nслід відхилити.";
//		getJTextAreaResult().append(text);
		
//////////////////////////////////////////////////////////////////////////////////////		
		getJTextAreaResult().setText("Тест на стохостичність.\n");
		/* calculate borders */
		double borders[] = new double[m + 2];
		borders[0] = -0.5;
		for (int j = 0; j < m + 1; j++){
			borders[j+1] = j+0.5;
		}
		/* calculate teor */
		double at[] = new double[m + 1];
		for (int j = 0; j < m + 1; j++){
			at[j] = t[j]* bag.size();
		}
		/* calculate fact */
		double af[] = new double[m + 1];
		for (int j = 0; j < m + 1; j++){
			af[j] = a[j]* bag.size();
		}
		// Диаграмма теоретических частот
		getDiagram().getPainter().setColor(java.awt.Color.blue);
		getDiagram().drawNeedleDiagram( x, at, 0.25, true);
		// Диаграмма экспериментальных частот
		getDiagram().getPainter().setColor(java.awt.Color.orange);
		getDiagram().drawNeedleDiagram( x, af, 0.1, false);
		double res[] = stat.StatTables.pirsonResult(af, at, borders,
				getJTextAreaResult(), 0);
		double hi = res[0];
		double maxh = res[1]; // Критическое значение хи-квадрат
		String texth = "Хі-квадрат = " + stat.StatTables.format(hi, 1, 1)
				+ "\r\nКритичне значення = "
				+ stat.StatTables.format(maxh, 1, 1)
				+ "\r\nГіпотезу про cтохостичність";
		if (hi < maxh)
			texth += " можна прийняти"; 
		else
			texth += " слід відкинути";
		getJTextAreaResult().append(texth);
	}

	/**
	 * Insert the method's description here. Creation date: (05.11.2005
	 * 15:07:23)
	 * 
	 * @param item
	 *            java.lang.String
	 * @param size
	 *            int
	 * @param inteval
	 *            int
	 * @param parametr
	 *            int
	 */
	public void testNamed(String item, String size, String inteval,
			String parametr) {
		if (item == " на період") {
			periodTest(Integer.parseInt(size), Integer.parseInt(inteval));
		}
		if (item == " на рівномірність") {
			uniformTest(Integer.parseInt(size), Integer.parseInt(inteval));
		}
		if (item == " на стохостичність") {
			stohosTest(Integer.parseInt(size), Double.parseDouble(parametr));
		}
		if (item == " на незалежність") {
			undepTest(Integer.parseInt(size));
		}
		if (item == " виведення значень") {
			showNumberFor(Integer.parseInt(size));
		}
	}

	/**
	 * Проверяем генератор(ы) на независимость Creation date: (19.11.2005
	 * 22:30:06)
	 * 
	 * @param size
	 *            int - длина последовательности
	 */
	public void undepTest(int size) {
		// Класс, обеспечивающий тестирование и вывод результатов в отдельном
		// потоке
		class Test extends Thread {
			private int shift;

			private int size;

			Test(int newSize, int newShift) {
				super();
				size = newSize;
				shift = newShift;
				
			}

			public void run(){
				if(size<shift) shift = size;
			
				widgets.Diagram diagram = TestRandomUI.this.getDiagram();
				diagram.setVerticalMaxText("1.1");
				diagram.setVerticalMinText("-1.1");
				diagram.setHorizontalMaxText(String.valueOf(shift));
				diagram.setHorizontalMinText(String.valueOf("0"));
			
				/* Определение критического значения критерия Стьюдента */
				double maxT = (stat.StatTables.student05(size));
				float maxR = (float) (maxT/Math.sqrt(size-2 + maxT*maxT));
				diagram.getPainter().setColor(Color.RED );
				diagram.getPainter().placeToXY(0, maxR);
				diagram.getPainter().drawToXY(shift, maxR);
				diagram.getPainter().placeToXY(0, -maxR);
				diagram.getPainter().drawToXY(shift,-maxR);
				
				diagram.getPainter().setColor(Color.BLACK);
				diagram.getPainter().placeToXY(0, 0);
				diagram.getPainter().drawToXY(shift,0);
				
	
				diagram.getPainter().setColor(Color.MAGENTA);
				diagram.getPainter().placeToXY(0, 0);
				/* Создаем maccивы для случайных последовательности */
				double[] a0 = new double[size + shift];
				double[] a1 = new double[size];
				double[] a2 = new double[size];
				/*
				 * Создаем генератор случайных чисел в соответствии с визуальной
				 * частью и заполняем нулевую последовательность с учетом
				 * требуемого сдвига
				 */
				Randoms rnd1 = TestRandomUI.this.createGenerator(false);
				for (int i = 0; i < size + shift; i++) {
					a0[i] = rnd1.nextFloat();
				}	
				// Копируем элементы в первую последовательность
				for (int i = 0; i < size; i++) {
					a1[i] = a0[i];
				}
				
				for (int x = 1; x <=shift; x++) {
					// Копируем элементы вj вторую последовательность
					for (int i = 0; i < size; i++) {
						a2[i] = a0[i + x];
					}
					/* Расчет коэффициента корреляции */
					float r = (float) stat.StatTables.koefKorr(a1, a2);
					
					diagram.getPainter().fillOvalAtXY(x,r,2,2);
					
				}
				
	
	
	
				

			}
		} // Конец описания внутреннего класса

		/* Считывание параметра теста */
		int shift = Integer.parseInt(getJTextFieldShift().getText());

		// Очистка поля вывода текста
		getJTextAreaResult().setText("Тест на незалежність. \n"+
		"На графіку показано, як змінюється коефіцієт кореляції "+
				"в залежності від величини зсуву між початком базової "+
		"та зсунутої послідовностей.\n");
		// Создание и запуск потока тестирования
		Test z = new Test(size, shift);
		z.start();
		return;
	}

	/**
	 * Тестирование генератора случайных чисел на равномерность. Creation date:
	 * (10.11.2005 23:34:27)
	 * 
	 * @param size
	 *            int - объем выборки.
	 * @param interval
	 *            int - число интервалов.
	 */
	public void uniformTest(int size, int interval) {
		/* Создание и настройка генератора в соответствии с визуальной частью */
		Randoms rnd = this.createGenerator(false);
		/* Создаем гистограмму, как накопитель данных */
		stat.Histo h = new stat.Histo();
		h.initFromTo(0, 1, interval);
		/* Заполняем гистограмму */
		for (int i = 0; i < size; i++)
			h.add((double) rnd.nextFloat());
		/* Отображаем гистограмму на панели диаграммы */
		h.showRelFrec(getDiagram(), java.awt.Color.magenta, 0.8, 0.1, true);
		// Извлекаем из гистограммы массив абсолютных частот
		// Крайние дополнительные интервалы не учитываем, там пусто
		double[] af = new double[interval];
		for (int i = 0; i < interval; i++) {
			af[i] = h.absolutFrequency()[i + 1];
		}
		// Создаем массив теоретических частот
		double[] at = new double[interval];
		for (int i = 0; i < interval; i++) {
			at[i] = (double) size / interval;
		}
		/* Проводим тест */
		getJTextAreaResult().setText("Тест на рівномірность\n");
		// Вычисляем результаты по критерию
		double res[] = stat.StatTables.pirsonResult(af, at, h.getBorders(),
				getJTextAreaResult(), 0);
		double hi = res[0];
		double max = res[1]; // Критическое значение хи-квадрат
		String text = "Хі-квадрат = " + stat.StatTables.format(hi, 1, 1)
				+ "\r\nКритичне значення = "
				+ stat.StatTables.format(max, 1, 1)
				+ "\r\nГіпотезу про рівномірний розподіл";
		if (hi < max)
			text += " можна прийняти";
		else
			text += " слід відкинути";
		getJTextAreaResult().append(text);
	}
}
