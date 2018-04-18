package br.com.ppcacws.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.ppcacws.model.Nivel;

public class NivelRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;


	static {

		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}

	public NivelRepository() {

		entityManager = entityManagerFactory.createEntityManager();
	}

	public Nivel getNivel(Integer codigo) {

		Nivel nivel = null;

		try {

			nivel = (Nivel) this.entityManager.find(Nivel.class, codigo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return nivel;
	}

	@SuppressWarnings("unchecked")
	public List<Nivel> listarNiveis() {

		List<Nivel> listaNiveis = null;

		try {

			Query query = this.entityManager.createQuery("SELECT n FROM Nivel n ORDER BY n.descricaoNivel");

			listaNiveis = (List<Nivel>) query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listaNiveis;
	}

	public boolean salvar(Nivel nivel) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.persist(nivel);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean alterar(Nivel nivel) {

		try {

			this.entityManager.getTransaction().begin();
			nivel = (Nivel) this.entityManager.merge(nivel);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean excluir(Nivel nivel) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.remove(nivel);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

}
