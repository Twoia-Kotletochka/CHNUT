package example.disco;

import process.Actor;
import process.Store;


public class Discoteka extends Actor {
	private boolean open;
	private DiscoGUI gui;
	private Store queue;
	double openTime;
	double dancingTime;

	public Discoteka(DiscoGUI gui, DiscoModel model) {
		this.gui = gui;
		openTime =gui.getChooseDataOpenTime().getDouble();
		dancingTime =gui.getChooseDataDancingTime().getDouble();
		queue = new Store();
		queue.setPainter(gui.getDiagram().getPainter());
		queue.setDispatcher(model.getDispatcher());
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void dancerOut() {
		queue.remove(1);
	}

	public void dancerIn() {
		queue.add(1);
	}

	@Override
	protected void rule() {
		holdForTime(openTime);
		open=true;
		holdForTime(dancingTime);
		open=false;
	}

}
