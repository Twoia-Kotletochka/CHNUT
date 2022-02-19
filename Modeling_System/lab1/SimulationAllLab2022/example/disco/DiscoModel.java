/**
 * 
 */
package example.disco;

import process.Dispatcher;
import process.MultiActor;


public class DiscoModel  {

	DiscoGUI gui;
	Discoteka discoteka;
	MultiActor allDansers;
	Dispatcher dispatcher;
	
	public DiscoModel(DiscoGUI gui, Dispatcher dispatcher){
		this.gui = gui;
		this.dispatcher=dispatcher;
		discoteka = new Discoteka(gui, this);
		int n=gui.getChooseDataNumberOfClients().getInt();
		Dancer d = createDancer();
		allDansers=new MultiActor("MultiActor for Dancers",d,n);
	}
	
	Dancer createDancer(){
		Dancer d=new Dancer(gui, this, discoteka);
		d.setNameForProtocol("Dancer");
		return d;
	}
	
	public Dispatcher getDispatcher() {
				return dispatcher;
	}
	
	public void startExperiment() {
		dispatcher.addStartingActor(discoteka);		
		dispatcher.addStartingActor(allDansers);
		dispatcher.start();
	}


}
