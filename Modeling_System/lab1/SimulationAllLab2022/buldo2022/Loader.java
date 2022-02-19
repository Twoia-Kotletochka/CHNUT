package buldo2022;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import process.Store;
import rnd.Randomable;

/**
 * Клас для навантажувача. Абстракція «навантажувач» моделює робочу машину, що
 * навантажує ґрунт у самоскиди, беручи його з купи. Будемо вважати, що розмір
 * порції, яку навантажувач бере з купи за один раз дорівнює одинці. На
 * завантаження порції ґрунту навантажувач витрачає деякий час, що є випадковою
 * величиною. Найбільш вірогідно, що ця величина підпорядковується нормальному
 * закону. Навантажувач може працювати коли є ґрунт у купі і самоскид, що чекає
 * завантаження. Коли ж ці умови не виконуються, навантажувач чекає на їх
 * виконання. Абстракція «навантажувач» у системі безпосередньо пов’язана з
 * абстракцією «купа ґрунту» та абстракцією «черга самоскидів».
 *
 *
 */
public class Loader extends Actor {

	/**
	 * Купа грунту
	 * 
	 */
	private Store heap;

	/**
	 * Черга самосвалів, що чекають завантаження
	 * 
	 */
	private QueueForTransactions<Lorry> queueToLoader;

	/**
	 * Тривалість роботи навантажувача
	 */
	private double finishTime;

	/**
	 * Генератор часу, що витрачається на одну порцію грунту
	 * 
	 */
	private Randomable rnd;

	/**
	 * Умова наявності самоскида у черзі. У загальному випадку створювати у
	 * конструкторі не можна, бо умови можуть використовувати посилання на
	 * об'єкт, який ще не створено. Доцільно ініціалізувати на початку методу
	 * rule. Якщо умов декілька і вони складні то доцільно створити метод
	 * initConditions()
	 */
	private BooleanSupplier isLorry;

	/**
	 * Умова наявності грунту у купі. У загальному випадку створювати у
	 * конструкторі не можна, бо умови можуть використовувати посилання на
	 * об'єкт, який ще не створено. Доцільно ініціалізувати на початку методу
	 * rule. Якщо умов декілька і вони складні то доцільно створити метод
	 * initConditions()
	 */
	private BooleanSupplier heapSize;

	/**
	 * Коструктор, у якому ініціалізується гісограма для накоптчення часу
	 * простою
	 * 
	 */
	public Loader(String name, BuldGUI gui, BuldModel model) {
		setNameForProtocol(name);
		heap = model.getHeap();
		queueToLoader = model.getQueueToLoader();
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rnd = gui.getRndLoader();
		setHistoForActorWaitingTime(model.histoLoader);
	}

	/**
	 * Правила дії навантажувача. Навантажувач працює із купою грунту і чергою
	 * самоскидів. Якщо є самоскид, готовий до завантаження, навантажувач
	 * забирає його з черги на обслуговування. Далі починається цикл
	 * завантаження самоскиду. Якщо у купі є ґрунт , навантажувач перевантажує
	 * порцію ґрунту із купи до кузова самоскиду. Цикл завантаження повторюється
	 * поки самоскид не буде завантажений. На перевантаження порції ґрунту
	 * навантажувач витрачає випадковий час. Якщо у купі закінчився ґрунт або
	 * нема самоскида, що потребує завантаження, навантажувач переходить у стан
	 * чекання. Після повного завантаження самоскиду правила дії повторюються.
	 * Навантажувач працює впродовж усього часу моделювання.
	 * 
	 * @throws DispatcherFinishException
	 */
	protected void rule() throws DispatcherFinishException {
		isLorry = () -> queueToLoader.size() > 0;
		heapSize = () -> heap.getSize() > 0;
		// Цикл виконанння правил дії навантажувача
		while (getDispatcher().getCurrentTime() <= finishTime) {
			// Перевірка, чи є в черзі самоскид,
			// і якщо його нема - чекання
			waitForCondition(isLorry, "має бути самоскид");
			// Забираємо самоскид на обслуговування
			Lorry lorry = queueToLoader.removeFirst();
			// Цикл завантаження самоскиду
			while (!lorry.isFull()) {
				waitForCondition(heapSize, "має бути грунт у купі");
				getDispatcher().printToProtocol(getNameForProtocol() + " бере порцію грунту");
				heap.remove(1);
				// Затримка на час перевантаження
				holdForTime(rnd.next());
				// Додаємо грунт у самосвал
				lorry.addPortion();
				getDispatcher().printToProtocol(
						getNameForProtocol() + " додає порцію грунту у самоскид " + lorry.getNameForProtocol());
			}
		}
	}

	/**
	 * Цей метод потрібен для корегування тривалості моделювання під час
	 * дослідження перехідних процесі
	 * 
	 * @param finishTime
	 *            - час моделювання, що сформує TransProcessManager
	 */
	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;

	}
}
