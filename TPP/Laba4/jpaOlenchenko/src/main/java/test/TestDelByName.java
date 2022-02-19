package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpaOlenchenko.Audience;
import jpaOlenchenko.Corps;

public class TestDelByName {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaOlenchenko");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Corps.findAll");
		

		List<Corps> list = q.getResultList();
		for (Corps cth : list) {
			System.out.println(cth.getId() + " " + cth.getAudience() + " " + cth.getCorps() + " " + cth.getFurniture());
			for(Audience gr : cth.getAudiences()) {
				System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
			}
		}
		
		
		String sq = "DELETE FROM Corps cth WHERE cth.corps = ?1";
		Query qDel = em.createQuery(sq);
		qDel.setParameter(1, "3");
		em.getTransaction().begin();
		qDel.executeUpdate();
		em.getTransaction().commit();

		System.out.println("\nÏ³ñëÿ\n");
		

		List<Corps> list1 = q.getResultList();
		for (Corps cth1 : list1) {
			System.out.println(cth1.getId() + " " + cth1.getAudience() + " " + cth1.getCorps() + " " + cth1.getFurniture());
			for(Audience gr1 : cth1.getAudiences()) {
				System.out.println("\t"+gr1.getId() + " " + gr1.getArea() + " "+ gr1.getFloor() + " "+ gr1.getNumber());
			}
		}
	}
}
