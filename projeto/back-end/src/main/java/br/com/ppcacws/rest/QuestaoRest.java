package br.com.ppcacws.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jettison.json.JSONObject;

import br.com.ppcacws.enumeration.EnumDescricaoSituacaoResposta;
import br.com.ppcacws.model.Questao;
import br.com.ppcacws.repository.DisciplinaRepository;
import br.com.ppcacws.repository.NivelRepository;
import br.com.ppcacws.repository.QuestaoRepository;
import br.com.ppcacws.repository.RespostaRepository;
import br.com.ppcacws.service.JogarService;
import br.com.ppcacws.vo.QuestaoVo;
import br.com.ppcacws.vo.RespostaVo;

@Path("/questao")
@RequestScoped
public class QuestaoRest {

	@Inject
	private JogarService jogarService;
	
	private final QuestaoRepository questaoRepository = new QuestaoRepository();
	private final RespostaRepository respostaRepository = new RespostaRepository();
	private final DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
	private final NivelRepository nivelRepository = new NivelRepository();


	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarQuestaoPorId(@PathParam("id") String id) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		QuestaoVo questao = null;
		
		GenericEntity<QuestaoVo> entity = null;
		
		try {
			
			Questao questaoEntity = questaoRepository.getQuestao(Integer.parseInt(id));
			
			questao = QuestaoVo.clone(questaoEntity);
			
			entity = new GenericEntity<QuestaoVo>(questao) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhuma Questão encontrada para o Id: " + id);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/listarQuestoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarQuestoes() {

		List<Questao> listaQuestoes = questaoRepository.listarQuestoes();

		List<QuestaoVo> lista = QuestaoVo.popularQuestoes(listaQuestoes);

		GenericEntity<List<QuestaoVo>> entity = new GenericEntity<List<QuestaoVo>>(lista) {};
		
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("/buscarQuestaoPorDisciplinaENivelRandom")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarQuestaoPorDisciplinaENivelRandom(
			@QueryParam("idDisciplina") String idDisciplina, @QueryParam("idNivel") String idNivel) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		QuestaoVo questao = null;
		
		GenericEntity<QuestaoVo> entity = null;
		
		try {
			
			Questao questaoEntity = questaoRepository.buscarQuestaoPorDisciplinaENivelRandom(
					Integer.parseInt(idDisciplina), Integer.parseInt(idNivel));
			
			questao = QuestaoVo.clone(questaoEntity);
			
			entity = new GenericEntity<QuestaoVo>(questao) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhuma Questão encontrada!");
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/buscarQuestoesPorDisciplinaENivelRandom")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarQuestoesPorDisciplinaENivelRandom(
			@QueryParam("idDisciplina") String idDisciplina, @QueryParam("idNivel") String idNivel) {

		List<Questao> listaQuestoes = questaoRepository.
				buscarQuestoesPorDisciplinaENivelRandom(Integer.parseInt(idDisciplina), Integer.parseInt(idNivel));
		
		List<QuestaoVo> lista = QuestaoVo.popularQuestoes(listaQuestoes);

		GenericEntity<List<QuestaoVo>> entity = new GenericEntity<List<QuestaoVo>>(lista) {};
		
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("/buscarQuestoesPorUsuarioDisciplinaENivelRandom")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarQuestoesPorUsuarioDisciplinaENivelRandom(@QueryParam("idUsuario") String idUsuario,
			@QueryParam("idDisciplina") String idDisciplina, @QueryParam("idNivel") String idNivel) {

		List<Questao> listaQuestoes = questaoRepository.
				buscarQuestoesPorDisciplinaENivelRandom(Integer.parseInt(idDisciplina), Integer.parseInt(idNivel));
		
		List<Questao> listaQuestoesDisponiveis = jogarService.removerQuestoesRespondidas(listaQuestoes, idUsuario);

		List<QuestaoVo> lista = QuestaoVo.popularQuestoes(listaQuestoesDisponiveis);

		GenericEntity<List<QuestaoVo>> entity = new GenericEntity<List<QuestaoVo>>(lista) {};
		
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("/responderQuestao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response responderQuestao(@QueryParam("idQuestao") String idQuestao, @QueryParam("letraResposta") String letraResposta) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		QuestaoVo questao = null;
		
		RespostaVo resposta = null;
		
		try {
			
			questao = QuestaoVo.clone(questaoRepository.getQuestao(Integer.parseInt(idQuestao)));
			
			resposta = RespostaVo.clone(respostaRepository.getRespostaPorLetra(Integer.parseInt(idQuestao), letraResposta));
			
			EnumDescricaoSituacaoResposta situacaoResposta = jogarService.responderQuestao(questao, resposta);
			
			response.put("Mensagem", situacaoResposta.getDescricaoSituacaoReposta());
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.OK).entity(json).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Não foi possível responder a Questão Id: " + idQuestao);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/responderQuestaoPorUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response responderQuestaoPorUsuario(@QueryParam("idUsuario") String idUsuario, 
			@QueryParam("idQuestao") String idQuestao, @QueryParam("letraResposta") String letraResposta) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		QuestaoVo questao = null;
		
		RespostaVo resposta = null;
		
		try {
			
			questao = QuestaoVo.clone(questaoRepository.getQuestao(Integer.parseInt(idQuestao)));
			
			resposta = RespostaVo.clone(respostaRepository.getRespostaPorLetra(Integer.parseInt(idQuestao), letraResposta));
			
			EnumDescricaoSituacaoResposta situacaoResposta = jogarService.responderQuestaoPorUsuario(idUsuario, questao, resposta);
			
			response.put("Mensagem", situacaoResposta.getDescricaoSituacaoReposta());
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.OK).entity(json).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Não foi possível responder a Questão Id: " + idQuestao);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}

	@POST
	@Path("/cadastrarQuestao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarQuestao(QuestaoVo questao) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Questao questaoEntity = null;
		
		try {
			
			questaoEntity = new Questao(questao.getDescricaoQuestao(), 
					disciplinaRepository.getDisciplina(questao.getIdDisciplina()),
							nivelRepository.getNivel(questao.getIdNivel()));

			boolean sucesso = questaoRepository.salvar(questaoEntity);

			if(sucesso) {

				response.put("Mensagem", "A Questão " + questao.getDescricaoQuestao() + " foi cadastrada com sucesso. "
						+ "URI Json: ../quizminado/rest/questao/cadastrarQuestao");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao cadastrar a Questão: " + questao.getDescricaoQuestao());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao cadastrar a Questão: " + questao.getDescricaoQuestao());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}
	
	@PUT
	@Path("/alterarQuestao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarQuestao(QuestaoVo questao) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Questao questaoEntity = null;

		String descricaoQuestao = null;

		try {

			questaoEntity = questaoRepository.getQuestao(questao.getIdQuestao());
			
			if(questaoEntity == null) {

				throw new Exception();
			}
			
			descricaoQuestao = questaoEntity.getDescricaoQuestao();

			PropertyUtils.copyProperties(questaoEntity, questao);
			
			questaoEntity.setDisciplina(disciplinaRepository.getDisciplina(questao.getIdDisciplina()));
			questaoEntity.setNivel(nivelRepository.getNivel(questao.getIdNivel()));

			boolean sucesso = questaoRepository.alterar(questaoEntity);

			if(sucesso) {

				response.put("Mensagem", "A Questão " + descricaoQuestao + " foi alterada para " 
						+ questaoEntity.getDescricaoQuestao() + ". URI Json: ../quizminado/rest/questao/alterarQuestao");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao alterar a Questão Id: " + questao.getIdQuestao());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao alterar a Questão Id: " + questao.getIdQuestao());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}
	
	@DELETE
	@Path("/deletarQuestao/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarQuestaoPorId(@PathParam("id") String id) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		String descricaoQuestaoRemovida = null;

		try {

			Questao questaoEntity = questaoRepository.getQuestao(Integer.parseInt(id));

			if(questaoEntity == null) {

				throw new Exception();
			}

			descricaoQuestaoRemovida = questaoEntity.getDescricaoQuestao();

			boolean sucesso = questaoRepository.excluir(questaoEntity);

			if(sucesso) {

				response.put("Mensagem", "Questão " + descricaoQuestaoRemovida + " removida com sucesso.");

				json = new JSONObject(response);

				return Response.ok(json).build();

			} else {

				response.put("Mensagem", "Não foi possível remover a Questão Id: " + id);

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_FOUND).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Nenhuma Questão encontrada para o Id: " + id);

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}

}
