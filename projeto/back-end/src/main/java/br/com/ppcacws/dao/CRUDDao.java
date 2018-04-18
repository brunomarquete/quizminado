package br.com.ppcacws.dao;

public interface CRUDDao<T> {

	void salvar(T Entidade);
	
	T recuperar(Integer id);
	
	T atualizar(T entidade);
	
	void deletar(T entidade);
	
}
