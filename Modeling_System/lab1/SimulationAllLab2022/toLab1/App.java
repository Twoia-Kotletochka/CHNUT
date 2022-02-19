package toLab1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import rnd.Discret;
import rnd.Negexp;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Dimension;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagram = null;

	private ChooseRandom chooseRandom = null;

	private Histo histo = null; // @jve:decl-index=0:visual-constraint="235,283"

	private DiscretHisto discretHisto = null; // @jve:decl-index=0:visual-constraint="297,283"

	private JButton jButtonGenerate = null;

	private JScrollPane jScrollPane = null;

	private JTextArea jTextArea = null;
	private Diagram diagram_1;
	private JTextField textFieldV;

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setMinimumSize(new Dimension(100, 100));
			diagram.setPreferredSize(new Dimension(406, 200));
			diagram.setTitleText("Щільність розподілу імовірностей");
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
			chooseRandom.setTitle("Налаштовано розподіл");
			chooseRandom.setRandom(new Negexp(1));
		}
		return chooseRandom;
	}

	/**
	 * This method initializes histo
	 * 
	 * @return stat.Histo
	 */
	private Histo getHisto() {
		if (histo == null) {
			histo = new Histo();
		}
		return histo;
	}

	/**
	 * This method initializes discretHisto
	 * 
	 * @return stat.DiscretHisto
	 */
	private DiscretHisto getDiscretHisto() {
		if (discretHisto == null) {
			discretHisto = new DiscretHisto();
		}
		return discretHisto;
	}

	/**
	 * This method initializes jButtonGenerate
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonHisto() {
		if (jButtonGenerate == null) {
			jButtonGenerate = new JButton();
			jButtonGenerate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			jButtonGenerate.setText("   Generate    ");
			jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onGenerate();
				}

			});
		}
		return jButtonGenerate;
	}
	protected void onGenerate() {

		int v = Integer.parseInt(textFieldV.getText());
		IHisto histo;
		if(chooseRandom.getRandom().getClass() == Discret.class) {
			histo = new DiscretHisto();
		}
		else {
			histo = new Histo();
		}
		histo.init();
		for (int i = 0; i < v; i++)
			histo.add(getChooseRandom().next());
		histo.showRelFrec(getDiagram());
		getJTextArea().setText(histo.toString());
		getJTextArea().select(0, 0);
		diagram_1.setHorizontalMinText(diagram.getHorizontalMinText());
		diagram_1.setHorizontalMaxText(diagram.getHorizontalMaxText());
		diagram_1.setVerticalMinText("0");
		diagram_1.setVerticalMaxText("1.2");
		diagram_1.setGridByX(diagram.getGridByX());
		diagram_1.setGridByY(7);
		diagram_1.clear();
		float min = (float) Double.parseDouble(diagram.getHorizontalMinText());	
		float max = (float) Double.parseDouble(diagram.getHorizontalMaxText());	
		Painter p =diagram_1.getPainter();
		p.placeToXY(min, 0);
		p.setColor(Color.red);
		p.setPenSize(2);

		if(chooseRandom.getRandom().getClass() != Discret.class) {	
			double[] x = ((Histo)histo).realBorders();
			int i = 0; 
			for (; i < x.length-1;) {
				
		
				p.drawToXY((float)x[i+1], (float)((Histo)histo).accumFrequency()[i]);
				i++;
			}
		}
		else {
			DiscretHisto hs =(DiscretHisto)histo;
			int n = hs.relfrequencies().length;
			double[] accum = new double[n];
			accum[0]= hs.relfrequencies()[0];
			p.drawToXY((float)hs.values()[0], 0);
			p.drawToXY((float)hs.values()[0], (float)accum[0]);
			int i = 1;
			for (; i < accum.length; i++) {
				accum[i]=accum[i-1]+hs.relfrequencies()[i];
				p.drawToXY((float)hs.values()[i],(float)accum[i-1]);
				p.drawToXY((float)hs.values()[i], (float)accum[i]);

			}
			p.drawToXY(max, (float)accum[i-1]);	
System.out.println(max);
		}

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
			jTextArea.setMargin(new Insets(5, 5, 0, 0));
		}
		return jTextArea;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App application = new App();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public App() {
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
		this.setSize(423, 312);
		this.setContentPane(getJContentPane());
		this.setTitle("ApplicationToLab1");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[] { 100, 20, 25, 0 };
			gbl_jContentPane.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_jContentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
			gbl_jContentPane.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_chooseRandom = new GridBagConstraints();
			gbc_chooseRandom.fill = GridBagConstraints.HORIZONTAL;
			gbc_chooseRandom.insets = new Insets(0, 5, 0, 5);
			gbc_chooseRandom.gridx = 0;
			gbc_chooseRandom.gridy = 2;
			jContentPane.add(getChooseRandom(), gbc_chooseRandom);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.gridheight = 2;
			gbc_jScrollPane.gridwidth = 2;
			gbc_jScrollPane.insets = new Insets(5, 0, 5, 0);
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.gridx = 1;
			gbc_jScrollPane.gridy = 0;
			jContentPane.add(getJScrollPane(), gbc_jScrollPane);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 2;
			jContentPane.add(getTextFieldV(), gbc_textField);
			GridBagConstraints gbc_jButtonHisto = new GridBagConstraints();
			gbc_jButtonHisto.gridwidth = 1;
			gbc_jButtonHisto.insets = new Insets(5, 5, 0, 0);
			gbc_jButtonHisto.gridx = 2;
			gbc_jButtonHisto.gridy = 2;
			jContentPane.add(getJButtonHisto(), gbc_jButtonHisto);
			GridBagConstraints gbc_diagram = new GridBagConstraints();
			gbc_diagram.fill = GridBagConstraints.BOTH;
			gbc_diagram.insets = new Insets(5, 5, 5, 5);
			gbc_diagram.gridx = 0;
			gbc_diagram.gridy = 0;
			jContentPane.add(getDiagram(), gbc_diagram);
			GridBagConstraints gbc_diagram_1 = new GridBagConstraints();
			gbc_diagram_1.insets = new Insets(5, 5, 5, 5);
			gbc_diagram_1.fill = GridBagConstraints.BOTH;
			gbc_diagram_1.gridx = 0;
			gbc_diagram_1.gridy = 1;
			jContentPane.add(getDiagram_1(), gbc_diagram_1);
		}
		return jContentPane;
	}

	public Diagram getDiagram_1() {
		if (diagram_1 == null) {
			diagram_1 = new Diagram();
			diagram_1.setMinimumSize(new Dimension(100, 100));
			diagram_1.setPreferredSize(new Dimension(406, 200));
			GridBagLayout gridBagLayout = (GridBagLayout) diagram_1.getLayout();
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
			gridBagLayout.columnWidths = new int[]{0, 0, 0};
			diagram_1.setTitleText("Інтегральна функця");
		}
		return diagram_1;
	}
	public JTextField getTextFieldV() {
		if (textFieldV == null) {
			textFieldV = new JTextField();
			textFieldV.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u041E\u0431\u0441\u044F\u0433 \u0432\u0438\u0431\u0456\u0440\u043A\u0438", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			textFieldV.setText("1000");
			textFieldV.setName("jTextFieldV");
			textFieldV.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return textFieldV;
	}
} // @jve:decl-index=0:visual-constraint="34,2"
