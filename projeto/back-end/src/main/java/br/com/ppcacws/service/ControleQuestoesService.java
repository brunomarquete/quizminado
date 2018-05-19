package br.com.ppcacws.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.ppcacws.model.Questao;
import br.com.ppcacws.vo.QuestaoVo;

@Named("controleQuestoesService")
@ApplicationScoped
public class ControleQuestoesService {
	
	private Map<String, QuestaoVo> mapaRespostaQuestoes;
	
	
	public List<Questao> removerQuestoesRespondidas(List<Questao> listaQuestoesDisponiveis, String idUsuario) {
		
		String chaveUsuarioQuestao = "";
		
		QuestaoVo questaoRespondida = null;
		
		Iterator<Questao> questoesDisponiveisIterator = listaQuestoesDisponiveis.iterator();
		
		while(questoesDisponiveisIterator.hasNext()) {
			
			QuestaoVo questaoDisponivel = QuestaoVo.clone(questoesDisponiveisIterator.next());
			
			chaveUsuarioQuestao = this.getChaveUsuarioQuestao(idUsuario, questaoDisponivel.getIdQuestao());
			
			questaoRespondida = this.getMapaRespostaQuestoes().get(chaveUsuarioQuestao);
			
			if(questaoRespondida != null && 
					questaoDisponivel.getIdQuestao().equals(questaoRespondida.getIdQuestao())) {
				
				questoesDisponiveisIterator.remove();
			}
		}
		
		return listaQuestoesDisponiveis;
	}
	
	
	public String getChaveUsuarioQuestao(String idUsuario, Integer idQuestao) {
		
		return idUsuario + "_" + idQuestao.toString();
	}
	
	public Map<String, QuestaoVo> getMapaRespostaQuestoes() {
		if(mapaRespostaQuestoes == null) {
			mapaRespostaQuestoes = new HashMap<String, QuestaoVo>();
		}
		return mapaRespostaQuestoes;
	}
	
	public void limparQuestoesRespondidas() {
		
		mapaRespostaQuestoes = new HashMap<String, QuestaoVo>();
	}
	
}
