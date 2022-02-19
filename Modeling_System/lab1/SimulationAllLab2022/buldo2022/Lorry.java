package buldo2022;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;

/**
 * Клас для абстракції Самоскид. Завданням абстракції "самоскид" є перевезення
 * ґрунту від навантажувача до замовника. Шлях до замовника та від замовника до
 * навантажувача потребує випадкових проміжків часу. Під’їхавши до
 * навантажувача,самоскидів стає у чергу і чекає, поки його завантажать. Після
 * цього знову їде до замовника. У замовника самоскид розвантажується і знову
 * їде до навантажувача. Працює самоскид впродовж усього часу моделювання.
 * Особливість поведінки самоскида полягає у тому, що окрім основної діяльності
 * він має реєструватися у списку, який містиь самоскиди, що їдуть до
 * навантажувача та від нього. Це пов’язано з вимогою завдання забезпечити
 * динамічну індикацію самоскидів, які знаходяться у дорозі.
 * 
 *
 */
public class Lorry extends Actor {

	/**
	 * Тривалість роботи самосвалу
	 */
	private double finishTime;

	/**
	 * Черга самосвалів у дорозі
	 */
	private QueueForTransactions<Lorry> queueLorryOnRoad;

	/**
	 * Черга самосвалів до нантажувача
	 * 
	 */
	private QueueForTransactions<Lorry> queueToLoader;

	/**
	 * Генератор часу, що витрачається на дорогу в один кінець
	 * 
	 */
	private Randomable rnd;

	/**
	 * Місткіть кузову
	 * 
	 */
	private int bodySize;

	/**
	 * Рівень завантаження кузову
	 * 
	 */
	private int bodyLoad;

	/**
	 * Умова, після виконання якої самоскид може відїжджати від навантажувача.
	 * У загальному випадку ініціалізувати у конструкторі не можна,
	 * бо умови можуть використовувати посилання на об'єкт,
	 * який ще не створено. 
	 * Доцільно ініціалізувати на початку методу rule.
	 * Якщо умов декілька і вони складні
	 * то доцільно створити метод initConditions()
	 */

	private BooleanSupplier isBodyFull;

	/**
	 * Коструктор, у якому ініціалізується гістограма для накопичення часу
	 * простою
	 * 
	 */
	public Lorry(String name, BuldGUI gui, BuldModel model) {
		setNameForProtocol(name);
		this.queueToLoader = model.getQueueToLoader();
		this.queueLorryOnRoad = model.getQueueLorryOnRoad();
		this.finishTime = gui.getChooseDataFinishTime().getDouble();
		this.bodySize = gui.getChooseDataBodySize().getInt();
		this.rnd = gui.getRndLorry();
		this.setHistoForActorWaitingTime(model.histoLorry);
	}
	
	/**
	 * Правила дії абстракції "Самоскид". Самоскид їде до купи грунту, стає у
	 * чергу до навантажувача і чекає, поки його завантажать. Після цього їде до
	 * замовника. У замовника самоскид розвантажується і знову їде до
	 * навантажувача. Працює самоскид впродовж усього часу моделювання.
	 * @throws DispatcherFinishException 
	 */
	public void rule() throws DispatcherFinishException {
		isBodyFull = () -> isFull();
		// Цикл правил дії самосвалу
		while (getDispatcher().getCurrentTime() <= finishTime) {
			// Самосвал їде до навантажувача
			// i реєструється у списку самоскидів, що їдуть
			queueLorryOnRoad.addLast(this);
			holdForTime(rnd.next());
			// вилучає себе із відповідного списку
			queueLorryOnRoad.remove(this);
			// Самосвал стає у чергу до навантажувача,
			queueToLoader.addLast(this);
			// Чекає поки завантажать
				waitForCondition(isBodyFull, "кузов має бути повним");
			// Самосвал їде на розвантаження
			getDispatcher().printToProtocol(
					getNameForProtocol() + " поїхав розвантажуватися");
			// реєструється у списку самоскидів, що їдуть
			queueLorryOnRoad.add(this);
			holdForTime(rnd.next());
			// вилучає себе із списку самоскидів, що їдуть
			queueLorryOnRoad.remove(this);
			getDispatcher().printToProtocol(
					getNameForProtocol() + " розвантажується");
			// Самоскид розвантажується
			bodyLoad = 0;
		}
	}

	// /////////////////////////////////////
	// Методи, які використовує навантажувач
	// /////////////////////////////////////

	/**
	 * Метод, що додає порцію грунту у кузов самоскиду
	 * 
	 */
	public void addPortion() {
		bodyLoad++;
		getDispatcher().printToProtocol(
				getNameForProtocol() + "- у кузові стало " + bodyLoad);
	}

	/**
	 * Метод перевірки завантаженості самоскиду
	 * 
	 */
	public boolean isFull() {
		return bodyLoad >= bodySize;
	}


	/**
	 * Цей метод потрібен для корегування тривалості моделювання
	 * під час дослідження перехідних процесі
	 * @param finishTime - час моделювання, що сформує TransProcessManager
	 */
		public void setFinishTime(double finishTime) {
			this.finishTime = finishTime;

		}
}
