package example.competition;

import java.awt.Color;
import java.util.List;

public interface ITeam {

	public int getTeamNumber();
	
	public void setTeamNumber(int teamNumber);	
	
	public Color getTeamColor();
	
	public void setTeamColor(Color color);
	
	public void setUI (IVisualPart ui);

	public List<ITeamMember> getMemberList();

}