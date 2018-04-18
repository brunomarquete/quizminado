package br.com.ppcacws.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ppcacws.model.Questao;

@XmlRootElement(name = "questao")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestaoVo {

	private Integer idQuestao;
	private String descricaoQuestao;
	
	private Integer idDisciplina;
	private DisciplinaVo disciplina;
	
	private Integer idNivel;
	private NivelVo nivel;
	
	
	public QuestaoVo() {
		
	}
	
	public QuestaoVo(String descricaoQuestao, DisciplinaVo disciplina,
			NivelVo nivel) {
		this.descricaoQuestao = descricaoQuestao;
		this.disciplina = disciplina;
		this.nivel = nivel;
	}
	
	public QuestaoVo(Integer idQuestao, String descricaoQuestao, 
			DisciplinaVo disciplina, NivelVo nivel) {
		this.idQuestao = idQuestao;
		this.descricaoQuestao = descricaoQuestao;
		this.disciplina = disciplina;
		this.nivel = nivel;
	}
	
	
	public static List<QuestaoVo> popularQuestoes(List<Questao> listaQuestoes) {
		
		List<QuestaoVo> lista = new ArrayList<QuestaoVo>();
		
		DisciplinaVo disciplina = null;
		
		NivelVo nivel = null;
		
		for(Questao questao : listaQuestoes) {
			
			disciplina = DisciplinaVo.clone(questao.getDisciplina());
			
			nivel = NivelVo.clone(questao.getNivel());
			
			lista.add(new QuestaoVo(questao.getIdQuestao(), questao.getDescricaoQuestao(), disciplina, nivel));
		}
		
		return lista;
	}
	
	public static QuestaoVo clone(Questao questaoEntity) {
		
		DisciplinaVo disciplina = DisciplinaVo.clone(questaoEntity.getDisciplina());
		
		NivelVo nivel = NivelVo.clone(questaoEntity.getNivel());
		
		QuestaoVo questao = new QuestaoVo(questaoEntity.getIdQuestao(), questaoEntity.getDescricaoQuestao(), disciplina, nivel);
		
		return questao;
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
	
	public Integer getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	public DisciplinaVo getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(DisciplinaVo disciplina) {
		this.disciplina = disciplina;
	}
	
	public Integer getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}
	
	public NivelVo getNivel() {
		return nivel;
	}
	public void setNivel(NivelVo nivel) {
		this.nivel = nivel;
	}
	
}
