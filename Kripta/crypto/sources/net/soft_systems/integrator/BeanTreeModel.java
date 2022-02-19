//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\integrator\\BeanTreeModel.java

package net.soft_systems.integrator;
import java.util.*;
import javax.swing.event.*;
import javax.swing.tree.*;
public class BeanTreeModel implements TreeModel {
	protected Bean rootBean;
	/**
	 * @associates TreeModelListener
	 */
	private Vector treeModelListeners = new Vector();
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param rootBean
	 * @roseuid 3D6527FC01BB -----------------------------------------------------------------------------------------------------
	 */
	public BeanTreeModel(Bean rootBean) { this.rootBean = rootBean; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param oldRoot
	 * @roseuid 3D6527FC0270 -----------------------------------------------------------------------------------------------------
	 */
	protected void fireTreeStructureChanged(Bean oldRoot) {
		int len = treeModelListeners.size();
		TreeModelEvent e = new TreeModelEvent(this,
			new Object[] { oldRoot });
		for (int i = 0; i < len; i++) {
			((TreeModelListener)treeModelListeners.elementAt(i)).treeStructureChanged(e);
		}
	}
	public TreePath getPath(Bean bean) { return getPath(bean, new TreePath(rootBean), rootBean); }
	public TreePath getPath(Bean bean, TreePath parentPath, Bean parentBean) {
		if (parentBean == bean) { return parentPath; }
		else {
			if (parentBean != null) {
				Vector childBeans = parentBean.getChildBeans();
				if (childBeans != null) {
					Enumeration en = childBeans.elements();
					while (en.hasMoreElements()) {
						Bean newBean      = (Bean)en.nextElement();
						TreePath newPath  = parentPath.pathByAddingChild(newBean);
						TreePath beanPath = getPath(bean, newPath, newBean);
						if (beanPath != null) { return beanPath; }
					}
				}
			}
		}
		return null;
	}
	public TreePath fireAddBean(Bean newBean) {
		TreePath newPath    = getPath(newBean);
		TreePath parentPath = newPath.getParentPath();
		Bean parentBean     = (Bean)parentPath.getLastPathComponent();
		int newIndex        = parentBean.getChildBeans().indexOf(newBean);
		fireAddBean(parentPath, newIndex);
		return newPath;
	}
	public void fireAddBean(TreePath parentPath, int childIndex) {
		int len = treeModelListeners.size();
		int[] indices = new int[1];
		indices[0] = childIndex;
		TreeModelEvent e = new TreeModelEvent(this, parentPath, indices, null);
		for (int i = 0; i < len; i++)
			{ ((TreeModelListener)treeModelListeners.elementAt(i)).treeNodesInserted(e); }
	}
	public void fireRemoveBean(TreePath parentPath, int childIndex) {
		int len = treeModelListeners.size();
		int[] indices = new int[1];
		indices[0] = childIndex;
		TreeModelEvent e = new TreeModelEvent(this, parentPath, indices, null);
		for (int i = 0; i < len; i++)
			{ ((TreeModelListener)treeModelListeners.elementAt(i)).treeNodesRemoved(e); }
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param l
	 * @roseuid 3D6527FC031A -----------------------------------------------------------------------------------------------------
	 */
	public void addTreeModelListener(TreeModelListener l) { treeModelListeners.addElement(l); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param parent
	 * @param index
	 * @return java.lang.Object
	 * @roseuid 3D6527FD01F9 -----------------------------------------------------------------------------------------------------
	 */
	public Object getChild(Object parent, int index) {
		Bean p = (Bean)parent;
		if (p.getChildBeans() != null) { return p.getChildBeans().elementAt(index); }
		else { return null; }
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param parent
	 * @return int
	 * @roseuid 3D6527FD02E9 -----------------------------------------------------------------------------------------------------
	 */
	public int getChildCount(Object parent) {
		Bean p = (Bean)parent;
		if (p.getChildBeans() != null) { return p.getChildBeans().size(); }
		else { return 0; }
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param parent
	 * @param child
	 * @return int
	 * @roseuid 3D6527FD036C -----------------------------------------------------------------------------------------------------
	 */
	public int getIndexOfChild(Object parent, Object child) {
		Bean p = (Bean)parent;
		if (p.getChildBeans() != null) { return p.getChildBeans().indexOf(child); }
		else { return -1; }
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return java.lang.Object
	 * @roseuid 3D6527FE0038 -----------------------------------------------------------------------------------------------------
	 */
	public Object getRoot() { return rootBean; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param node
	 * @return boolean
	 * @roseuid 3D6527FE004C -----------------------------------------------------------------------------------------------------
	 */
	public boolean isLeaf(Object node) { return getChildCount(node) == 0; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param l
	 * @roseuid 3D6527FE00D8 -----------------------------------------------------------------------------------------------------
	 */
	public void removeTreeModelListener(TreeModelListener l) { treeModelListeners.removeElement(l); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param path
	 * @param newValue
	 * @roseuid 3D6527FE018C -----------------------------------------------------------------------------------------------------
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
		System.out.println("*** valueForPathChanged : " + path + " --> " + newValue);
	}
}

