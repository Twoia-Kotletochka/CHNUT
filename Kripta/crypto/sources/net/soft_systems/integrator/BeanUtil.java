/*
 * BeanUtil.java
 *
 * Created on субота, 24, серпня 2002, 18:50
 */

package net.soft_systems.integrator;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import org.w3c.dom.Element;
public class BeanUtil extends Object {
	/**
	 * Returns bounding rectangle of bean <code>parent</code> and child
	 * beans without Identifier signs. Can return null if there were no beans
	 * with signs (RenderableBeans with not null <code>Sign</code> object. Rectangle is not scaled.
	 * @param beanVectors Vectors containing vectors of beans
	 * @return Bounding rectangle. can be null
	 */
	static public Rectangle getBeansBounds(Vector beanVectors) {
		Rectangle rect = null;
		Enumeration en = beanVectors.elements();
		Vector beans;
		while (en.hasMoreElements()) {
			beans = (Vector)en.nextElement();
			for (int i = 0; i < beans.size(); i++) {
				Bean b = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(b.getClass())) {
					RenderableBean rb = (RenderableBean)b;
					Sign sign = rb.getSign();
					if (sign != null) {
						AffineTransform transform = new AffineTransform(1, 0, 0, 1, rb.getX(), rb.getY());
						if (rect == null) { rect = sign.getBounds(transform); }
						else { rect.add(sign.getBounds(transform)); }
					}
				}
				Rectangle subRect = getBeansBounds(b);
				if (subRect != null) {
					if (rect == null) { rect = subRect; }
					else { rect.add(subRect); }
				}
			}
		}
		return rect;
	}
	/**
	 * Returns bounding rectangle of bean <code>parent</code> and child
	 * beans without Identifier signs. Can return null if there were no beans
	 * with signs (RenderableBeans with not null <code>Sign</code> object. Rectangle is not scaled.
	 * @param parent Parent bean
	 * @return Bounding rectangle. can be null
	 */
	static public Rectangle getBeansBounds(Bean parent) {
		Rectangle rect = null;
		Vector beans = parent.getChildBeans();
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean b = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(b.getClass())) {
					RenderableBean rb = (RenderableBean)b;
					Sign sign = rb.getSign();
					if (sign != null) {
						AffineTransform transform = new AffineTransform(1, 0, 0, 1, rb.getX(), rb.getY());
						if (rect == null) { rect = sign.getBounds(transform); }
						else { rect.add(sign.getBounds(transform)); }
					}
				}
				Rectangle subRect = getBeansBounds(b);
				if (subRect != null) {
					if (rect == null) { rect = subRect; }
					else { rect.add(subRect); }
				}
			}
		}
		return rect;
	}
	static public String getUniqName(String baseName, Vector beans) {
		if (beans != null) {
			boolean busy;
			int no = 1;
			do {
				busy = false;
				for (int i = 0; i < beans.size(); i++) {
					Bean childBean = (Bean)beans.elementAt(i);
					if (childBean.getId().equals(baseName + no)) {
						busy = true;
						break;
					}
				}
				if (busy) { no++; }
			}
			while (busy);
			return baseName + no;
		}
		else {
			Debug.error("Пустое значение при поиске уникального идентификатора");
			return "";
		}
	}
	static public Bean getBeanById(Bean parent, String id) {
		Vector beans = parent.getChildBeans();
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				if (childBean.getId().equals(id)) { return childBean; }
			}
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				Bean subChildBean = getBeanById(childBean, id);
				if (subChildBean != null) { return subChildBean; }
			}
		}
		return null;
	}
	static public void defaultStore(Bean bean, Element beanElement, BeanConfig config) {
		config.setAttribute(beanElement, "id", bean.getId());
		config.setAttribute(beanElement, "name", bean.getName());
		config.setAttribute(beanElement, "class", bean.getClass().getName());
	}
	static public Vector findBeansInRect(Vector beanVectors, Rectangle2D rect) {
		Enumeration en = beanVectors.elements();
		Vector resultBeans = new Vector();
		Vector beans;
		Enumeration enBean;
		Bean childBean;
		while (en.hasMoreElements()) {
			beans = (Vector)en.nextElement();
			enBean = beans.elements();
			while (enBean.hasMoreElements()) {
				childBean = (Bean)enBean.nextElement();
				if (RenderableBean.class.isAssignableFrom(childBean.getClass())) {
					RenderableBean renderableBean = (RenderableBean)childBean;
					Sign s = renderableBean.getSign();
					if (s != null) {
						Shape signShape = s.getSelection();
						AffineTransform t = new AffineTransform(1, 0, 0, 1, renderableBean.getX(),
							renderableBean.getY());
						Shape transShape = t.createTransformedShape(signShape);
						if (transShape.intersects(rect)) { resultBeans.add(renderableBean); }
					}
				}
				Vector subResultBeans = findBeansInRect(childBean, rect);
				if (subResultBeans != null) { resultBeans.addAll(subResultBeans); }
			}
		}
		return resultBeans;
	}
	static public Vector findBeansInRect(Bean parentBean, Rectangle2D rect) {
		Vector beans = parentBean.getChildBeans();
		Vector resultBeans = new Vector();
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(childBean.getClass())) {
					RenderableBean renderableBean = (RenderableBean)childBean;
					Sign s = renderableBean.getSign();
					if (s != null) {
						Shape signShape = s.getSelection();
						AffineTransform t = new AffineTransform(1, 0, 0, 1, renderableBean.getX(),
							renderableBean.getY());
						Shape transShape = t.createTransformedShape(signShape);
						if (transShape.intersects(rect)) { resultBeans.add(renderableBean); }
					}
				}
			}
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				Vector subResultBeans = findBeansInRect(childBean, rect);
				if (subResultBeans != null) { resultBeans.addAll(subResultBeans); }
			}
		}
		return resultBeans;
	}
	static public void defaultLoad(Bean bean, Element beanElement, BeanConfig config) {
		try {
			bean.setId(config.getAttribute(beanElement, "id"));
			bean.setName(config.getAttribute(beanElement, "name"));
		}
		catch (Exception ex) { Debug.error("Не удалось получить аттрибуты элемента"); }
	}
	static public RenderableBean findBeanAt(Vector beanVectors, double x, double y) {
		Enumeration en = beanVectors.elements();
		while (en.hasMoreElements()) {
			Vector beans = (Vector)en.nextElement();
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(childBean.getClass())) {
					RenderableBean renderableBean = (RenderableBean)childBean;
					Sign s = renderableBean.getSign();
					if (s != null) {
						Shape signShape = s.getSelection();
						AffineTransform t = new AffineTransform(1, 0, 0, 1, renderableBean.getX(),
							renderableBean.getY());
						Shape transShape = t.createTransformedShape(signShape);
						if (transShape.contains(x, y)) { return renderableBean; }
					}
				}
			}
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				RenderableBean subChildBean = findBeanAt(childBean, x, y);
				if (subChildBean != null) { return subChildBean; }
			}
		}
		return null;
	}
	static public RenderableBean findBeanAt(Bean parentBean, double x, double y) {
		Vector beans = parentBean.getChildBeans();
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(childBean.getClass())) {
					RenderableBean renderableBean = (RenderableBean)childBean;
					Sign s = renderableBean.getSign();
					if (s != null) {
						Shape signShape = s.getSelection();
						AffineTransform t = new AffineTransform(1, 0, 0, 1, renderableBean.getX(),
							renderableBean.getY());
						Shape transShape = t.createTransformedShape(signShape);
						if (transShape.contains(x, y)) { return renderableBean; }
					}
				}
			}
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				RenderableBean subChildBean = findBeanAt(childBean, x, y);
				if (subChildBean != null) { return subChildBean; }
			}
		}
		return null;
	}
	static public int indexOfBeanById(Vector beans, String id) {
		if (id != null) {
			int index = 0;
			Enumeration en = beans.elements();
			while (en.hasMoreElements()) {
				Bean bean = (Bean)en.nextElement();
				if (bean.getId().equals(id)) { return index; }
				index++;
			}
		}
		return -1;
	}
	static public Bean getBeanById(Vector beans, String id) {
		if (id != null) {
			Enumeration en = beans.elements();
			while (en.hasMoreElements()) {
				Bean bean = (Bean)en.nextElement();
				if (bean.getId().equals(id)) { return bean; }
			}
		}
		return null;
	}
}

