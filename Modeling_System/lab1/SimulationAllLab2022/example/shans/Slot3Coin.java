package example.shans;

import java.util.Random;
import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import rnd.Randomable;

public class Slot3Coin extends Actor implements ISlot {
	private Random rnd = new Random();

	private double prize = 0;

	private boolean coin = false;

	private double money = 0;

	private boolean result = false;

	private int count = 0;
	
	private Randomable random = null; 

	private Randomable getRandom() {
		return random;
	}

	public void setRandom(Randomable random) {
		this.random = random;
	}

	public double getMoney() {
		double res = money;
		money = 0;
		return res;
	}

	public void setPrizeSize(double prize) {
		this.prize = prize;
	}

	public void init() {
		count = 0;
		result = false;
		money = 0;
		coin = false;
	}

	public void putCoin() {
		coin = true;
		result=false;
	}

	public boolean isResult() {
		return result;
	}

	public void rule() {
		BooleanSupplier c = ()-> coin;
		while (true) {
			//Ожидание монеты
			coin = false;
			try {
				waitForCondition(c,"Ждет монету");
			} catch (DispatcherFinishException e) {
				return;
			}
			
			//Формирование результата	
			holdForTime(getRandom().next());
			if (rnd.nextFloat() < 0.5F) {
				count = 0;
				getDispatcher().printToProtocol("  Cорвалось!");
			} else {
				count++;
				getDispatcher().printToProtocol("  Получилось " + count);
			}
			if (count == 3) {
				money = prize;
				count = 0;
			}
			result = true;
		}
	}
}
