package br.com.ppcacws.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.ppcacws.resources.EntityManagerProducer;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CRUDImplDao<T> implements CRUDDao<T> {

	private Class<T> entidade;


	public CRUDImplDao(Class<T> entidade) {

		this.entidade = entidade;
	}

	//TODO VERIFICAR PORQUE NÃO ESTÁ INSERINDO???
	@Override
	public void salvar(T entidade) {
		
		Transaction transaction = null;

		try {

			Session session = (Session) getEntityManager().getDelegate();

			transaction = session.getTransaction();

			if(!transaction.isActive())
				session.beginTransaction();

			getEntityManager().persist(entidade);

			//TODO SE FIZER O FLUSH DÁ ERRO
			//getEntityManager().flush();

			transaction.commit();

		} catch (Exception e) {

			e.printStackTrace();

			transaction.rollback();

		} finally {

			getEntityManager().close();
		}
	}

	@Override
	public T recuperar(Integer id) {

		T t = null;

		try {

			t = (T) getEntityManager().find(entidade, id);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			getEntityManager().close();
		}

		return t;
	}

	@Override
	public T atualizar(T entidade) {

		Transaction transaction = null;

		try {

			Session session = (Session) getEntityManager().getDelegate();

			transaction = session.getTransaction();

			if(!transaction.isActive())
				session.beginTransaction();

			entidade = getEntityManager().merge(entidade);

			//getEntityManager().flush();

			transaction.commit();

		} catch (Exception e) {

			e.printStackTrace();

			transaction.rollback();

		} finally {

			getEntityManager().close();
		}

		return entidade;
	}

	@Override
	public void deletar(T entidade) {

		Transaction transaction = null;

		try {

			Session session = (Session) getEntityManager().getDelegate();

			transaction = session.getTransaction();

			if(!transaction.isActive())
				session.beginTransaction();

			getEntityManager().remove(entidade);

			//getEntityManager().flush();

			transaction.commit();

		} catch (Exception e) {

			e.printStackTrace();

			transaction.rollback();

		} finally {

			getEntityManager().close();
		}
	}


	public EntityManager getEntityManager() {

		return EntityManagerProducer.getEntityManager();
	}

}
