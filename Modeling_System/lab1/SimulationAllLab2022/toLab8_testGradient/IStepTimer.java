package toLab8_testGradient;

public interface IStepTimer {
	double getCurrentTime();

	double getStep();

	void setIController(IController newIController);

	void setIOperatingObject(IOperatingObject param);

	void test();
}