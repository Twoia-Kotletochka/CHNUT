package test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpaOlenchenko.Audience;
import jpaOlenchenko.Corps;

public class TestDelById {
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
		em.getTransaction().begin();
		Corps st = em.find(Corps.class, 101);
		em.remove(st);
		em.getTransaction().commit();

		System.out.println("\nÏ³ñëÿ\n");
		
		@SuppressWarnings("unchecked")
		List<Corps> list1 = q.getResultList();
		for (Corps cth : list1) {
			System.out.println(cth.getId() + " " + cth.getAudience() + " " + cth.getCorps() + " " + cth.getFurniture());
			for(Audience gr : cth.getAudiences()) {
				System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
			}
		}
	}
}
