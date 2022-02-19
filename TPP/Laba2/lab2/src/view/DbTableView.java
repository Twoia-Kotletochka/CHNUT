package view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DbTableView extends JTable {
	public DbTableView() {
	}
	
	public  void setDbTableModel(List<Map<String, Object>> mapList) {
		Object[][] values = null;
		Object[] header = new Object[]{"EmptyTable"};
		if(mapList.size() > 0) {
			header = mapList.get(0).keySet().toArray(header);
			values = new Object[mapList.size()][header.length];
			int i = 0;
			for (Map<String, Object> map : mapList) 
				values[i++] = map.values().toArray();
		}
		setModel(new DefaultTableModel(values, header));
	}

	public Map<String, Object> getSelectedRowMap() {
		int row = getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Row is not selected");
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<>(); 
		for (int i = 0; i < getModel().getColumnCount(); i++) {
			String key = getModel().getColumnName(i);
			Object value = getModel().getValueAt(row, i);
			map.put(key, value);
		}
		return map;
	}
}
