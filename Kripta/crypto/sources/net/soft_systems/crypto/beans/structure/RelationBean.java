package net.soft_systems.crypto.beans.structure;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.base.SystemHolder;
import net.soft_systems.integrator.*;
import org.w3c.dom.Element;
public class RelationBean extends LeafBean implements RenderableBean {
	public void moveTo(double x, double y) { }
	public RelationBean() { }
	static private SystemHolder systemInfo;
	static protected SystemHolder getSystemInfo(BeanConfig config) {
		if (systemInfo == null) { systemInfo = SystemHolder.getNewSystemHolder(config.getRootBean()); }
		return systemInfo;
	}
	public RelationBean(SubjectBean subject, ResourceBean resource) {
		this.subject = subject;
		this.resource = resource;
	}
	public boolean equals(SubjectBean subject, ResourceBean resource) {
		return this.subject == subject && this.resource == resource;
	}
	public void setResource(ResourceBean resource) { this.resource = resource; }
	public void setThreat(ThreatBean threat) {
		this.threat = threat;
		if (getThreat() == null) { setVulnerability(null); }
		else if (getVulnerability() == null) { setVulnerability(Run.infoSystem.createVulnerability()); }
	}
	private void setVulnerability(VulnerabilityBean vulnerability) {
		if (vulnerability == null && this.vulnerability != null) {
			setProtection(null);
			Run.infoSystem.delVulnerability(this.vulnerability);
			this.vulnerability = null;
		}
		else { this.vulnerability = vulnerability; }
	}
	public void setProtection(ProtectionBean protection) {
		this.protection = protection;
		if (getProtection() == null) { setBoundary(null); }
		else if (getProtection() != null && getVulnerability() != null && getBoundary() == null) {
			setBoundary(Run.infoSystem.createBoundary());
		}
	}
	public ResourceBean getResource() { return resource; }
	public ThreatBean getThreat() { return threat; }
	public VulnerabilityBean getVulnerability() { return vulnerability; }
	public ProtectionBean getProtection() { return protection; }
	public SubjectBean getSubject() { return subject; }
	public void setSubject(SubjectBean subject) { this.subject = subject; }
	public BoundaryBean getBoundary() { return boundary; }
	public void setBoundary(BoundaryBean boundary) {
		if (boundary == null && this.boundary != null) {
			Run.infoSystem.delBoundary(this.boundary);
			this.boundary = null;
			doChangeParams();
		}
		else {
			this.boundary = boundary;
			evalBoundaryRisk();
		}
	}
	public String getId() {
		if (getSubject() != null && getResource() != null) {
			return getSubject().getId() + " - " + getResource().getId();
		}
		else { return "not defined"; }
	}
	public void setId(String id) { }
	public void setName(String name) { }
	public String getName() { return getId(); }
	public String toString() { return getName(); }
	private SubjectBean subject;
	private ResourceBean resource;
	private ThreatBean threat;
	private VulnerabilityBean vulnerability;
	private ProtectionBean protection;
	private BoundaryBean boundary;
	public void store(Element beanElement, BeanConfig config) {
		super.store(beanElement, config);
		if (subject != null) { config.setAttribute(beanElement, "subject", subject.getId()); }
		if (resource != null) { config.setAttribute(beanElement, "resource", resource.getId()); }
		if (threat != null) { config.setAttribute(beanElement, "threat", threat.getId()); }
		if (vulnerability != null) { config.setAttribute(beanElement, "vulnerability", vulnerability.getId()); }
		if (protection != null) { config.setAttribute(beanElement, "protection", protection.getId()); }
		if (boundary != null) { config.setAttribute(beanElement, "boundary", boundary.getId()); }
	}
	public void load(Element beanElement, BeanConfig config) {
		super.load(beanElement, config);
		subject = (SubjectBean)BeanUtil.getBeanById(getSystemInfo(config).getSubjects(),
			config.getAttribute(beanElement, "subject"));
		resource = (ResourceBean)BeanUtil.getBeanById(getSystemInfo(config).getResources(),
			config.getAttribute(beanElement, "resource"));
		threat = (ThreatBean)BeanUtil.getBeanById(getSystemInfo(config).getThreats(),
			config.getAttribute(beanElement, "threat"));
		vulnerability = (VulnerabilityBean)BeanUtil.getBeanById(getSystemInfo(config).getVulnerabilities(),
			config.getAttribute(beanElement, "vulnerability"));
		protection = (ProtectionBean)BeanUtil.getBeanById(getSystemInfo(config).getProtections(),
			config.getAttribute(beanElement, "protection"));
		boundary = (BoundaryBean)BeanUtil.getBeanById(getSystemInfo(config).getBoundaries(),
			config.getAttribute(beanElement, "boundary"));
		evalBoundaryRisk();
	}
	public Vector getBeans() {
		Vector beans = new Vector();
		if (getResource() != null) { beans.add(getResource()); }
		if (getBoundary() != null) { beans.add(getBoundary()); }
		if (getProtection() != null) { beans.add(getProtection()); }
		if (getVulnerability() != null) { beans.add(getVulnerability()); }
		if (getThreat() != null) { beans.add(getThreat()); }
		if (getSubject() != null) { beans.add(getSubject()); }
		return beans;
	}
	public Sign getSign() {
		Sign sign = new RelationSign();
		sign.setColor(Color.gray);
		Vector beans = getBeans();
		Enumeration en = beans.elements();
		double lastX = 0, lastY = 0, x, y;
		boolean first = true;
		while (en.hasMoreElements()) {
			Bean bean = (Bean)en.nextElement();
			if (RenderableBean.class.isAssignableFrom(bean.getClass())) {
				RenderableBean rBean = (RenderableBean)bean;
				if (first) {
					lastX = rBean.getX() + rBean.getCenterX() - getX();
					lastY = rBean.getY() + rBean.getCenterY() - getY();
					first = false;
				}
				else {
					x = rBean.getX() + rBean.getCenterX() - getX();
					y = rBean.getY() + rBean.getCenterY() - getY();
					sign.addShape(new Line2D.Double(x, y, lastX, lastY));
					lastX = x;
					lastY = y;
				}
			}
		}
		return sign;
	}
	public double getCenterX() { return 0; }
	public double getCenterY() { return 0; }
	public double getX() { return getResource().getX() + getResource().getCenterX(); }
	public double getY() { return getResource().getY() + getResource().getCenterY(); }
	public boolean containsBean(Bean bean) {
		return getResource() == bean || getSubject() == bean || getProtection() == bean || getThreat() == bean ||
			getVulnerability() == bean || getBoundary() == bean;
	}
	public void evalBoundaryRisk() {
		if (threat != null && vulnerability != null && protection != null && boundary != null) {
			boundary.evalRisk(threat.getProbability(), vulnerability.getLoss(), protection.getStrength());
			doChangeParams();
		}
	}
	public String getFullName() {
		if (getSubject() != null && getResource() != null) {
			String fullName = "" + getSubject();
			if (getThreat() != null) { fullName += "-" + getThreat(); }
			if (getVulnerability() != null) { fullName += "-" + getVulnerability(); }
			if (getProtection() != null) { fullName += "-" + getProtection(); }
			if (getBoundary() != null) { fullName += "-" + getBoundary(); }
			fullName += "-" + getResource();
			return fullName;
		}
		else { return "not defined"; }
	}
	public double getBoundaryRisk() {
		if (getBoundary() != null) { return getBoundary().getRisk(); }
		if (getThreat() != null && getVulnerability() != null) {
			return getThreat().getProbability() * getVulnerability().getLoss() * (1 - getProtectionStrength());
		}
		else { return 0; }
	}
	public double getProtectionStrength() {
		if (getProtection() != null) { return getProtection().getStrength(); }
		else { return 0; }
	}
	protected void doChangeParams() {
		if (Run.infoSystem != null) { Run.infoSystem.evalTotalRisks(); }
	}
}

