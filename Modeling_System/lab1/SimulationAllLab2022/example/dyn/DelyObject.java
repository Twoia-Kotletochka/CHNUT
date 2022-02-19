package example.dyn;

import java.util.Arrays;

import dynamic.DynamicActor;

public class DelyObject extends DynamicActor {

	private double[] delyArray;
	private double tau;
	private DynamicActor dataSource;
	

	public DelyObject(double step, double value, double finishTime,
			DynMain gui, DynModel model) {
		super(step, value, finishTime);
		this.tau = gui.getTau();
		dataSource = model.getWaving();
		if(tau>=step) {
			delyArray = new double[(int) (tau/step)];
			Arrays.fill(delyArray, value);
		}
	}

	@Override
	protected double calculateNewValue() {
		if(tau>=step) {
		value = delyArray[delyArray.length - 1];
		System.arraycopy(delyArray, 0, delyArray, 1, delyArray.length - 1);
		delyArray[0] = dataSource.getValue();
		}
		else
			value = dataSource.getValue();
		return value;
	}

}
