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
@Table(name = "tb_resposta", schema = "bd_quizminado")
public class Resposta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resposta")
	private Integer idResposta;
	
	@Column(name = "ds_resposta")
	private String descricaoResposta;
	
	@Column(name = "st_resposta", columnDefinition = "CHAR", length = 1)
	private String situacaoResposta;
	
	@ManyToOne
	@JoinColumn(name="id_questao")
	private Questao questao;
	
	
	public Resposta() {
		
	}
	
	public Resposta(String descricaoResposta, String situacaoResposta, 
			Questao questao) {
		super();
		this.descricaoResposta = descricaoResposta;
		this.situacaoResposta = situacaoResposta;
		this.questao = questao;
	}


	public Integer getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(Integer idResposta) {
		this.idResposta = idResposta;
	}
	
	public String getDescricaoResposta() {
		return descricaoResposta;
	}
	public void setDescricaoResposta(String descricaoResposta) {
		this.descricaoResposta = descricaoResposta;
	}
	
	public String getSituacaoResposta() {
		return situacaoResposta;
	}
	public void setSituacaoResposta(String situacaoResposta) {
		this.situacaoResposta = situacaoResposta;
	}

	public Questao getQuestao() {
		return questao;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idResposta == null) ? 0 : idResposta.hashCode());
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
		Resposta other = (Resposta) obj;
		if (idResposta == null) {
			if (other.idResposta != null)
				return false;
		} else if (!idResposta.equals(other.idResposta))
			return false;
		return true;
	}
	
}
