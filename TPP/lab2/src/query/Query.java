package query;

public class Query {
	public static String query1(String floor, int idCorps) {
		return String.format("select area, number from Audience where floor = '%s' and idCorps = %d ",floor, idCorps);
	}

}
