package br.com.ppcacws.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ppcacws.enumeration.EnumDescricaoSituacaoResposta;
import br.com.ppcacws.repository.RespostaRepository;
import br.com.ppcacws.vo.QuestaoVo;
import br.com.ppcacws.vo.RespostaVo;

@Named("jogarService")
@RequestScoped
public class JogarService {
	
	private final RespostaRepository respostaRepository = new RespostaRepository();
	
	@Inject
	private ControleQuestoesService controleQuestoesService;
	
	
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
			
			String chaveUsuarioQuestao = controleQuestoesService.getChaveUsuarioQuestao(idUsuario, questao.getIdQuestao());
			
			controleQuestoesService.getMapaRespostaQuestoes().put(chaveUsuarioQuestao, questao);
		
			respostaCerta = RespostaVo.clone(respostaRepository.getRespostaCertaPorQuestao(questao.getIdQuestao()));
			
			return (respostaCerta.getDescricaoSituacaoResposta().equals(respostaUsuario.getDescricaoSituacaoResposta()) 
					? EnumDescricaoSituacaoResposta.CERTO : EnumDescricaoSituacaoResposta.ERRADO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
}
