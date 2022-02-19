package test;

import java.util.List;
import jpaOlenchenko.Corps;
import jpaOlenchenko.Audience;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestNamedQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("jpaOlenchenko");
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
		}
}
