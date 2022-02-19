package toLab5_Theory;

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
	// Гістограма для часу перебування транзакції у черзі
	private Histo histoTransactionWaitInQueue;

	// Конструктор, у якому ініціалізуються усі поля класу
	// через доступ до моделі та візуальної частини
	public Device(String name, TheoryGUI gui, Model model) {
		setNameForProtocol(name);
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rndDev = gui.getChooseRandomDev();
		queue = model.getQueue();
		histoTransactionWaitInQueue = model.getHistoTransactionWaitInQueue();
		setHistoForActorWaitingTime(model.getHistoWaitDevice());
	}

	public void rule() throws DispatcherFinishException {
		// Створюємо умову, виконання якої буде чекати актор
		BooleanSupplier queueSize = () -> queue.size() > 0;
		// цикл виконання правил дії
		while (getDispatcher().getCurrentTime() <= finishTime) {
				waitForCondition(queue.notEmpty(), "у черзі має з'явиться транзакція");
			double waitTime = getDispatcher().getCurrentTime()
					- (double) queue.removeFirst();
			histoTransactionWaitInQueue.add(waitTime);
			holdForTime(rndDev.next());
		}
	}
}
