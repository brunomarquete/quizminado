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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jettison.json.JSONObject;

import br.com.ppcacws.model.Resposta;
import br.com.ppcacws.repository.QuestaoRepository;
import br.com.ppcacws.repository.RespostaRepository;
import br.com.ppcacws.service.RespostaService;
import br.com.ppcacws.vo.RespostaVo;

@Path("/resposta")
@RequestScoped
public class RespostaRest {

	@Inject
	private RespostaService respostaService;
	
	private final RespostaRepository respostaRepository = new RespostaRepository();
	private final QuestaoRepository questaoRepository = new QuestaoRepository();


	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarRespostaPorId(@PathParam("id") String id) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		RespostaVo resposta = null;
		
		GenericEntity<RespostaVo> entity = null;
		
		try {
			
			Resposta respostaEntity = respostaRepository.getResposta(Integer.parseInt(id));
			
			resposta = RespostaVo.clone(respostaEntity);
			
			entity = new GenericEntity<RespostaVo>(resposta) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhuma Resposta encontrada para o Id: " + id);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/buscarRespostaCertaPorQuestao/{idQuestao:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarRespostaCertaPorQuestao(@PathParam("idQuestao") String idQuestao) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		RespostaVo resposta = null;
		
		GenericEntity<RespostaVo> entity = null;
		
		try {
			
			Resposta respostaEntity = respostaRepository.getRespostaCertaPorQuestao(Integer.parseInt(idQuestao));
			
			resposta = RespostaVo.clone(respostaEntity);
			
			entity = new GenericEntity<RespostaVo>(resposta) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhuma Resposta encontrada para o Id da Questão: " + idQuestao);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/listarRespostas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarRespostas() {

		List<Resposta> listaRespostas = respostaRepository.listarRespostas();

		List<RespostaVo> lista = RespostaVo.popularRespostas(listaRespostas);

		GenericEntity<List<RespostaVo>> entity = new GenericEntity<List<RespostaVo>>(lista) {};
		
		return Response.ok(entity).build();
	}
	
	@POST
	@Path("/cadastrarResposta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarResposta(RespostaVo resposta) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Resposta respostaEntity = null;
		
		try {
			
			respostaEntity = new Resposta(resposta.getDescricaoResposta(), 
					resposta.getSituacaoResposta(), questaoRepository.getQuestao(resposta.getIdQuestao()));

			boolean sucesso = respostaRepository.salvar(respostaEntity);

			if(sucesso) {

				response.put("Mensagem", "A Resposta " + resposta.getDescricaoResposta() + " foi cadastrada com sucesso. "
						+ "URI Json: ../quizminado/rest/resposta/cadastrarResposta");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao cadastrar a Resposta: " + resposta.getDescricaoResposta());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao cadastrar a Resposta: " + resposta.getDescricaoResposta());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}
	
	@PUT
	@Path("/alterarResposta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarResposta(RespostaVo resposta) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Resposta respostaEntity = null;

		String descricaoResposta = null;

		try {

			respostaEntity = respostaRepository.getResposta(resposta.getIdResposta());
			
			if(respostaEntity == null) {

				throw new Exception();
			}
			
			descricaoResposta = respostaEntity.getDescricaoResposta();

			PropertyUtils.copyProperties(respostaEntity, resposta);
			
			respostaEntity.setQuestao(questaoRepository.getQuestao(resposta.getIdQuestao()));

			boolean sucesso = respostaRepository.alterar(respostaEntity);

			if(sucesso) {

				response.put("Mensagem", "A Resposta " + descricaoResposta + " foi alterada para " 
						+ respostaEntity.getDescricaoResposta() + ". URI Json: ../quizminado/rest/resposta/alterarResposta");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao alterar a Resposta Id: " + resposta.getIdResposta());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao alterar a Resposta Id: " + resposta.getIdResposta());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}
	
	@DELETE
	@Path("/deletarResposta/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarRespostaPorId(@PathParam("id") String id) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		String descricaoRespostaRemovida = null;

		try {

			Resposta respostaEntity = respostaRepository.getResposta(Integer.parseInt(id));

			if(respostaEntity == null) {

				throw new Exception();
			}

			descricaoRespostaRemovida = respostaEntity.getDescricaoResposta();

			boolean sucesso = respostaRepository.excluir(respostaEntity);

			if(sucesso) {

				response.put("Mensagem", "Resposta " + descricaoRespostaRemovida + " removida com sucesso.");

				json = new JSONObject(response);

				return Response.ok(json).build();

			} else {

				response.put("Mensagem", "Não foi possível remover a Resposta Id: " + id);

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_FOUND).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Nenhuma Resposta encontrada para o Id: " + id);

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}

}
