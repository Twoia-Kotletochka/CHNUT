package toLab3and4;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import stat.Histo;

public class Transaction extends Actor {
	private double createTime;
	private QueueForTransactions<Transaction> queue;
	private Histo histoQueue;
	private Histo histoService;
	private boolean serviceDone;

	public Transaction(Model model) {
		this.queue = model.getQueue();
		this.histoQueue = model.getHistoTransactionWaitInQueue();
		this.histoService = model.getHistoTransactionServiceTime();
	}

	public double getCreateTime() {
		return createTime;
	}

	public void setServiceDone(boolean b) {
		this.serviceDone = b;
	}

	@Override
	public String toString() {
		return "Transaction " + createTime;
	}

	@Override
	protected void rule() throws DispatcherFinishException {
		createTime = dispatcher.getCurrentTime();
		nameForProtocol = "Транзакція " + createTime;
		queue.add(this);
		waitForCondition(() -> !queue.contains(this), "мають забрати на обслуговування");
		histoQueue.add(dispatcher.getCurrentTime() - createTime);
		waitForCondition(() -> serviceDone, "мають завершити обслуговування");
		histoService.add(dispatcher.getCurrentTime() - createTime);
	}

}
