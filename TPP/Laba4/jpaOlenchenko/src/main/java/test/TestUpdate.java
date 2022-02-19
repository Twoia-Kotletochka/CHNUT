package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpaOlenchenko.Audience;

public class TestUpdate {
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpaOlenchenko");
		EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Audience.findAll");
			//Output graduates list before update 
			List<Audience> list = q.getResultList();
			for (Audience gr : list) System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
			//Update floor name with id = 1
			em.getTransaction().begin();
			Audience grd = em.find(Audience.class, 1);
			System.out.println("\nÏ³ñëÿ\n");
			grd.setFloor("1212");
			em.getTransaction().commit();
			//Output Audience list after update 
			list = q.getResultList();
			for (Audience gr : list) System.out.println("\t"+gr.getId() + " " + gr.getArea() + " "+ gr.getFloor() + " "+ gr.getNumber());
		}

}
