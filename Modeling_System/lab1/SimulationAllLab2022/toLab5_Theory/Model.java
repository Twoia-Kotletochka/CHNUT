package toLab5_Theory;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.QueueForTransactions;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.stat.IStatisticsable;

//Клас моделі
public class Model implements IStatisticsable {
	// Посилання на диспетчера
	private Dispatcher dispatcher;
	// Посилання на візуальну частину
	private TheoryGUI gui;
	// Генератор транзакцій
	private TransactGenerator transactGenerator = null;
	// Черга транзакцій
	private QueueForTransactions<Double> queue = null;
	// Обслуговуючий прилад
	private Device device = null;
	// Гістограма для довжини черги
	private DiscretHisto discretHisto;
	// Гістограма для часу перебування у черзі
	private Histo histoTransactionWaitInQueue;
	// Гістограма для часу чекання Device
	private Histo histoWaitDevice;

	// ////////////////////////////////////////
	// Єдиний спосіб створити модель, це викликати цей конструктор
	// Він гарантовано передає посилання на диспетчера та TransGUI
	// ////////////////////////////////////////

	public Model(Dispatcher d, TheoryGUI g) {
		if (d == null || g == null) {
			System.out
					.println("Не визначено диспетчера або TransGUI для BuldModel");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
		// Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	private void componentsToStartList() {
		dispatcher.addStartingActor(getTransactGenerator());
		dispatcher.addStartingActor(getDevice());

	}

	public TransactGenerator getTransactGenerator() {
		if (transactGenerator == null) {
			transactGenerator = new TransactGenerator("Generator", gui,
					this);
		}
		return transactGenerator;
	}

	public QueueForTransactions<Double> getQueue() {
		if (queue == null) {
			queue = new QueueForTransactions<>("Queue", dispatcher,
					getDiscretHisto(), gui.getChooseDataQmaxSize().getInt());
		}
		return queue;
	}

	public Device getDevice() {
		if (device == null) {
			device = new Device("Device", gui, this);
		}
		return device;
	}

	public DiscretHisto getDiscretHisto() {
		if (discretHisto == null) {
			discretHisto = new DiscretHisto();
		}
		return discretHisto;
	}

	public Histo getHistoTransactionWaitInQueue() {
		if (histoTransactionWaitInQueue == null) {
			histoTransactionWaitInQueue = new Histo();
		}
		return histoTransactionWaitInQueue;
	}

	public Histo getHistoWaitDevice() {
		if (histoWaitDevice == null) {
			histoWaitDevice = new Histo();
		}
		return histoWaitDevice;
	}

	public void initForTest() {
		getQueue().setPainter(gui.getDiagramQueueSize().getPainter());

	}

	// Реалізація інтерфейсу IStatisticsable
	@Override
	public void initForStatistics() {

	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги", getDiscretHisto());
		map.put("Гістограма для часу чекання у черзі",
				getHistoTransactionWaitInQueue());
		map.put("Гістограма для часу простою обслуговуючого пристрою",
				getDevice().getWaitingTimeHisto());
		return map;
	}
}
