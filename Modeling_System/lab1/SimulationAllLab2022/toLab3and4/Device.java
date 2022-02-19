package toLab3and4;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;

// Клас для обслуговуючого пристрою
public class Device extends Actor {

	// Черга для тразакцій
	private QueueForTransactions<Transaction> queue;
	// Генератор часу, що витрачає прилад на обслуговування транзакції
	private Randomable rnd;
	// Час роботи генератора
	private double finishTime;

	// Конструктор, у якому ініціалізуються усі поля класу
	// через доступ до моделі та візуальної частини
	public Device(String name, GUI gui, Model model) {
		setNameForProtocol(name);
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rnd = gui.getChooseRandomDev();
		queue = model.getQueue();
	}

	public void rule() throws DispatcherFinishException {
		// Створюємо умову, виконання якої буде чекати актор
		BooleanSupplier queueSize = () -> queue.size() > 0;
		// цикл виконання правил дії
		while (getDispatcher().getCurrentTime() <= finishTime) {
			// Перевірка наявності транзакції та чекання на її появу
			waitForCondition(queueSize, "у черзі має з'явиться транзакція");
			Transaction transaction = queue.removeFirst();
			// Імітація обробки транзакції
			holdForTime(rnd.next());
			transaction.setServiceDone(true);
		}
	}
}
