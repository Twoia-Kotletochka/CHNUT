package example.disco;

import java.util.function.BooleanSupplier;

import process.DispatcherFinishException;
import process.QueueForTransactions;


public class DancerNew extends Dancer {

	private DiscoGuiNew gui;
	private DiscoModelNew model;

	private QueueForTransactions<DancerNew> queueWaitFrend;
	private QueueForTransactions<DancerNew>  queueWaitOpen;
	private DancerNew frend;
	private	BooleanSupplier findFrend;
	private	BooleanSupplier discoOpen;

	public DancerNew(DiscoGuiNew gui, DiscoModelNew model, Discoteka discoteka) {
		super(gui, model, discoteka);
		this.gui = gui;
		this.model = model;
		queueWaitFrend = this.model.getQueueWaitFrend();
		queueWaitOpen = this.model.getQueueWaitOpen();
	}
	protected void rule() {
		initCondition();
		if (getFrend() == null) {
			DancerNew frend = new DancerNew(gui, model, discoteka);
			frend.setNameForProtocol(this.getNameForProtocol() + "-Frend");
			frend.setFrend(this);
			this.setFrend(frend);
			getDispatcher().addStartingActor(frend);
		}
		try {
			holdForTime(wayRnd.next());
			queueWaitFrend.add(this);
			waitForCondition(findFrend,"���� �� ������ ����");
			//����� ��� ������ ��� �� �������
			if (findFrend.getAsBoolean()){
				getFrend().terminateWaiting();
			}
			queueWaitFrend.remove(this);
			queueWaitOpen.add(this);
			waitForCondition(discoOpen, "�������� ���������");
			queueWaitOpen.remove(this);
			discoteka.dancerIn();
			waitForConditionOrHoldForTime(discoClosed,"���� ������ ������", dancingRnd.next());
			discoteka.dancerOut();
		} catch (DispatcherFinishException e) {
			return;
		}
	}
	
	
	private void initCondition() {
		 findFrend = () ->{
			for (DancerNew f: queueWaitFrend) {
				if (f.getFrend() == DancerNew.this)
					return true;
			}
			return false;
		};
		
		discoOpen =() -> discoteka.isOpen();
	}

	
	
	DancerNew getFrend() {
		return frend;
	}

	void setFrend(DancerNew frend) {
		this.frend = frend;
	}


}
