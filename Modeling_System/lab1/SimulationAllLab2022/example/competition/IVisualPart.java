/**
 * 
 */
package example.competition;

import rnd.Randomable;

/**
 * @author pgbiv
 * Данный интерфейс определяет требования к визуальной части приложения
 */
public interface IVisualPart {


	/**
	 * 
	 * @return количество заданий для команд
	 */
	public int nTask();

/**
	 * 
	 * @return количество  команд
	 */
	public int nTeam();

	/**
	 * 
	 * @return количество игроков в команде
	 */
	public int nTeamMember();

	/**
	 * 
	 * @return генератор случайных чисел
	 */
	public Randomable random();
	public void drawGamerTaskReady(ITeamMember member, double taskTime);
	public void drawComleteStage(ITeam team, int taskNumber, double taskTime);
	public void printTeamResult(ITeam team);
}
