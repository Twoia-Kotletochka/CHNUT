package view;

import controller.Controller;
import controller.DbConnector;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Font;
import query.Query;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.Frame;

public class MainWindow
{
    private static Frame infoFrame;
    private JFrame frame;
    private JMenuBar menuBar;
    private JList<String> list;
    private JPanel buttonPanel;
    private JButton btnWriteQuery;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnQuery1;
    private JButton btnQuery2;
    private JScrollPane scrollPaneTextArea;
    private JTextArea textArea;
    private JLabel lblNewLabel;
    private JMenu menuAbout;
    private JMenuItem mntmDeveloper;
    private JMenu mnFile;
    private JScrollPane scrollPane;
    private DbTableView dbTableView;
    private JMenuItem mntmSetDbFullName;
    
    public static void main( String[] args) {
            
                try {
                     MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
    
    public MainWindow() {
        initialize();
    }
    
    private void initialize() {
        (frame = new JFrame()).setTitle("Project Commandant Olenchenko KIt-211");
        frame.setBounds(100, 100, 390, 316);
        frame.setDefaultCloseOperation(3);
        frame.setJMenuBar(getMenuBar());
        frame.getContentPane().add(getList(), "West");
        frame.getContentPane().add(getScrollPane(), "Center");
        frame.getContentPane().add(getLblNewLabel(), "North");
        frame.getContentPane().add(getScrollPaneTextArea(), "South");
        frame.getContentPane().add(getButtonPanel(), "East");
    }
    
    private Component getScrollPaneTextArea() {
        if (scrollPaneTextArea == null) {
            (scrollPaneTextArea = new JScrollPane()).setPreferredSize(new Dimension(2, 60));
            scrollPaneTextArea.setViewportView(getTextArea());
        }
        return scrollPaneTextArea;
    }
    
    private Component getLblNewLabel() {
        if (lblNewLabel == null) {
            (lblNewLabel = new JLabel("TableName")).setHorizontalAlignment(0);
        }
        return lblNewLabel;
    }
    
    public JMenuBar getMenuBar() {
        if (menuBar == null) {
            (menuBar = new JMenuBar()).add(getMnFile());
            menuBar.add(getMenuAbout());
        }
        return menuBar;
    }
    
    public JList getList() {
        if (list == null) {
            (list = new JList<String>()).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked( MouseEvent e) {
                   onMouseClickedList(e);
                }
            });
            list.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192)), new BevelBorder(1, null, null, null, null)));
            list.setModel(new AbstractListModel() {
                String[] values = { "Corps", "Audience", "Furniture" };
                

                public int getSize() {
                    return values.length;
                }
                
                @Override
                public Object getElementAt( int index) {
                    return values[index];
                }
            });
        }
        return list;
    }
    
    public JPanel getButtonPanel() {
        if (buttonPanel == null) {
            (buttonPanel = new JPanel()).setLayout(new GridLayout(6, 1, 0, 0));
            buttonPanel.add(getBtnAdd());
            buttonPanel.add(getBtnEdit());
            buttonPanel.add(getBtnDelete());
            buttonPanel.add(getBtnQuery1());
            buttonPanel.add(getBtnQuery2());
            buttonPanel.add(getBtnWriteQuery());
        }
        return buttonPanel;
    }
    
    private Component getBtnQuery1() {
        if (btnQuery1 == null) {
            (btnQuery1 = new JButton("Query1")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                    onBtnQuery1();
                }
            });
            btnQuery1.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnQuery1;
    }
    
    protected void onBtnQuery1() {
         DlgQuery1 dlg = new DlgQuery1();
        dlg.setVisible(true);
         String finish = dlg.getTeamName();
         int IdFieldNumber = dlg.getIdFieldNumber();
         String query = Query.query1(finish, IdFieldNumber);
        dbTableView.setDbTableModel(Controller.executeQuery(query));
    }
    
    private Component getBtnQuery2() {
        if (btnQuery2 == null) {
            (btnQuery2 = new JButton("Query2")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                    onBtnQuery2(e);
                }
            });
            btnQuery2.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnQuery2;
    }
    
    public JButton getBtnWriteQuery() {
        if (btnWriteQuery == null) {
            (btnWriteQuery = new JButton("Write Query")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                    onWriteQuery();
                }
            });
            btnWriteQuery.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnWriteQuery;
    }
    
    protected void onWriteQuery() {
         DlgWriteQuery dlg = new DlgWriteQuery();
        dlg.setVisible(true);
         String query = dlg.getQuery();
        dlg.dispose();
        if (query.toLowerCase().indexOf("select") >= 0) {
            dbTableView.setDbTableModel(Controller.executeQuery(query));
            textArea.setText(query);
        }
        else {
             int res = Controller.executeUpdate(query);
            textArea.setText("Updated " + res);
        }
    }
    
    public JButton getBtnAdd() {
        if (btnAdd == null) {
            (btnAdd = new JButton("Add")).addActionListener(new ActionListener() {

                public void actionPerformed( ActionEvent e) {
                	
                	  String tableName = getSelectedTable();
                     if (tableName == null) {
                         return;
                     }
                     try {
                         Class<?> clz = Class.forName("view.Dlg" + getSelectedTable());
                         IDlg dlg = (IDlg) clz.newInstance(); ///////////////////////////
                         dlg.setVisible(true);
                         Map<String, Object> map = dlg.getMap();
                         Controller.add(tableName, map);
                         getTextArea().setText("Added " + map.toString());
                         dbTableView.setDbTableModel(getDbData(tableName));
                     }
                     catch (Exception e2) {
                         e2.printStackTrace();
                     }
                }
            });
            btnAdd.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnAdd;
    }
    
    public JButton getBtnEdit() {
        if (btnEdit == null) {
            (btnEdit = new JButton("Edit")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                	 String tableName = getSelectedTable();
                     if (tableName == null) {
                         return;
                     }
                      Map<String, Object> map = dbTableView.getSelectedRowMap();
                     if (map == null) {
                         return;
                     }
                     try {
                         Class clz = Class.forName("view.Dlg" + getSelectedTable());
                         Constructor<IDlg> cns = clz.getConstructor(Map.class);
                         IDlg dlg = cns.newInstance(map);
                         dlg.setVisible(true);
                         Map<String, Object> newMap = dlg.getMap();
                         Controller.edit(tableName, newMap);
                         getTextArea().setText("OLd data " + map.toString());
                         dbTableView.setDbTableModel(getDbData(tableName));
                     }
                     catch (Exception e2) {
                         e2.printStackTrace();
                     }
                }
            });
            btnEdit.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnEdit;
    }
    
    public JButton getBtnDelete() {
        if (btnDelete == null) {
            (btnDelete = new JButton("Delete")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                  onBtnDelete(e);
                }
            });
            btnDelete.setBorder(new SoftBevelBorder(0, null, null, null, null));
        }
        return btnDelete;
    }
    
    public JTextArea getTextArea() {
        if (textArea == null) {
            (textArea = new JTextArea()).setFont(new Font("Monospaced", 0, 11));
            textArea.setTabSize(4);
            textArea.setText("Iformation area");
        }
        return textArea;
    }
    
    protected void onBtnQuery2( ActionEvent e) {
         String query = Query.query1("3", 201);
        dbTableView.setDbTableModel(Controller.executeQuery(query));
    }
    
    private String getSelectedTable() {
         String tableName = list.getSelectedValue();
        if (tableName == null) {
            JOptionPane.showMessageDialog(frame, "Table was not Selected.");
        }
        return tableName;
    }
    
    protected static void onMntmAbout( ActionEvent e) {
        getInfoFrame().setVisible(true);
    }
    
    private static Frame getInfoFrame() {
        if (MainWindow.infoFrame == null) {
            MainWindow.infoFrame = new InfoFrame();
        }
        return MainWindow.infoFrame;
    }
    
    protected void onMouseClickedList( MouseEvent e) {
        getTextArea().setText("");
         String tableName = getSelectedTable();
        if (tableName == null) {
            return;
        }
        if (!Controller.tableExist(tableName)) {
            Controller.createTable(tableName);
        }
         List<Map<String, Object>> model = getDbData(tableName);
        dbTableView.setDbTableModel(model);
    }
    
    private List<Map<String, Object>> getDbData( String tableName) {
        try {
             String queryClass = "query.Query" + tableName;
             Class<?> clz = Class.forName(queryClass);
             Method mtd = clz.getMethod("queryGetAll", (Class<?>[])new Class[0]);
             String sql = (String)mtd.invoke(null, new Object[0]);
             List<Map<String, Object>> model = Controller.executeQuery(sql);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public JMenu getMenuAbout() {
        if (menuAbout == null) {
            (menuAbout = new JMenu("About")).add(getMntmDeveloper());
        }
        return menuAbout;
    }
    
    public JMenuItem getMntmDeveloper() {
        if (mntmDeveloper == null) {
            (mntmDeveloper = new JMenuItem("DeveloperInfo")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                    onMntmDeveloper(e);
                }
            });
        }
        return mntmDeveloper;
    }
    
    protected void onMntmDeveloper( ActionEvent e) {
        getInfoFrame().setVisible(true);
    }
    
    //protected void onBtnAdd( ActionEvent e) {
    //   
    //}
    
 
    protected void onBtnDelete( ActionEvent e) {
         String tableName = getSelectedTable();
        if (tableName == null) {
            return;
        }
         Map<String, Object> map = dbTableView.getSelectedRowMap();
        if (map == null) {
            return;
        }
        Controller.delete(tableName, map);
        dbTableView.setDbTableModel(getDbData(tableName));
    }
    
    private JMenu getMnFile() {
        if (mnFile == null) {
            (mnFile = new JMenu("File")).add(getMntmSetDbFullName());
        }
        return mnFile;
    }
    
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            (scrollPane = new JScrollPane()).setViewportView(getDbTableView());
        }
        return scrollPane;
    }
    
    private DbTableView getDbTableView() {
        if (dbTableView == null) {
            dbTableView = new DbTableView();
        }
        return dbTableView;
    }
    
    private JMenuItem getMntmSetDbFullName() {
        if (mntmSetDbFullName == null) {
            (mntmSetDbFullName = new JMenuItem("SetDbFullName")).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e) {
                    onSetDbFullName();
                }
            });
        }
        return mntmSetDbFullName;
    }
    
    protected void onSetDbFullName() {
         String dbName = JOptionPane.showInputDialog("Enter DB full name", "d:/1/dbByv");
        DbConnector.setDbFullName(dbName);
    }
}
