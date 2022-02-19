package toLab3and4;

import process.Actor;
import rnd.Randomable;

// Клас генератора заявок
public class Generator extends Actor {
	//Посилання на модель системи
	private Model model;
	// Генератор випадкового часу створення транзакції
	private Randomable rnd;
	// Тривалість роботи генератора
	private double finishTime;


	// Конструктор
	public Generator(String name, GUI gui, Model model) {
			setNameForProtocol(name);
			this.model = model;
			finishTime = gui.getChooseDataFinishTime().getDouble();
			rnd = gui.getChooseRandomGen();
	}

	// Правила дії
	public void rule() {
		while (getDispatcher().getCurrentTime() <= finishTime) {
			holdForTime(rnd.next());
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " створює транзакцію.");
			Transaction transaction = new Transaction(model);
			dispatcher.addStartingActor(transaction);
		}
	}
}
