package br.com.ppcacws.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CRUDRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	
	static {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}
	
	public CRUDRepository() {
		
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
