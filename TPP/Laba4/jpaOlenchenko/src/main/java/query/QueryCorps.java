package query;

import java.util.Map;
import jpaOlenchenko.Corps;

public class QueryCorps {

	public static String queryCreate() {
		// This method was not realize, because tables already exist
		return null;
	}

	public static String queryGetAll() {
		return "select c.id, c.audience, c.corps, c.furniture from Corps c";
	}

	public static Corps createObject(Map<String, Object> map) {
		Corps grd = new Corps();
		grd.setAudience((String) map.get("audience"));
		grd.setCorps((String) map.get("corps"));
		grd.setFurniture((String) map.get("furniture"));
		return grd;
	}

	public static Object editObject(Object obj, Map<String, Object> map) {
		Corps grd = (Corps) obj;
		grd.setAudience((String) map.get("audience"));
		grd.setCorps((String) map.get("corps"));
		grd.setFurniture((String) map.get("furniture"));
		return grd;
	}

	public static String queryDelById(int id) {
		return "DELETE FROM Corps WHERE id = " + id;
	}
}
