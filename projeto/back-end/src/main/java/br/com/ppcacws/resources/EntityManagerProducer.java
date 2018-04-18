package br.com.ppcacws.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton
 */
public class EntityManagerProducer {

	private static EntityManagerFactory entityManagerFactory;

	static {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}
	
	public static EntityManagerFactory getEmf() {
		
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager() {
		
		return getEmf().createEntityManager();
	}
	
}
