package example.dyn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import process.Dispatcher;
import process.IModelFactory;
import widgets.ChooseData;
import widgets.Diagram;

public class DynMain {

	private JFrame frame;
	private Diagram diagram_1;
	private Diagram diagram_2;
	private ChooseData chooseDataK;
	private ChooseData chooseDataT;
	private ChooseData chooseDataTau;
	private ChooseData chooseDataFinishTime;
	private JButton btnStart;
	private JCheckBox chckbxNewCheckBox;
	private ChooseData chooseDataStep;
	private ChooseData chooseDataDisturb;
	private Diagram diagram_3;
	private ChooseData chooseDataKw;
	private ChooseData chooseDataTw;
	private ChooseData chooseDataKsi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DynMain window = new DynMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DynMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 485);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		diagram_1 = new Diagram();
		diagram_1.setTitleText("WavingObject");
		diagram_1.setVerticalMaxText("10");
		diagram_1.setPainterColor(Color.MAGENTA);
		GridBagConstraints gbc_diagram_1 = new GridBagConstraints();
		gbc_diagram_1.gridwidth = 2;
		gbc_diagram_1.insets = new Insets(0, 0, 5, 0);
		gbc_diagram_1.fill = GridBagConstraints.BOTH;
		gbc_diagram_1.gridx = 0;
		gbc_diagram_1.gridy = 0;
		panel.add(diagram_1, gbc_diagram_1);
		
		diagram_2 = new Diagram();
		diagram_2.setTitleText("DelyObject");
		diagram_2.setVerticalMaxText("10");
		diagram_2.setPainterColor(Color.BLUE);
		GridBagConstraints gbc_diagram_2 = new GridBagConstraints();
		gbc_diagram_2.gridwidth = 2;
		gbc_diagram_2.insets = new Insets(0, 0, 5, 0);
		gbc_diagram_2.fill = GridBagConstraints.BOTH;
		gbc_diagram_2.gridx = 0;
		gbc_diagram_2.gridy = 1;
		panel.add(diagram_2, gbc_diagram_2);
		
		diagram_3 = new Diagram();
		diagram_3.setTitleText("InertionObject");
		diagram_3.setPainterColor(Color.RED);
		GridBagConstraints gbc_diagram_3 = new GridBagConstraints();
		gbc_diagram_3.gridwidth = 2;
		gbc_diagram_3.insets = new Insets(0, 0, 5, 5);
		gbc_diagram_3.fill = GridBagConstraints.BOTH;
		gbc_diagram_3.gridx = 0;
		gbc_diagram_3.gridy = 2;
		panel.add(diagram_3, gbc_diagram_3);
		
		chckbxNewCheckBox = new JCheckBox("New check box");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 3;
		panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTest();
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 3;
		panel.add(btnStart, gbc_btnStart);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(9, 0, 0, 0));
		
		chooseDataDisturb = new ChooseData();
		chooseDataDisturb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_chooseDataDisturb_actionPerformed(arg0);
			}
		});
		chooseDataDisturb.setText("5");
		chooseDataDisturb.setTitle("Disturb");
		panel_1.add(chooseDataDisturb);
		
		chooseDataKw = new ChooseData();
		chooseDataKw.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					onKw_caretUpdate();
				} catch (Exception e) {
	
				}
			}
		});
		chooseDataKw.setTitle("Kw");
		chooseDataKw.setText("1");
		panel_1.add(chooseDataKw);
		
		chooseDataTw = new ChooseData();
		chooseDataTw.setTitle("Tw");
		chooseDataTw.setText("0.2");
		panel_1.add(chooseDataTw);
		
		chooseDataKsi = new ChooseData();
		chooseDataKsi.setTitle("ksi");
		chooseDataKsi.setText("0.1");
		panel_1.add(chooseDataKsi);
		
		chooseDataTau = new ChooseData();
		chooseDataTau.setText("0.5");
		chooseDataTau.setTitle("tau");
		panel_1.add(chooseDataTau);
		
		chooseDataK = new ChooseData();
		chooseDataK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_chooseDataK_actionPerformed(e);
			}
		});
		chooseDataK.setText("1");
		chooseDataK.setTitle("k");
		panel_1.add(chooseDataK);
		
		chooseDataT = new ChooseData();
		chooseDataT.setText("1");
		chooseDataT.setTitle("T");
		panel_1.add(chooseDataT);
		
		chooseDataStep = new ChooseData();
		chooseDataStep.setText("0.01");
		chooseDataStep.setTitle("Step");
		panel_1.add(chooseDataStep);
		
		chooseDataFinishTime = new ChooseData();
		chooseDataFinishTime.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				onChangeFinishTime(arg0);
			}
		});
		chooseDataFinishTime.setText("5");
		chooseDataFinishTime.setTitle("finishTime");
		panel_1.add(chooseDataFinishTime);
		splitPane.setDividerLocation(100);
	}
	private void startTest() {
		//������ ������� ��� ��������� �������
		diagram_1.clear();
		diagram_2.clear();
		diagram_3.clear();
		
		//��������� ����������
		Dispatcher dispatcher = new Dispatcher();	

		//��������� ������ �� ��������� �������
		IModelFactory factory = (d)-> new DynModel(d, this);
		DynModel model =(DynModel) factory.createModel(dispatcher);
		
		// ������ ������ ������ ���������� �� ����� ������ �����
		btnStart.setEnabled(false);
		dispatcher.addDispatcherFinishListener(
				()->btnStart.setEnabled(true));
		
		//������ ������ �� ������ � ����� ����������
		model.initForTest();
	
		//��������� ������
		dispatcher.start();
	}


	protected void onChangeFinishTime(CaretEvent arg0) {
		String text = chooseDataFinishTime.getText();
		diagram_1.setHorizontalMaxText(text);
		diagram_2.setHorizontalMaxText(text);
		diagram_3.setHorizontalMaxText(text);
	}

	public double getT() {
		return chooseDataT.getDouble();

	}

	public double getTau() {
		return chooseDataTau.getDouble();
	}

	public double getK() {
		return chooseDataK.getDouble();
	}

	public double getDisturb() {
		
		return chooseDataDisturb.getDouble();
	}

	public double getFinishTime() {
		return chooseDataFinishTime.getDouble();
	}

	public double getStep() {
		
		return chooseDataStep.getDouble();
	}

	public Diagram getDiagram_1() {
		return diagram_1;
	}
	public Diagram getDiagram_2() {
		return diagram_2;
	}

	public Diagram getDiagram_3() {
		return diagram_3;
	}
	

	
	protected void onKw_caretUpdate() {
		int v = (int) (2 * chooseDataKw.getDouble() * chooseDataDisturb.getDouble());
		diagram_1.setVerticalMaxText(String.valueOf(v));
		diagram_2.setVerticalMaxText(String.valueOf(v));
		v = (int) (v * chooseDataK.getDouble()/2);
		diagram_3.setVerticalMaxText(String.valueOf(v));
	}
	protected void do_chooseDataDisturb_actionPerformed(ActionEvent arg0) {
		onKw_caretUpdate();
	}
	protected void do_chooseDataK_actionPerformed(ActionEvent e) {
		onKw_caretUpdate();
	}

	public double getKw() {
		
		return chooseDataKw.getDouble();
	}

	public double getKsi() {
		
		return chooseDataKsi.getDouble();
	}

	public double getTw() {
		
		return chooseDataTw.getDouble();
	}
	
	public ChooseData getChooseDataK() {
		return chooseDataK;
	}
	public ChooseData getChooseDataKw() {
		return chooseDataKw;
	}
	public ChooseData getChooseDataDisturb() {
		return chooseDataDisturb;
	}
	public ChooseData getChooseDataTw() {
		return chooseDataTw;
	}
	public ChooseData getChooseDataKsi() {
		return chooseDataKsi;
	}
	public ChooseData getChooseDataTau() {
		return chooseDataTau;
	}
}
