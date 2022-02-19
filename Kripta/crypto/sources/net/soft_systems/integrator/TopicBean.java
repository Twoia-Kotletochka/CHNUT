//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\integrator\\TopicBean.java

package net.soft_systems.integrator;
import java.util.Vector;
import javax.swing.JPopupMenu;
import org.w3c.dom.Element;
public class TopicBean implements Bean {
	String id;
	String name;
	/**
	 * @associates Bean
	 */
	Vector children = new Vector();
	public TopicBean() { }
	public TopicBean(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public void setId(String id) { this.id = id; }
	public void setName(String id) { this.name = id; }
	public String getName() { return name; }
	public String getId() { return id; }
	public Vector getChildBeans() { return children; }
	public void removeBean(String id) {
		for (int i = 0; i < children.size(); i++) {
			Bean b = (Bean)children.elementAt(i);
			if (b.getId().equals(id)) {
				children.remove(i);
				break;
			}
		}
	}
	public void removeBean(Bean bean) { children.remove(bean); }
	public String toString() { return getName(); }
	public void addBean(Bean bean) {
		children.add(bean);
		bean.setParent(this);
	}
	public void initMenu(JPopupMenu menu) { }
	public void store(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultStore(this, beanElement, config); }
	public void load(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultLoad(this, beanElement, config); }
	private Bean parent;
	/**
	 * @labelDirection forward
	 * @directed
	 */
	private Bean lnkBean;
	/**
	 * @return parent bean or null if bean is <code>rootBean</code>
	 */
	public Bean getParent() { return parent; }
	/**
	 * Sets parent bean. Must be called in realization of addBean of parent bean class
	 * @param parent parent Bean
	 */
	public void setParent(Bean parent) { this.parent = parent; }
}

