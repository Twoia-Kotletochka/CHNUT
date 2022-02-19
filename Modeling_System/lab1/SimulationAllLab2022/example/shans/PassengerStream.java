package example.shans;



import process.Actor;
import rnd.Randomable;

public class PassengerStream extends Actor {
	private Bus bus =null;
	private int count = 0;
	private Randomable random=null;
	
	private Randomable getRandom() {
		return random;
	}
	public void setRandom(Randomable random) {
		this.random = random;
	}
	public PassengerStream(Bus bus) {
		super();
		this.bus = bus;
	}
	public void rule() {
		while (! bus.isArrive()& count<6) {
			holdForTime(getRandom().next());
			count++;
			getDispatcher().printToProtocol("В очереди стало "+getCount());
		}
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	public PassengerStream() {
		super();
	}
	public int getCount() {
		return count;
	}
	public void init() {
		count = 0;
	}

}
