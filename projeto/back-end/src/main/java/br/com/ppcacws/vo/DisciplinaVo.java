package br.com.ppcacws.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ppcacws.model.Disciplina;

@XmlRootElement(name = "disciplina")
@XmlAccessorType(XmlAccessType.FIELD)
public class DisciplinaVo {

	private Integer idDisciplina;
	private String descricaoDisciplina;
	
	
	public DisciplinaVo() {
		
	}
	
	public DisciplinaVo(String descricaoDisciplina) {
		this.descricaoDisciplina = descricaoDisciplina;
	}
	
	public DisciplinaVo(Integer idDisciplina, String descricaoDisciplina) {
		this.idDisciplina = idDisciplina;
		this.descricaoDisciplina = descricaoDisciplina;
	}
	
	
	public static List<DisciplinaVo> popularDisciplinas(List<Disciplina> listaDisciplinas) {
		
		List<DisciplinaVo> lista = new ArrayList<DisciplinaVo>();
		
		for(Disciplina disciplina : listaDisciplinas) {
			
			lista.add(new DisciplinaVo(disciplina.getIdDisciplina(), disciplina.getDescricaoDisciplina()));
		}
		
		return lista;
	}
	
	public static DisciplinaVo clone(Disciplina disciplinaEntity) {
		
		DisciplinaVo disciplina = new DisciplinaVo(disciplinaEntity.getIdDisciplina(), disciplinaEntity.getDescricaoDisciplina());
		
		return disciplina;
	}


	public Integer getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	public String getDescricaoDisciplina() {
		return descricaoDisciplina;
	}
	public void setDescricaoDisciplina(String descricaoDisciplina) {
		this.descricaoDisciplina = descricaoDisciplina;
	}
	
}
