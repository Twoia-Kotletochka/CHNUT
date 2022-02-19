package example.smo2;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import qusystem.FinishDevice;
import qusystem.GetPutDevice;
import qusystem.TransactionsGenerator;

public class QSystemModel {
	private IQSystemExampleUI ui = null;
	private Dispatcher dispatcher = null;  //  @jve:decl-index=0:visual-constraint="48,62"
	private QueueForTransactions queue1 = null;  //  @jve:decl-index=0:visual-constraint="122,192"
	private QueueForTransactions queue2 = null;  //  @jve:decl-index=0:visual-constraint="263,214"
	private TransactionsGenerator transactionsGenerator = null;  //  @jve:decl-index=0:visual-constraint="14,116"
	private GetPutDevice getPutDevice = null;  //  @jve:decl-index=0:visual-constraint="216,108"
	private FinishDevice finishDevice = null;  //  @jve:decl-index=0:visual-constraint="393,172"
	private MultiActor multiGetPut = null;  //  @jve:decl-index=0:visual-constraint="217,62"
	private MultiActor multiFinish = null;  //  @jve:decl-index=0:visual-constraint="385,65"

	/**
	 * This method initializes dispatcher	
	 * 	
	 * @return process.Dispatcher	
	 */
	public Dispatcher getDispatcher() {
		if (dispatcher == null) {
			System.out.println("Не визначено dispatcher для QSystemModel");
		}
		return dispatcher;
	}

	/**
	 * This method initializes queue1	
	 * 	
	 * @return qusystem.QueueForTransactions	
	 */
	private QueueForTransactions getQueue1() {
		if (queue1 == null) {
			queue1 = new QueueForTransactions();
			queue1.setDispatcher(getDispatcher());
			queue1.setNameForProtocol("Черга1");
		}
		return queue1;
	}

	/**
	 * This method initializes queue2	
	 * 	
	 * @return qusystem.QueueForTransactions	
	 */
	private QueueForTransactions getQueue2() {
		if (queue2 == null) {
			queue2 = new QueueForTransactions();
			queue2.setDispatcher(getDispatcher());
			queue2.setNameForProtocol("Черга2");
		}
		return queue2;
	}

	/**
	 * This method initializes transactionsGenerator	
	 * 	
	 * @return qusystem.TransactionsGenerator	
	 */
	private TransactionsGenerator getTransactionsGenerator() {
		if (transactionsGenerator == null) {
			transactionsGenerator = new TransactionsGenerator();
			transactionsGenerator.setNameForProtocol("TransactionsGenerator");
			transactionsGenerator.setOutputQueue(getQueue1());
			transactionsGenerator.setRandom(ui.getChooseRandomTransGen());
		}
		return transactionsGenerator;
	}

	/**
	 * This method initializes getPutDevice	
	 * 	
	 * @return qusystem.GetPutDevice	
	 */
	private GetPutDevice getGetPutDevice() {
		if (getPutDevice == null) {
			getPutDevice = new GetPutDevice();
			getPutDevice.setNameForProtocol("GetPutDevice");
			getPutDevice.setInputQueue(getQueue1());
			getPutDevice.setOutputQueue(getQueue2());
			getPutDevice.setRandom(ui.getChooseRandomGetPut());
		}
		return getPutDevice;
	}

	/**
	 * This method initializes finishDevice	
	 * 	
	 * @return qusystem.FinishDevice	
	 */
	private FinishDevice getFinishDevice() {
		if (finishDevice == null) {
			finishDevice = new FinishDevice();
			finishDevice.setNameForProtocol("FinishDevice");
			finishDevice.setInputQueue(getQueue2());
			finishDevice.setRandom(ui.getChooseRandomFinish());
		}
		return finishDevice;
	}

	/**
	 * This method initializes multiGetPut	
	 * 	
	 * @return process.MultiActor	
	 */
	private MultiActor getMultiGetPut() {
		if (multiGetPut == null) {
			multiGetPut = new MultiActor();
			multiGetPut.setNameForProtocol("MultiGetPut");
			multiGetPut.setOriginal(getGetPutDevice());
		}
		return multiGetPut;
	}

	/**
	 * This method initializes multiFinish	
	 * 	
	 * @return process.MultiActor	
	 */
	private MultiActor getMultiFinish() {
		if (multiFinish == null) {
			multiFinish = new MultiActor();
			multiFinish.setNameForProtocol("MultiFinish");
			multiFinish.setOriginal(getFinishDevice());
		}
		return multiFinish;
	}

	public void setUi(IQSystemExampleUI ui) {
		this.ui = ui;
	}
	
	public void initForStart(){
		//Настройка и инициализайия компонентов модели
		getQueue1().init();
		getQueue1().setPainter(ui.getPainter1());
		getQueue2().init();
		getQueue2().setPainter(ui.getPainter2());
		getTransactionsGenerator().setFinishTime(ui.getFinishTime());
		getGetPutDevice().setFinishTime(ui.getFinishTime());
		getFinishDevice().setFinishTime(ui.getFinishTime());
		getMultiGetPut().setNumberOfClones(ui.getNClones1());
		getMultiFinish().setNumberOfClones(ui.getNClones2());
	}
	public void componentsToStartList(){
		getDispatcher().addStartingActor(getTransactionsGenerator());
		getDispatcher().addStartingActor(getMultiGetPut());
		getDispatcher().addStartingActor(getMultiFinish());
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher=dispatcher;
		
	}

}
