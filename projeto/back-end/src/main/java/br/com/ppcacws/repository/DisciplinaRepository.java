package br.com.ppcacws.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.ppcacws.model.Disciplina;

public class DisciplinaRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;


	static {

		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}

	public DisciplinaRepository() {

		entityManager = entityManagerFactory.createEntityManager();
	}

	public Disciplina getDisciplina(Integer codigo) {

		Disciplina disciplina = null;

		try {

			disciplina = (Disciplina) this.entityManager.find(Disciplina.class, codigo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return disciplina;
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> listarDisciplinas() {

		List<Disciplina> listaDisciplinas = null;

		try {

			Query query = this.entityManager.createQuery("SELECT d FROM Disciplina d ORDER BY d.descricaoDisciplina");

			listaDisciplinas = (List<Disciplina>) query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listaDisciplinas;
	}

	public boolean salvar(Disciplina disciplina) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.persist(disciplina);
			this.entityManager.getTransaction().commit();
			
			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();
			
			return false;
		}
	}

	public boolean alterar(Disciplina disciplina) {

		try {

			this.entityManager.getTransaction().begin();
			disciplina = (Disciplina) this.entityManager.merge(disciplina);
			this.entityManager.getTransaction().commit();
			
			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();
			
			return false;
		}
	}

	public boolean excluir(Disciplina disciplina) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.remove(disciplina);
			this.entityManager.getTransaction().commit();
			
			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();
			
			return false;
		}
	}

}
