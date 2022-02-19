package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import controller.Controller;
import controller.DbConnector;
import view.DlgSelect;

public class TestSelectDialog {
	public static void main(String[] args) {
		Connection conn = DbConnector.getConnection();
		try {
			Statement st = conn.createStatement();
			String sql = "select * from Corps";
			ResultSet rs = st.executeQuery(sql);
			List<Map<String, Object>> list = 
					Controller.rsToMapList(rs);
			DlgSelect ds = new DlgSelect(list);
			ds.setVisible(true);
			System.out.println(ds.getMap());
			ds.dispose();
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}

}
