/**
 * 
 */
package example.market;

import java.util.ArrayList;

import process.Dispatcher;
import widgets.Painter;

public class MarketModel {

	private ArrayList<Client> allClientList;
	private Dispatcher dispatcher;
	private MarketGUI gui;
	private int numberOfClient;
	private int numberOfReady;
	private Painter painter;

	public MarketModel(Dispatcher d, MarketGUI g) {
		if (d == null || g == null) {
			System.out
					.println("Диспетчер або  gui для MarketModel не визначено.");
			System.out.println("Модель працювати не може.");
			System.exit(0);
		}
		gui = g;
		dispatcher = d;
		painter = gui.getDiagram().getPainter();
		numberOfClient = gui.getChooseDataNumberOfClients().getInt();
		componentsToStartList();

	}

	private void componentsToStartList() {
		for (Client client : getAllClientList())
			dispatcher.addStartingActor(client);
	}

	public ArrayList<Client> getAllClientList() {
		if (allClientList == null) {
			allClientList = new ArrayList<Client>(numberOfClient);
			for (int i = 0; i < numberOfClient; i++)
				allClientList.add(i, new Client("Client " + String.valueOf(i),
						gui, this));
		}
		return allClientList;
	}

	public void incNumberOfReady() {
		numberOfReady++;
		if (painter != null) {
			float x = (float) dispatcher.getCurrentTime();
			float y = (float) numberOfReady;
			painter.drawToXY(x, y);
		}
	}

	public int getNumberOfReady() {
		return numberOfReady;
	}

	public void initForStart() {
		dispatcher.setProtocolFileName("");
	}

}
