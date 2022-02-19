/**
 * 
 */
package example.competition;

/**
 * @author P.Byvoino 01.01.2002 0:06:23
 * 
 */
public interface ITeamMember {
	
	/**
	 * @return int
	 * Количество модулей, сделанных членом команды
	 */
	public int getN();
	/**
	 * @return int
	 * Номер члена команды
	 */
	public int getNumber();
	/**
	 * @return ITeam
	 * Возвращает ссылку на свою команду
	 */
	public ITeam getTeam();

	/**
	 * @param ITeam 
	 * Сылка на команду, которой принадлежит объект
	 */
	public void setTeam(ITeam team);
	

	public void setCompleteStage(boolean b);

	/**
	 * @param IVisualPart 
	 * Сылка на интерфейс пользователя
	 */
	public void setUI(IVisualPart ui);

}
