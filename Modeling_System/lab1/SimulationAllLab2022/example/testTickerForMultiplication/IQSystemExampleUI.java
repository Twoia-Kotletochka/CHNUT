package example.testTickerForMultiplication;

import widgets.ChooseRandom;
import widgets.Painter;

public interface IQSystemExampleUI {
	Painter getPainter1();
	Painter getPainter2();
	double getFinishTime();
	ChooseRandom getChooseRandomTransGen();
	ChooseRandom getChooseRandomGetPut();
	ChooseRandom getChooseRandomFinish();
	int getNClones1();
	int getNClones2();
}
