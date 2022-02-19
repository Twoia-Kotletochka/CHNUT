package test;

import java.sql.Connection;

import controller.DbConnector;

public class CreateDB {
	public static void main(String[] args) {	
		Connection conn = DbConnector.getConnection();
		System.out.println(conn);
	}

}
