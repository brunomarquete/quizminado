package br.com.ppcacws.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.ppcacws.model.Resposta;

public class RespostaRepository {

	private static EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;


	static {

		entityManagerFactory = Persistence.createEntityManagerFactory("quizminado");
	}

	public RespostaRepository() {

		entityManager = entityManagerFactory.createEntityManager();
	}

	public Resposta getResposta(Integer codigo) {

		Resposta resposta = null;

		try {

			resposta = (Resposta) this.entityManager.find(Resposta.class, codigo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resposta;
	}
	
	public Resposta getRespostaPorLetra(Integer codigoQuestao, String letraResposta) {

		Resposta resposta = null;

		try {
			
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append(" SELECT r FROM Resposta r ");
			
			queryBuilder.append(" 	INNER JOIN r.questao q ");
			
			queryBuilder.append(" WHERE q.idQuestao = :P_CODIGO_QUESTAO ");
			
			queryBuilder.append(" 	AND r.letraResposta = :P_LETRA_RESPOSTA ");

			Query query = this.entityManager.createQuery(queryBuilder.toString());
			
			query.setParameter("P_CODIGO_QUESTAO", codigoQuestao);
			
			query.setParameter("P_LETRA_RESPOSTA", letraResposta);
			
			resposta = (Resposta) query.getSingleResult();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resposta;
	}

	@SuppressWarnings("unchecked")
	public List<Resposta> listarRespostas() {

		List<Resposta> listaRespostas = null;

		try {

			Query query = this.entityManager.createQuery("SELECT r FROM Resposta r ORDER BY r.descricaoResposta");

			listaRespostas = (List<Resposta>) query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listaRespostas;
	}
	
	public Resposta getRespostaCertaPorQuestao(Integer codigoQuestao) {

		Resposta resposta = null;

		try {
			
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append(" SELECT r FROM Resposta r ");
			
			queryBuilder.append(" 	INNER JOIN r.questao q ");
			
			queryBuilder.append(" WHERE q.idQuestao = :P_CODIGO_QUESTAO ");
			
			queryBuilder.append(" 	AND r.situacaoResposta = 'C' ");

			Query query = this.entityManager.createQuery(queryBuilder.toString());
			
			query.setParameter("P_CODIGO_QUESTAO", codigoQuestao);
			
			resposta = (Resposta) query.getSingleResult();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resposta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resposta> getRespostasPorQuestao(Integer codigoQuestao) {

		List<Resposta> listaRespostas = null;

		try {
			
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append(" SELECT r FROM Resposta r ");
			
			queryBuilder.append(" 	INNER JOIN r.questao q ");
			
			queryBuilder.append(" WHERE q.idQuestao = :P_CODIGO_QUESTAO ");
			
			queryBuilder.append(" ORDER BY r.letraResposta ASC ");

			Query query = this.entityManager.createQuery(queryBuilder.toString());
			
			query.setParameter("P_CODIGO_QUESTAO", codigoQuestao);
			
			listaRespostas = (List<Resposta>) query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listaRespostas;
	}

	public boolean salvar(Resposta resposta) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.persist(resposta);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean alterar(Resposta resposta) {

		try {

			this.entityManager.getTransaction().begin();
			resposta = (Resposta) this.entityManager.merge(resposta);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

	public boolean excluir(Resposta resposta) {

		try {

			this.entityManager.getTransaction().begin();
			this.entityManager.remove(resposta);
			this.entityManager.getTransaction().commit();

			return true;

		} catch (Exception e) {

			this.entityManager.getTransaction().rollback();

			e.printStackTrace();

			return false;
		}
	}

}
