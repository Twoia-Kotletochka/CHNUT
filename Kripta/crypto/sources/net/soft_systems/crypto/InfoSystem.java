package net.soft_systems.crypto;
import java.util.*;
import net.soft_systems.crypto.base.ModelSystemHolder;
import net.soft_systems.crypto.beans.ConfigGroupBean;
import net.soft_systems.crypto.beans.model.MessageGroupBean;
import net.soft_systems.crypto.beans.policy.*;
import net.soft_systems.crypto.beans.process.*;
import net.soft_systems.crypto.beans.structure.*;
import net.soft_systems.crypto.event.SystemChangeListener;
import net.soft_systems.integrator.*;
import net.soft_systems.integrator.event.BeanListener;
import net.soft_systems.model.base.ProcessGenerator;
import net.soft_systems.model.message.Message;
public class InfoSystem extends ModelSystemHolder {
	public ProcessGenerator processGenerator = new ProcessGenerator();
	public ProcessGenerator getProcessGenerator() { return processGenerator; }
	static public InfoSystem getNewInfoSystem(Bean rootBean) {
		InfoSystem is = new InfoSystem();
		is.init(rootBean);
		return is;
	}
	public InfoSystem() { super(); }
	public Vector getThreatRelations(ThreatBean threat) {
		Enumeration en = getRelations().elements();
		Vector resultRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getThreat() == threat) { resultRelations.add(relation); }
		}
		return resultRelations;
	}
	public Vector getProtectionRelations(ProtectionBean bean) {
		Enumeration en = getRelations().elements();
		Vector resultRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getProtection() == bean) { resultRelations.add(relation); }
		}
		return resultRelations;
	}
	public Vector getVulnerabilityRelations(VulnerabilityBean bean) {
		Enumeration en = getRelations().elements();
		Vector resultRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getVulnerability() == bean) { resultRelations.add(relation); }
		}
		return resultRelations;
	}
	public Vector getSubjectRelations(SubjectBean subject) {
		Enumeration en = getRelations().elements();
		Vector subjectRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getSubject() == subject) { subjectRelations.add(relation); }
		}
		return subjectRelations;
	}
	public boolean relationExists(SubjectBean subject, ResourceBean resource) {
		Enumeration en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.equals(subject, resource)) { return true; }
		}
		return false;
	}
	public void addMessage(Message message) { getMessages().add(message); }
	public void addRelation(SubjectBean subject, ResourceBean resource) {
		if (!relationExists(subject, resource)) {
			RelationBean relation = new RelationBean(subject, resource);
			//getRelations().add(relation);
			Run.integrator.addBean(relation, getRelationsTopic(), false);
		}
	}
	public void delRelation(RelationBean relation) {
		Run.integrator.delBean(relation, getRelationsTopic(), false);
	}
	public void delBoundary(BoundaryBean boundary)
		{ Run.integrator.delBean(boundary, getBoundaryTopic(), false); }
	/**
	 * @return Relations containing <code>bean</code>
	 * @param bean Bean of any type which exists in relation
	 */
	public Vector getRelations(Bean bean) {
		Enumeration en = getRelations().elements();
		Vector resultRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.containsBean(bean)) { resultRelations.add(relation); }
		}
		return resultRelations;
	}
	public Vector getRelationsWithThreats() {
		Enumeration en = getRelations().elements();
		Vector resultRelations = new Vector();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getThreat() != null) { resultRelations.add(relation); }
		}
		return resultRelations;
	}
	public boolean containsResource(Vector relations, ResourceBean resource) {
		Enumeration en = relations.elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getResource() == resource) { return true; }
		}
		return false;
	}
	public boolean containsThreat(Vector relations, ThreatBean threat) {
		Enumeration en = relations.elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getThreat() == threat) { return true; }
		}
		return false;
	}
	public VulnerabilityBean createVulnerability() {
		return (VulnerabilityBean)Run.integrator.addBean(VulnerabilityBean.class, getVulnerabilityTopic(), false);
	}
	public BoundaryBean createBoundary() {
		return (BoundaryBean)Run.integrator.addBean(BoundaryBean.class, getBoundaryTopic(), false);
	}
	public void delRelations(ResourceBean resource) {
		Vector toRemove     = new Vector();
		Enumeration en      = getRelations().elements();
		Bean relationsTopic = getRelationsTopic();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getResource() == resource) { toRemove.add(relation); }
		}
		en = toRemove.elements();
		while (en.hasMoreElements()) { Run.integrator.delBean((Bean)en.nextElement(), relationsTopic, false); }
	}
	public void delRelations(SubjectBean subject) {
		Vector toRemove     = new Vector();
		Enumeration en      = getRelations().elements();
		Bean relationsTopic = getRelationsTopic();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getSubject() == subject) { toRemove.add(relation); }
		}
		en = toRemove.elements();
		while (en.hasMoreElements()) { Run.integrator.delBean((Bean)en.nextElement(), relationsTopic, false); }
	}
	public void delVulnerability(VulnerabilityBean vulnarability) {
		Run.integrator.delBean(vulnarability, getVulnerabilityTopic(), false);
	}
	public void delProtectionFromRelations(ProtectionBean bean) {
		Enumeration en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getProtection() == bean) { relation.setProtection(null); }
		}
	}
	public void delThreatFromRelations(ThreatBean threat) {
		Enumeration en = getRelations().elements();
		while (en.hasMoreElements()) {
			RelationBean relation = (RelationBean)en.nextElement();
			if (relation.getThreat() == threat) { relation.setThreat(null); }
		}
	}
	public void evalBoundaryRisks(Vector relations) {
		if (relations != null) {
			Enumeration en = relations.elements();
			while (en.hasMoreElements()) {
				RelationBean relation = (RelationBean)en.nextElement();
				relation.evalBoundaryRisk();
			}
		}
		Run.infoSystem.evalTotalRisks();
	}
	/**
	 * Evaluates risks on boundaries of all relations which include bean <code>dependBean</code>
	 * @param dependBean Risk depends on <code>dependBean</code>
	 */
	public void evalBoundaryRisks(Bean dependBean) { evalBoundaryRisks(getRelations(dependBean)); }
	public void evalTotalRisks() {
		totalRisk = 0;
		if (getRelations() != null) {
			Enumeration en = getRelations().elements();
			while (en.hasMoreElements()) {
				RelationBean relation = (RelationBean)en.nextElement();
				totalRisk += relation.getBoundaryRisk();
			}
		}
		totalStrength = 1.0 / totalRisk;
	}
	public double getTotalStrength() { return totalStrength; }
	public double getTotalRisk() { return totalRisk; }
	/**
	 * Creates initial bean hierarchy.
	 * @return Root bean of hierarchy
	 */
	static public Bean getInitBeans() {
		Bean b, rootBean, groupBean;
		rootBean = new TopicBean("root", "Система криптографической защиты");
		groupBean = new StructureGroupBean();
		b = new StructureBean();
		groupBean.addBean(b);
		rootBean.addBean(groupBean);
		b = new ResourceGroupBean();
		groupBean.addBean(b);
		b = new SubjectGroupBean();
		groupBean.addBean(b);
		b = new ThreatGroupBean();
		groupBean.addBean(b);
		b = new ProtectionGroupBean();
		groupBean.addBean(b);
		b = new BoundaryGroupBean();
		groupBean.addBean(b);
		b = new VulnerabilityGroupBean();
		groupBean.addBean(b);
		b = new RelationGroupBean();
		groupBean.addBean(b);
		groupBean = new PolicyGroupBean();
		rootBean.addBean(groupBean);
		RightGroupBean rightGroup = new RightGroupBean();
		groupBean.addBean(rightGroup);
		groupBean = new TopicBean("realization", "Динамическая модель");
		rootBean.addBean(groupBean);
		ProcessDiagramBean processDiagramBean = new ProcessDiagramBean();
		groupBean.addBean(processDiagramBean);
		ProcessGroupBean processGroupBean = new ProcessGroupBean();
		groupBean.addBean(processGroupBean);
		ChannelGroupBean channelGroupBean = new ChannelGroupBean();
		groupBean.addBean(channelGroupBean);
		groupBean = new TopicBean("modelling", "Моделирование");
		rootBean.addBean(groupBean);
		b = new MessageGroupBean();
		groupBean.addBean(b);
		groupBean = new ConfigGroupBean();
		rootBean.addBean(groupBean);
		b = new TopicBean("avail-policies", "Модели политики безопасности");
		groupBean.addBean(b);
		b.addBean(new ClassRefBean(HRUPolicy.class.getName(), HRUPolicy.getDefaultName()));
		// b.addBean(new ClassRefBean(TAMPolicy.class.getName(), TAMPolicy.getDefaultName()));
		// b.addBean(new ClassRefBean(RolePolicy.class.getName(), RolePolicy.getDefaultName()));
		return rootBean;
	}
	private double totalStrength;
	private double totalRisk;
	public boolean isPolicyEnabled(Class policyClass) {
		Enumeration en = getPolicies().elements();
		Bean obj;
		while (en.hasMoreElements()) {
			obj = (Bean)en.nextElement();
			if (policyClass.isAssignableFrom(obj.getClass())) { return true; }
		}
		return false;
	}
	public void enablePolicy(Class policyClass) {
		if (!isPolicyEnabled(policyClass)) {
			try {
				PolicyBean newPolicy = (PolicyBean)policyClass.newInstance();
				newPolicy.initNewPolicy();
				Run.integrator.addBean(newPolicy, getPolicyTopic(), false);
			}
			catch (Exception ex) { Debug.error("Ошибка при включении политики безопасности " + ex.getMessage()); }
		}
	}
	public void disablePolicy(Class policyClass) {
		if (isPolicyEnabled(policyClass)) {
			for (int i = 0; i < getPolicies().size(); i++) {
				Bean curPolicy = (Bean)getPolicies().elementAt(i);
				if (policyClass.isAssignableFrom(curPolicy.getClass())) {
					Run.integrator.delBean(curPolicy, getPolicyTopic(), false);
					break;
				}
			}
		}
	}
	public void onDelBean(Bean bean, Bean parent) {
		Debug.debug("Deleting bean: " + bean + " parent: " + parent);
		if (bean instanceof NodeBean) { removeChannelWith((NodeBean)bean); }
		else if (bean instanceof SubjectBean) { delRelations((SubjectBean)bean); }
		else if (bean instanceof ResourceBean) { delRelations((ResourceBean)bean); }
		else if (bean instanceof ProcessBean) { removeChannelWith((ProcessBean)bean); }
	}
	public void onAfterDelBean(String beanId, Bean parentBean, Class beanClass) {
		if (ResourceBean.class.isAssignableFrom(beanClass) || SubjectBean.class.isAssignableFrom(beanClass)) {
			doElementsChanged(getSubjects(), getResources());
		}
		else if (RightBean.class.isAssignableFrom(beanClass)) { doRightsChanged(getRights()); }
	}
	public void onAddBean(Bean bean, Bean parent) {
		if (ResourceBean.class.isAssignableFrom(bean.getClass()) ||
			SubjectBean.class.isAssignableFrom(bean.getClass())) {
				doElementsChanged(getSubjects(), getResources());
		}
		else if (RightBean.class.isAssignableFrom(bean.getClass())) { doRightsChanged(getRights()); }
	}
	public void onBeforeAddBean(Bean bean, Bean parent) {
		Debug.debug("Before adding bean: " + bean.getId() + " parent: " + parent);
	}
	protected BeanListener createBeanListener() {
		return new BeanListener() {
			public void addBean(Bean bean, Bean parent) { onAddBean(bean, parent); }
			public void delBean(Bean bean, Bean parent) { onDelBean(bean, parent); }
			public void beforeAddBean(Bean bean, Bean parent) { onBeforeAddBean(bean, parent); }
			public void afterDelBean(String beanId, Bean parentBean, Class beanClass) {
				onAfterDelBean(beanId, parentBean, beanClass);
			}
		};
	}
	public void init() {
		super.init(Run.rootBean);
		Run.integrator.addBeanListener(createBeanListener());
	}
	/**
	 * @associates SystemChangeListener
	 */
	Vector systemChangeListeners = new Vector();
	public void addSystemChangeListener(SystemChangeListener listener) { systemChangeListeners.add(listener); }
	public void removeSystemChangeListener(SystemChangeListener listener) {
		systemChangeListeners.remove(listener);
	}
	public void doElementsChanged(Vector subjects, Vector resources) {
		Enumeration en = systemChangeListeners.elements();
		SystemChangeListener obj;
		while (en.hasMoreElements()) {
			obj = (SystemChangeListener)en.nextElement();
			obj.elementsChanged(subjects, resources);
		}
	}
	public void doRightsChanged(Vector availRights) {
		Enumeration en = systemChangeListeners.elements();
		SystemChangeListener obj;
		while (en.hasMoreElements()) {
			obj = (SystemChangeListener)en.nextElement();
			obj.rightsChanged(availRights);
		}
	}
	public Vector getObjects() {
		Vector objects = new Vector();
		objects.addAll(getResources());
		objects.addAll(getSubjects());
		return objects;
	}
	/**
	 * @return channel which has source node at <code>node</code>
	 * @param node Node which is source of result node
	 */
	public ChannelBean getChannelFrom(NodeBean node) {
		Vector channels = getChannels();
		Enumeration en = channels.elements();
		ChannelBean channel;
		while (en.hasMoreElements()) {
			channel = (ChannelBean)en.nextElement();
			if (channel.getSrcNode() == node) { return channel; }
		}
		return null;
	}
	/**
	 * @return Vector of channels which is connected to <code>node</code>
	 * @param node Node used in channels
	 */
	public Vector getChannelWith(NodeBean node) {
		Vector channels = getChannels();
		Enumeration en = channels.elements();
		ChannelBean channel;
		Vector result = new Vector();
		while (en.hasMoreElements()) {
			channel = (ChannelBean)en.nextElement();
			if (channel.containsNode(node)) { result.add(channel); }
		}
		return result;
	}
	/**
	 * @return Vector of channels which is connected to <code>process</code>
	 * @param process Process where channels are connected to
	 */
	public Vector getChannelWith(ProcessBean process) {
		Vector channels = getChannels();
		Enumeration en = channels.elements();
		ChannelBean channel;
		Vector result = new Vector();
		while (en.hasMoreElements()) {
			channel = (ChannelBean)en.nextElement();
			if (channel.containsProcess(process)) { result.add(channel); }
		}
		return result;
	}
	/**
	 * Removes channels which were connected to node
	 * @param node Node used in channels
	 */
	public void removeChannelWith(NodeBean node) {
		Vector result     = getChannelWith(node);
		Enumeration en    = result.elements();
		Bean channelGroup = getChannelTopic();
		while (en.hasMoreElements()) { Run.integrator.delBean((Bean)en.nextElement(), channelGroup, false); }
	}
	/**
	 * Removes channels which were connected to nodes of process
	 * @param process Process where channels are connected
	 */
	public void removeChannelWith(ProcessBean process) {
		Vector result     = getChannelWith(process);
		Enumeration en    = result.elements();
		Bean channelGroup = getChannelTopic();
		while (en.hasMoreElements()) { Run.integrator.delBean((Bean)en.nextElement(), channelGroup, false); }
	}
	public ProcessBean getProcessByElement(Bean elem) {
		Vector processes = getProcesses();
		Enumeration en = processes.elements();
		ProcessBean process;
		while (en.hasMoreElements()) {
			process = (ProcessBean)en.nextElement();
			if (process.getElement().equals(elem)) return process;
		}
		return null;
	}
}

