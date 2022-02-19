package net.soft_systems.integrator;
import java.util.Vector;
import net.soft_systems.crypto.InfoSystem;
import net.soft_systems.routines.Config;
import org.w3c.dom.*;
public class BeanConfig extends Config {
	/**
	 * @associates Bean
	 */
	Vector beanLine = new Vector();
	protected Bean rootBean;
	public Bean getRootBean() { return rootBean; }
	public String getConfigFilename() { return "beans.xml"; }
	public BeanConfig() { super(); }
	protected Bean initDefault() { return InfoSystem.getInitBeans(); }
	/**
	 * This function must be called only from <code>load</code> method of subclasses of <code>Bean</code>.
	 * It returns bean which is parent in hierarchy to the reciever of message <code>load</code> of class
	 * <code>parentClass</code>
	 * @return Parent Bean
	 */
	public Bean getParentBean(Class parentClass) {
		Bean bean;
		if (beanLine.size() > 0) {
			for (int i = beanLine.size() - 1; i >= 0; i--) {
				bean = (Bean)beanLine.elementAt(i);
				if (parentClass.isAssignableFrom(bean.getClass())) { return bean; }
			}
			return null;
		}
		else { return null; }
	}
	/**
	 * This function must be called only from <code>load</code> method of subclasses of <code>Bean</code>.
	 * It returns parent bean of reciever of message <code>load</code>
	 * @return Parent Bean
	 */
	public Bean getParentBean() { return currentParentBean; }
	private Bean currentParentBean;
	public Bean read() {
		load();
		if (isInitialized()) {
			Element beansElement = getElementByTagName("beans");
			if (beansElement != null) {
				Element rootElement = getChildElementByTag(beansElement, "bean");
				if (rootElement != null) {
					rootBean = readBeanFromXML(rootElement);
					currentParentBean = rootBean;
					beanLine.clear();
					beanLine.add(currentParentBean);
					if (rootBean != null) { readBeansFromXML(rootBean, rootElement); }
					return rootBean;
				}
				else {
					Debug.critical("Не найден корневой элемент иерархии");
					return null;
				}
			}
			else {
				Debug.critical("Ошибка в формате конфигурации");
				return null;
			}
		}
		else {
			rootBean = initDefault();
			this.store(rootBean);
			return rootBean;
		}
	}
	public void readBeansFromXML(Bean parentBean, Element parentElement) {
		Bean oldParentBean;
		Element beans = getChildElementByTag(parentElement, "child-beans");
		if (beans != null) {
			NodeList childNodes = beans.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				if (Element.class.isAssignableFrom(item.getClass())) {
					Element elem = (Element)item;
					if (elem.getNodeName().equals("bean")) {
						Bean childBean = readBeanFromXML(elem);
						if (childBean != null) {
							beanLine.add(childBean);
							oldParentBean = currentParentBean;
							currentParentBean = childBean;
							parentBean.addBean(childBean);
							readBeansFromXML(childBean, elem);
							beanLine.remove(beanLine.size() - 1);
							currentParentBean = oldParentBean;
						}
					}
				}
			}
		}
	}
	public void readBeansFromXML(Vector beans, Element parentElement) {
		if (beans != null) {
			NodeList childNodes = parentElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				if (Element.class.isAssignableFrom(item.getClass())) {
					Element elem = (Element)item;
					if (elem.getNodeName().equals("bean")) {
						Bean childBean = readBeanFromXML(elem);
						if (childBean != null) {
							beans.add(childBean);
							readBeansFromXML(childBean, elem);
						}
					}
				}
			}
		}
	}
	public Bean readBeanFromXMLRecursive(Element beanElement) {
		Bean oldParentBean;
		Bean bean = readBeanFromXML(beanElement);
		if (bean != null) {
			oldParentBean = currentParentBean;
			currentParentBean = bean;
			beanLine.add(bean);
			readBeansFromXML(bean, beanElement);
			beanLine.remove(beanLine.size() - 1);
			currentParentBean = oldParentBean;
		}
		return bean;
	}
	public Bean readBeanFromXML(Element beanElement) {
		String beanClassName = getAttribute(beanElement, "class");
		if (beanClassName != null) {
			try {
				Class beanClass = Class.forName(beanClassName);
				if (Bean.class.isAssignableFrom(beanClass)) {
					Bean bean = (Bean)beanClass.newInstance();
					bean.load(beanElement, this);
					Debug.notice("Загружен элемент " + bean.getId() + " класса " + bean.getClass().getName());
					return bean;
				}
				else {
					Debug.critical("Класс элемента" + beanClassName + " не наследует базовый класс Bean");
					return null;
				}
			}
			catch (ClassNotFoundException ex)
				{ Debug.critical("Не удалось найти класс элемента " + beanClassName); }
			catch (InstantiationException ex)
				{ Debug.critical("Не удалось создать объект класса " + beanClassName); }
			catch (IllegalAccessException ex) {
				Debug.critical("Ошибка при создании нового объекта класса " + beanClassName);
			}
		}
		else { Debug.critical("Пустое имя класса элемента в конфигурации"); }
		return null;
	}
	public void store(Bean rootBean) {
		initializeEmpty();
		Element beansNode = getDocument().createElement("beans");
		getDocument().appendChild(beansNode);
		storeBeanToXML(rootBean, beansNode);
		save();
	}
	public void storeBeanToXML(Bean bean, Element parentElement) {
		Element beanNode = getDocument().createElement("bean");
		bean.store(beanNode, this);
		parentElement.appendChild(beanNode);
		storeBeansToXML(bean, beanNode);
	}
	public void storeBeansToXML(Bean parentBean, Element parentElement) {
		Vector beans = parentBean.getChildBeans();
		if (beans != null) {
			Element childBeansNode = getDocument().createElement("child-beans");
			parentElement.appendChild(childBeansNode);
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				storeBeanToXML(childBean, childBeansNode);
			}
		}
	}
	public void storeBeansToXML(Vector beans, Element parentElement) {
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				storeBeanToXML(childBean, parentElement);
			}
		}
	}
}

