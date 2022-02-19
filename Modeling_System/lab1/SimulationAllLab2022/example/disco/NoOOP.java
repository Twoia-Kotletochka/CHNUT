package example.disco;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rnd.Randomable;
import widgets.Diagram;
import widgets.Painter;

public class NoOOP {

	NoOOPDiscoGUI gui;

	public NoOOP(NoOOPDiscoGUI gui) {
		super();
		this.gui = gui;
	}

	public void start() {
		List<Double> startTimeList = new ArrayList<>();
		List<Double> finishTimeList = new ArrayList<>();
		Randomable rndStart = gui.getChooseRandomWay();
		Randomable rndDancing = gui.getChooseRandomDance();
		int n = gui.getChooseDataNumberOfClients().getInt();
		double openTime = gui.getChooseDataOpenTime().getDouble();
		double closeTime = openTime
				+ gui.getChooseDataDancingTime().getDouble();
		//Створюємо коллекції часу входу і часу виходу
		double x = 0, finishTime = 0;
		for (int i = 0; i < n; i++) {
			x = rndStart.next();
			if (x < openTime)
				x = openTime;
			if (x < closeTime) {
				startTimeList.add(x);
				finishTime = x + rndDancing.next();
				if (finishTime > closeTime)
					finishTime = closeTime;
				finishTimeList.add(finishTime);
			}
		}
		//Впорядковуємо колекції за зростанням часу
		Collections.sort(startTimeList);
		Collections.sort(finishTimeList);
		//Створюємо повязані масиви часу і кількості танцюючих
		double[] time = new double[startTimeList.size() + finishTimeList.size()];
		double[] size = new double[startTimeList.size() + finishTimeList.size()];
		time[0] = startTimeList.get(0);
		size[0] = 1;
		int iStart = 1, iFinish = 0, iAll = 1;
		while (iStart < startTimeList.size() && iFinish < finishTimeList.size()) {
			if (startTimeList.get(iStart) < finishTimeList.get(iFinish)) {
				time[iAll] = startTimeList.get(iStart++);
				size[iAll] = size[iAll - 1] + 1;
			} else {
				time[iAll] = finishTimeList.get(iFinish++);
				size[iAll] = size[iAll - 1] - 1;
			}
			iAll++;
		}
		for (int i = iFinish; i < finishTimeList.size(); i++) {
			time[iAll] = finishTimeList.get(iFinish++);
			size[iAll] = size[iAll++ - 1] - 1;
		}
		//Рисуємо графік
		Diagram d = gui.getDiagram();
		Painter p = d.getPainter();
		p.setColor(Color.MAGENTA);
		p.drawDependency(time, size, Color.MAGENTA, false);
	}

}
