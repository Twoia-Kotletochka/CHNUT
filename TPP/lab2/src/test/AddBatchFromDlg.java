package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import controller.DbConnector;
import view.DlgCorps;

public class AddBatchFromDlg {
	public static void main(String[] args){
		try {
			Connection conn = DbConnector.getConnection();
			String query ="insert into Corps(corps, audience, furniture) "
					+ "values(?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query );	
			DlgCorps dlg = new DlgCorps();
			while(true){
				dlg.clear();
				dlg.setVisible(true);
				Map<String, Object> map = dlg.getMap();
				if(map == null) break;
				pst.setString(1,(String) map.get("corps"));
				pst.setString(2,(String) map.get("audience"));
				pst.setString(3,(String) map.get("furniture"));
				pst.addBatch();
			}
			dlg.dispose();
			pst.executeBatch();		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		PrintTable1.main(null);
	}

}
