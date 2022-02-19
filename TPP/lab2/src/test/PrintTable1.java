package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DbConnector;

public class PrintTable1 {
	public static void main(String[] args) {
		Connection conn = DbConnector.getConnection();
		try {
			Statement st = conn.createStatement();
			String query = "select * from Corps";
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				System.out.println(rs.getString(1) + "  " 
						+ rs.getString("corps") + "  "
						+ rs.getString("audience")+ "  "
						+ rs.getString("furniture"));
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}

}
