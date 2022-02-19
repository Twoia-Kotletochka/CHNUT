package buldo2022;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.Store;
import rnd.Randomable;

/**
 * Клас для абстракції "Бульдозер". Абстракція «бульдозер» моделює робочу
 * машину, що додає до купи порції ґрунту, збільшуючи таким чином її розмір.
 * Одноразова порція ґрунту в реальній системі, звичайно, є випадковою
 * величиною. Але купа ґрунту накопичує ці порції, тобто інтегрує їх, і таким
 * чином зменшує вплив коливань розміру порції. Тому, для спрощення моделі
 * будемо вважати, що порція ґрунту має стале значення і дорівнює одиниці. Тобто
 * одиницею виміру кількості ґрунту у системі буде середнє значення порції
 * ґрунту, що бульдозер додає до купи за один раз. На видобування порції ґрунту
 * бульдозер витрачає деякий час, що є випадковою величиною. Найбільш вірогідно,
 * що ця величина підпорядковується закону Ерланга. Бульдозер припиняє свою
 * роботу, якщо розмір купи збільшується до деякого критичного розміру і
 * відновлює роботу тільки після того, як розмір купи стає удвічі меншим за
 * критичний розмір.
 * 
 *
 */

public class Buldo extends Actor {
	// Посилання на купу грунту, до якої бульдозер додаає грунт
	private Store heap;

	// Критичний розмір купи земли, при якому бульдозер припиняє роботу
	private double heapMaxSize;

	//Тривлістьсть роботи бульдозера
	private double finishTime;

	// Генератор часу, що витрачає бульдозер на одну порцію грунту
	private Randomable rnd;

	// Умова, після виконання якої бульдозер відновлює роботу.
	private BooleanSupplier heapHalfSize;

	// Коструктор, у якому ініціалізуються поля об'єкту 
	public Buldo(String name, BuldGUI gui, BuldModel model) {
		setNameForProtocol(name);
		heap = model.getHeap();
		heapMaxSize = gui.getChooseDataHeapMaxSize().getDouble();
		finishTime = gui.getChooseDataFinishTime().getDouble();
		rnd = gui.getRndBuldo();
		setHistoForActorWaitingTime(model.histoBuldo);
	}

	// Правила дії бульдозера. Бульдозер видобує порції ґрунту, витрачаючи на це
	protected void rule() throws DispatcherFinishException {
		// Ініціалізація умов
		heapHalfSize = () -> heap.getSize() <= heapMaxSize / 2;
		// Цикл правил дії бульдозера
		while (getDispatcher().getCurrentTime() <= finishTime) {
			// Затримка на час формування порції грунту
			holdForTime(rnd.next());
			// Збільшення розміру купи на одну порцію
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " додає порцію грунту.");
			heap.add(1);
			// Зупинка,якщо купа досягла критичного розміру
			if (heap.getSize() >= heapMaxSize) {
					waitForCondition(heapHalfSize,
							"поки купа зменшіться удвічи");
			}
		}
	}

	// Метод для корегування тривалості моделювання
		public void setFinishTime(double finishTime) {
			this.finishTime = finishTime;
		}
}
