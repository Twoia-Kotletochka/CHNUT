package toLab5_Theory;

import process.Actor;
import process.QueueForTransactions;
import rnd.Randomable;

// Клас генератора заявок
public class TransactGenerator extends Actor {
	// Черга для тразакцій
	private QueueForTransactions<Double> queue;
	// Генератор часу, що витрачається на створення транзакції
	private Randomable rndGen;
	// Час роботи генератора
	private double finishTime;

	// Конструктор
	public TransactGenerator(String name, TheoryGUI gui, Model model) {
			setNameForProtocol(name);
			finishTime = gui.getChooseDataFinishTime().getDouble();
			rndGen = gui.getChooseRandomGen();
			queue = model.getQueue();	
	}

	// Правила дії
	public void rule() {
		while (getDispatcher().getCurrentTime() <= finishTime) {
			holdForTime(rndGen.next());
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " створює транзакцію.");
			queue.addLast(getDispatcher().getCurrentTime());
		}
	}
}
