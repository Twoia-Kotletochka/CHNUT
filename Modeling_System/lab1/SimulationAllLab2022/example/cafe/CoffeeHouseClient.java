package example.cafe;

import process.QueueForTransactions;
import rnd.Randomable;

public class CoffeeHouseClient extends process.Actor {
	/**
	 * Ссылка на очередь, которая моделирует кафе
	 */
	private QueueForTransactions cafe;

	/**
	 * Ссылка на генератор случайных чисел, который будет задавать время
	 * пербывания клиента в кафе.
	 */
	private Randomable random;

	/**
	 * CoffeeHouseClient constructor.
	 */
	public CoffeeHouseClient() {
		super();
	}

	/**
	 * Метод служит для передачи ссылки на очередь, которая моделирует кафе.
	 * Creation date: (02.03.2006 18:33:19)
	 * 
	 * @param newQueue
	 *            qusystem.QueueForTransactions
	 */
	public void setCafe(process.QueueForTransactions newQueue) {
		cafe = newQueue;
	}

	/**
	 * Метод служит для передачи ссылки на генератор случайных чисел, который
	 * будет задавать время пербывания клиента в кафе. Creation date:
	 * (02.03.2006 18:33:19)
	 * 
	 */
	public void setRandom(Randomable random) {
		this.random = random;
	}

	/**
	 * Метод обеспечивает доступ к очереди, которая моделирует кафе. Creation
	 * date: (02.03.2006 18:33:19)
	 * 
	 * @return qusystem.QueueForTransactions
	 */
	public process.QueueForTransactions getCafe() {
		if (cafe == null)
			System.out
					.print("Клиенту кафе не передана ссылка на очередь, моделирующую кафе.");
		return cafe;
	}

	/**
	 * Метод обеспечивает доступ к генератору случайных чисел, который будет
	 * задавать время пербывания клиента в кафе. Creation date: (02.03.2006
	 * 18:33:19)
	 * 
	 * @return rnd.Randomable
	 */

	private Randomable getRandom() {
		if (random == null)
			System.out
					.println("Клиенту кафе не передана ссылка на генератор случайных чисел, который будет задавать время пербывания клиента в кафе.");

		return random;
	}

	/**
	 * Правила действия клиента кафе. Creation date: (02.03.2006 18:33:19)
	 * 
	 */
	public void rule() {
		// Клиент заходит в кафе.
		// Фактически добавляет себя в очередь-кафе
		getCafe().add(this);
		// Клиент задерживается в кафе на время,
		// полученное от генератора случайных чисел
		holdForTime(getRandom().next());
		// Клиент покидает кафе.
		// Фактически удаляет себя из очереди-кафе
		getCafe().remove(this);
	}

}
