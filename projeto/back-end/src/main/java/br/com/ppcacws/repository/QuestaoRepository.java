package br.com.ppcacws.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.ppcacws.model.Questao;

public class QuestaoRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;


	static {

		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}

	public QuestaoRepository() {

		entityManager = entityManagerFactory.createEntityManager();
	}

	public Questao getQuestao(Integer codigo) {

		Questao questao = null;

		try {

			questao = (Questao) this.entityManager.find(Questao.class, codigo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return questao;
	}

	@SuppressWarnings("unchecked")
	public List<Questao> listarQuestoes() {

		List<Questao> listaQuestoes = null;

		try {

			Query query = this.entityManager.createQuery("SELECT q FROM Questao q ORDER BY q.descricaoQuestao");

			listaQuestoes = (List<Questao>) query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listaQuestoes;
	}

	public boolean salvar(Questao questao) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.persist(questao);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean alterar(Questao questao) {

		try {

			this.entityManager.getTransaction().begin();
			questao = (Questao) this.entityManager.merge(questao);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean excluir(Questao questao) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.remove(questao);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

}
