package example.disco;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import process.Dispatcher;
import widgets.Diagram;

public class DiscoGuiNew extends DiscoGUI {
	private Diagram diagram;
	private Diagram diagram_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiscoGuiNew frame = new DiscoGuiNew();
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
	public DiscoGuiNew() {
		getDiagram().setTitleText("\u0422\u0430\u043D\u0446\u044E\u044E\u0442\u044C");
		getChooseDataDancingTime().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				double t1 = getChooseDataOpenTime().getDouble();
				double t2 = getChooseDataDancingTime().getDouble();
				String s = String.valueOf(t1 + t2 + 1);
				getDiagram().setHorizontalMaxText(s);
				getDiagramWaitFrend().setHorizontalMaxText(s);
				getDiagramWaitOpen().setHorizontalMaxText(s);

			}
		});
		getChooseDataOpenTime().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				double t1 = getChooseDataOpenTime().getDouble();
				double t2 = getChooseDataDancingTime().getDouble();
				String s = String.valueOf(t1 + t2 + 1);
				getDiagram().setHorizontalMaxText(s);
				getDiagramWaitFrend().setHorizontalMaxText(s);
				getDiagramWaitOpen().setHorizontalMaxText(s);
			}
		});
		getDiagram().setVerticalMaxText("200");
		getChooseDataNumberOfClients().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				int n = Integer.parseInt(chooseDataNumberOfClients
						.getText());
				getDiagram().setVerticalMaxText(String.valueOf(2 * n));
				getDiagramWaitFrend().setVerticalMaxText(String.valueOf(2 * n));
				getDiagramWaitOpen().setVerticalMaxText(String.valueOf(2 * n));
			}
		});
		getDiagram().setBounds(270, 266, 292, 125);
		getJButtonStart().setLocation(88, 357);
		
		JPanel panel = new JPanel();
		panel.setBounds(270, 18, 292, 247);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		diagram_1 = new Diagram();
		diagram_1.setTitleText("\u0427\u0435\u043A\u0430\u044E\u0442\u044C \u043D\u0430 \u043F\u0430\u0440\u0442\u043D\u0435\u0440\u0430");
		diagram_1.setVerticalMaxText("200");
		diagram_1.setPainterColor(Color.RED);
		GridBagLayout gbl_diagram_1 = (GridBagLayout) diagram_1.getLayout();
		gbl_diagram_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_diagram_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_diagram_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_diagram_1.columnWidths = new int[]{0, 0, 0};
		panel.add(diagram_1);
		
		diagram = new Diagram();
		diagram.setTitleText("\u0427\u0435\u043A\u0430\u044E\u0442\u044C \u0432\u0456\u0434\u043A\u0440\u0438\u0442\u0442\u044F");
		diagram.setVerticalMaxText("200");
		diagram.setPainterColor(Color.RED);
		panel.add(diagram);
		setBounds(100, 100, 588, 432);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public Diagram getDiagramWaitOpen() {
		return diagram;
	}
	public Diagram getDiagramWaitFrend() {
		return diagram_1;
	}
	
	
	protected DiscoModel createModel(Dispatcher d) {
		return new DiscoModelNew(this,d);
	}
	protected void resetPainters(){
		getDiagram().getPainter().placeToXY(0, 0);
		getDiagramWaitFrend().getPainter().placeToXY(0, 0);
		getDiagramWaitOpen().getPainter().placeToXY(0, 0);	
	}

	

}
