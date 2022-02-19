package example.shans;

import process.Actor;
import process.Dispatcher;
// Модель должна многократно запускаться без повторного создания компонент
// Цель запуска: получить результат игры Шанса
// Перед запуском информацию берет из интерфейса пользователя
// Ссылка на интерфейс пользователя передается через конструктор
// При запуске модель инициализирует свои компоненты, передает их диспетчеру и активизирует его
// После завершения процесса моделирования метод getResult() возвращает результат моделирования
// Компонент  slot в модели должен быть сменным
//
public class Model {

	private Dispatcher dispatcher = null;  //  @jve:decl-index=0:visual-constraint="51,30"
	private Bus bus = null;  //  @jve:decl-index=0:visual-constraint="38,85"
	private PassengerStream passengerStream = null;  //  @jve:decl-index=0:visual-constraint="99,83"
	private Slot3Coin slot3coin = null;  //  @jve:decl-index=0:visual-constraint="215,176"
	private MisterShans misterShans = null;  //  @jve:decl-index=0:visual-constraint="296,83"
//	private IShansVisualPart ui= null;
	private ISlot slot=null;
	public Model(){
		super();
		initialize();
	}
	public Model(IShansVisualPart ui){
		this();
		setUi(ui);
		initialize();		
	}
	private void initialize(){
		setSlot(getSlot3coin());
	}

public void setUi(IShansVisualPart ui) {
	// Устанавливаем параметры компонентов
	// в соответствии с настройками визуальной части
	// (Это вторая группа настроек)
	getBus().setRandom(ui.getBusRandom());
	getPassengerStream().setRandom(ui.getPassengerRandom());
	getSlot().setRandom(ui.getSlotRandom());
	getSlot().setPrizeSize(ui.getPrizeSize());

	}
/**
 * Метод возвращает компоненты в исходное состояние
 *
 */
	
	public void runModel(){
		getBus().init();
		getPassengerStream().init();
		getSlot().init();
		getDispatcher().addStartingActor(getBus());
		getDispatcher().addStartingActor(getPassengerStream());
		getDispatcher().addStartingActor((Actor)getSlot());
		getDispatcher().addStartingActor(getMisterShans());
		getDispatcher().start();
	}

	/**
	 * This method initializes dispatcher	
	 * 	
	 * @return process.Dispatcher	
	 */
	public Dispatcher getDispatcher() {
		if (dispatcher == null) {
			dispatcher = new Dispatcher();
			dispatcher.setProtocolFileName("");
		}
		return dispatcher;
	}

	/**
	 * This method initializes bus	
	 * 	
	 * @return example.shans.Bus	
	 */
	private Bus getBus() {
		if (bus == null) {
			bus = new Bus();
			bus.setNameForProtocol("Bus");
		}
		return bus;
	}

	/**
	 * This method initializes passengerStream	
	 * 	
	 * @return example.shans.PassengerStream	
	 */
	private PassengerStream getPassengerStream() {
		if (passengerStream == null) {
			passengerStream = new PassengerStream();
			passengerStream.setNameForProtocol("PassengerStream");
			passengerStream.setBus(getBus());
		}
		return passengerStream;
	}

	/**
	 * This method initializes slot3coin	
	 * 	
	 * @return example.shans.Slot3Coin	
	 */
	private Slot3Coin getSlot3coin() {
		if (slot3coin == null) {
			slot3coin = new Slot3Coin();
			slot3coin.setNameForProtocol("Slot3Coin");
		}
		return slot3coin;
	}

	/**
	 * This method initializes misterShans	
	 * 	
	 * @return example.shans.MisterShans	
	 */
	private MisterShans getMisterShans() {
		if (misterShans == null) {
			misterShans = new MisterShans();
			misterShans.setNameForProtocol("MisterShans");
			misterShans.setPassengerStream(getPassengerStream());
			misterShans.setSlot(getSlot3coin());
			misterShans.setBus(getBus());
		}
		return misterShans;
	}
	public ISlot getSlot() {
		return slot;
	}
	public void setSlot(ISlot slot) {
		this.slot = slot;
	}
	public double getProfit(){
		return getMisterShans().getProfit();
	}

}
