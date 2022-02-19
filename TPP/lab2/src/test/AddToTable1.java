package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DbConnector;

public class AddToTable1 {
	public static void main(String[] args) {
		Connection conn = DbConnector.getConnection();
		String query = "insert into Corps(corps, audience, furniture)"
				+ " values(?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1,"1");
			pst.setString(2,"123");
			pst.setString(3,"66");
			pst.executeUpdate();

			pst.setString(1,"1");
			pst.setString(2, "125");
			pst.setString(3,"9");
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
