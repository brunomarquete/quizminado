package br.com.ppcacws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_questao", schema = "bd_quizminado")
public class Questao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_questao")
	private Integer idQuestao;
	
	@Column(name = "ds_questao")
	private String descricaoQuestao;
	
	@ManyToOne
	@JoinColumn(name="id_disciplina")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name="id_nivel")
	private Nivel nivel;
	
	
	public Questao() {
		
	}
	
	public Questao(String descricaoQuestao) {
		super();
		this.descricaoQuestao = descricaoQuestao;
	}
	
	public Questao(String descricaoQuestao, Disciplina disciplina,
			Nivel nivel) {
		super();
		this.descricaoQuestao = descricaoQuestao;
		this.disciplina = disciplina;
		this.nivel = nivel;
	}


	public Integer getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(Integer idQuestao) {
		this.idQuestao = idQuestao;
	}
	
	public String getDescricaoQuestao() {
		return descricaoQuestao;
	}
	public void setDescricaoQuestao(String descricaoQuestao) {
		this.descricaoQuestao = descricaoQuestao;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idQuestao == null) ? 0 : idQuestao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		if (idQuestao == null) {
			if (other.idQuestao != null)
				return false;
		} else if (!idQuestao.equals(other.idQuestao))
			return false;
		return true;
	}
	
}
