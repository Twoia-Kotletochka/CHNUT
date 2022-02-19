package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestSql {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaOlenchenko");
		EntityManager em = emf.createEntityManager();
		// SQL query
		String sql = "SELECT * FROM Audience  WHERE Audience.number >= '0'";
		Query qSql = em.createNativeQuery(sql);
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Object[]> list = qSql.getResultList();
		em.getTransaction().commit();
		// Output list
		for (Object[] arr : list) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + "\t");
			}
			System.out.println();
		}
	}
}
