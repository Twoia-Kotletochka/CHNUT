package query;
import java.util.Map;

public class QueryCorps {

	public static String queryCreate() {
		String sql = "create table Corps ("
				+ "ID integer generated always as identity,"
				+ "corps varchar(30) default '' not null, " 
				+ "audience varchar(30) default '', "
				+ "furniture varchar(30) default '', "
				+ "primary key (ID))";
		return sql;
	}
	
	public static String queryGetAll() {
		return "select * from Corps";
	}
	
	public static String queryAdd(Map<String, Object> map) {
	        return String.format("insert into Corps(corps, audience, furniture) values('%s', '%s', '%s')", map.get("corps"), map.get("audience"), map.get("furniture"));
	 }

	 public static String queryEdit(Map<String, Object> map) {
	        return String.format("update Corps set  corps = '%s', audience = '%s', furniture ='%s'  where ID = %d", map.get("corps"), map.get("audience"), map.get("furniture"), map.get("id"));
	 }
	 
	  public static String queryDelById(int id) {
	        return "DELETE FROM Corps WHERE id = " + id;
	    }
}
