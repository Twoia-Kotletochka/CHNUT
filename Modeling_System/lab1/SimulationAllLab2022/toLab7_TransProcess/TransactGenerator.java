package toLab7_TransProcess;

import process.Actor;
import process.QueueForTransactions;
import rnd.Randomable;

// Клас генератора заявок
public class TransactGenerator extends Actor {
	// Черга для тразакцій
	private QueueForTransactions<Double> queue;
	// Генератор часу, що витрачається на створення транзакції
	private Randomable rndGen;
	public Randomable getRndGen() {
		return rndGen;
	}
	// Час роботи генератора
	private double finishTime;

	// Конструктор
	public TransactGenerator(String name, TransGUI gui, Model model) {
			setNameForProtocol(name);
			finishTime = gui.getChooseDataFinishTime().getDouble();
			rndGen = gui.getChooseRandomGen();
			queue = model.getQueue();

			
	}
	// Метод, у якому ініціалізуються поля класу
	protected void initFields() {
	}

	// Правила дії
	public void rule() {
		initFields();
		while (getDispatcher().getCurrentTime() <= finishTime) {
			holdForTime(rndGen.next());
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " створює транзакцію.");
			queue.addLast(getDispatcher().getCurrentTime());
		}
	}
	public void setFinishTime(double finishTime) {
		this.finishTime =finishTime;
		
	}
}
