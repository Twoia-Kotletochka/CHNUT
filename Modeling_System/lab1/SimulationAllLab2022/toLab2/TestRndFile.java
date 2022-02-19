package toLab2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import rnd.RandomGenerators;
import stat.Histo;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

public class TestRndFile extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFileName;
	//private File file;
	private ChooseData chooseDataV;
	private JTextArea textArea;
	private Diagram diagram;
	private Histo histo;
	private ChooseRandom chooseRandom;
	private String fileAbsolutePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					TestRndFile frame = new TestRndFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestRndFile() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 618, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 200, 0};
		gbl_contentPane.rowHeights = new int[]{155, 209, 0};
		gbl_contentPane.columnWeights = new double[]{2.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{164, 0};
		gbl_panel_1.rowHeights = new int[]{23, 44, 59, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnNewButton_5 = new JButton("\u0412\u0438\u0431\u0456\u0440 \u0444\u0430\u0439\u043B\u0443");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onChooseFile();
			}
			
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_5.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 0;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		
		textFieldFileName = new JTextField();
		textFieldFileName.setText("\u041D\u0435 \u0432\u0438\u0431\u0440\u0430\u043D\u043E");
		textFieldFileName.setName("");
		textFieldFileName.setMinimumSize(new Dimension(6, 40));
		textFieldFileName.setPreferredSize(new Dimension(40, 50));
		textFieldFileName.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0406\u043C'\u044F \u0432\u0438\u0431\u0440\u0430\u043D\u043E\u0433\u043E \u0444\u0430\u0439\u043B\u0443", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_textFieldFileName = new GridBagConstraints();
		gbc_textFieldFileName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFileName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFileName.gridx = 0;
		gbc_textFieldFileName.gridy = 1;
		panel_1.add(textFieldFileName, gbc_textFieldFileName);
		textFieldFileName.setColumns(10);
		
		chooseDataV = new ChooseData();
		chooseDataV.setText("0");
		chooseDataV.setTitle("\u041E\u0431\u0441\u044F\u0433 \u0432\u0438\u0431\u0456\u0440\u043A\u0438");
		GridBagConstraints gbc_chooseDataV = new GridBagConstraints();
		gbc_chooseDataV.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseDataV.gridx = 0;
		gbc_chooseDataV.gridy = 2;
		panel_1.add(chooseDataV, gbc_chooseDataV);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 67));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{312, 0};
		gbl_panel.rowHeights = new int[]{25, 25, 30, 25, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton_1 = new JButton("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u0434 \u043F\u043E\u0447\u0430\u0442\u043A\u0443 \u0444\u0430\u0439\u043B\u0443");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onShowFirst50();
			}
		});
		btnNewButton_1.setMargin(new Insets(2, 4, 2, 4));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("\u0422\u0435\u0441\u0442 \u0437\u0430 \u041F\u0456\u0440\u0441\u043E\u043D\u043E\u043C \u043D\u0430 \u0432\u0456\u0434\u043F\u043E\u0432\u0456\u0434\u043D\u0456\u0441\u0442\u044C \u0437\u0430\u043A\u043E\u043D\u0443");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTestPirson();
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438 \u0444\u0430\u0439\u043B\u0443");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCalcParameters();
			}
		});
		btnNewButton_2.setMargin(new Insets(2, 4, 2, 4));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 5, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_3.setMargin(new Insets(2, 4, 2, 4));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 5, 5, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		chooseRandom = new ChooseRandom();
		chooseRandom.setTitle("\u0417\u0430\u043A\u043E\u043D \u0440\u043E\u0437\u043F\u043E\u0434\u0456\u043B\u0443 \u0434\u043B\u044F \u0442\u0435\u0441\u0442\u0443\u0432\u0430\u043D\u043D\u044F");
		chooseRandom.setMinimumSize(new Dimension(124, 80));
		chooseRandom.setPreferredSize(new Dimension(137, 60));
		GridBagConstraints gbc_chooseRandom = new GridBagConstraints();
		gbc_chooseRandom.insets = new Insets(0, 5, 5, 5);
		gbc_chooseRandom.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseRandom.gridx = 0;
		gbc_chooseRandom.gridy = 2;
		panel.add(chooseRandom, gbc_chooseRandom);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		scrollPane.setViewportView(textArea);
		
		diagram = new Diagram();
		diagram.setTitleText("\u0413\u0456\u0441\u0442\u043E\u0433\u0440\u0430\u043C\u0430");
		GridBagConstraints gbc_diagram = new GridBagConstraints();
		gbc_diagram.fill = GridBagConstraints.BOTH;
		gbc_diagram.gridx = 1;
		gbc_diagram.gridy = 1;
		contentPane.add(diagram, gbc_diagram);
	}

	protected void onTestPirson() {
		// Выбираем закон распределения
		RandomGenerators gen = getChooseRandom().getRandom();
		// Создаем гистограмму для файла
		if ((gen != null) && (createHisto())) {
			/* Берем из гистограммы эмпирические частоты */
			double[] a = histo.absolutFrequency();
			double[] b = histo.getBorders();
			/* Создаем массив теоретических частот */
			double[] t = new double[a.length];
			t[0] = gen.probability(b[0]);
			t[t.length - 1] = 1 - gen.probability(b[b.length - 1]);
			for (int i = 1; i < t.length - 1; i++)
				t[i] = gen.probability(b[i]) - gen.probability(b[i - 1]);
			/* Превращаем теор. частоты в абсолютные */
			double[] ta = new double[t.length];
			for (int i = 0; i < ta.length; i++)
				ta[i] = t[i] * histo.count();
			/* Рисуем гистограммы эмпирическую и теоретическую */
			histo.showRelFrec(getDiagram(), java.awt.Color.magenta, 0.9,
					0.05, true);
			getDiagram().setPainterColor(java.awt.Color.orange);
			getDiagram().drawBarsDiagram(histo.realBorders(), t, 0.6, 0.3,
					false);
			// Проводим тест и выводим результаты
			textArea.setText("Гіпотеза про відповідність\n");
			textArea.append("вибраному закону розподілу:\n");
			textArea.append(gen.toString() + "\n");
			double[] res = stat.StatTables.pirsonResult(a, ta, histo
					.realBorders(), textArea, gen.getKParm());
			textArea.append(
					"Хі-квадрат = " + stat.StatTables.format(res[0], 1, 1));
			textArea.append(
					"\nКритичне значення = "
							+ stat.StatTables.format(res[1], 1, 1));
			if (res[0] < res[1])
				textArea.append("\nГіпотезу можна прийняти");
			else
				textArea.append("\nГіпотезу слід відкинути");
		}

		
	}

	protected void onCalcParameters() {
		if (this.createHisto()) {
			textArea.setText("Файл " + fileAbsolutePath + ".\n");
			chooseDataV.setInt(histo.count());
			/* Вывод гистограмы */
			histo.showRelFrec(getDiagram(), java.awt.Color.magenta, 0.8,
					0.15, true);
			/* Вывод параметров */
			textArea.append(histo.toString());
			textArea.select(0, 0);
		}
		
	}

	protected void onShowFirst50() {
	BufferedReader dataInStream = openFileForRead();
//		try {
//			dataInStream = new LineNumberReader(new FileReader(file));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		};
		
		int cnt = 0;
		textArea.setText("Перші 50 значень файлу" + "\r\n");
		diagram.clear();
		try {
			String result;
			while ((result = dataInStream.readLine()) != null) {
				/* Output to window not more then 50 numer from begining */
				if (cnt <= 50) {
					this.textArea.append(result + "\n");
				}
				cnt++;
			}
			dataInStream.close();
			chooseDataV.setInt(cnt);
		} catch (IOException ioe) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Imposible to read line строку", "Виведенн значень файлу",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void onChooseFile() {
		JFileChooser dialog = new JFileChooser();
		dialog.showOpenDialog(null);
		File file = dialog.getSelectedFile();
		if(file!=null){
			fileAbsolutePath = 	file.getAbsolutePath();
		textFieldFileName.setText(file.getAbsolutePath());
		}
		else{
			textFieldFileName.setText("Файл не вибрано");	
			fileAbsolutePath = null;
		}
		}
		
	public ChooseData getChooseDataV() {
		return chooseDataV;
	}


	public JTextArea getTextArea() {
		return textArea;
	}
	public Diagram getDiagram() {
		return diagram;
	}
	public boolean createHisto() {
		try {
			/* Предварительная обработка файла" */
			LineNumberReader dataInStream = openFileForRead();
			String result;
			if(histo == null)
				histo = new Histo();
			histo.init();
			while ((result = dataInStream.readLine()) != null) {
				double z = Double.parseDouble(result);
				histo.add(z);
			}
			dataInStream.close();
		} catch (IOException ioe) {
			javax.swing.JOptionPane.showMessageDialog(null, "Reading error",
					"Створення гістограми для файлу",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (java.lang.Throwable ivjExc) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Помилка перетворення", "Створення гістограми для файлу",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public LineNumberReader openFileForRead() {
		try {fileAbsolutePath = getTextFieldFileName().getText();
			
			return new LineNumberReader(new FileReader(new File(fileAbsolutePath)));
		} catch (Exception fnf) {
			javax.swing.JOptionPane.showMessageDialog(null, "Файл "
					+ " не знайдено",
					"Спроба відкриття файлу для читання",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	public ChooseRandom getChooseRandom() {
		return chooseRandom;
	}
	public JTextField getTextFieldFileName() {
		return textFieldFileName;
	}
}
