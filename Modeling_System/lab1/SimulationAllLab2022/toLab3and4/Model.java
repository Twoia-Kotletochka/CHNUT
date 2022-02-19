package toLab3and4;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.MultiActor;
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
	private GUI gui;
	
	////////Актори\\\\\\\\\
	// Генератор транзакцій
	private Generator generator;
	// Обслуговуючий прилад
	private Device device;		
	//Бригада обслуговуючих пристроїв
	private MultiActor multiDevice;
	
	/////////Черги\\\\\\\\\
	// Черга транзакцій
	private QueueForTransactions<Transaction> queue;
	
	/////////Гістограми\\\\\\\\\\\\
	// Гістограма для довжини черги
	private DiscretHisto discretHistoQueue;
	// Гістограма для часу перебування у черзі
	private Histo histoTransactionWaitInQueue;
	// Гістограма для часу обслуговування
	private Histo histoTransactionServiceTime;
	// Гістограма для часу чекання Device
	private Histo histoWaitDevice;

	// ////////////////////////////////////////
	// Єдиний спосіб створити модель, це викликати цей конструктор
	// Він гарантовано передає посилання на диспетчера та TransGUI
	// ////////////////////////////////////////

	public Model(Dispatcher d, GUI g) {
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
		dispatcher.addStartingActor(getGenerator());
		dispatcher.addStartingActor(getMultiDevice());

	}

	public Generator getGenerator() {
		if (generator == null) {
			generator = new Generator("Generator", gui, this);
		}
		return generator;
	}

	public QueueForTransactions<Transaction> getQueue() {
		if (queue == null) {
			queue = new QueueForTransactions<>("Queue", dispatcher,
					getDiscretHistoQueue());
		}
		return queue;
	}

	public Device getDevice() {
		if (device == null) {
			device = new Device("Device", gui, this);
			device.setHistoForActorWaitingTime(getHistoWaitDevice());
		}
		return device;
	}
	
	
	public MultiActor getMultiDevice() {
		if(multiDevice == null){
			multiDevice = new MultiActor();
			multiDevice.setOriginal(getDevice());
			multiDevice.setNumberOfClones(gui.getChooseDataNdevice().getInt());
			multiDevice.setNameForProtocol("Створення бригади обслуговуючих пристроїв");
		}
		return multiDevice;
	}


	public DiscretHisto getDiscretHistoQueue() {
		if (discretHistoQueue == null) {
			discretHistoQueue = new DiscretHisto();
		}
		return discretHistoQueue;
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
		getQueue().setPainter(gui.getDiagramQueue().getPainter());
		if(gui.getJCheckBox().isSelected()){
			dispatcher.setProtocolFileName("Console");
		}

	}

	public Histo getHistoTransactionServiceTime() {
		if( histoTransactionServiceTime== null)
			histoTransactionServiceTime = new Histo();
		return histoTransactionServiceTime;
	}

	// Реалізація інтерфейсу IStatisticsable
	@Override
	public void initForStatistics() {

	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги", getDiscretHistoQueue());
		map.put("Гістограма для часу чекання у черзі",
				getHistoTransactionWaitInQueue());
		map.put("Гістограма для часу простою обслуговуючого пристрою",
				getDevice().getWaitingTimeHisto());
		map.put("Гістограма для часу обслуговування",
				getHistoTransactionServiceTime());

		return map;
	}
}
