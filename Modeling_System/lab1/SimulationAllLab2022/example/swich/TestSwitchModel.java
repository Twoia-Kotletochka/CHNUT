package example.swich;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import stat.Histo;

public class TestSwitchModel {
	private ITestSwitchUI ui = null;

	private Dispatcher dispatcher = null; // @jve:decl-index=0:visual-constraint="86,9"

	private MultiActor multiPackageGenerator = null; // @jve:decl-index=0:visual-constraint="35,65"

	private PackageGenerator packageGenerator = null; // @jve:decl-index=0:visual-constraint="45,115"

	private SwitchDevice switchDevice = null; // @jve:decl-index=0:visual-constraint="343,76"

	private Histo histo = null; // @jve:decl-index=0:visual-constraint="228,24"

	private QueueForPackage store = null; // @jve:decl-index=0:visual-constraint="225,73"

	private QueueForTransactions queueForFile = null;  //  @jve:decl-index=0:visual-constraint="199,118"

	/**
	 * This method initializes dispatcher
	 * 
	 * @return process.Dispatcher
	 */
	public Dispatcher getDispatcher() {
		if (dispatcher == null) {
			dispatcher = new Dispatcher();
			dispatcher.setProtocolFileName("");
		}
		return dispatcher;
	}

	/**
	 * This method initializes multiFinish
	 * 
	 * @return process.MultiActor
	 */
	private MultiActor getMultiFinish() {
		if (multiPackageGenerator == null) {
			multiPackageGenerator = new MultiActor();
			multiPackageGenerator.setNameForProtocol("MultiGen");
			multiPackageGenerator.setOriginal(getPackageGenerator());
		}
		return multiPackageGenerator;
	}

	public void setUi(ITestSwitchUI ui) {
		this.ui = ui;
	}

	public void start() {
		double packageDelayCoeff=(ui.getPackageSize()+12)/ui.getPackageSize();
		double realChanalCapacity =ui.getSwitchCapacity()/packageDelayCoeff;
		// Настройка и инициализайия компонентов модели
		getQueueForFile().init();
		
		getStore().init();
		getStore().setPainter(ui.getPainter1());

		getPackageGenerator().setFinishTime(ui.getFinishTime());
		getPackageGenerator().setRandom(ui.getRandomFileStream());
		getPackageGenerator().setFileSizeRandom(ui.getRandomFileSize());
		getPackageGenerator().setPackageSize(ui.getPackageSize());
		getPackageGenerator().setChanalCapacity(realChanalCapacity);
		getMultiFinish().setNumberOfClones(ui.getNumberOfPort());

		getSwitchDevice().setFinishTime(ui.getFinishTime());
		getSwitchDevice().setSwithCapacity(realChanalCapacity);

		getDispatcher().addStartingActor(getMultiFinish());
		getDispatcher().addStartingActor(getSwitchDevice());
		getDispatcher().start();
	}

	/**
	 * This method initializes packageGenerator
	 * 
	 * @return testSwitch.PackageGenerator
	 */
	private PackageGenerator getPackageGenerator() {
		if (packageGenerator == null) {
			packageGenerator = new PackageGenerator();
			packageGenerator.setStore(getStore());
			packageGenerator.setQueueForFile(getQueueForFile());
			packageGenerator.setDevice(getSwitchDevice());
		}
		return packageGenerator;
	}

	/**
	 * This method initializes switchDevice
	 * 
	 * @return testSwitch.SwitchDevice
	 */
	private SwitchDevice getSwitchDevice() {
		if (switchDevice == null) {
			switchDevice = new SwitchDevice();
			switchDevice.setInputQueue(getQueueForFile());
			switchDevice.setBuferQueue(getStore());
		}
		return switchDevice;
	}

	/**
	 * This method initializes histo
	 * 
	 * @return stat.Histo
	 */
	public Histo getHisto() {
		if (histo == null) {
			histo = new Histo();
		}
		return histo;
	}

	/**
	 * This method initializes store
	 * 
	 * @return testSwitch.QueueForPackage
	 */
	private QueueForPackage getStore() {
		if (store == null) {
			store = new QueueForPackage();
			store.setDispatcher(getDispatcher());
			store.setHisto(getHisto());
		}
		return store;
	}

	/**
	 * This method initializes queueForFile	
	 * 	
	 * @return qusystem.QueueForTransactions	
	 */
	private QueueForTransactions getQueueForFile() {
		if (queueForFile == null) {
			queueForFile = new QueueForTransactions();
			queueForFile.setDispatcher(getDispatcher());

		}
		return queueForFile;
	}

}
