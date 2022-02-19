package example.dyn;

import java.util.function.BiFunction;

import dynamic.DynamicActor;
import dynamic.DynamicActorRK4;


public class InertionObject extends DynamicActorRK4{


		
	private double t;
	private double k;

	private DynamicActor dataSource;

	public InertionObject(double step, double value, double finishTime,
							DynMain gui, DynModel model) {
		super(step, value, finishTime);
		t = gui.getT();
		k = gui.getK();
		dataSource = model.getDelayObject();
	}

	@Override
	protected BiFunction<Double, Double, Double> derivFunc() {
		return (time,value)->(k * dataSource.getValue() - value) / t;
	}



	
}
