package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpaOlenchenko.Audience;

public class TestParamemterQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf =
		Persistence.createEntityManagerFactory("jpaOlenchenko");
		EntityManager em = emf.createEntityManager();
					String sq = "select gr from Audience gr where gr.area = ?1";
					Query q = em.createQuery(sq);
					q.setParameter(1, "10");
					@SuppressWarnings("unchecked")
					List<Audience> list = q.getResultList();
					for (Audience gr : list) {
						System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
					}
		}
}
