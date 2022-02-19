package controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;

public class Controller {

	private static EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("jpaOlenchenko");

	public static boolean tableExist(String tableName) {
		// If table exist, it is possible to count number of records.
		// If table does not exist, exception occurs.
		String testQuery = String.format(
				"select count(*) from %s ", tableName);
		EntityManager em = emf.createEntityManager();	
		Query q = em.createNativeQuery(testQuery);
		try {
			q.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}finally {
			em.close();
		}
	}

	public static List<Map<String,Object>> executeQuery(String jpql) {		//Output for debuging
		System.out.println(jpql);
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(jpql);
		query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);	
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = query.getResultList();
		em.close();
		return list;
	}

	public static void createTable(String queryClass) {
		//This method was not realize, because tables already exist
	}

	public static void add(String tableName,Map<String,Object> map) {
		Object obj = null;
		try {
			String queryClass  = "query.Query" + tableName;
			Class<?> clz = Class.forName(queryClass  );
			Method mth = clz.getDeclaredMethod("createObject",Map.class);
			obj = mth.invoke(null, map);			
		} catch (Exception e1) {		
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"Object create problem");
			return;
		} 
		EntityManager em = emf.createEntityManager();
		System.out.println(obj);
		try {		
			em.getTransaction().begin();
			em.persist(obj);	
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void edit(String tableName,Map<String,Object> map) {
		EntityManager em = emf.createEntityManager();
		Object obj = null;
		try {
			Class<?> clzTable = Class.forName("jpaOlenchenko." + tableName);
			obj = em.find(clzTable, map.get("id"));
			Class<?> clzQuery = Class.forName("query.Query" + tableName);
			Method mtd = clzQuery.getMethod(
"editObject",Object.class, Map.class);
			obj = mtd.invoke(null, obj, map);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		if(obj != null)
			try {
				em.getTransaction().begin();
				em.merge(obj);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				em.close();
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
/*
	public static void delete(String tableName, Map<String, Object> map) {
		

		int id = (int)map.get("id");
		Class<?> clazz = null;
		try {	
			clazz = Class.forName("jpaOlenchenko." + tableName);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}	
		if(clazz != null) {
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				Object delObj = em.find(clazz, id);
				em.remove(delObj);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
			}
		*/


	public static Object getObjectById(String tableName, int id) {
		EntityManager em = emf.createEntityManager();	
		Object obj = null;
		try {
			Class<?> clz = Class.forName("jpaOlenchenko." + tableName);
			obj = em.find(clz, id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
		return obj;
	}

	public static int executeUpdate(String query) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery(query);
		try {
			em.getTransaction().begin();
			int n = q.executeUpdate();	
			em.getTransaction().commit();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return 0;
	}
}
