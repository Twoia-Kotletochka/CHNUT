package mainForAllLab;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import tester.Tester;
import buldo2022.BuldGUI;
import example.cafe.CoffeeHouseUI;
import example.disco.DiscoGuiNew;
import example.market.MarketGUI;
import example.smo2.QSystemExampleUI;
import toLab1.HomeWorkForLab1;
import toLab2.TestRandomUI;
import toLab2.TestRndFile;
import toLab2.TestStreamUI;
import toLab3and4.GUI;
import toLab7_TransProcess.TransGUI;
import toLab8_testGradient.TestGradientUI;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
		MainUI application = new MainUI();
		application.setLocation(210, 70);
		application.setVisible(true);
	}

	private JButton jButton1 = null;
	
	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private JButton jButton4 = null;

	private JButton jButton5 = null;

	private JButton jButton51 = null;
	
	private JButton jButton521 = null;

	private JButton jButton5211 = null;

	private JButton jButton5212 = null;

	private JButton jButton5213 = null;
	
	private JButton jButton52131;
	

	private JButton jButton6 = null; 

	private JButton jButton61 = null;

	private JButton jButton62 = null;

	private JButton jButton621 = null;

	private JButton jButton6211 = null;

	private JButton jButton62111 = null; 

	private JButton jButton62112 = null;

	private JButton jButton62113 = null;

	private JButton jButton7 = null;

	private JButton jButton70;

	private JButton jButton71 = null;



	private JButton jButton72 = null; 

	private JButton jButton73 = null;

	private JButton jButton74 = null;

	private JButton jButton75 = null;

	private JButton jButton76 = null;

	private JButton jButton77 = null; 

	private JButton jButton78 = null;

	private JButton jButton79 = null;

	private JButton jButton8 = null; 

	private JButton jButton9 = null;

	private JButton jButton91 = null;

	private JButton jButton92 = null;

	private JButton jButton93 = null;


	private JButton jButton94 = null; 

	private JButton jButton941 = null;

	private JButton jButton942 = null;

	private JButton jButton943 = null;

	private JButton jButton944;

	private JButton jButtonD3Komponents = null; 

	private JButton jButtonD3smo = null;

	private JButton jButtonD03 = null;

	private JButton jButtonD05 = null;

	private JButton jButtonD15Ord = null;

	private JButton jButtonD1Example = null;

	private JButton jButtonD1RandomValue = null; 

	private JButton jButtonD222 = null;

	private JButton jButtonD223 = null;

	private JButton jButtonD2Test = null;

	private JButton jButtonD2TestRundomUI = null;

	private JButton jButtonD4teor = null;

	private JButton jButtonD4example = null;

	private JButton jButtonD34 = null;

	private JButton jButtonD37 = null;

	private JButton jButtonD3Example = null;

	private JButton jButtonD3Model = null;

	private JButton jButtonD3ord = null;

	private JButton jButtonD3rgr = null;
	
	private JButton jButtonD3GUI = null;

	private JButton jButtonD3Test = null;

	private JButton jButtonItogTest = null;

	private JPanel jContentPane = null;

	private JPanel jContentPane2 = null;

	private JPanel jContentPane4 = null;

	private JPanel jContentPaneD1 = null;

	private JPanel jContentPaneD2 = null; 

	private JPanel jContentPaneD3 = null;

	private JDialog jDialog1 = null;

	private JDialog jDialog2 = null;

	private JDialog jDialog3 = null;

	private JDialog jDialog5 = null;

	private JDialog jDialog4 = null;

	private JDialog jDialog6 = null;

	private JDialog jDialog7 = null;

	private JDialog jDialog9 = null;
	private JFrame jFrame = null;
	private JPanel jPanel1 = null;

	private JPanel jContentPaneD4 = null;

	private JPanel jPanel2 = null;


	
	private JPanel jPanel7 = null;
	

	private JPanel jPanelForMainButtons = null;
	private JScrollPane jScrollPane = null;

	private JTextPane jTextPane = null;

	/**
	 * This is the default constructor
	 */
	public MainUI() {
		super();
		initialize();
	}

	
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton51 == null) {
			jButton51 = new JButton();
			jButton51.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton51.setText("Лабораторне застосування");
			jButton51.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					toLab5_Theory.TheoryGUI app = new toLab5_Theory.TheoryGUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);
				}
			});
		}
		return jButton51;
	}
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton1.setText("1 Спогади про  теорію ймовірностей");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog1().setVisible(true);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton10
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton102() {
		if (jButton62 == null) {
			jButton62 = new JButton();
			jButton62.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton62.setText("Багаторівневий експеримент");
			jButton62.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					showIntoFrame("Теоретичнівідомості до лабораторної роботи №7",
							"/zhtm/lab7/MultiLevelExp.htm");
				}
			});
		}
		return jButton62;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton13() {
		if (jButton61 == null) {
			jButton61 = new JButton();
			jButton61.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton61.setText("Лабораторне застосування");
			jButton61.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					toLab6_FactorExperiment.FactorGUI app = new toLab6_FactorExperiment.FactorGUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);
				}
			});
		}
		return jButton61;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2
					.setText("2 Генератори випадкових чисел");
			jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(true);

				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3
					.setText("3 Побудова імітаційних моделей систем масового обслуговування");
			jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					getJDialog3().setVisible(true);
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4
					.setText("4 Засоби імітаційного моделювання паралельних процесів");
			jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog4().setVisible(true);

				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton5
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5
					.setText("5 Аналітичне та експериментальне дослідження найпростішої СМО");
			jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(true);
				}
			});
		}
		return jButton5;
	}

	/**
	 * This method initializes jButton521
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton521() {
		if (jButton521 == null) {
			jButton521 = new JButton();
			jButton521.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton521.setText("Тест");
			jButton521.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(5);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab5();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №5");
//							application.getJFrame().setVisible(true);
						}
					});

				}
			});
		}
		return jButton521;
	}

	/**
	 * This method initializes jButton5211
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5211() {
		if (jButton5211 == null) {
			jButton5211 = new JButton();
			jButton5211.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton5211.setText("Аналітичні методи дослідженя СМО");
			jButton5211.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №6",
							"/zhtm/lab6/analitics5.htm");

				}
			});
		}
		return jButton5211;
	}

	/**
	 * This method initializes jButton5212
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5212() {
		if (jButton5212 == null) {
			jButton5212 = new JButton();
			jButton5212.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton5212.setText("Побудова моделі для дослідження СМО");
			jButton5212.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №6",
							"/zhtm/lab6/project5.htm");

				}
			});
		}
		return jButton5212;
	}

	/**
	 * This method initializes jButton5213
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5213() {
		if (jButton5213 == null) {
			jButton5213 = new JButton();
			jButton5213.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton5213.setText("Порядок виконання");
			jButton5213.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи №6",
							"/zhtm/lab6/ord5.htm");

				}
			});
		}
		return jButton5213;
	}

	private JButton getJButton52131() {
		if (jButton52131 == null) {
			jButton52131 = new JButton();
			jButton52131.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton52131.setText("Методика отримання стат. інформації");
			jButton52131.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog5().setVisible(false);
					showIntoFrame("Методика отримання статистичної інформації",
							"/zhtm/lab6/stat5.htm");

				}
			});
		}
		return jButton52131;
	}

	/**
	 * This method initializes jButton6
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("6 Однофакторні багаторівневі експерименти з моделями");
			jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(true);
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton621
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton621() {
		if (jButton621 == null) {
			jButton621 = new JButton();
			jButton621.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton621.setText("Тест");
			jButton621.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(6);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab6();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №6");
//							application.getJFrame().setVisible(true);
					}
				});

				}
			});
		}
		return jButton621;
	}

	/**
	 * This method initializes jButton6211
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton6211() {
		if (jButton6211 == null) {
			jButton6211 = new JButton();
			jButton6211.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton6211.setText("Порядок виконання");
			jButton6211.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи №7",
							"/zhtm/lab7/Ord6.htm");

				}
			});
		}
		return jButton6211;
	}

	/**
	 * This method initializes jButton62111
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton62111() {
		if (jButton62111 == null) {
			jButton62111 = new JButton();
			jButton62111.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton62111.setText("Довірчий інтервал");
			jButton62111.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №7",
							"/zhtm/lab7/DovInterval_.htm");
				}
			});
		}
		return jButton62111;
	}

	/**
	 * This method initializes jButton62112
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton62112() {
		if (jButton62112 == null) {
			jButton62112 = new JButton();
			jButton62112.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton62112.setText("Компоненти для автоматизації");
			jButton62112.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					showIntoFrame("Теоретичнівідомості до лабораторної роботи №7",
							"/zhtm/lab7/Components.htm");
				}
			});
		}
		return jButton62112;
	}

	/**
	 * This method initializes jButton62113
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton62113() {
		if (jButton62113 == null) {
			jButton62113 = new JButton();
			jButton62113.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton62113.setText("Опис лабораторного застосування");
			jButton62113.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog6().setVisible(false);
					showIntoFrame("Теоретичнівідомості до лабораторної роботи №7",
							"/zhtm/lab7/LabProject.htm");

				}
			});
		}
		return jButton62113;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7
					.setText("7 Перехідні процеси у системах масового обслуговування");
			jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(true);
				}
			});
		}
		return jButton7;
	}

	private JButton getJButton70() {
		if (jButton70 == null) {
			jButton70 = new JButton();
			jButton70.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton70.setText("Опис лабораторного застосування");
			jButton70.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/LabProject.htm");
				}
			});
		}
		return jButton70;
	}

	/**
	 * This method initializes jButton71
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton71() {
		if (jButton71 == null) {
			jButton71 = new JButton();
			jButton71.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton71.setText("Методика пошуку перехідного процесу");
			jButton71.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/TransProcess.htm");

				}
			});
		}
		return jButton71;
	}

	/**
	 * This method initializes jButton72
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton72() {
		if (jButton72 == null) {
			jButton72 = new JButton();
			jButton72.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton72.setText("Функція регресі та цільова функція");
			jButton72.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/MinSqrMinAbs.htm");
				}
			});
		}
		return jButton72;
	}

	/**
	 * This method initializes jButton73
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton73() {
		if (jButton73 == null) {
			jButton73 = new JButton();
			jButton73.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton73.setText("Метод чисел Фібоначчі");
			jButton73.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/Fibo1.htm");

				}
			});
		}
		return jButton73;
	}

	/**
	 * This method initializes jButton74
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton74() {
		if (jButton74 == null) {
			jButton74 = new JButton();
			jButton74.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton74.setText("Інші методи");
			jButton74.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/other.htm");

				}
			});
		}
		return jButton74;
	}

	/**
	 * This method initializes jButton75
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton75() {
		if (jButton75 == null) {
			jButton75 = new JButton();
			jButton75.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton75.setText("Засоби для виявлення перехідного процесу");
			jButton75.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/Components.htm");

				}
			});
		}
		return jButton75;
	}

	/**
	 * This method initializes jButton76
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton76() {
		if (jButton76 == null) {
			jButton76 = new JButton();
			jButton76.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton76.setText("Лабораторне застосування");
			jButton76.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					TransGUI app = new TransGUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);
				}
			});
		}
		return jButton76;
	}

	/**
	 * This method initializes jButton77
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton77() {
		if (jButton77 == null) {
			jButton77 = new JButton();
			jButton77.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton77.setText("Тест");
			jButton77.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(7);
//						}
//							MainTest application = (MainTest) MainTest
//									.getTestForLab7();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №7");
//							application.getJFrame().setVisible(true);
						}
					});
				}
			});

		}
		return jButton77;
	}

	/**
	 * This method initializes jButton78
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton78() {
		if (jButton78 == null) {
			jButton78 = new JButton();
			jButton78.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton78.setText("Порядок виконання");
			jButton78.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи №8",
							"/zhtm/lab8/Ord.htm");

				}
			});
		}
		return jButton78;
	}

	private JButton getJButton79() {

		if (jButton79 == null) {
			jButton79 = new JButton();
			jButton79.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton79.setText("Засоби для пошуку параметрів перехідного процесу");
			jButton79.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog7().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №8",
							"/zhtm/lab8/ParmFinder.htm");

				}
			});
		}
		return jButton79;
	}

	/**
	 * This method initializes jButton8
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setText("Багатофакторні експерименти для пошуку екстремуму");
			jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					new TestGradientUI().setVisible(true);
				}
			});
		}
		return jButton8;
	}

	/**
	 * This method initializes jButton9
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton9() {
		if (jButton9 == null) {
			jButton9 = new JButton();
			jButton9.setText("Приклади виконання завдань на РГР");
			jButton9.setActionCommand("Приклади виконання завдань на  РГР");
			jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(true);
				}
			});
		}
		return jButton9;
	}

	/**
	 * This method initializes jButton81
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton91() {
		if (jButton91 == null) {
			jButton91 = new JButton();
			jButton91.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton91.setText("РГР \"Buldo\"");
			jButton91.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new BuldGUI().setVisible(true);
				}
			});
		}
		return jButton91;
	}

	/**
	 * This method initializes jButton82
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton92() {
		if (jButton92 == null) {
			jButton92 = new JButton();
			jButton92.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton92.setText("Моделювання комутатора");
			jButton92.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new example.swich.SwitchUI().setVisible(true);
				}
			});
		}
		return jButton92;
	}




	/**
	 * This method initializes jButton83
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton93() {
		if (jButton93 == null) {
			jButton93 = new JButton();
			jButton93.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton93.setText("Мистер Шанс");
			jButton93.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new example.shans.ShansUI().setVisible(true);
				}
			});
		}
		return jButton93;
	}

	/**
	 * This method initializes jButton84
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton94() {
		if (jButton94 == null) {
			jButton94 = new JButton();
			jButton94.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButton94.setText("Змагання");
			jButton94.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new example.competition.VisualPart().setVisible(true);

				}
			});
		}
		return jButton94;
	}

	/**
	 * This method initializes jButton941
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton941() {
		if (jButton941 == null) {
			jButton941 = new JButton();
			jButton941.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton941.setText("Кафе");
			jButton941.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new CoffeeHouseUI().setVisible(true);

				}
			});
		}
		return jButton941;
	}

	/**
	 * This method initializes jButton942
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton942() {
		if (jButton942 == null) {
			jButton942 = new JButton();
			jButton942.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton942.setText("Вплив реклами");
			jButton942.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new MarketGUI().setVisible(true);

				}
			});
		}
		return jButton942;
	}



	/**
	 * This method initializes jButton943
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton943() {
		if (jButton943 == null) {
			jButton943 = new JButton();
			jButton943.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton943.setText("Двофазна СМО");
			jButton943.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new QSystemExampleUI().setVisible(true);

				}
			});
		}
		return jButton943;
	}


	private JButton getJButton944() {
		if (jButton944 == null) {
			jButton944 = new JButton();
			jButton944.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButton944.setText("Дискотека");
			jButton944.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog9().setVisible(false);
					new DiscoGuiNew().setVisible(true);

				}
			});
		}
		return jButton944;
	}

	/**
	 * This method initializes jButtonD3Komponents
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3Komponents() {
		if (jButtonD3Komponents == null) {
			jButtonD3Komponents = new JButton();
			jButtonD3Komponents.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD3Komponents.setText("Інформація про компоненти");
			jButtonD3Komponents.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи № 3",
							"/zhtm/lab3/Lab3Komponents.htm");
				}
			});
		}
		return jButtonD3Komponents;
	}

	/**
	 * This method initializes jButtonD3smo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3smo() {
		if (jButtonD3smo == null) {
			jButtonD3smo = new JButton();
			jButtonD3smo.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD3smo.setText("Інформація про СМО");
			jButtonD3smo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи № 4",
							"/zhtm/lab3/SMO.htm");
				}
			});
		}
		return jButtonD3smo;
	}

	/**
	 * This method initializes jButtonD1Example
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD1Example() {
		if (jButtonD1Example == null) {
			jButtonD1Example = new JButton();
			jButtonD1Example.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD1Example.setText("Що треба створити самостійно");
			jButtonD1Example.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog1().setVisible(false);
					HomeWorkForLab1 app = new HomeWorkForLab1();
					app.setLocationRelativeTo(jButtonD1Example);
					app.setVisible(true);

				}
			});
		}
		return jButtonD1Example;
	}

	/**
	 * This method initializes jButtonD03
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD1Ord() {
		if (jButtonD03 == null) {
			jButtonD03 = new JButton();
			jButtonD03.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD03.setText("Порядок виконання");
			jButtonD03.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog1().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи № 1",
							"/zhtm/lab1/Lab1Ord.htm");
				}
			});
		}
		return jButtonD03;
	}

	/**
	 * This method initializes jButtonD1RandomValue
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD1RandomValue() {
		if (jButtonD1RandomValue == null) {
			jButtonD1RandomValue = new JButton();
			jButtonD1RandomValue.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD1RandomValue.setText("Знайомство з випадковими величинами");
			jButtonD1RandomValue.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog1().setVisible(false);
					toLab1.App app = new toLab1.App();
					app.setLocationRelativeTo(jButtonD1RandomValue);
					app.setVisible(true);
				}
			});
		}
		return jButtonD1RandomValue;
	}

	/**
	 * This method initializes jButtonD05
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD1Test() {
		if (jButtonD05 == null) {
			jButtonD05 = new JButton();
			jButtonD05.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD05.setText("Тест");
			jButtonD05.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog1().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(1);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab1();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №1");
//							application.getJFrame().setVisible(true);
						}
					});
				}
			});
		}
		return jButtonD05;
	}

	/**
	 * This method initializes jButtonD222
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD222() {
		if (jButtonD222 == null) {
			jButtonD222 = new JButton();
			jButtonD222.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD222.setText("Демонстрація випадкових потоків");
			jButtonD222.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(false);
					TestStreamUI app = new TestStreamUI();
					app.setLocationRelativeTo(jButtonD222);
					app.setVisible(true);

				}
			});
		}
		return jButtonD222;
	}

	private JButton getJButtonD223() {
		if (jButtonD223 == null) {
			jButtonD223 = new JButton();
			jButtonD223.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD223.setText("Тестування файлів випадкових чисел");
			jButtonD223.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(false);
					TestRndFile app = new TestRndFile();
					app.setLocationRelativeTo(jButtonD223);
					app.setVisible(true);

				}
			});
		}
		return jButtonD223;
	}

	/**
	 * This method initializes jButtonD15Ord
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD2Ord() {
		if (jButtonD15Ord == null) {
			jButtonD15Ord = new JButton();
			jButtonD15Ord.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD15Ord.setText("Порядок виконання");
			jButtonD15Ord.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи №2",
							"/zhtm/lab2/Lab2Ord.htm");
				}
			});
		}
		return jButtonD15Ord;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD2Test() {
		if (jButtonD2Test == null) {
			jButtonD2Test = new JButton();
			jButtonD2Test.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD2Test.setText("Тест");
			jButtonD2Test.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(2);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab2();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №2");
//							application.getJFrame().setVisible(true);
						}
					});
				}
			});
		}
		return jButtonD2Test;
	}

	/**
	 * This method initializes jButtonD36
	 * 
	 * @return javax.swing.JButton
	 */

	/**
	 * This method initializes jButton10
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD2TestRundomUI() {
		if (jButtonD2TestRundomUI == null) {
			jButtonD2TestRundomUI = new JButton();
			jButtonD2TestRundomUI.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD2TestRundomUI.setActionCommand("");
			jButtonD2TestRundomUI.setText("Лабораторне застосування");
			jButtonD2TestRundomUI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog2().setVisible(false);
					TestRandomUI app = new TestRandomUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);
				}
			});
		}
		return jButtonD2TestRundomUI;
	}

	/**
	 * This method initializes jButtonD4teor
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD4teor() {
		if (jButtonD4teor == null) {
			jButtonD4teor = new JButton();
			jButtonD4teor.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD4teor.setText("Теоретичні відомості");
			jButtonD4teor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog4().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи №4",
							"/zhtm/lab4/Lab4teor.htm");
				}
			});
		}
		return jButtonD4teor;
	}

	/**
	 * This method initializes jButtonD4example
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD4example() {
		if (jButtonD4example == null) {
			jButtonD4example = new JButton();
			jButtonD4example.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD4example.setText("Приклад створення класів для акторів");
			jButtonD4example.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog4().setVisible(false);
					showIntoFrame(
							"Приклад створення класів для акторів до лабораторної роботи №4",
							"/zhtm/lab4/Lab4Example.htm");
				}
			});
		}
		return jButtonD4example;
	}



	/**
	 * This method initializes jButtonD34
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD4ord() {
		if (jButtonD34 == null) {
			jButtonD34 = new JButton();
			jButtonD34.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD34.setText("Порядок виконання");
			jButtonD34.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog4().setVisible(false);
					showIntoFrame("Порядок виконання лабораторної роботи №4",
							"/zhtm/lab4/Lab4Ord.htm");
				}
			});
		}
		return jButtonD34;
	}

	/**
	 * This method initializes jButtonD37
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD4test() {
		if (jButtonD37 == null) {
			jButtonD37 = new JButton();
			jButtonD37.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD37.setText("Тест");
			jButtonD37.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog4().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(4);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab4();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі № 4");
//							application.getJFrame().setVisible(true);
						}
					});

				}
			});
		}
		return jButtonD37;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3Example() {
		if (jButtonD3Example == null) {
			jButtonD3Example = new JButton();
			jButtonD3Example.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD3Example.setActionCommand("");
			jButtonD3Example.setText("Приклад розробки моделі простої СМО");
			jButtonD3Example.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи № 3",
							"/zhtm/lab3/Lab3Example.htm");
				}
			});
		}
		return jButtonD3Example;
	}

	/**
	 * This method initializes jButton31
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3Model() {
		if (jButtonD3Model == null) {
			jButtonD3Model = new JButton();
			jButtonD3Model.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButtonD3Model.setText("Методика розробки моделі");
			jButtonD3Model.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					showIntoFrame("Теоретичні відомості до лабораторної роботи № 3",
							"/zhtm/lab3/Lab3Model.htm");
				}
			});
		}
		return jButtonD3Model;
	}

	/**
	 * This method initializes jButtonD3ord
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3ord() {
		if (jButtonD3ord == null) {
			jButtonD3ord = new JButton();
			jButtonD3ord.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonD3ord.setText("Порядок виконання");
			jButtonD3ord.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					showIntoFrame("Інформація до виконання лабораторної роботи № 3",
							"/zhtm/lab3/Lab3Ord.htm");

				}
			});
		}
		return jButtonD3ord;
	}

	/**
	 * This method initializes jButtonD01
	 * 
	 * @return javax.swing.JButton
	 */


	/**
	 * This method initializes jButton32
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3rgr() {
		if (jButtonD3rgr == null) {
			jButtonD3rgr = new JButton();
			jButtonD3rgr.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButtonD3rgr.setText("Приклад реалізації РГР");
			jButtonD3rgr.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					buldo2022.BuldGUI app = new buldo2022.BuldGUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);

				}
			});
		}
		return jButtonD3rgr;
	}
	private JButton getJButtonD3GUI() {
		if (jButtonD3GUI == null) {
			jButtonD3GUI = new JButton();
			jButtonD3GUI.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButtonD3GUI.setText("Приклад реалізації моделі простої СМО");
			jButtonD3GUI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					GUI app = new toLab3and4.GUI();
					app.setLocationRelativeTo(MainUI.this);
					app.setVisible(true);

				}
			});
		}
		return jButtonD3GUI;
	}

	/**
	 * This method initializes jButton43
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonD3Test() {
		if (jButtonD3Test == null) {
			jButtonD3Test = new JButton();
			jButtonD3Test.setText("Тест");
			jButtonD3Test.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5,
					5, 5, java.awt.Color.white));
			jButtonD3Test.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJDialog3().setVisible(false);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(3);
//							MainTest application = (MainTest) MainTest
//									.getTestForLab3();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle("Тест по лаб.роботі №3");
//							application.getJFrame().setVisible(true);
						}
					});
				}
			});
		}
		return jButtonD3Test;
	}

	/**
	 * This method initializes jButtonItogTest
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonItogTest() {
		if (jButtonItogTest == null) {
			jButtonItogTest = new JButton();
			jButtonItogTest.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
					Color.white));
			jButtonItogTest.setText("Ітоговий тест");
			jButtonItogTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Tester.runTest(8);
//							MainTest application = (MainTest) MainTest
//									.getTestForAllLab();
//							application.getJFrame().setLocationRelativeTo(MainUI.this);
//							application.getJFrame().setTitle(
//									"Тест по усім лаб.роботам");
//							application.getJFrame().setVisible(true);
						}
					});
				}
			});
		}
		return jButtonItogTest;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			JLabel jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 78, 495, 16));
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel2.setFont(new java.awt.Font("MS Reference Sans Serif",
					java.awt.Font.BOLD, 14));
			jLabel2.setText("Перелік тем з дисципліни \"Моделювання\"");
			
			JLabel jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 37, 495, 16));
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			jLabel1.setText("Кафедра інформаційних та комп'ютерних систем");
			
			JLabel jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 11, 494, 16));
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			jLabel.setText("Національний університет \"Чернігівська політехніка\"");
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new java.awt.Color(202, 227, 252));
			jContentPane.add(getJPanelForMainButtons(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jContentPane2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane2() {
		if (jContentPane2 == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(8);
			gridLayout2.setColumns(1);
			gridLayout2.setVgap(2);
			jContentPane2 = new JPanel();
			jContentPane2.setLayout(gridLayout2);
			jContentPane2.add(getJButton91(), null);
			jContentPane2.add(getJButton92(), null);
			jContentPane2.add(getJButton93(), null);
			jContentPane2.add(getJButton94(), null);
			jContentPane2.add(getJButton941(), null);
			jContentPane2.add(getJButton943(), null);
			jContentPane2.add(getJButton942(), null);
			jContentPane2.add(getJButton944(), null);
		}
		return jContentPane2;
	}



	/**
	 * This method initializes jContentPane4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane4() {
		if (jContentPane4 == null) {
			jContentPane4 = new JPanel();
			jContentPane4.setLayout(new CardLayout());
			jContentPane4.add(getJScrollPane(), getJScrollPane().getName());
		}
		return jContentPane4;
	}

	/**
	 * This method initializes jContentPaneD1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPaneD1() {
		if (jContentPaneD1 == null) {
			GridLayout gridLayoutD1 = new GridLayout();
			gridLayoutD1.setRows(5);
			gridLayoutD1.setHgap(0);
			gridLayoutD1.setVgap(3);
			gridLayoutD1.setColumns(1);
			jContentPaneD1 = new JPanel();
			jContentPaneD1.setLayout(gridLayoutD1);
			jContentPaneD1.add(getJButtonD1Ord(), null);
			jContentPaneD1.add(getJButtonD1RandomValue(), null);
			jContentPaneD1.add(getJButtonD1Example(), null);
			jContentPaneD1.add(getJButtonD1Test(), null);
		}
		return jContentPaneD1;
	}

	/**
	 * This method initializes jContentPaneD2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPaneD2() {
		if (jContentPaneD2 == null) {
			GridLayout gridLayout3 = new GridLayout();
			gridLayout3.setRows(5);
			gridLayout3.setColumns(1);
			gridLayout3.setVgap(3);
			jContentPaneD2 = new JPanel();
			jContentPaneD2.setLayout(gridLayout3);
			jContentPaneD2.add(getJButtonD2TestRundomUI(), null);
			jContentPaneD2.add(getJButtonD223(), null);
			jContentPaneD2.add(getJButtonD222(), null);
			jContentPaneD2.add(getJButtonD2Ord(), null);
			jContentPaneD2.add(getJButtonD2Test(), null);
		}
		return jContentPaneD2;
	}





	/**
	 * This method initializes jContentPaneD3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPaneD3() {
		if (jContentPaneD3 == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(8);
			gridLayout1.setVgap(2);
			jContentPaneD3 = new JPanel();
			jContentPaneD3.setLayout(gridLayout1);
			jContentPaneD3.add(getJButtonD3smo(), null);
			jContentPaneD3.add(getJButtonD3Komponents(), null);
			jContentPaneD3.add(getJButtonD3Model(), null);
			jContentPaneD3.add(getJButtonD3Example(), null);
			jContentPaneD3.add(getJButtonD3GUI(), null);
			jContentPaneD3.add(getJButtonD3rgr(), null);
			jContentPaneD3.add(getJButtonD3ord(), null);
			jContentPaneD3.add(getJButtonD3Test(), null);
		}
		return jContentPaneD3;
	}

	/**
	 * This method initializes jDialog1
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog1() {
		if (jDialog1 == null) {
			jDialog1 = new JDialog();
			jDialog1.setModal(true);
			jDialog1.setTitle("Лабораторна робота 1");
			jDialog1.setSize(new Dimension(282, 186));
			jDialog1.setContentPane(getJContentPaneD1());
			jDialog1.setLocationRelativeTo(getJButton1());
		}
		return jDialog1;
	}

	/**
	 * This method initializes jDialog2
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog2() {
		if (jDialog2 == null) {
			jDialog2 = new JDialog();
			jDialog2.setSize(new Dimension(243, 225));
			jDialog2.setModal(true);
			jDialog2.setTitle("Лабораторна робота 2");
			jDialog2.setLocationRelativeTo(getJButton2());
			jDialog2.setContentPane(getJContentPaneD2());
		}
		return jDialog2;
	}

	/**
	 * This method initializes jDialog
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog3() {
		if (jDialog3 == null) {
			jDialog3 = new JDialog(this);
			jDialog3.setSize(new Dimension(299, 300));
			jDialog3.setTitle("Лабораторна робота 3");
			jDialog3.setModal(true);
			jDialog3.setLocationRelativeTo(getJButton3());
			jDialog3.setContentPane(getJContentPaneD3());
		}
		return jDialog3;
	}

	/**
	 * This method initializes jDialog
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog5() {
		if (jDialog5 == null) {
			jDialog5 = new JDialog();
			jDialog5.setSize(new Dimension(275, 244));
			jDialog5.setModal(true);
			jDialog5.setTitle("Лабораторна робота №6");
			jDialog5.setLocationRelativeTo(getJButton5());
			jDialog5.setContentPane(getJPanel1());
		}
		return jDialog5;
	}


	/**
	 * This method initializes jDialog4
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog4() {
		if (jDialog4 == null) {
			jDialog4 = new JDialog();
			jDialog4.setModal(true);
			jDialog4.setLocationRelativeTo(getJButton4());
			jDialog4.setSize(new Dimension(290, 200));
			jDialog4.setTitle("Лабораторна робота 4");
			jDialog4.setLocationRelativeTo(getJButton4());
			jDialog4.setContentPane(getJContentPaneD4());
		}
		return jDialog4;
	}

	/**
	 * This method initializes jDialog
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog6() {
		if (jDialog6 == null) {
			jDialog6 = new JDialog();
			jDialog6.setSize(new Dimension(246, 274));
			jDialog6.setModal(true);
			jDialog6.setTitle("Лабораторна робота №7");
			jDialog6.setLocationRelativeTo(getJButton6());
			jDialog6.setContentPane(getJPanel2());
		}
		return jDialog6;
	}



	/**
	 * This method initializes jDialog7
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog7() {
		if (jDialog7 == null) {
			jDialog7 = new JDialog();
			jDialog7.setModal(true);
			jDialog7.setSize(new Dimension(336, 360));
			jDialog7.setContentPane(getJPanel7());
			jDialog7.setTitle("Лабораторна робота №8");
			jDialog7.setLocationRelativeTo(getJButton7());
		}
		return jDialog7;
	}

	/**
	 * This method initializes jDialog2
	 * 
	 * @return javax.swing.JDialog
	 */
	private JDialog getJDialog9() {
		if (jDialog9 == null) {
			jDialog9 = new JDialog(this);
			jDialog9.setSize(new Dimension(301, 290));
			jDialog9.setModal(true);
			jDialog9.setLocationRelativeTo(getJButton9());
			jDialog9.setContentPane(getJContentPane2());
		}
		return jDialog9;
	}

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(102, 62));
			jFrame.setTitle("");
			jFrame.setContentPane(getJContentPane4());
			jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		return jFrame;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridLayout gridLayout4 = new GridLayout();
			gridLayout4.setRows(6);
			gridLayout4.setVgap(1);
			jPanel1 = new JPanel();
			jPanel1.setLayout(gridLayout4);
			jPanel1.add(getJButton5211(), null);
			jPanel1.add(getJButton5212(), null);
			jPanel1.add(getJButton52131(), null);
			jPanel1.add(getJButton5213(), null);
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton521(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jContentPaneD4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPaneD4() {
		if (jContentPaneD4 == null) {
			GridLayout gridLayout41 = new GridLayout();
			gridLayout41.setRows(4);
			gridLayout41.setVgap(3);
			jContentPaneD4 = new JPanel();
			jContentPaneD4.setLayout(gridLayout41);
			jContentPaneD4.add(getJButtonD4teor(), null);
			jContentPaneD4.add(getJButtonD4example(), null);
			jContentPaneD4.add(getJButtonD4ord(), null);
			jContentPaneD4.add(getJButtonD4test(), null);
		}
		return jContentPaneD4;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridLayout gridLayout5 = new GridLayout();
			gridLayout5.setRows(7);
			gridLayout5.setVgap(3);
			jPanel2 = new JPanel();
			jPanel2.setLayout(gridLayout5);
			jPanel2.add(getJButton62111(), null);
			jPanel2.add(getJButton102(), null);
			jPanel2.add(getJButton62112(), null);
			jPanel2.add(getJButton62113(), null);
			jPanel2.add(getJButton6211(), null);
			jPanel2.add(getJButton13(), null);
			jPanel2.add(getJButton621(), null);
		}
		return jPanel2;
	}


	/**
	 * This method initializes jPanel7
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			GridLayout gridLayout7 = new GridLayout();
			gridLayout7.setRows(10);
			gridLayout7.setVgap(3);
			jPanel7 = new JPanel();
			jPanel7.setLayout(gridLayout7);
			jPanel7.add(getJButton71(), null);
			jPanel7.add(getJButton75(), null);
			jPanel7.add(getJButton72(), null);
			jPanel7.add(getJButton73(), null);
			jPanel7.add(getJButton74(), null);
			jPanel7.add(getJButton79(), null);
			jPanel7.add(getJButton79(), null);
			jPanel7.add(getJButton70(), null);
			jPanel7.add(getJButton78(), null);
			jPanel7.add(getJButton76(), null);
			jPanel7.add(getJButton77(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jPanelForMainButtons
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelForMainButtons() {
		if (jPanelForMainButtons == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(10);
			gridLayout.setVgap(1);
			gridLayout.setColumns(0);
			jPanelForMainButtons = new JPanel();
			jPanelForMainButtons.setBorder(javax.swing.BorderFactory.createCompoundBorder(
					javax.swing.BorderFactory
							.createBevelBorder(javax.swing.border.BevelBorder.RAISED),
					javax.swing.BorderFactory
							.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
			jPanelForMainButtons.setLayout(gridLayout);
			jPanelForMainButtons.setBounds(new Rectangle(8, 103, 495, 407));
			jPanelForMainButtons.add(getJButton1(), null);
			jPanelForMainButtons.add(getJButton2(), null);
			jPanelForMainButtons.add(getJButton3(), null);
			jPanelForMainButtons.add(getJButton4(), null);
			jPanelForMainButtons.add(getJButton5(), null);
			jPanelForMainButtons.add(getJButton6(), null);
			jPanelForMainButtons.add(getJButton7(), null);
			jPanelForMainButtons.add(getJButton8(), null);
			jPanelForMainButtons.add(getJButton9(), null);
			jPanelForMainButtons.add(getJButtonItogTest(), null);
		}
		return jPanelForMainButtons;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setName("jScrollPane");
			jScrollPane.setViewportView(getJTextPane());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setName("jTextPane");
			jTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			jTextPane.setEditable(false);
			jTextPane.setContentType("text/html");
		}
		return jTextPane;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(525, 559);
		this.setContentPane(getJContentPane());
		this.setTitle("Навчальний програмний комплекс з дисципліни \"Моделювання\"");
	}


	private void showIntoFrame(String title, String ur) {
		getJFrame().setVisible(true);
		getJFrame().setTitle(title);
		getJFrame().setLocation(210, 70);
		URL url = getClass().getResource(ur);
		try {
			getJTextPane().setPage(url);
		} catch (IOException e1) {
			System.out.println("Problems with file" + ur);
		}
	}

} // @jve:decl-index=0:visual-constraint="40,-82"
