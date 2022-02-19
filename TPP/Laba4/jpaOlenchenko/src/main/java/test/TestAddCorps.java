package test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpaOlenchenko.Audience;
import jpaOlenchenko.Corps;

public class TestAddCorps {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaOlenchenko");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Corps.findAll");

		@SuppressWarnings("unchecked")
		List<Corps> list = q.getResultList();
		for (Corps cth : list) {
			System.out.println(cth.getId() + " " + cth.getAudience() + " " + cth.getCorps() + " " + cth.getFurniture());
			for(Audience gr : cth.getAudiences()) {
				System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
			}
		}

		// Транзакція створення нової кафедри
		Corps cth = new Corps();
		cth.setAudience("91");
		cth.setCorps("91");
		cth.setFurniture("991");

		em.getTransaction().begin();
		em.persist(cth);
		em.getTransaction().commit();
		//
		
		System.out.println("\n->\n");
		
		@SuppressWarnings("unchecked")
		List<Corps> list1 = q.getResultList();
		for (Corps cth1 : list1) {
			System.out.println(cth1.getId() + " " + cth1.getAudience() + " " + cth1.getCorps() + " " + cth1.getFurniture());
			for(Audience gr : cth1.getAudiences()) {
				System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
			}
		}
	}
}
