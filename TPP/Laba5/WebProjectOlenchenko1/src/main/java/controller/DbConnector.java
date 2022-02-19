package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnector {
	private static String dbFullName = "dbOlenchenko";
	private static String url = "jdbc:derby:" + dbFullName;;
	private static Properties prop = new Properties();
	static private  Connection conn;
	static{		
		prop.setProperty("user", "Olenchenko");
		prop.setProperty("password","1212");
		prop.setProperty("create", "true");
	}
	public static Connection getConnection(){
		if(conn == null)
			try {
				//This string need when class is using by server 
				//If driver was download nothing to do 
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (ClassNotFoundException e1) {} 
			try {	
				conn = DriverManager.getConnection(url, prop);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage() +
						" or May by connection use another project?");
				e.printStackTrace();
			}
		return conn;
	}
	
	public static String getDbFullName() {
		return dbFullName;
	}
	public static void setDbFullName(String dbFullName) {
		DbConnector.url = "jdbc:derby:" + dbFullName;
	}
	public static void setPropertyUser(String str) {
		prop.setProperty("user", str);
	}
	public static void setPropertyPassword(String str) {
		prop.setProperty("password", str);
	}
	public static void setPropertyCreate(String str) {
		prop.setProperty("create", str);
	}
}
