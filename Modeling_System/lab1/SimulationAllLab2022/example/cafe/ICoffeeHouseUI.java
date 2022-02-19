package example.cafe;

import widgets.ChooseRandom;
import widgets.Painter;


public interface ICoffeeHouseUI {

	public abstract ChooseRandom getRndClient();

	public abstract ChooseRandom getRndGen();

	public abstract double getFinishTime();

	public abstract Painter getCaffeeHousePainter();

}