package example.shans;

import rnd.Randomable;

public interface ISlot {

	void setRandom(Randomable slotRandom);

	void setPrizeSize(double prizeSize);

	void init();

	boolean isResult();

	void putCoin();

	double getMoney();

}
