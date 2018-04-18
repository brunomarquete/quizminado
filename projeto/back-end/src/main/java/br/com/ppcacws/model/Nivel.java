package br.com.ppcacws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_nivel", schema = "bd_quizminado")
public class Nivel {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nivel")
	private Integer idNivel;
	
	@Column(name = "ds_nivel")
	private String descricaoNivel;
	
	@Column(name = "nu_dificuldade")
	private Integer numeroDificuldade;
	
	
	public Nivel() {
		
	}
	
	public Nivel(String descricaoNivel, Integer numeroDificuldade) {
		super();
		this.descricaoNivel = descricaoNivel;
		this.numeroDificuldade = numeroDificuldade;
	}


	public Integer getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}
	
	public String getDescricaoNivel() {
		return descricaoNivel;
	}
	public void setDescricaoNivel(String descricaoNivel) {
		this.descricaoNivel = descricaoNivel;
	}
	
	public Integer getNumeroDificuldade() {
		return numeroDificuldade;
	}
	public void setNumeroDificuldade(Integer numeroDificuldade) {
		this.numeroDificuldade = numeroDificuldade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNivel == null) ? 0 : idNivel.hashCode());
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
		Nivel other = (Nivel) obj;
		if (idNivel == null) {
			if (other.idNivel != null)
				return false;
		} else if (!idNivel.equals(other.idNivel))
			return false;
		return true;
	}
	
}
