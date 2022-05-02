/* Generated by Together */

package net.soft_systems.crypto.beans.policy;
import java.beans.PropertyVetoException;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.process.ProcessBean;
import net.soft_systems.crypto.frames.policy.PolicyFrame;
import net.soft_systems.integrator.*;
import net.soft_systems.model.message.Message;
import net.soft_systems.model.server.ModelServerImpl;
public class HRUPolicy extends PolicyBean {
	public boolean checkRight(String right, ProcessBean srcProcess, ProcessBean dstProcess) {
		Bean srcBean                = srcProcess.getElement();
		Bean dstBean                = dstProcess.getElement();
		RightMatrixBean rightMatrix = getRightMatrix();
		Vector rights               = rightMatrix.getRights(srcBean, dstBean);
		if (rights == null) { rights = rightMatrix.getRights(dstBean, srcBean); }
		if (rights != null) {
			Enumeration en = rights.elements();
			RightBean rightBean;
			while (en.hasMoreElements()) {
				rightBean = (RightBean)en.nextElement();
				if (rightBean.getId().equals(right)) return true;
			}
			return false;
		}
		else return false;
	}
	public boolean checkRight(RightBean right, Bean subject, Bean object) {
		RightMatrixBean rightMatrix = getRightMatrix();
		Vector rights = rightMatrix.getRights(subject, object);
		if (rights != null) { return rights.contains(right); }
		else return false;
	}
	public boolean addRight(RightBean right, Bean subject, Bean object) {
		RightMatrixBean rightMatrix = getRightMatrix();
		Vector rights = rightMatrix.getRights(subject, object);
		if (rights != null) {
			if (!rights.contains(right)) {
				rights.add(right);
				ModelServerImpl.SERVER.logMessage(Message.createAddRightMessage(right, subject, object));
				return true;
			}
			else return false;
		}
		else return false;
	}
	public boolean removeRight(RightBean right, Bean subject, Bean object) {
		RightMatrixBean rightMatrix = getRightMatrix();
		Vector rights = rightMatrix.getRights(subject, object);
		if (rights != null) {
			if (rights.contains(right)) {
				rights.remove(right);
				ModelServerImpl.SERVER.logMessage(Message.createRemoveRightMessage(right, subject, object));
				return true;
			}
			else return false;
		}
		else return false;
	}
	public HRUPolicy() { }
	public RightMatrixBean getRightMatrix() { return (RightMatrixBean)getChildBeans().elementAt(0); }
	public CommandBean getCommand(String commandId) {
		Bean commandGroup = (Bean)getChildBeans().elementAt(1);
		return (CommandBean)BeanUtil.getBeanById(commandGroup, commandId);
	}
	public void initNewPolicy() {
		RightMatrixBean rightMatrix = new RightMatrixBean(Run.infoSystem.getSubjects(),
			Run.infoSystem.getResources());
		addBean(rightMatrix);
		CommandGroupBean commandGroup = new CommandGroupBean();
		addBean(commandGroup);
	}
	public String getId() { return "hru"; }
	public String getName() { return HRUPolicy.getDefaultName(); }
	static public String getDefaultName() { return "������������� ������ ���������-�����-�������"; }
	private EditFrame editFrame;
	public EditFrame getEditFrame() {
		if (editFrame == null) { editFrame = new PolicyFrame(this); }
		return editFrame;
	}
	public void closeEditFrame() {
		if (editFrame != null) {
			try { editFrame.setClosed(true); }
			catch (PropertyVetoException ex) { Debug.warning("PropertyVetoException :" + ex.getMessage()); }
			editFrame = null;
		}
	}
	protected boolean checkConditions(CommandBean command) {
		Vector conditions = command.getConditions();
		Enumeration en = conditions.elements();
		ConditionBean cond;
		RightMatrixBean rightMatrix = getRightMatrix();
		while (en.hasMoreElements()) {
			cond = (ConditionBean)en.nextElement();
			if (!checkRight(cond.getRight(), cond.getSubject(), cond.getObject())) return false;
		}
		return true;
	}
	protected void execOperation(OperationBean oper) { oper.exec(); }
	protected void execOperations(CommandBean command) {
		Enumeration en = command.getOperations().elements();
		OperationBean oper;
		while (en.hasMoreElements()) {
			oper = (OperationBean)en.nextElement();
			execOperation(oper);
		}
	}
	public boolean execCommand(CommandBean command) {
		if (checkConditions(command)) {
			execOperations(command);
			return true;
		}
		else return false;
	}
}
