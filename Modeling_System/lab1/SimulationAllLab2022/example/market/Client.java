/**
 * 
 */
package example.market;

import java.util.List;
import java.util.Random;

import process.Actor;
import rnd.Randomable;

/**
 * @author 44
 * 
 */
public class Client extends Actor {
	
	private MarketGUI gui;
	private MarketModel model;
	private  double finishTime;
	private  Randomable advertisingRnd;
	private  Randomable contactRnd;
	private  List<Client> allClientList;
	private boolean ready = false;
	Random rnd=new Random();

	public Client(String name, MarketGUI g,MarketModel m) {
		if(m==null || g == null){
			System.out.println("�� ��������� model ��� gui ��� Client!?");
			System.exit(0);
		}
		setNameForProtocol(name);
		model = m;
		gui=g;
		advertisingRnd = gui.getChooseRandomAdvertising().getRandom();
		contactRnd = gui.getChooseRandomContact().getRandom();
		finishTime = gui.getChooseDataFinishTime().getDouble();
		allClientList = model.getAllClientList();
		
	}
	protected void rule() {
		// ������� ������ 䳿 �볺���
		// �������� �� ����������� �� ��� ������ �������,
		// ��� ���� ���� ����� ���� ������� � �� �������� �� ��������,
		// ���������� ����� setReady
		waitForConditionOrHoldForTime(()-> ready," ���� ����� �����", advertisingRnd.next());
		if (!ready) {
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " ������� ������ �������.");
			setReady();
		}
		// ����� �볺�� ������ ����������� �����, ���������� ����� �볺���
		while (getDispatcher().getCurrentTime() < finishTime
				& model.getNumberOfReady() < allClientList.size()) {
			// �������� �� ������� � ����� �� ������
			holdForTime(contactRnd.next());
			// ������� ��������� ����� ����������� �볺���
			int someClientNumber = rnd.nextInt(allClientList.size());
			Client someClient = (Client) allClientList.get(someClientNumber);
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " ������ "
							+ someClient.getNameForProtocol());
			if (someClient.ready)
				getDispatcher().printToProtocol(
						"  " + someClient.getNameForProtocol()
								+ " ��� ���� �����");
			else {
				getDispatcher().printToProtocol(
						"  " + someClient.getNameForProtocol() + " ������ "
								+ getNameForProtocol());
				someClient.setReady();
			}
		}
	}

	private void setReady() {
		if (!this.ready) {
			this.ready = true;
			model.incNumberOfReady();
		}
	}


}
