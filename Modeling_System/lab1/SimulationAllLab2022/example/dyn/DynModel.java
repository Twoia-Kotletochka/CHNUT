package example.dyn;

import dynamic.DynamicActor;
import process.Dispatcher;

public class DynModel {
	Dispatcher dispatcher;
	DynMain gui;
	
	InertionObject object;
	DelyObject dely;
	WavingObject waving;
	
	public DynModel(Dispatcher d, DynMain dynMain) {
		dispatcher = d;
		gui = dynMain;
		componentsToStartList();
	}
	private void componentsToStartList() {
		dispatcher.addStartingActor(getInerionObject());
		dispatcher.addStartingActor(getDelayObject());
		dispatcher.addStartingActor(getWaving());
	}
	public DelyObject getDelayObject() {
		if(dely == null)
			dely = new DelyObject(gui.getStep(), 0, gui.getFinishTime(),gui, this);
		return dely;
	}
	public DynamicActor getInerionObject() {
		if(object == null) {
			object = new InertionObject(gui.getStep(), 0, gui.getFinishTime(),  gui, this);
		}
		return object;
	}
	public DynamicActor getWaving() {
		if(waving == null) {
			waving = new WavingObject(gui.getStep(), 0, gui.getFinishTime(),  gui, this);
		}
		return waving;
	}
	public void initForTest() {
getWaving().setPainter(gui.getDiagram_1().getPainter()); 
		getInerionObject().setPainter(gui.getDiagram_3().getPainter()); 
		getDelayObject().setPainter(gui.getDiagram_2().getPainter()); 
		
		dispatcher.setProtocolFileName("");


	}

}
