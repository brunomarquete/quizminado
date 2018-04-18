package br.com.ppcacws.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.ppcacws.model.Disciplina;

@Stateless
public class DisciplinaDao extends CRUDImplDao<Disciplina> {

	
	public DisciplinaDao() {
		
		super(Disciplina.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> getListaDisciplinas() {
		
		List<Disciplina> listaDisciplina = null;
		
		try {
			
			Query query = getEntityManager().createQuery("FROM Disciplina");
			
			listaDisciplina = (List<Disciplina>) query.getResultList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally {
			
			getEntityManager().close();
		}
		
		return listaDisciplina;
	}
	
}
