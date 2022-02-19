package query;
import java.util.Map;
public class QueryAudience {
		//Запити на створення таблиці БД
		public static String queryCreate() {	
			String sql ="create table Audience ("
					+ "ID int generated always as identity,"
					+ "number varchar(30) default '' not null,"
					+ "area varchar(30) default '' not null," 
					+ "floor varchar(30) default '' not null," 
					 
					
					+ "idCorps int constraint FK_GRAD_CTH "
					+ "references Corps on delete cascade, " 
					+ "primary key (ID))";
			return sql;
		}
		
	
		
public static String queryGetAll() {
return "select Audience.ID, Audience.number, Audience.area, Audience.floor, Corps.corps AS Corps,  Corps.ID AS idCorps from Audience, Corps WHERE Corps.ID=Audience.idCorps ORDER BY Corps, Audience.number";
}

public static String queryAdd( Map<String, Object> map) {
return String.format("insert into Audience(number, area, floor, idCorps) values('%s', '%s', '%s', %d)", map.get("number"), map.get("area"), map.get("floor"), map.get("idCorps"));
}

public static String queryEdit( Map<String, Object> map) {
    return String.format("update Audience set  number = '%s', area = '%s', floor = '%s', idCorps = %d where ID = %d", map.get("number"), map.get("area"), map.get("floor"), map.get("idCorps"), map.get("id"));
}
public static String queryDelById( int id) {
    return "DELETE FROM Audience WHERE ID = " + id;
}
}
