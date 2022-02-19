package net.soft_systems.integrator;
import java.util.Vector;
import org.w3c.dom.Element;
abstract public class LeafBean implements Bean {
	/**
	 * @return null Vector
	 */
	final public Vector getChildBeans() { return null; }
	/**
	 * addBean does nothing because LeafBean cannot have children
	 */
	final public void addBean(Bean bean) { };
	/**
	 * removeBean does nothing because LeafBean cannot have children
	 */
	final public void removeBean(String id) { };
	public void removeBean(Bean bean) { }
	/**
	 * Saves bean configuration to xml storage
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to set element data
	 */
	public void store(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultStore(this, beanElement, config); }
	/**
	 * Loads bean configuration from xml element
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to get data from element
	 */
	public void load(Element beanElement, BeanConfig config)
		{ BeanUtil.defaultLoad(this, beanElement, config); }
	public String toString() { return this.getName(); }
	private Bean parent;
	/**
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

