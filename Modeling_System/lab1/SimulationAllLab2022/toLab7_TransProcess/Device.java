package toLab7_TransProcess;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;
import stat.Histo;

// Клас для обслуговуючого пристрою
public class Device extends Actor {

	// Черга для тразакцій
	private QueueForTransactions<Double> queue;
	// Час роботи генератора
	private double finishTime;
	// Генератор часу, що витрачає прилад на обслуговування транзакції
	private Randomable rndDev;
	public Randomable getRndDev() {
		return rndDev;
	}

	// Гістограма для часу перебування транзакції у черзі
	private Histo histoTransactionWaitInQueue;
	// Умова, виконання якої буде чекати Device
	private BooleanSupplier queueSize;

	// Конструктор,  у якому ініціалізуються усі поля класу
	// через доступ до моделі та візуальної частини
	public Device(String name, TransGUI gui, Model model) {
		setNameForProtocol(name);
		setHistoForActorWaitingTime(model.getHistoWaitDevice());
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rndDev = gui.getChooseRandomDev();
		histoTransactionWaitInQueue = model.getHistoTransactionWaitInQueue();
		queue = model.getQueue();
	}

	 // Метод потрібен для реалізації інтерфейсу IExperementable
	 public void setRnd(Randomable rnd) {
	 rndDev = rnd;
	 }

	// Метод,
	private void initConditions() {
		// Створюємо умову, виконання якої буде чекати актор
		queueSize = () -> queue.size() > 0;
	}

	public void rule() {
		initConditions();
		// цикл виконання правил дії
		while (getDispatcher().getCurrentTime() <= finishTime) {
			try {
				waitForCondition(queueSize,
						"поки у " + queue.getNameForProtocol()
								+ " з'явиться транзакція");
			} catch (DispatcherFinishException e) {
				return;
			}
			double waitTime = getDispatcher().getCurrentTime()
					- (double) queue.removeFirst();
			histoTransactionWaitInQueue.add(waitTime);
			holdForTime(rndDev.next());
		}
	}

	public void setFinishTime(double finishTime2) {
		this.finishTime =finishTime;
		
	}

}
