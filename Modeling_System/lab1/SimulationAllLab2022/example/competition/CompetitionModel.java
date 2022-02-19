/**
 * 
 */
package example.competition;

import java.awt.Color;

import process.Actor;
import process.Dispatcher;

/**
 * @author pgBiv Класс обеспечивает создание игроков, объединение их в команды и
 *         запуск процесса имитации игры
 */
public class CompetitionModel {
	/**
	 * Ссылка на интерфейс пользователя
	 */
	private VisualPart ui=null;
	private Dispatcher dispatcher ;
		
	public Dispatcher getDispatcher() {
		if (dispatcher==null)System.out.println("Не визначено диспетчера для моделі");
		return dispatcher;
	}


	public CompetitionModel() {
		super();
	}




	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher=dispatcher;
		
	}


	public void teamsToStartList() {
		Color[] color = { Color.blue, Color.red, Color.pink, Color.magenta, Color.green };
		for (int i = 1; i <= ui.nTeam(); i++) {
			Team team = new Team();
			team.setTeamNumber(i);
			team.setTeamColor(color[i%color.length]);
			team.setUI(ui);
			getDispatcher().addStartingActor((Actor)team);
		}
	
	}


	public VisualPart getUi() {
		if (ui==null)System.out.println("Не визначено ui для моделі");
		return ui;
	}


	public void setUi(VisualPart ui) {
		this.ui = ui;
	}

}
