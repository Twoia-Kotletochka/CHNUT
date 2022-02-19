/**
 * 
 */
package example.disco;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import rnd.Randomable;


/**
 * @author 44
 * 
 */
public class Dancer extends Actor {

	Randomable wayRnd;
	Randomable dancingRnd;
	Discoteka discoteka;
	
	BooleanSupplier discoOpen = () -> discoteka.isOpen();
	
	BooleanSupplier discoClosed = ()-> !discoteka.isOpen();
	
	public Dancer( DiscoGUI gui,DiscoModel model, Discoteka discoteka) {
		this.discoteka=discoteka;
		wayRnd = gui.getChooseRandomWay();
		dancingRnd = gui.getChooseRandomDance();
	}

	protected void rule() {
		holdForTime(wayRnd.next());
		try {
			waitForCondition(discoOpen, "���� �� ��������� ���������");
		} catch (DispatcherFinishException e) {
			return;
		}
		discoteka.dancerIn();
		holdForTimeOrWaitForCondition(dancingRnd.next(),discoClosed, "���� �� ��������� ���������, ��� ��������");
		discoteka.dancerOut();
	}
}
