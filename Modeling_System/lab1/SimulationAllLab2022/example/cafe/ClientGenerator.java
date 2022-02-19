package example.cafe;

import qusystem.TransactionsGenerator;

public class ClientGenerator extends TransactionsGenerator {

	/**
	 * Ссылка на образец клиента кафе, который будет использоваться при
	 * клонировании клиентов
	 */
	public CoffeeHouseClient original = null;

	/**
	 * ClientGenerator constructor.
	 */
	public ClientGenerator() {
		super();
	}

	/**
	 * Метод служит для передачи ссылки на образец клиента кафе, который будет
	 * использоваться для клонирования клиентов. Creation date: (02.03.2006
	 * 18:33:19)
	 * 
	 * @param original
	 *            rgr.transGenExample.CoffeeHouseClient
	 */
	public void setOriginal(CoffeeHouseClient original) {
		this.original = original;
	}

	/* *
	 * Переопределяет метод суперкласса, используемый для создания заявки. Новая
	 * заявка создается путем клонирования объекта original Имя заявки делается
	 * уникальным путем добавления времени ее появления. Так как заявка является
	 * "Актером" то после создания она добавляется в стартовый список
	 * "Диспетчера", который ее активизирует.
	 * 
	 * @see qusystem.TransactionsGenerator#createTransaction()
	 */
	protected void createTransaction() {
		CoffeeHouseClient client = (CoffeeHouseClient) original.clone();
		client.setNameForProtocol("Клієнт № "
				+ getDispatcher().getCurrentTime());
		getDispatcher().addStartingActor(client);
	}

}
