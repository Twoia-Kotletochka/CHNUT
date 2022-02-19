package toLab8_testGradient;

public interface IController {
	double[] getParameters();

	double getResult();

	void init();

	void onChangeTime();

	void setIOperatingObject(IOperatingObject param);

	void setIStepTimer(IStepTimer newIMonitor);

	void setParameters(double[] parmArray);
}