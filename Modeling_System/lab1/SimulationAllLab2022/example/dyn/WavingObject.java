package example.dyn;

import dynamic.DynamicActor;

public class WavingObject extends DynamicActor {
	private double kw;
	private double tw;
	private double ksi;
	private double z = 0;
	private double disturb;
	
	public WavingObject(double step, double value, double finishTime, DynMain gui, DynModel model) {
		super(step, value, finishTime);
		kw = gui.getKw();
		tw = gui.getTw();
		ksi = gui.getKsi();
		disturb = gui.getDisturb();
	}

	@Override
	protected double calculateNewValue() {
		//z = rk4((time,v)->disturb, step, z);
		z = rk4((time,v)->(kw*disturb - value - 2*ksi*tw*v)/tw/tw, step, z);
		value = rk4((time,v)->z, step, value);

		return value;
	}

}
