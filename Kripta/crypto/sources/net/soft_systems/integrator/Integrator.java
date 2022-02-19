package net.soft_systems.integrator;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.*;
import net.soft_systems.crypto.*;
import net.soft_systems.integrator.event.*;
/**
 * Class which encapsulates User Interface Elements with Beans Hierarchy, provides
 * function for user interface.
 */
public class Integrator {
	/**
	 * @associates InitMenuListener
	 */
	private Vector initMenuListeners;
	/**
	 * @associates BeanListener
	 */
	private Vector beanListeners;
	BeanConfig beanConfig;
	protected JToolBar mainToolBar;
	public IntegratorConfig theIntegratorConfig;
	public Bean rootBean;
	protected JDesktopPane desktop;
	protected JFrame frame;
	protected JSplitPane splitPane, vertSplitPane;
	protected JTree tree;
	protected JPanel toolsPanel;
	static public Messages messages;
	private JComponent messagesPanel;
	static private JTextArea messagesArea = new JTextArea();
	public JFrame getParentFrame() { return frame; }
	/**
	 * Invokes registered <code>InitMenuListeners</code> method <code>init</code>
	 */
	protected void initMenus(JMenuBar menu) {
		Enumeration en = initMenuListeners.elements();
		while (en.hasMoreElements()) {
			InitMenuListener listener = (InitMenuListener)en.nextElement();
			listener.init(menu);
		}
	}
	/**
	 * Invokes registered <code>BeanListener</code> method <code>addBean</code>
	 */
	protected void doAddBean(Bean bean, Bean parent) {
		Enumeration en = beanListeners.elements();
		while (en.hasMoreElements()) {
			BeanListener listener = (BeanListener)en.nextElement();
			listener.addBean(bean, parent);
		}
	}
	/**
	 * Invokes registered <code>BeanListener</code> method <code>delBean</code>
	 */
	protected void doDelBean(Bean bean, Bean parent) {
		Enumeration en = beanListeners.elements();
		while (en.hasMoreElements()) {
			BeanListener listener = (BeanListener)en.nextElement();
			listener.delBean(bean, parent);
		}
	}
	/**
	 * adds InitMenuListener
	 */
	public void addInitMenuListener(InitMenuListener listener) { initMenuListeners.add(listener); }
	/**
	 * adds BeanListener
	 */
	public void addBeanListener(BeanListener listener) { beanListeners.add(listener); }
	public void removeBeanListener(BeanListener listener) { beanListeners.remove(listener); }
	public Integrator(String filename) {
		theIntegratorConfig = new IntegratorConfig();
		beanConfig = new BeanConfig();
		beanConfig.setFilename(filename);
		rootBean = beanConfig.read();
		messages = new Messages();
		initMenuListeners = new Vector();
		beanListeners = new Vector();
	}
	public Bean getRootBean() { return rootBean; }
	public void addToolBar(JToolBar tb) {
		if (tb != null) {
			Component[] comps = toolsPanel.getComponents();
			boolean exists = false;
			for (int i = 0; i < comps.length; i++) {
				if (comps[i] == tb) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				toolsPanel.add(tb);
				toolsPanel.repaint();
			}
		}
	}
	public void removeToolBar(JToolBar tb) {
		if (tb != null) {
			toolsPanel.remove(tb);
			toolsPanel.repaint();
		}
	}
	public void showEditFrame(EditableBean editableBean) {
		EditFrame editFrame = editableBean.getEditFrame();
		editFrame.addCloseListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditFrame frame = (EditFrame)e.getSource();
					removeToolBar(frame.getToolBar());
				}
			});
		if (addFrameToDesktop(editFrame)) { addToolBar(editFrame.getToolBar()); }
	}
	private class NewBeanActionListener implements ActionListener {
		private Bean parentBean;
		private Class selectedBeanClass;
		private TreePath parentPath;
		public NewBeanActionListener(Bean parentBean, Class selectedBeanClass, TreePath parentPath) {
			this.parentBean = parentBean;
			this.selectedBeanClass = selectedBeanClass;
			this.parentPath = parentPath;
		}
		public void actionPerformed(ActionEvent e) { addBean(selectedBeanClass, parentBean, true); }
	}
	private class DeleteBeanActionListener implements ActionListener {
		private Bean parentBean;
		private DynamicBean selectedBean;
		public DeleteBeanActionListener(Bean parentBean, DynamicBean selectedBean) {
			this.parentBean = parentBean;
			this.selectedBean = selectedBean;
		}
		public void actionPerformed(ActionEvent e) {
/*            int childIndex = ((BeanTreeModel)tree.getModel()).getIndexOfChild(parentBean, selectedBean);
            String id = selectedBean.getId();
            removeFrameFromDesktop(selectedBean);
            parentBean.removeBean(id);
            System.out.println("Removed bean of class " + selectedBean.getClass().getName() + " with id " + id);
            TreePath parentPath = tree.getSelectionPath().getParentPath();
            ((BeanTreeModel)tree.getModel()).fireRemoveBean(parentPath, childIndex);
            tree.setSelectionPath(parentPath);
*/
			delBean(selectedBean, parentBean, true);
		}
	}
	public void delBean(Bean bean, Bean parentBean, boolean select) {
		BeanTreeModel model = (BeanTreeModel)tree.getModel();
		TreePath path       = model.getPath(bean);
		TreePath parentPath = path.getParentPath();
		int index           = parentBean.getChildBeans().indexOf(bean);
		String id           = bean.getId();
		Class beanClass     = bean.getClass();
		removeFrameFromDesktop(bean);
		doDelBean(bean, parentBean);
		parentBean.removeBean(bean);
		Debug.notice("Удален элемент класса " + bean.getClass().getName() + " с идентификатором " + id);
		doAfterDelBean(id, parentBean, beanClass);
		model.fireRemoveBean(parentPath, index);
		if (select) { tree.setSelectionPath(parentPath); }
	}
	protected void updateTreeAdd(Bean selected) {
		BeanTreeModel model = (BeanTreeModel)tree.getModel();
		TreePath newPath = model.fireAddBean(selected);
	}
	protected void updateTreeAddSelect(Bean selected) {
		BeanTreeModel model = (BeanTreeModel)tree.getModel();
		TreePath newPath = model.fireAddBean(selected);
		tree.setSelectionPath(newPath);
	}
	/**
	 * Updates tree after bean editing.
	 * @param selected bean which was changed
	 */
	public void updateTreeEdit(Bean selected) {
		BeanTreeModel model = (BeanTreeModel)tree.getModel();
		TreePath newPath = model.getPath(selected);
		tree.setSelectionPath(newPath);
		tree.repaint();
	}
	/**
	 * If Bean implements <code>EditableBean</code> shows
	 * <code>EditFrame</code> for changing properties of that bean
	 * @param bean Bean that is intended to be changed
	 */
	public void tryEditBean(Bean bean) {
		if (EditableBean.class.isAssignableFrom(bean.getClass())) { showEditFrame((EditableBean)bean); }
	}
	/**
	 * Creates and adds new bean of class <code>beanClass</code> to parent
	 * Bean <code>parentBean</code> Updates tree and selects new Bean in tree
	 * @param beanClass Class of new bean
	 * @param parentBean Parent bean. New bean becomes child of <code>parentBean</code>
	 */
	public Bean addBean(Class beanClass, Bean parentBean, boolean select) {
		try {
			Bean newBean = (Bean)beanClass.newInstance();
			if (BaseNameEnabled.class.isAssignableFrom(newBean.getClass())) {
				BaseNameEnabled dynBean = (BaseNameEnabled)newBean;
				String id = BeanUtil.getUniqName(dynBean.getBaseName(), parentBean.getChildBeans());
				newBean.setId(id);
			}
			addBean(newBean, parentBean, select);
			return newBean;
		}
		catch (InstantiationException ex) { Debug.critical("Ошибка создания объекта: " + ex.getMessage()); }
		catch (IllegalAccessException ex) { Debug.critical("Ошибка создания объекта: " + ex.getMessage()); }
		return null;
	}
	public void addBean(Bean bean, Bean parentBean, boolean select) {
		doBeforeAddBean(bean, parentBean);
		parentBean.addBean(bean);
		Debug.notice("Добавлен новый класс " + bean.getClass().getName() + " с идентификатором " + bean.getId());
		if (select) {
			updateTreeAddSelect(bean);
			tryEditBean(bean);
		}
		else { updateTreeAdd(bean); }
		doAddBean(bean, parentBean);
	}
	private class ConfFilter extends FileFilter {
		public String getExtension(File f) {
			String ext = null;
			String s   = f.getName();
			int i      = s.lastIndexOf('.');
			if (i > 0 && i < s.length() - 1) { ext = s.substring(i + 1).toLowerCase(); }
			return ext;
		}
		public boolean accept(File f) {
			if (f.isDirectory()) { return true; }
			String extension = getExtension(f);
			if (extension != null) {
				if (extension.equals("xml")) { return true; }
				else { return false; }
			}
			return false;
		}
		//The description of this filter
		public String getDescription() { return "Конфигурация (xml)"; }
	}
	protected void open() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(beanConfig.getConfigDir()));
		ConfFilter filter = new ConfFilter();
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.getParentFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			beanConfig = new BeanConfig();
			beanConfig.setFilename(chooser.getSelectedFile().getAbsolutePath());
			Bean oldRoot = rootBean;
			rootBean = beanConfig.read();
			Run.rootBean = rootBean;
			Run.infoSystem = new InfoSystem();
			Run.infoSystem.init();
			Run.infoSystem.evalTotalRisks();
			BeanTreeModel m = (BeanTreeModel)tree.getModel();
			m.fireTreeStructureChanged(rootBean);
		}
	}
	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu(messages.getMessage("file-menu"));
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItem;
		menuItem = new JMenuItem(messages.getMessage("exit-menu"));
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						frame.setVisible(false);
						finish();
					}
					finally { System.exit(0); }
				}
			});
		menu.add(menuItem);
		menuBar.add(menu);
		initMenus(menuBar);
		menu = new JMenu(messages.getMessage("window-menu"));
		menu.setMnemonic(KeyEvent.VK_F);
		menuItem = new JMenuItem(messages.getMessage("icon-menu"));
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { iconifyAllFrames(); }
			});
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}
	public JMenuBar getMenuBar() { return frame.getJMenuBar(); }
	public void addKeyListener(KeyListener listener) { frame.addKeyListener(listener); }
	public void removeKeyListener(KeyListener listener) { frame.removeKeyListener(listener); }
	static public void addMessage(String message) {
		messagesArea.append(message);
		messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
	}
	public JComponent getMessagesPanel() {
		messagesArea.setEditable(false);
		JScrollPane panel = new JScrollPane(messagesArea);
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(100, 10));
		panel.setMinimumSize(new Dimension(100, 10));
		panel.getViewport().move(0, messagesArea.getHeight());
		return panel;
	}
	public void start(String[] args) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(theIntegratorConfig.getLeft(), theIntegratorConfig.getTop(), theIntegratorConfig.getWidth(),
			theIntegratorConfig.getHeight());
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					finish();
					frame.setVisible(false);
					System.exit(0);
				}
			});
		desktop = new JDesktopPane();
		frame.setJMenuBar(createMenuBar());
		desktop.putClientProperty("JDesktopPane.dragMode", "outline");
		tree = new JTree(new BeanTreeModel(rootBean));
		tree.setExpandsSelectedPaths(true);
		tree.setAutoscrolls(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getClickCount() > 1) {
						// showing edit frame
						TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
						if (selPath != null) {
							tree.setSelectionPath(selPath);
							Bean nodeInfo = (Bean)selPath.getLastPathComponent();
							if (EditableBean.class.isAssignableFrom(nodeInfo.getClass())) {
								showEditFrame((EditableBean)nodeInfo);
							}
						}
					}
					else if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
						// showing popup menu
						TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
						if (selPath != null) {
							tree.setSelectionPath(selPath);
							Bean nodeInfo = (Bean)selPath.getLastPathComponent();
							Bean parentBean;
							TreePath parentPath = selPath.getParentPath();
							if (parentPath != null) { parentBean = (Bean)parentPath.getLastPathComponent(); }
							else { parentBean = null; }
							if (nodeInfo == null) { return; }
							//if (BeanUtil.isImplementing(nodeInfo.getClass(),
							// PopupMenuBean.class))
							if (PopupMenuBean.class.isAssignableFrom(nodeInfo.getClass())) {
								JPopupMenu popupMenu = new JPopupMenu();
								PopupMenuBean popupBean = (PopupMenuBean)nodeInfo;
								//if (BeanUtil.isImplementing(nodeInfo.getClass(),ParentDynamicBean.class))
								if (ParentDynamicBean.class.isAssignableFrom(nodeInfo.getClass())) {
									ParentDynamicBean parentDynBean = (ParentDynamicBean)nodeInfo;
									JMenuItem menuItem = new JMenuItem(messages.getMessage("new-object") + " " +
										parentDynBean.getChildName());
									menuItem.addActionListener(
										new NewBeanActionListener(parentDynBean, parentDynBean.getChildClass(),
										tree.getSelectionPath()));
									popupMenu.add(menuItem);
								}
								//if (parentBean!=null &&
								// BeanUtil.isImplementing(nodeInfo.getClass(),
								// DynamicBean.class))
								if (parentBean != null && DynamicBean.class.isAssignableFrom(nodeInfo.getClass())) {
									DynamicBean dynBean = (DynamicBean)nodeInfo;
									JMenuItem menuItem = new JMenuItem(messages.getMessage("new"));
									menuItem.addActionListener(
										new NewBeanActionListener(parentBean, dynBean.getClass(),
										tree.getSelectionPath().getParentPath()));
									popupMenu.add(menuItem);
									menuItem = new JMenuItem(messages.getMessage("del"));
									menuItem.addActionListener(new DeleteBeanActionListener(parentBean, dynBean));
									popupMenu.add(menuItem);
								}
								popupBean.initMenu(popupMenu);
								popupMenu.show(tree, e.getX(), e.getY());
							}
						}
					}
				}
			});
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(100, 100));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(treeView);
		splitPane.setRightComponent(desktop);
		toolsPanel = new JPanel();
		mainToolBar = new JToolBar();
		toolsPanel.add(mainToolBar);
		Dimension minimumSize = new Dimension(200, 200);
		treeView.setMinimumSize(minimumSize);
		desktop.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(theIntegratorConfig.getSplitPos());
		splitPane.setPreferredSize(new Dimension(300, 200));
		frame.getContentPane().add(toolsPanel, BorderLayout.NORTH);
		messagesPanel = getMessagesPanel();
		vertSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertSplitPane.setTopComponent(splitPane);
		vertSplitPane.setBottomComponent(messagesPanel);
		vertSplitPane.setDividerLocation(theIntegratorConfig.getVerticalSplitPos(frame.getHeight() -
			toolsPanel.getHeight() - 300));
		frame.getContentPane().add(vertSplitPane, BorderLayout.CENTER);
		frame.setVisible(true);
		Debug.notice("Конфигурация загружена");
	}
	public boolean addFrameToDesktop(JInternalFrame frame) {
		frame.setVisible(true);
		boolean exists = false;
		JInternalFrame[] frames = desktop.getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].equals(frame)) { exists = true; }
		}
		if (!exists) { desktop.add(frame); }
		desktop.getDesktopManager().deiconifyFrame(frame);
		try { frame.setSelected(true); }
		catch (PropertyVetoException ex) { }
		return !exists;
	}
	public void removeFrameFromDesktop(Bean bean) {
		JInternalFrame[] frames = desktop.getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			if (EditFrame.class.isAssignableFrom(frames[i].getClass())) {
				EditFrame editFrame = (EditFrame)frames[i];
				if (editFrame.getBean() == bean) {
					editFrame.setVisible(false);
					removeToolBar(editFrame.getToolBar());
					editFrame = null;
				}
			}
		}
	}
	public void removeFrameFromDesktop(EditFrame editFrame) {
		editFrame.setVisible(false);
		removeToolBar(editFrame.getToolBar());
	}
	public void iconifyAllFrames() {
		JInternalFrame[] frames = desktop.getAllFrames();
		for (int i = 0; i < frames.length; i++) { desktop.getDesktopManager().iconifyFrame(frames[i]); }
	}
	public void finish() {
		theIntegratorConfig.setSplitPos(splitPane.getDividerLocation());
		theIntegratorConfig.setVerticalSplitPos(vertSplitPane.getDividerLocation());
		theIntegratorConfig.setLeft(frame.getBounds().x);
		theIntegratorConfig.setTop(frame.getBounds().y);
		theIntegratorConfig.setWidth(frame.getBounds().width);
		theIntegratorConfig.setHeight(frame.getBounds().height);
		theIntegratorConfig.save();
		beanConfig.store(rootBean);
	}
	public void saveBeans() { }
	public JFrame getFrame() { return frame; }
	public void doBeforeAddBean(Bean bean, Bean parent) {
		Enumeration en = beanListeners.elements();
		while (en.hasMoreElements()) {
			BeanListener listener = (BeanListener)en.nextElement();
			listener.beforeAddBean(bean, parent);
		}
	}
	public void doAfterDelBean(String beanId, Bean parentBean, Class beanClass) {
		Enumeration en = beanListeners.elements();
		while (en.hasMoreElements()) {
			BeanListener listener = (BeanListener)en.nextElement();
			listener.afterDelBean(beanId, parentBean, beanClass);
		}
	}
}

