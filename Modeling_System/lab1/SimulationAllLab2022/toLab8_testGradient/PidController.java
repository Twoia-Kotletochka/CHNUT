package toLab8_testGradient;

public class PidController implements IController {
	private double k = 0;

	private double i = 0;

	private double d = 0;

	private double target = 0;

	private double output = 0;

	private double dError = 0;

	private IStepTimer stepTimer;

	private IOperatingObject operatingObject;

	private widgets.Painter painter;

	private double lastI = 0;

	private double lastE = 0;

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 19:55:41)
	 * 
	 * @return double
	 */
	public double getOutput() {
		return output;
	}

	public double[] getParameters() {
		double[] pid = new double[3];
		pid[0] = k;
		pid[1] = i;
		pid[2] = d;
		return pid;
	}

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 19:56:06)
	 * 
	 * @return double
	 */
	public double getResult() {
		return dError;
	}

	/**
	 * Insert the method's description here. Creation date: (13.04.2006
	 * 20:02:17)
	 */
	public void init() {
		dError = 0;
		lastE = 0;
		lastI = getOutput();
		if (painter != null) {
			painter.getDiagram().clear();
			painter.placeToXY(0, (float) output);
		}
	}

	public void onChangeTime() {
		// шаг интегрирования
		double step = stepTimer.getStep();
		// сигнал рассогласования
		double e = target - operatingObject.getOutput();
		// динамическая ошибка
		if (e * lastE < 0) {
			dError += (e * e + lastE * lastE) * step / Math.abs(e - lastE) / 2;
		} else {
			dError += Math.abs((e + lastE) / 2 * step);
		}
		// пропорциональное воздействие
		double uk = k * e;
		// интегральное воздействие по методу трапеций
		double ui = lastI + i / k * (e + lastE) / 2 * step;
		// дифференциальное воздействие
		double ud = d / k * (e - lastE) / step;
		output = uk + ui + ud;
		lastE = e;
		lastI = ui;
		if (painter != null) {
			painter
					.drawToXY((float) stepTimer.getCurrentTime(),
							(float) output);
		}
		// передача воздействия объекту управления
		operatingObject.onSetInput(output);
	}

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 20:50:57)
	 * 
	 * @param newControlObject
	 *            toLab8_testGradient.IControlObject
	 */
	public void setIOperatingObject(IOperatingObject newOperatingObject) {
		operatingObject = newOperatingObject;
	}

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 20:20:08)
	 * 
	 * @param newMonitor
	 *            toLab8_testGradient.IMonitor
	 */
	public void setIStepTimer(IStepTimer newStepTimer) {
		stepTimer = newStepTimer;
	}

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 19:55:41)
	 * 
	 * @param newOutput
	 *            double
	 */
	public void setOutput(double newOutput) {
		output = newOutput;
	}

	/**
	 * Insert the method's description here. Creation date: (13.04.2006
	 * 20:50:55)
	 * 
	 * @param newPainte4r
	 *            paint.Painter
	 */
	public void setPainter(widgets.Painter newPainter) {
		painter = newPainter;
	}

	public void setParameters(double[] pid) {
		k = pid[0];
		i = pid[1];
		d = pid[2];
	}

	/**
	 * Insert the method's description here. Creation date: (09.04.2006
	 * 19:54:29)
	 * 
	 * @param newTarget
	 *            double
	 */
	public void setTarget(double newTarget) {
		target = newTarget;
	}
}
