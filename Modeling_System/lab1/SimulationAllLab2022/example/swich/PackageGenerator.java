package example.swich;

import process.Actor;
import process.QueueForTransactions;
import rnd.Randomable;

public class PackageGenerator extends Actor  {
	protected SwitchDevice device;
	protected QueueForPackage store;
	protected QueueForTransactions queueForFile;
	protected Randomable fileSizeRandom;
	protected double finishTime = 0;
	protected double chanalCapacity = 0;
	protected int packageSize =0;
private Randomable random=null;
	private Randomable getRandom() {
	return random;
}

public void setRandom(Randomable random) {
	this.random = random;
}

	public PackageGenerator() {
		super();
	}

	/**
	 * Определяет условие продолжения работы генератора заявок. Creation date:
	 * (14.05.2005 11:46:03)
	 * 
	 * @return boolean
	 */
	protected boolean continueCondition() {
		return getDispatcher().getCurrentTime() <= finishTime;
	}

	protected void createTransaction() {
		TransactionFile file =new TransactionFile();
		//fileSize in MByte
		double fileSize=getFileSizeRandom().next();
		double waitTime=0;
		if (getDevice().isBusy()) {
			waitTime=getDevice().getActivateTime()-getDispatcher().getCurrentTime();
		}
		double waitTimeInMBytes=waitTime*60*getChanalCapacity()/8;
		if (waitTimeInMBytes>fileSize) {
			waitTimeInMBytes=fileSize;
		}
		file.setBufferedMByte(waitTimeInMBytes);
		file.setFileSizeMByte(fileSize);
		getStore().add(waitTimeInMBytes);
		getQueueForFile().add(file);
	}

	/**
	 * Insert the method's description here. Creation date: (14.05.2005
	 * 11:43:20)
	 * 
	 * @return double
	 */
	public double getFinishTime() {
		return finishTime;
	}


	public void rule() {
		while (true) {
			holdForTime(getRandom().next());
			if (!continueCondition()) {
				break;
			}
			;
			getDispatcher().printToProtocol(
					getNameForProtocol() + " створює файл.");
			createTransaction();		
		}
	}

	/**
	 * Insert the method's description here. Creation date: (14.05.2005
	 * 11:43:20)
	 * 
	 * @param newFinishTime
	 *            double
	 */
	public void setFinishTime(double newFinishTime) {
		finishTime = newFinishTime;
	}


	public QueueForPackage getStore() {
		return store;
	}

	public Randomable getFileSizeRandom() {
		return fileSizeRandom;
	}

	public void setFileSizeRandom(Randomable fileSizeRandom) {
		this.fileSizeRandom = fileSizeRandom;
	}

	public void setStore(QueueForPackage store) {
		this.store = store;
	}

	public int getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(int packageSize) {
		this.packageSize = packageSize;
	}

	public double getChanalCapacity() {
		return chanalCapacity;
	}

	public void setChanalCapacity(double chanalCapacity) {
		this.chanalCapacity = chanalCapacity;
	}

	public QueueForTransactions getQueueForFile() {
		return queueForFile;
	}

	public void setQueueForFile(QueueForTransactions queueForFile) {
		this.queueForFile = queueForFile;
	}

	public SwitchDevice getDevice() {
		return device;
	}

	public void setDevice(SwitchDevice device) {
		this.device = device;
	}
}
