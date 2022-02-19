/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.frames;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import net.soft_systems.crypto.Run;
import net.soft_systems.crypto.beans.structure.RelationBean;
import net.soft_systems.integrator.*;
import net.soft_systems.integrator.event.BeanListener;
abstract public class CryptoCanvas extends JPanel {
	protected Vector beansVectors;
	protected Vector selectionBeansVectors;
	/**
	 * Vector of selected beans
	 * @associates RenderableBean
	 */
	protected Vector selectedBeans;
	/**
	 * Parent scroll pane
	 */
	protected JScrollPane scrollPane;
	/**
	 * Bean which is dragged now.
	 */
	protected RenderableBean draggedBean;
	/**
	 * First point in selection Rectangle. It is set in screen coordinates.
	 * Before using point coordinates must be scaled
	 */
	protected Point2D selectedFirstPoint;
	/**
	 * Mouse Y position relative to dragged bean
	 */
	protected double draggedYPos;
	/**
	 * Mouse X position relative to dragged bean
	 */
	protected double draggedXPos;
	/**
	 * Current scale
	 */
	protected double scale = 1d;
	/**
	 * Current point in selection Rectangle. It is set in screen
	 * coordinates. Before using point coordinates must be scaled
	 */
	protected Point2D currentDragPoint;
	static final int HORIZONTAL_LAYOUT = 0;
	static final int VERTICAL_LAYOUT = 1;
	/**
	 * Bean of relation which is edited in the canvas.
	 * Relation is edited by dragging mouse from one bean to another
	 */
	private RenderableBean editedRelation;
	/**
	 * Constructs canvas
	 */
	public CryptoCanvas() {
		beansVectors = getBeansVectors();
		selectionBeansVectors = getSelectBeansVectors();
		selectedBeans = new Vector();
		setBackground(Color.white);
		addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) { doMousePressed(e); }
				public void mouseReleased(MouseEvent e) { doMouseReleased(e); }
			});
		addMouseMotionListener(
			new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) { doMouseDragged(e); }
			});
		Run.integrator.addBeanListener(
			new BeanListener() {
				public void addBean(Bean bean, Bean parent) { repaint(); }
				public void afterDelBean(String id, Bean parent, Class beanClass) { repaint(); }
				public void delBean(Bean bean, Bean parent) { }
				public void beforeAddBean(Bean bean, Bean parent) { }
			});
		Run.integrator.addKeyListener(
			new KeyListener() {
				public void keyReleased(KeyEvent e) { }
				public void keyTyped(KeyEvent e) { }
				public void keyPressed(KeyEvent e) { doKeyPressed(e); }
			});
	}
	/**
	 * Invoked when user pressed delete button
	 * @param selectedBeans Beans which are selected
	 */
	public void tryDeleteSelected(Vector selectedBeans) { }
	/**
	 * @return vector of selected beans
	 */
	public Vector getSelectedBeans() { return selectedBeans; }
	/**
	 * Invoked when key is pressed
	 */
	public void doKeyPressed(KeyEvent e) {
		if (getSelectedBeans().size() > 0) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_DELETE:
					tryDeleteSelected(getSelectedBeans());
				break;
				case KeyEvent.VK_ENTER:
				break;
			}
		}
	}
	/**
	 * Invoked when user dragges mouse in the panel
	 * @param e Mouse event of dragging
	 */
	protected void doMouseDragged(MouseEvent e) {
		if (editedRelation != null) {
			if (srcRelationPoint != curRelationPoint) { drawRelationLine(); }
			curRelationPoint = new Point2D.Double(e.getX(), e.getY());
			if (srcRelationPoint != curRelationPoint) { drawRelationLine(); }
		}
		else if (draggedBean != null && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			moveBeansBy(selectedBeans, e.getX() / scale - draggedXPos - draggedBean.getX(),
				e.getY() / scale - draggedYPos - draggedBean.getY());
			updateSize();
		}
		if (selectedFirstPoint != null && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			drawDragRect();
			currentDragPoint = new Point2D.Double(e.getX(), e.getY());
			drawDragRect();
		}
	}
	/**
	 * Sets scale of beans structure
	 */
	public void setScale(double scale) {
		if (scale < 0.1) { scale = 0.1; }
		else if (scale > 10) { scale = 10; }
		this.scale = scale;
		repaint(0, 0, getWidth(), getHeight());
	}
	/**
	 * @return Vector containing vectors of beans which are drawn on the canvas. Order of painting beans is
	 * the same as order of vectors
	 */
	abstract public Vector getBeansVectors();
	/**
	 * Paint method of panel
	 */
	public void paint(Graphics g) {
		synchronized(this) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
			// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			updateSize();
			paintVectors(g2d, beansVectors);
		}
	}
	/**
	 * Paints vector of vectors of beans
	 * @param vectors Vectors of vectors including beans
	 */
	protected void paintVectors(Graphics2D g2d, Vector vectors) {
		if (vectors != null) {
			Enumeration en = vectors.elements();
			while (en.hasMoreElements()) {
				Vector beanVector = (Vector)en.nextElement();
				paintBeans(g2d, beanVector);
			}
		}
	}
	/**
	 * @return Transform which must be applied to scale and move absolute
	 * coordinates of bean to screen coordinates of that bean
	 * @param bean Bean which coordinates must be scaled
	 */
	public AffineTransform getBeanTransform(RenderableBean bean) {
		return new AffineTransform(scale, 0, 0, scale, bean.getX() * scale, bean.getY() * scale);
	}
	/**
	 * @return Scale
	 */
	public double getScale() { return scale; }
	/**
	 * Paints all child beans of parent bean
	 * @param g2d Graphic device
	 * @param parent Parent bean.
	 */
	protected void paintBeans(Graphics2D g2d, Vector beans) {
		if (beans != null) {
			int x;
			int y;
			for (int i = 0; i < beans.size(); i++) {
				Bean b = (Bean)beans.elementAt(i);
				if (RenderableBean.class.isAssignableFrom(b.getClass())) {
					RenderableBean rb = (RenderableBean)b;
					AffineTransform transform = getBeanTransform(rb);
					x = (int)(rb.getX() * scale);
					y = (int)(rb.getY() * scale);
					Sign sign = rb.getSign();
					if (sign != null) {
						if (selectedBeans.contains(rb)) {
							g2d.setColor(Color.blue);
							float[] dash = new float[2];
							dash[0] = 3;
							dash[1] = 3;
							BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND,
								BasicStroke.JOIN_MITER, 10, dash, 0);
							Stroke lastStroke = g2d.getStroke();
							g2d.setStroke(stroke);
							Shape r = sign.getSelection();
							Shape sh = transform.createTransformedShape(r);
							g2d.draw(sh);
							g2d.setStroke(lastStroke);
						}
						g2d.setColor(Color.black);
						sign.draw(g2d, transform);
						if (sign.getDrawId()) {
							g2d.setColor(Color.red);
							g2d.drawString(b.getId(), (float)x, (float)(y - 4 * scale));
						}
					}
				}
				paintBeans(g2d, b.getChildBeans());
			}
		}
	}
	/**
	 * Repaints bean
	 */
	public void repaintBean(RenderableBean bean) {
		if (bean != null) {
			repaintBeanRelations(bean);
			repaint(getBeanShape(bean).getBounds());
		}
	}
	/**
	 * Invoked when user releases mouse button
	 * @param e Mouse event object
	 */
	protected void doMouseReleased(MouseEvent e) {
		if (selectedFirstPoint != null && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			drawDragRect();
			Rectangle2D selectionRect = getUnscaledDragRect();
			Vector oldSelectedBeans = selectedBeans;
			selectedBeans = BeanUtil.findBeansInRect(selectionBeansVectors, selectionRect);
			repaintBeans(oldSelectedBeans);
			repaintBeans(selectedBeans);
		}
		else if (editedRelation != null && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			double x                = e.getX() / scale;
			double y                = e.getY() / scale;
			RenderableBean nextBean = BeanUtil.findBeanAt(selectionBeansVectors, x, y);
			drawRelationLine();
			if (nextBean != null) {
				if (tryAddToRelation(editedRelation, nextBean)) {
					Debug.debug("Added to relation " + editedRelation + " bean " + nextBean);
					repaintBean(editedRelation);
				}
			}
			editedRelation = null;
			srcRelationPoint = null;
			curRelationPoint = null;
		}
	}
	/**
	 * Clears vector of selected beans
	 */
	protected void deselectBeans() { selectedBeans.clear(); }
	/**
	 * Updates size of canvas according to beans position and sizes
	 */
	protected void updateSize() {
		Rectangle beansBounds = BeanUtil.getBeansBounds(selectionBeansVectors);
		if (beansBounds != null) {
			beansBounds = getScaleTransform().createTransformedShape(beansBounds).getBounds();
			beansBounds.grow(100, 100);
			setSize(beansBounds.x + beansBounds.width, beansBounds.y + beansBounds.height);
		}
	}
	/**
	 * @return Vector containing vectors of beans which can be selected on the canvas. Order of selection is
	 * the same as order of vectors
	 */
	abstract public Vector getSelectBeansVectors();
	/**
	 * @return Parent <code>ScrollPane</code>
	 */
	protected JScrollPane getScrollPane() { return scrollPane; }
	/**
	 * Invoked when user presses mouse button
	 * @param e Mouse event object
	 */
	protected void doMousePressed(MouseEvent e) {
		double x                       = e.getX() / scale;
		double y                       = e.getY() / scale;
		RenderableBean newSelectedBean = BeanUtil.findBeanAt(selectionBeansVectors, x, y);
		// selecting new bean
		if (newSelectedBean != null) {
			if (!selectedBeans.contains(newSelectedBean)) {
				Vector oldSelectedBeans = selectedBeans;
				selectedBeans = new Vector();
				selectedBeans.add(newSelectedBean);
				repaintBeans(oldSelectedBeans);
				repaintBean(newSelectedBean);
			}
		}
		if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			// setting dragged bean
			draggedBean = newSelectedBean;
			if (draggedBean != null) {
				draggedYPos = y - draggedBean.getY();
				draggedXPos = x - draggedBean.getX();
				editedRelation = isRelationSource(draggedBean);
				if (editedRelation != null) {
					srcRelationPoint = new Point2D.Double(editedRelation.getX() * scale,
						editedRelation.getY() * scale);
					curRelationPoint = srcRelationPoint;
					Debug.debug("Editing relation " + editedRelation);
				}
			}
			//setting selected Rectangle first point
			if (newSelectedBean == null) {
				selectedFirstPoint = new Point2D.Double(e.getX(), e.getY());
				currentDragPoint = (Point2D)selectedFirstPoint.clone();
			}
			else { selectedFirstPoint = null; }
			// double click processing
			if (e.getClickCount() > 1 && newSelectedBean != null) {
				if (EditableBean.class.isAssignableFrom(newSelectedBean.getClass())) {
					Run.integrator.showEditFrame((EditableBean)newSelectedBean);
				}
			}
		}
		else if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
			// selecting new bean
			if (newSelectedBean != null) {
				if (PopupMenuBean.class.isAssignableFrom(newSelectedBean.getClass())) {
					JPopupMenu popupMenu = new JPopupMenu();
					PopupMenuBean popupBean = (PopupMenuBean)newSelectedBean;
					popupBean.initMenu(popupMenu);
					popupMenu.show(this, e.getX(), e.getY());
				}
			}
		}
	}
	/**
	 * @return Bounding rectangle of identifier of bean in screen coordinates
	 */
	protected Rectangle getIdShape(RenderableBean bean) {
		Graphics2D g2d = (Graphics2D)getGraphics();
		double width   = g2d.getFontMetrics().stringWidth(bean.getId());
		double height  = g2d.getFontMetrics().getHeight();
		return new Rectangle((int)(bean.getX() * scale - 0.5), (int)(bean.getY() * scale - 4 * scale - height - 0.5),
			(int)(width + 1.5), (int)(height + 1.5));
	}
	/**
	 * Returns bounding rectangle of bean sign
	 * @param bean Bean
	 * @return Bounding rectangle of <code>bean</code>
	 */
	public Rectangle getBeanShape(Bean bean) {
		if (bean != null) {
			Rectangle rect = null;
			if (RenderableBean.class.isAssignableFrom(bean.getClass())) {
				RenderableBean rbean = (RenderableBean)bean;
				Sign sign = rbean.getSign();
				rect = sign.getBounds(getBeanTransform(rbean));
				if (sign.getDrawId()) {
					Rectangle idRect = getIdShape(rbean);
					rect.add(idRect);
				}
				rect.grow(2, 2);
			}
			Rectangle childRect = getBeanVectorShape(bean.getChildBeans());
			if (childRect != null) {
				if (rect == null) { rect = childRect; }
				else { rect.add(childRect); }
			}
			return rect;
		}
		return null;
	}
	protected Rectangle getBeanVectorShape(Vector beans) {
		Rectangle rect = null;
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Bean childBean = (Bean)beans.elementAt(i);
				Rectangle childRect = getBeanShape(childBean);
				if (childRect != null) {
					if (rect == null) { rect = childRect; }
					else { rect.add(childRect); }
				}
			}
		}
		return rect;
	}
	/**
	 * Moves all beans in vector <code>beans</code> by relative distance <code>deltaX,deltaY</code>
	 * @param beans Vector of beans to be moved
	 * @param deltaX relative X distance. It must be in absolute coordinates.
	 * @param deltaY relative Y distance. It must be in absolute coordinates.
	 */
	protected void moveBeansBy(Vector beans, double deltaX, double deltaY) {
		Enumeration en = beans.elements();
		while (en.hasMoreElements()) {
			RenderableBean bean = (RenderableBean)en.nextElement();
			Rectangle oldRepaintShape = getBeanShape(bean);
			bean.moveTo(bean.getX() + deltaX, bean.getY() + deltaY);
			repaintBean(bean);
			repaint(oldRepaintShape);
		}
	}
	/**
	 * Sets parent scroll pane
	 * @param pane Parent <code>ScrollPane</code>
	 */
	protected void setScrollPane(JScrollPane pane) { this.scrollPane = pane; }
	/**
	 * @return Selection rectangle
	 */
	protected Rectangle2D getUnscaledDragRect() {
		Rectangle2D rect = getDragRect();
		if (rect != null) {
			rect.setRect(rect.getX() / scale, rect.getY() / scale, rect.getWidth() / scale,
				rect.getHeight() / scale);
		}
		return rect;
	}
	/**
	 * @return Transform which must be applied to scale absolute coordinates to screen coordinates
	 */
	public AffineTransform getScaleTransform() { return new AffineTransform(scale, 0, 0, scale, 0, 0); }
	/**
	 * Moves <code>bean</code> to new position and repaints canvas in
	 * nessesary places. Position must be in absolute values - not scaled.
	 * @param bean Bean which is moved
	 * @param newX New X position of bean.
	 * @param newY New Y position of bean.
	 */
	public void moveBean(RenderableBean bean, double newX, double newY) {
		if (bean != null) {
			Rectangle oldRepaintShape = getBeanShape(bean);
			bean.moveTo(newX, newY);
			repaintBean(bean);
			repaint(oldRepaintShape);
		}
	}
	/**
	 * @return Selection rectangle in screen coordinates. Need to divide all coordinates by scale.
	 */
	protected Rectangle2D getDragRect() {
		if (currentDragPoint != null && selectedFirstPoint != null) {
			double x, y, w, h;
			if (currentDragPoint.getX() > selectedFirstPoint.getX()) {
				x = selectedFirstPoint.getX();
				w = currentDragPoint.getX() - selectedFirstPoint.getX();
			}
			else {
				x = currentDragPoint.getX();
				w = selectedFirstPoint.getX() - currentDragPoint.getX();
			}
			if (currentDragPoint.getY() > selectedFirstPoint.getY()) {
				y = selectedFirstPoint.getY();
				h = currentDragPoint.getY() - selectedFirstPoint.getY();
			}
			else {
				y = currentDragPoint.getY();
				h = selectedFirstPoint.getY() - currentDragPoint.getY();
			}
			return new Rectangle2D.Double(x, y, w, h);
		}
		else { return null; }
	}
	/**
	 * Draws selection rectangle.
	 */
	protected void drawDragRect() {
		Rectangle2D rect = getDragRect();
		if (rect != null) {
			Graphics2D g2d = (Graphics2D)getGraphics();
			g2d.setXORMode(Color.white);
			g2d.draw(rect);
			g2d.setPaintMode();
		}
	}
	protected Point2D srcRelationPoint = null;
	protected Point2D curRelationPoint;
	/**
	 * Draws relation line
	 */
	protected void drawRelationLine() {
		if (srcRelationPoint != null && curRelationPoint != null) {
			Graphics2D g2d = (Graphics2D)getGraphics();
			g2d.setXORMode(Color.white);
			g2d.draw(
				new Line2D.Double(srcRelationPoint.getX(), srcRelationPoint.getY(), curRelationPoint.getX(),
				curRelationPoint.getY()));
			g2d.setPaintMode();
		}
	}
	/**
	 * Repaints all beans in vector
	 * @param beans Vector of beans
	 */
	public void repaintBeans(Vector beans) {
		Enumeration en = beans.elements();
		while (en.hasMoreElements()) {
			RenderableBean rBean = (RenderableBean)en.nextElement();
			repaintBeanRelations(rBean);
			repaintBean(rBean);
		}
	}
	protected void repaintBeanRelations(Bean b) {
		if (!RelationBean.class.isAssignableFrom(b.getClass())) {
			Vector relations = getBeanRelations(b);
			repaintBeans(relations);
		}
	}
	abstract protected Vector getBeanRelations(Bean b);
	/**
	 * set locations of elements automatically according to layout mode <code>layoutMode</code>
	 * @param layoutMode mode of layout. Can be <code>HORIZONTAL_LAYOUT</code>, <code>VERTICAL_LAYOUT</code>
	 * @see VERTICAL_LAYOUT HORIZONTAL_LAYOUT
	 */
	public void layoutElements(int layoutMode) {
		int vectStep = 100;
		int elemStep = 100;
		int pos      = vectStep / 4;
		Vector beans = beansVectors;
		if (beans != null) {
			for (int i = 0; i < beans.size(); i++) {
				Vector b = (Vector)beans.elementAt(i);
				if (layoutVector(b, elemStep, pos, layoutMode)) { pos += vectStep; }
			}
			repaint();
		}
	}
	protected boolean layoutVector(Vector beans, int step, int absPos, int layoutMode) {
		boolean result = false;
		if (beans != null) {
			Enumeration en = beans.elements();
			int pos = step / 4;
			while (en.hasMoreElements()) {
				Bean bean = (Bean)en.nextElement();
				if (RenderableBean.class.isAssignableFrom(bean.getClass())) {
					RenderableBean rBean = (RenderableBean)bean;
					result = true;
					if (layoutMode == VERTICAL_LAYOUT) { rBean.moveTo(pos, absPos); }
					else { rBean.moveTo(absPos, pos); }
					pos += step;
				}
			}
		}
		return result;
	}
	/**
	 * Must return relation bean which includes <code>draggedBean</code>
	 * @return instance of relation bean with bean <code>draggedBean</code>
	 */
	abstract protected RenderableBean isRelationSource(RenderableBean draggedBean);
	/**
	 * Must return true if bean <code>nextBean</code> can be included in relation <code>relation</code>
	 * and add that bean to the ralation
	 * @return true if bean <code>nextBean</code> can be included in relation <code>relation</code>.
	 */
	abstract protected boolean tryAddToRelation(RenderableBean relation, RenderableBean nextBean);
}

