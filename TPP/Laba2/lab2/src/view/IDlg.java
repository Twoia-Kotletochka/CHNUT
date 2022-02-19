package view;

import java.util.Map;

public interface IDlg {
	  Map<String, Object> getMap();
	  void clear();
	  void setVisible(boolean b);
	  void dispose();
	}

