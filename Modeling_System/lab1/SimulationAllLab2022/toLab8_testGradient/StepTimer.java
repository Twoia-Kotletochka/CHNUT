package toLab8_testGradient;

public class StepTimer implements IStepTimer {
	private double currentTime;

	private double finishTime;

	private double step;

	private IController controller;

	private IOperatingObject operatingObject;

	/**
	 * GradientMonitor constructor comment.
	 */
	public StepTimer() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:52:35)
	 * 
	 */
	public IController getController() {
		return controller;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:47:39)
	 * 
	 * @return double
	 */
	public double getCurrentTime() {
		return currentTime;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:50:05)
	 * 
	 * @return double
	 */
	public double getFinishTime() {
		return finishTime;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:52:35)
	 * 
	 * @param newController
	 *            toLab8_testGradient.IOptimizable
	 */
	public IOperatingObject getOperatingObject() {
		return operatingObject;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:51:20)
	 * 
	 * @return double
	 */
	public double getStep() {
		return step;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:50:05)
	 * 
	 * @param newFinishTime
	 *            double
	 */
	public void setFinishTime(double newFinishTime) {
		finishTime = newFinishTime;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:52:35)
	 * 
	 * @param newController
	 *            toLab8_testGradient.IOptimizable
	 */
	public void setIController(IController newController) {
		controller = newController;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:52:35)
	 * 
	 * @param newController
	 *            toLab8_testGradient.IOptimizable
	 */
	public void setIOperatingObject(IOperatingObject param) {
		operatingObject = param;
	}

	/**
	 * Insert the method's description here. Creation date: (08.04.2006
	 * 19:51:20)
	 * 
	 * @param newStep
	 *            double
	 */
	public void setStep(double newStep) {
		step = newStep;
	}

	public void test() {
		// Инициализация подчиненных объектов
		controller.init();
		operatingObject.init();
		// Цикл изменения модельного времени
		currentTime = 0;
		int n = (int) Math.round(finishTime / step + 0.5);
		for (int i = 0; i < n; i++) {
			currentTime = step * (i + 1);
			// Сигнал регулятору об изменении времени
			controller.onChangeTime();
		}
	}
}
