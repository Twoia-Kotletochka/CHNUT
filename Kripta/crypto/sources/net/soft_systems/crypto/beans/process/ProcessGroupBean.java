/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.beans.process;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.structure.*;
import net.soft_systems.crypto.event.SystemChangeListener;
import net.soft_systems.integrator.*;
public class ProcessGroupBean extends TopicBean {
	public ProcessGroupBean() {
		super("processes", "Процессы системы");
		Run.infoSystem.addSystemChangeListener(createSystemChangeListener());
	}
	public SystemChangeListener createSystemChangeListener() {
		SystemChangeListener listener = new SystemChangeListener() {
			public void elementsChanged(Vector subjects, Vector resources)
				{ updateProcesses(subjects, resources); }
			public void rightsChanged(Vector availRights) { }
		};
		return listener;
	}
	public void updateProcesses(Vector subjects, Vector resources) {
		Debug.debug("Updating processes");
		Debug.debug("Subjects " + subjects.size());
		Debug.debug("Resources " + resources.size());
		Vector tmpSubjects = new Vector();
		tmpSubjects.addAll(subjects);
		Vector tmpResources = new Vector();
		tmpResources.addAll(resources);
		Vector childBeans = getChildBeans();
		Enumeration en = childBeans.elements();
		ProcessBean processBean;
		Vector toRemove = new Vector();
		while (en.hasMoreElements()) {
			processBean = (ProcessBean)en.nextElement();
			if (!processBean.isContainedIn(tmpSubjects, tmpResources)) { toRemove.add(processBean); }
			else {
				if (!tmpSubjects.remove(processBean.getElement())) { tmpResources.remove(processBean.getElement()); }
			}
		}
		en = toRemove.elements();
		while (en.hasMoreElements()) {
			processBean = (ProcessBean)en.nextElement();
			Run.integrator.delBean(processBean, this, false);
		}
		toRemove.clear();
		en = tmpSubjects.elements();
		SubjectBean subjBean;
		while (en.hasMoreElements()) {
			subjBean = (SubjectBean)en.nextElement();
			processBean = new ProcessBean(subjBean, BeanUtil.getUniqName("P", childBeans));
			Run.integrator.addBean(processBean, this, false);
		}
		en = tmpResources.elements();
		ResourceBean resourceBean;
		while (en.hasMoreElements()) {
			resourceBean = (ResourceBean)en.nextElement();
			processBean = new ProcessBean(resourceBean, BeanUtil.getUniqName("P", childBeans));
			Run.integrator.addBean(processBean, this, false);
		}
	}
}

