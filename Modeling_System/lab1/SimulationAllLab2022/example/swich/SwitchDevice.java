package example.swich;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.trans.ITransMonitoring;

public class SwitchDevice extends Actor {


	protected QueueForPackage buferQueue;
	
	protected QueueForTransactions inputQueue;

	protected double swithCapacity = 0;

	private double finishTime;
	
	private boolean busy=false;

	public SwitchDevice() {
		super();
	}

	public boolean continueCondition() {
		return getDispatcher().getCurrentTime() <= finishTime;
	}

	/**
	 * Insert the method's description here. Creation date: (04.02.2006
	 * 18:43:02)
	 * 
	 * @return double
	 */
	public double getFinishTime() {
		return finishTime;
	}

	/**
	 * Insert the method's description here. Creation date: (13.05.2005
	 * 21:21:15)
	 * 
	 * @return qusystem.QueueForTransactions
	 */
	public ITransMonitoring getInputQueue() {
		return inputQueue;
	}



	public void rule() {
		TransactionFile transaction;
		BooleanSupplier isTransaction=()->inputQueue.size() > 0;
		while (getDispatcher().getCurrentTime() <= finishTime) {
			setBusy(false);
			try {
				waitForCondition(isTransaction,"поки у " + inputQueue.getNameForProtocol()
								+ " зявиться файл");
			} catch (DispatcherFinishException e) {
				return;
			}
			if ( getDispatcher().getCurrentTime() > finishTime)
				break;
			setBusy(true);
			transaction = (TransactionFile) inputQueue.removeFirst();
			getBuferQueue().remove(transaction.getBufferedMByte());
			holdForTime(transaction.getFileSizeMByte()*8 / getSwithCapacity() / 60);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (04.02.2006
	 * 18:43:02)
	 * 
	 * @param newFinishTime
	 *            double
	 */
	public void setFinishTime(double newFinishTime) {
		finishTime = newFinishTime;
	}

	/**
	 * Insert the method's description here. Creation date: (13.05.2005
	 * 21:21:15)
	 * 
	 * @param newInputQueue
	 *            qusystem.QueueForTransactions
	 */
	public void setInputQueue(QueueForTransactions newInputQueue) {
		inputQueue = newInputQueue;
	}

	public double getSwithCapacity() {
		return swithCapacity;
	}

	public void setSwithCapacity(double swithCapacity) {
		this.swithCapacity = swithCapacity;
	}

	public boolean isBusy() {
		return busy;
	}

	private void setBusy(boolean busy) {
		this.busy = busy;
	}

	public QueueForPackage getBuferQueue() {
		return buferQueue;
	}

	public void setBuferQueue(QueueForPackage buferQueue) {
		this.buferQueue = buferQueue;
	}
}
