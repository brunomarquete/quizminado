package br.com.ppcacws.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ppcacws.dao.DisciplinaDao;
import br.com.ppcacws.model.Disciplina;

@Named("disciplinaService")
@RequestScoped
public class DisciplinaService {
	
	@Inject
	private DisciplinaDao disciplinaDao;
	
	
	public List<Disciplina> getListaDisciplinas() {
		
		return disciplinaDao.getListaDisciplinas();
	}
	
	public void salvar(Disciplina disciplina) {
		
		disciplinaDao.salvar(disciplina);
	}
	
}
