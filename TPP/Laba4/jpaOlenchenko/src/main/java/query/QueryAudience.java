package query;

import java.util.Map;
import controller.Controller;
import jpaOlenchenko.Audience;
import jpaOlenchenko.Corps;

public class QueryAudience {
	public static String queryCreate() {
		// This method was not realize, because tables already exist
		return null;
	}

	public static String queryGetAll() {
		return "select g.id, g.number, g.area, g.floor," + " g.corps.corps AS corps," + " g.corps.id AS idCorps"
				+ " FROM jpaOlenchenko.Audience g " + " ORDER BY corps, g.number";
	}

	public static Audience createObject(Map<String, Object> map) {
		Audience grd = new Audience();
		grd.setArea((String) map.get("area"));
		grd.setFloor((String) map.get("floor"));
		grd.setNumber((String) map.get("number"));

		int idCorps = (int) map.get("idCorps");
		Corps corps = (Corps) Controller.getObjectById("Corps", idCorps);
		grd.setCorps(corps);
		return grd;
	}

	public static Object editObject(Object obj, Map<String, Object> map) {
		Audience grd = (Audience) obj;
		grd.setArea((String) map.get("area"));
		grd.setFloor((String) map.get("floor"));
		grd.setNumber((String) map.get("number"));

		int idCorps = (int) map.get("idCorps");
		Corps corps = (Corps) Controller.getObjectById("Corps", idCorps);
		grd.setCorps(corps);
		return grd;
	}

	
	public static String queryDelById(int id) {
		return "DELETE FROM Audience WHERE id = " + id;
	}
}
