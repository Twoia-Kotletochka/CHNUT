package gg;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jtLab1.JarClassLoader;

public class ClassLoader {

	private JFrame frame;
	private JMenuItem mntmDeclaredFields;
	private Class<?> clz;

	public ClassLoader() {
		initialize();
	}

	String getFileName(String filter) {
		FileDialog fileDialog = new FileDialog(frame);
		fileDialog.setMode(FileDialog.LOAD);
		fileDialog.setFile(filter);
		fileDialog.setVisible(true);
		String dr = fileDialog.getDirectory();
		String fn = fileDialog.getFile();
		if (dr == null || fn == null)
			return null;
		return dr + fn;
	}

	@SuppressWarnings("resource")
	protected void onSelectJar(JScrollPane scrollPane, JTextArea textArea) {
		String fileName = getFileName("*.jar");
		// JarFile object creation
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(fileName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Problem with .jar file");
			e.printStackTrace();
			return;
		}
		// ClassLoader creation
		JarClassLoader loader = new JarClassLoader(fileName);
		// Extract classes from jar-file
		Enumeration<JarEntry> enm = jarFile.entries();
		clz = null;

		textArea.setText("Entry list from jar " + fileName + "\n");
		// We are only interested in one class from jar
		String searchName = "lab_1/DlgCommand.class";
		while (enm.hasMoreElements()) {
			JarEntry entry = enm.nextElement();
			String name = entry.getName();
			textArea.append(name + "\n");
			if (name.equals(searchName)) {
				// File name correction.
				String cs = name.replace('/', '.');
				cs = cs.substring(0, cs.lastIndexOf(".class"));
				try {
					// Class loading
					clz = loader.findClass(cs);
					textArea.append(clz + " was loaded\n");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassLoader window = new ClassLoader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	public void DialogOperation(JScrollPane scrollPane, JTextArea textArea) {

		Method mth;
		Object dlg;
		try {
			dlg = clz.newInstance();
			mth = clz.getMethod("setVisible");
			mth.invoke(dlg);
			mth = clz.getMethod("getMap");

			textArea.setText("Результат виклику метода getMap()\n");
			textArea.append(mth.invoke(dlg).toString() + "\n");

		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 607, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 573, 235);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 436, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnFIle = new JMenu("File");
		menuBar.add(mnFIle);

		JMenuItem mntmSelectJar = new JMenuItem("SelectJar");
		mntmSelectJar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSelectJar(scrollPane, textArea);
			}
		});
		mnFIle.add(mntmSelectJar);

		JMenu mnClassTest = new JMenu("Classtest");
		menuBar.add(mnClassTest);

		JMenuItem mntmFileds = new JMenuItem("Fileds");
		mntmFileds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Method for submenu Fileds
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Fileds\n");
				for (Field xxx : clz.getFields())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmFileds);

		mntmDeclaredFields = new JMenuItem("DeclaredFields");
		mntmDeclaredFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Declared Fields\n");
				for (Field xxx : clz.getDeclaredFields())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmDeclaredFields);

		JMenuItem mntmMethods = new JMenuItem("Methods");
		mntmMethods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Methods\n");
				for (Method xxx : clz.getMethods())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmMethods);

		JMenuItem mntmDeclaredMethods = new JMenuItem("DeclaredMethods");
		mntmDeclaredMethods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Declared Methods\n");
				for (Method xxx : clz.getDeclaredMethods())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmDeclaredMethods);

		JMenuItem mntmConstructors = new JMenuItem("Constructors");
		mntmConstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Constructors\n");
				for (Constructor<?> xxx : clz.getConstructors())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmConstructors);

		JMenuItem mntmDeclaredConstructors = new JMenuItem("DeclaredConstructors"); // Method for submenu
																					// DeclaredConstructor
		mntmDeclaredConstructors.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				textArea.setText("Class " + clz.toGenericString() + "\n");
				textArea.append("Declared Constructors\n");
				for (Constructor<?> xxx : clz.getDeclaredConstructors())
					textArea.append(xxx.toGenericString() + "\n");
			}
		});
		mnClassTest.add(mntmDeclaredConstructors);

		JMenuItem mntmDialogOperation = new JMenuItem("DialogOperation");
		mntmDialogOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogOperation(scrollPane, textArea);
			}
		});
		mnClassTest.add(mntmDialogOperation);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmDeveloperinfo = new JMenuItem("Developerinfo");
		mntmDeveloperinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				new Info().setVisible(true);
			}
		});
		mnAbout.add(mntmDeveloperinfo);
	}
}
