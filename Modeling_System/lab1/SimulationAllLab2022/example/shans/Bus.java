package example.shans;

import process.Actor;
import rnd.Randomable;

public class Bus extends Actor {
	private boolean arrive=false;
	
	private Randomable random=null; 
	private Randomable getRandom() {
		return random;
	}
	public void setRandom(Randomable random) {
		this.random = random;
	}
	public Bus() {
		super();
	}
	public void rule() {
		holdForTime(getRandom().next());
		arrive=true;
	}
	public void init() {
		arrive=false;
		
	}
	public boolean isArrive() {
		return arrive;
	}


}
