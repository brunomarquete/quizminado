package br.com.ppcacws.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.ppcacws.enumeration.EnumDescricaoSituacaoResposta;
import br.com.ppcacws.model.Questao;
import br.com.ppcacws.repository.RespostaRepository;
import br.com.ppcacws.vo.QuestaoVo;
import br.com.ppcacws.vo.RespostaVo;

@Named("jogarService")
@RequestScoped
public class JogarService {
	
	private final RespostaRepository respostaRepository = new RespostaRepository();
	
	private Map<String, QuestaoVo> mapaRespostaQuestoes;
	
	
	public EnumDescricaoSituacaoResposta responderQuestao(QuestaoVo questao, RespostaVo respostaUsuario) {
		
		RespostaVo respostaCerta = null;
		
		try {
			
			respostaCerta = RespostaVo.clone(respostaRepository.getRespostaCertaPorQuestao(questao.getIdQuestao()));
			
			return (respostaCerta.getDescricaoSituacaoResposta().equals(respostaUsuario.getDescricaoSituacaoResposta()) 
					? EnumDescricaoSituacaoResposta.CERTO : EnumDescricaoSituacaoResposta.ERRADO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public EnumDescricaoSituacaoResposta responderQuestaoPorUsuario(String idUsuario, QuestaoVo questao, RespostaVo respostaUsuario) {
		
		RespostaVo respostaCerta = null;
		
		try {
			
			this.getMapaRespostaQuestoes().put(this.getChaveUsuarioQuestao(idUsuario, questao.getIdQuestao()), questao);
		
			respostaCerta = RespostaVo.clone(respostaRepository.getRespostaCertaPorQuestao(questao.getIdQuestao()));
			
			return (respostaCerta.getDescricaoSituacaoResposta().equals(respostaUsuario.getDescricaoSituacaoResposta()) 
					? EnumDescricaoSituacaoResposta.CERTO : EnumDescricaoSituacaoResposta.ERRADO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<Questao> removerQuestoesRespondidas(List<Questao> listaQuestoesDisponiveis, String idUsuario) {
		
		String chaveUsuarioQuestao = "";
		
		QuestaoVo questaoRespondida = null;
		
		Iterator<Questao> questoesDisponiveisIterator = listaQuestoesDisponiveis.iterator();
		
		while(questoesDisponiveisIterator.hasNext()) {
			
			QuestaoVo questaoDisponivel = QuestaoVo.clone(questoesDisponiveisIterator.next());
			
			chaveUsuarioQuestao = this.getChaveUsuarioQuestao(idUsuario, questaoDisponivel.getIdQuestao());
			
			questaoRespondida = this.getMapaRespostaQuestoes().get(chaveUsuarioQuestao);
			
			if(questaoDisponivel.equals(questaoRespondida))
				questoesDisponiveisIterator.remove();
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
	
}
