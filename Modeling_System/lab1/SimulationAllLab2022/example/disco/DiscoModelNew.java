/**
 * 
 */
package example.disco;



import process.Dispatcher;
import process.QueueForTransactions;
import widgets.Painter;

public class DiscoModelNew extends DiscoModel {

	private QueueForTransactions<DancerNew> queueWaitFrend;
	private QueueForTransactions<DancerNew> queueWaitOpen;

	public DiscoModelNew(DiscoGuiNew gui, Dispatcher dispatcher) {
		super(gui, dispatcher);
	}
	
	DancerNew createDancer(){
		DancerNew d = new DancerNew((DiscoGuiNew) gui, this, discoteka);
		d.setNameForProtocol("DancerNew");
		return d;
	}


	public QueueForTransactions<DancerNew> getQueueWaitFrend() {
		if (queueWaitFrend == null) {
			queueWaitFrend = new QueueForTransactions<DancerNew>();
			queueWaitFrend.setNameForProtocol("queueWaitFrend");
			queueWaitFrend.setDispatcher(dispatcher);
			Painter p = ((DiscoGuiNew) gui).getDiagramWaitFrend().getPainter();
			queueWaitFrend.setPainter(p);
		}
		return queueWaitFrend;
	}

	public QueueForTransactions<DancerNew> getQueueWaitOpen() {
		if (queueWaitOpen == null) {
			queueWaitOpen = new QueueForTransactions<DancerNew>();
			queueWaitOpen.setNameForProtocol("queueWaitOpent");
			queueWaitOpen.setDispatcher(dispatcher);
			Painter p = ((DiscoGuiNew) gui).getDiagramWaitOpen().getPainter();
			queueWaitOpen.setPainter(p);
		}
		return queueWaitOpen;
	}

}
