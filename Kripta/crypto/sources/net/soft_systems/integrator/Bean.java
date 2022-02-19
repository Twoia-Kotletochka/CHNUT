//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\integrator\\Bean.java

package net.soft_systems.integrator;
import java.util.Vector;
import org.w3c.dom.Element;
public interface Bean {
	public String getId();
	public void setId(String id);
	public void setName(String name);
	public String getName();
	public Vector getChildBeans();
	public void addBean(Bean bean);
	public void removeBean(String id);
	public void removeBean(Bean bean);
	public String toString();
	/**
	 * Saves bean configuration to xml storage
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to set element data
	 */
	public void store(Element beanElement, BeanConfig config);
	/**
	 * Loads bean configuration from xml element
	 * @param beanElement Element which encapsulates configuration of the bean
	 * @param config Object which provides functions to get data from element
	 */
	public void load(Element beanElement, BeanConfig config);
	/**
	 * @return parent bean or null if bean is <code>rootBean</code>
	 */
	public Bean getParent();
	/**
	 * Sets parent bean. Must be called in realization of addBean of parent bean class
	 * @param parent parent Bean
	 */
	public void setParent(Bean parent);
}

