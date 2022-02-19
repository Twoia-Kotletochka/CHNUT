package toLab8_testGradient;

public interface IOperatingObject {
	double getOutput();

	void init();

	void onSetInput(double u);

	void setIStepTimer(IStepTimer newIMonitor);
}