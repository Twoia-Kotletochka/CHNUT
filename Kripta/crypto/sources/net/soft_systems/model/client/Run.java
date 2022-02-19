/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.client;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.*;
public class Run {
	JFrame frame;
	JTextField textField;
	JComboBox comboBox;
	ModelClient client;
	String processId;
	JTextArea messagesArea = new JTextArea();
	ClientRecieverImpl clientReciever;
	public Run() {
		try {
			clientReciever = new ClientRecieverImpl();
			clientReciever.setMessagesArea(messagesArea);
		}
		catch (RemoteException ex) { Debug.error("Ошибка при удаленном вызове " + ex.getMessage()); }
	}
	protected Vector getClientMethodNames() {
		if (client != null) { return client.getMethodNames(); }
		else { return new Vector(); }
	}
	protected void invokeMethod(String methodName) {
		try {
			if (client != null) { client.invokeMethod(methodName); }
		}
		catch (Exception ex) { Debug.error("Ошибка при удаленном вызове метода " + ex.getMessage()); }
	}
	private class ProcessFrame extends JFrame {
		public ProcessFrame() {
			super();
			init();
		}
		private JList methodsList;
		protected JPanel getProcessPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			panel.setPreferredSize(new Dimension(160, 200));
			panel.setMinimumSize(new Dimension(80, 200));
			//method panel
			JPanel methodPanel = new JPanel();
			methodPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Методы"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			panel.add(methodPanel,
				new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1.0, 1.0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(7, 7, 3, 7), 0, 0));
			methodPanel.setLayout(new GridBagLayout());
			methodsList = new JList(getClientMethodNames());
			JScrollPane scrollPane = new JScrollPane(methodsList);
			scrollPane.setPreferredSize(new Dimension(120, 100));
			scrollPane.setMinimumSize(new Dimension(120, 100));
			methodPanel.add(scrollPane,
				new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 7, 3, 7), 0, 0));
			JButton invokeButton = new JButton("Запустить");
			invokeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (methodsList.getSelectedValue() != null)
							{ invokeMethod((String)methodsList.getSelectedValue()); }
					}
				});
			methodPanel.add(invokeButton,
				new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 7, 3, 7), 0, 0));
			// Finish button
			JButton button = new JButton("Завершить");
			panel.add(button,
				new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1.0, 1.0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.NONE, new Insets(0, 7, 3, 3), 0, 0));
			button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) { unregisterProcess(); }
				});
			// Create button
			button = new JButton("Создать");
			panel.add(button,
				new GridBagConstraints(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE,
				GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(0, 3, 3, 7), 0, 0));
			button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) { registerProcess(); }
				});
			return panel;
		}
		protected JPanel getMessagesPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			messagesArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(messagesArea);
			scrollPane.setAutoscrolls(true);
			scrollPane.setPreferredSize(new Dimension(100, 100));
			scrollPane.setMinimumSize(new Dimension(100, 100));
			panel.add(new JLabel("Сообщения"),
				new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 7, 3, 7), 0, 0));
			panel.add(scrollPane,
				new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 7, 7, 7), 0, 0));
			return panel;
		}
		protected void init() {
			addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						closeFrame((JFrame)e.getSource());
						exit();
					}
				});
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitPane.setLeftComponent(getProcessPanel());
			splitPane.setRightComponent(getMessagesPanel());
			getContentPane().add(splitPane);
			setTitle("Процесс " + processId);
			setSize(600, 400);
		}
	}
	public void closeFrame(JFrame frame) {
		frame.dispose();
		textField = null;
		frame = null;
	}
	public void unregisterProcess() {
		try {
			if (client != null && clientReciever != null && processId != null) {
				client.unregisterProcess(processId, clientReciever);
			}
		}
		catch (Exception ex) { Debug.error("Ошибка при отключении клиента от процесса " + ex.getMessage()); }
	}
	public void exit() {
		unregisterProcess();
		System.exit(0);
	}
	protected void showProcessFrame() {
		ProcessFrame processFrame = new ProcessFrame();
		processFrame.show();
	}
	public void registerProcess() {
		try { client.registerProcess(processId, clientReciever); }
		catch (Exception ex) { Debug.error("Ошибка при подключении к процессу " + ex.getMessage()); }
	}
	protected void onSelectProcess(JComboBox combo) {
		if (combo.getSelectedItem() != null) {
			try {
				processId = (String)combo.getSelectedItem();
				registerProcess();
				closeFrame(frame);
				showProcessFrame();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				Debug.error("Ошибка : " + ex.getMessage());
			}
		}
	}
	protected void onEnteredBindName(String bindName) {
		closeFrame(frame);
		try {
			client = ModelClient.start(bindName);
			if (client != null) {
				Vector processIds = client.getProcessIds();
				askProcessId(processIds);
				// client.registerProcess("P1");
/*				client.registerProcess("P1");
				Vector methodNames = client.getProcessModel().getMethodNames();
				Debug.notice("" + methodNames.size());
				Enumeration enMethods = methodNames.elements();
				String method;
				Debug.debug("" + client.getProcessModel().getClass());
				Method methods[] = client.getProcessModel().getClass().getMethods();
				for (int i = 0; i < methods.length; i++)
				{
					Method meth = (Method)methods[i];
					Debug.notice("Method " + meth);
				}
				while (enMethods.hasMoreElements())
				{
					method = (String)enMethods.nextElement();
					Debug.notice("Invoking " + method);
					if (client.invokeMethod(method))
					{
						Debug.notice("Method " + method + " was successfully invoked");
					}
					else
					{
						Debug.notice("Error. Method " + method + " was not invoked");
					}
				}
				*/
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			Debug.error("Ошибка: " + ex.getMessage());
		}
	}
	public void askServerBindName(String defaultName) {
		frame = new JFrame();
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					closeFrame(frame);
					exit();
				}
			});
		frame.setTitle("Путь к rmi объекту сервера");
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());
		panel.add(new JLabel("Сервер"),
			new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 0.0, 0.0,
			GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 7, 3, 7), 1, 0));
		textField = new JTextField(defaultName);
		panel.add(textField,
			new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 0.0, 0.0,
			GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 7, 7), 0, 0));
		JButton button = new JButton("OK");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { onEnteredBindName(textField.getText()); }
			});
		panel.add(button,
			new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 0.0, 0.0,
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 7, 7, 3), 0, 0));
		button = new JButton("Закрыть");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeFrame(frame);
					exit();
				}
			});
		panel.add(button,
			new GridBagConstraints(1, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.0, 0.0,
			GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 7, 7), 0, 0));
		frame.setSize(200, 100);
		frame.show();
	}
	public void askProcessId(Vector processIds) {
		frame = new JFrame();
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					closeFrame(frame);
					exit();
				}
			});
		frame.setTitle("Выбор процесса");
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());
		panel.add(new JLabel("Процесс"),
			new GridBagConstraints(0, 0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 0.0, 0.0,
			GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 7, 3, 7), 1, 0));
		comboBox = new JComboBox(processIds);
		panel.add(comboBox,
			new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, GridBagConstraints.RELATIVE, 0.0, 0.0,
			GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 7, 7), 0, 0));
		JButton button = new JButton("OK");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { onSelectProcess(comboBox); }
			});
		panel.add(button,
			new GridBagConstraints(0, 1, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 0.0, 0.0,
			GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 7, 7, 3), 0, 0));
		button = new JButton("Закрыть");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeFrame(frame);
					exit();
				}
			});
		panel.add(button,
			new GridBagConstraints(1, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.0, 0.0,
			GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 7, 7), 0, 0));
		frame.setSize(200, 100);
		frame.show();
	}
	public void start() {
		String url = "rmi://127.0.0.1:1234/";
		askServerBindName(url);
	}
	static public void main(String[] args) { new Run().start(); }
}

