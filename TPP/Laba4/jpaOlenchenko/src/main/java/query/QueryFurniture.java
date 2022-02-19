package query;

import java.util.Map;

import controller.Controller;
import jpaOlenchenko.Audience;
import jpaOlenchenko.Furniture;

public class QueryFurniture {
	public static String queryCreate() {
		// This method was not realize, because tables already exist
		return null;
	}

	public static String queryGetAll() {
		return "select g.id, g.price, g.amount_of_furniture, g.weight, g.audience.number AS audience," + 
	" g.audience.id AS idAudience FROM jpaOlenchenko.Furniture g ORDER BY audience, g.price";
	}
	
	public static Furniture createObject(Map<String, Object> map) {
		Furniture grd = new Furniture();
		grd.setPrice((int) map.get("price"));
		grd.setAmount_of_furniture((int) map.get("amount_of_furniture"));
		grd.setWeight((int) map.get("weight"));

		int idAudience= (int) map.get("idAudience");
		Audience audience = (Audience) Controller.getObjectById("Audience", idAudience);
		grd.setAudience(audience);
		return grd;
	}
	
	public static Object editObject(Object obj, Map<String, Object> map) {
		Furniture grd = (Furniture) obj;
		grd.setPrice((int) map.get("price"));
		grd.setAmount_of_furniture((int) map.get("amount_of_furniture"));
		grd.setWeight((int) map.get("weight"));

		int idAudience= (int) map.get("idAudience");
		Audience audience = (Audience) Controller.getObjectById("Audience", idAudience);
		grd.setAudience(audience);
		return grd;
	}
	
	public static String queryDelById(int id) {
		return "DELETE FROM Furniture WHERE id = " + id;
	}
}