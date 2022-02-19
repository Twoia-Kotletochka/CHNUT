package controller;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.sql.ResultSet;

public class Controller
{
    public static List<Map<String, Object>> rsToMapList( ResultSet rs) {
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
             ResultSetMetaData metadata = rs.getMetaData();
             int columnCount = metadata.getColumnCount();
            while (rs.next()) {
                 Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= columnCount; ++i) {
                     String columnName = metadata.getColumnName(i);
                    map.put(columnName.toLowerCase(), rs.getObject(i));
                }
                list.add(map);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static boolean tableExist( String tableName) {
        try {
             Connection conn = DbConnector.getConnection();
             DatabaseMetaData md = conn.getMetaData();
             String name = tableName.toUpperCase();
            return md.getTables(null, null, name, null).next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Map<String, Object>> executeQuery( String query) {
        System.out.println(query);
        List<Map<String, Object>> list = null;
        try {
             Connection conn = DbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
            list = rsToMapList(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static int executeUpdate( String query) {
        System.out.println(query);
        try {
             Connection conn = DbConnector.getConnection();
             Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static int createTable( String tableName) {
        try {
             String queryClass = "query.Query" + tableName;
             Class<?> clz = Class.forName(queryClass);
             Method mtd = clz.getMethod("queryCreate", (Class<?>[])new Class[0]);
             String sql = (String)mtd.invoke(null, new Object[0]);
             int n = executeUpdate(sql);
            return n;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }
    
    public static void add( String tableName,  Map<String, Object> map) {
        try {
             String queryClass = "query.Query" + tableName;
             Class<?> clz = Class.forName(queryClass);
             Method mtd = clz.getMethod("queryAdd", Map.class);
             String sql = (String)mtd.invoke(null, map);
            executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void edit( String tableName,  Map<String, Object> map) {
        try {
             String queryClass = "query.Query" + tableName;
             Class<?> clz = Class.forName(queryClass);
             Method mtd = clz.getMethod("queryEdit", Map.class);
             String sql = (String)mtd.invoke(null, map);
            executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void delete( String tableName,  Map<String, Object> map) {
        try {
             String queryClass = "query.Query" + tableName;
             Class<?> clz = Class.forName(queryClass);
             Method mtd = clz.getMethod("queryDelById", Integer.TYPE);
             int id = (int) map.get("id");
             String sql = (String)mtd.invoke(null, id);
            executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

