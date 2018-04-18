package br.com.ppcacws.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ppcacws.model.Nivel;

@XmlRootElement(name = "nivel")
@XmlAccessorType(XmlAccessType.FIELD)
public class NivelVo {

	private Integer idNivel;
	private String descricaoNivel;
	private Integer numeroDificuldade;
	
	
	public NivelVo() {
		
	}
	
	public NivelVo(String descricaoNivel, Integer numeroDificuldade) {
		this.descricaoNivel = descricaoNivel;
		this.numeroDificuldade = numeroDificuldade;
	}
	
	public NivelVo(Integer idNivel, String descricaoNivel, 
			Integer numeroDificuldade) {
		this.idNivel = idNivel;
		this.descricaoNivel = descricaoNivel;
		this.numeroDificuldade = numeroDificuldade;
	}
	
	
	public static List<NivelVo> popularNiveis(List<Nivel> listaNiveis) {
		
		List<NivelVo> lista = new ArrayList<NivelVo>();
		
		for(Nivel nivel : listaNiveis) {
			
			lista.add(new NivelVo(nivel.getIdNivel(), nivel.getDescricaoNivel(), nivel.getNumeroDificuldade()));
		}
		
		return lista;
	}
	
	public static NivelVo clone(Nivel nivelEntity) {
		
		NivelVo nivel = new NivelVo(nivelEntity.getIdNivel(), nivelEntity.getDescricaoNivel(), nivelEntity.getNumeroDificuldade());
		
		return nivel;
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
	
}
