package buldo2022;

import java.util.HashMap;
import java.util.Map;


import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import process.Store;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.experiments.IExperimentable;
import widgets.stat.IStatisticsable;
import widgets.trans.ITransMonitoring;
import widgets.trans.ITransProcesable;

/**
 * Клас моделі Модель складається з БУЛЬДОЗЕРА, НАВАНТАЖУВАЧА та кількох
 * САМОСКИДІВ. Бульдозер згрібає ґрунт у КУПУ. На одну порцію ґрунту
 * він витрачає випадковий час. Навантажувач починає працювати коли у купі
 * є ґрунт і в черзі є самоскид, що чекає завантаження. Навантажувач
 * порціями насипає ґрунт з купи у кузов самоскиду. Місткість кузову 
 * самоскида - декілька порцій ґрунту.  Самоскиди виїжджають із автопарку 
 * і стають у ЧЕРГУ до навантажувача. Після завантаження самоскиди везуть 
 * ґрунт замовнику, розвантажуються, і повертаються до навантажувача.
 */
public class BuldModel implements IExperimentable, ITransProcesable,
		IStatisticsable { 
	
	// Посилання на диспетчера
	private Dispatcher dispatcher;

	// Посилання на візуальну частину
	private BuldGUI gui;

	// Бульдозер
	private Buldo buldo;

	// Навантажувач
	private Loader loader;
	
	// Зразок самоскида
	private Lorry lorry;

	// Бригада самоскидів
	private MultiActor multiLorry;

	// Купа грунту
	private Store heap;

	// Черга самоскидів до навантажувача
	private QueueForTransactions<Lorry> queueToLoader;

	// Уявна черга для самоскидів у дорозі
	private QueueForTransactions<Lorry> queueLorryOnRoad;
		
	/////////////////////////////////////////////////////////////
	//Накопичувачі статистики створюємо одразу з пакетним доступом
	/////////////////////////////////////////////////////////////
	// Гістограма для довжини черги до навантажувача
	DiscretHisto histoForQueueToLoader = new DiscretHisto();

	// Гістограма для часу простою бульдозера
	Histo histoBuldo = new Histo();

	// Гістограма для часу простою навантажувача
	Histo histoLoader = new Histo();

	// Гістограма для часу простою самоскида
	Histo histoLorry = new Histo();

	// Гістограма для розмірів купи
	Histo histoHeap = new Histo();

	// ////////////////////////////////////////
	// Єдиний спосіб створити модель, це викликати цей конструктор
	// Він гарантовано передає посилання на диспетчера та GUI
	// ////////////////////////////////////////

	public BuldModel(Dispatcher d, BuldGUI g) {
		if (d == null || g == null) {
			System.out.println("Не задано диспетчера або GUI для моделі");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
		// Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	// Передача акторів диспетчеру
	private void componentsToStartList() {
		dispatcher.addStartingActor(getBuldo());
		dispatcher.addStartingActor(getLoader());
		dispatcher.addStartingActor(getMultiLorry());
	}

	// ////////////////////////////////////////
	// Методи відкладеного створення акторів моделі,
	// ////////////////////////////////////////

	// Meтод створення бульдозера
	public Buldo getBuldo() {
		if (buldo == null) {
			buldo = new Buldo("Бульдозер", gui, this);
		}
		return buldo;
	}

	// Meтод створення навантажувача
	public Loader getLoader() {
		if (loader == null)
			loader = new Loader("Навантажувач", gui, this);
		return loader;
	}
	
	// Meтод створення зразка самоскида
	public Lorry getLorry() {
		if (lorry == null)
			lorry = new Lorry("Самоскид", gui, this);
		return lorry;
	}

	//Meтод створення бригади самоскидів
	public MultiActor getMultiLorry() {
		if (multiLorry == null) {
			multiLorry = new MultiActor("MultiActor для самоскидів",
					getLorry(), gui.getChooseDataNLorry().getInt());
		}
		return multiLorry;
	}

	// ////////////////////////////////////////
	// Методи відкладеного створення черг
	// ////////////////////////////////////////

	// Meтод створення купи грунту
	public Store getHeap() {
		if (heap == null) {
			heap = new Store("Купа грунту", dispatcher, histoHeap);
		}
		return heap;
	}

	// Meтод створення черги до навантажувачау
	public QueueForTransactions<Lorry> getQueueToLoader() {
		if (queueToLoader == null) {
			queueToLoader = new QueueForTransactions<Lorry>(
					"Черга до навантажувача", dispatcher, 
					histoForQueueToLoader);
		}
		return queueToLoader;
	}

	// Meтод створення уявної черги "самоскиди у дорозі"
	public QueueForTransactions<Lorry> getQueueLorryOnRoad() {
		if (queueLorryOnRoad == null) {
			queueLorryOnRoad = new QueueForTransactions<Lorry>(
					"Cамоскиди, що у дорозі", dispatcher);
		}
		return queueLorryOnRoad;
	}

	// ///////////////////////////////////////////////////////////
	// Методи ініціалізації моделі та реалізація інтерфейсів
	// //////////////////////////////////////////////////////////////

	// Ініціалізація для режиму "Тест"
	public void initForTest() {
		// Передаємо чергам painter-ів для динамічної індикації
		getHeap().setPainter(gui.getDiagramHeepSize().getPainter());
		getQueueToLoader().setPainter(
				gui.getDiagramQueueToLoader().getPainter());
		getQueueLorryOnRoad()
				.setPainter(gui.getDiagramLorryOnRoad().getPainter());
		if (gui.getJCheckBox().isSelected())
			dispatcher.setProtocolFileName("Console");
	}

	
	// /////////////////////////////////////
	// Реалізація інтерфейсу IStatisticsable
	// /////////////////////////////////////
	
	// Ініціалізацію для режиму "Статистика" не використовуємо
	@Override
	public void initForStatistics() {

	}
	
	//Метод формування мапи результатів
	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Гістограма для довжини черги до навантажувача",
				histoForQueueToLoader);
		map.put("Гістограма для розмірів купи", histoHeap);
		map.put("Гістограма для часу простою бульдозера", histoBuldo);
		map.put("Гістограма для часу простою самоскида", histoLorry);
		map.put("Гістограма для часу простою навантажувача", histoLoader);
		return map;
	}

	// /////////////////////////////////////
	// Реалізація інтерфейсу IExperimentable
	// /////////////////////////////////////
	
	// Налаштування для проведення експерименту
	public void initForExperiment(double factor) {
		multiLorry.setNumberOfClones((int) factor);
	}

	//Метод формування мапи результатів
	public Map<String, Double> getResultOfExperiment() {
		Map<String, Double> resultMap = new HashMap<>();
		resultMap.put("Час простою авто від їх кількості", histoLorry
				.getAverage());
		resultMap.put("Розмір купи від кількості авто", histoHeap.average());
		resultMap.put("Час простою бульдозера від кількості авто",
				histoBuldo.getAverage());
		resultMap.put("Час простою навантажувача від кількості авто",
				histoLoader.getAverage());
		return resultMap;
	}

	// /////////////////////////////////////
	// Реалізація інтерфейсу ITransprocesable
	// /////////////////////////////////////
	
	// Налаштування перед початком моделювання
	public void initForTrans(double finishTime) {
		getBuldo().setFinishTime(finishTime);
		getLoader().setFinishTime(finishTime);
		getLorry().setFinishTime(finishTime);
		gui.getChooseDataFinishTime().setDouble(finishTime);

	}

	//Метод формування мапи результатів
	@Override
	public Map<String, ITransMonitoring> getMonitoringObjects() {
		Map<String, ITransMonitoring> transMap = new HashMap<>();
		transMap.put("Купа грунту", getHeap());
		transMap.put("Черга до навантажувача", getQueueToLoader());
		transMap.put("Самоскиди на шляхах", getQueueLorryOnRoad());
		return transMap;
	}
}
