package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import controller.DbConnector;
import query.QueryCorps;
import query.QueryAudience;

public class CreateTable1 {
	public static void main(String[] args) {
		try {
			Connection conn = DbConnector.getConnection();
			Statement st = conn.createStatement();
			String query = QueryCorps.queryCreate();
			st.executeUpdate(query);
			
			query = QueryAudience.queryCreate();
			st.executeUpdate(query);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
}
