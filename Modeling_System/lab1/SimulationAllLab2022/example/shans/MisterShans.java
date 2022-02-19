package example.shans;



import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;

public class MisterShans extends Actor {
	private double profit = 0;
	private Bus bus;
	private PassengerStream passengerStream;
	
	
	public MisterShans() {
		super();
	}

	public double getProfit() {
		return profit;
	}
	
	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public void setPassengerStream(PassengerStream s) {
		this.passengerStream = s;
	}
	private ISlot slot;
	public void setSlot(ISlot slot) {
		this.slot = slot;
	}

	public void rule() {
		BooleanSupplier isResult =()->slot.isResult();
		profit = 0;
		double money;
		while (! bus.isArrive() & (passengerStream.getCount()<6)) {
			getDispatcher().printToProtocol("  Мистер Шанс бросает монетку");
			slot.putCoin();
			profit--;
			try {
				waitForCondition(isResult,"Ждет результата");
			} catch (DispatcherFinishException e) {
				return;
			}
			money=slot.getMoney();
			getDispatcher().printToProtocol("  Выиграл "+money);			
			profit+=money;
		}
	}

}
