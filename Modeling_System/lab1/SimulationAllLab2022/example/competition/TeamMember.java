package example.competition;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import rnd.Randomable;

/**
 * ����� ���������� �������� � ��������� ����� �������. ������ ������� �����
 * ������ ��������� ���� ����� ���������� ������� � �����, ���� ��� ������
 * ������� �������� ���������� ����� ������ ����� ������� � ������� ��������
 * ������ � ������������ ������� ����� ����� ���� ������� ���������� �
 * ���������� ����� ����� ������ ������� � ��� �� ��� ���, ���� �� ����� �������
 * ��� �������. ��� ����������� ���������� ������ �������� �� �������(team), �
 * ������� �� �������, ����� ��������� ITeam, � ���������� ������������ �����
 * ��������� IVisualPart.
 * 
 * @author P.Byvoino 24.01.2008 16:36:20
 * 
 */

public class TeamMember extends Actor {

	private int n; // ����� �������, ������� ������ ������

	private boolean completeStage; // ������� ���������� �����

	private Team team; // �������, � ������� ������� ������

	private VisualPart ui; // ������ �� ��������� ������������

	public TeamMember() {
		super();
	}

	/**
	 * ������� �������� ����� �������. Creation date: (08.05.2005 21:19:53)
	 * 
	 * @see process.Actor#rule()
	 */
	public void rule() {
		n = 0;
		int nTask = ui.nTask(); // ����� ����� �������
		Randomable random = ui.random(); // ��������� ���������� �������
		BooleanSupplier allReady = () -> completeStage; // ���������� �����
		while (n < nTask) {
			completeStage = false;
			double taskTime = random.next();
			holdForTime(taskTime);
			n = n + 1;
			ui.drawGamerTaskReady(this, taskTime);
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " ������� " + n + " � "
							+ nTask);
			try {
				waitForCondition(allReady,
						"���� �� ���� ��������� ��'������� �� ���������� ��������");
			} catch (DispatcherFinishException e) {
				return;
			}
		}
	}

	// ///////////////////////////////////////////////////////////////////
	// ������ ������� � ����� ��������

	public int getN() {
		return n;
	}

	public String getNameForProtocol() {

		return "���� ������� �" + String.valueOf(team.getTeamNumber())
				+ String.valueOf(getNumber());
	}

	/**
	 * Returns arr String that represents the value of this object.
	 * 
	 * @return String
	 */

	public Team getTeam() {
		return team;
	}

	public int getNumber() {
		return team.getMemberList().indexOf(this) + 1;
	}

	// ����� �� �������, ������� ����������� ������
	public void setTeam(Team team) {
		this.team = team;
	}

	// �c���� �� ��������� ������������
	public void setUI(VisualPart ui) {
		this.ui = ui;
	}

	public void setCompleteStage(boolean b) {
		completeStage = b;

	}

	public void setN(int n) {
		this.n = n;
	}

}
