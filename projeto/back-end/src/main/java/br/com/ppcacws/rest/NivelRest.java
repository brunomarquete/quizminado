package br.com.ppcacws.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
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

import br.com.ppcacws.model.Nivel;
import br.com.ppcacws.repository.NivelRepository;
import br.com.ppcacws.vo.NivelVo;

@Path("/nivel")
@RequestScoped
public class NivelRest {

	private final NivelRepository nivelRepository = new NivelRepository();


	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarNivelPorId(@PathParam("id") String id) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		NivelVo nivel = null;
		
		GenericEntity<NivelVo> entity = null;
		
		try {
			
			Nivel nivelEntity = nivelRepository.getNivel(Integer.parseInt(id));
			
			nivel = NivelVo.clone(nivelEntity);
			
			entity = new GenericEntity<NivelVo>(nivel) {};
			
			return Response.ok(entity).build();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhum Nível encontrado para o Id: " + id);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}
	
	@GET
	@Path("/listarNiveis")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarNiveis() {

		List<Nivel> listaNiveis = nivelRepository.listarNiveis();

		List<NivelVo> lista = NivelVo.popularNiveis(listaNiveis);

		GenericEntity<List<NivelVo>> entity = new GenericEntity<List<NivelVo>>(lista) {};
		
		return Response.ok(entity).build();
	}

	@POST
	@Path("/cadastrarNivel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarNivel(NivelVo nivel) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();

		Nivel nivelEntity = new Nivel(nivel.getDescricaoNivel(), nivel.getNumeroDificuldade());
		
		try {
			
			boolean sucesso = nivelRepository.salvar(nivelEntity);
			
			if(sucesso) {
				
				response.put("Mensagem", "O Nível " + nivel.getDescricaoNivel() + " foi cadastrado com sucesso. "
						+ "URI Json: ../quizminado/rest/nivel/cadastrarNivel");
				
				json = new JSONObject(response);
				
				return Response.status(Response.Status.OK).entity(json).build();
			
			} else {
				
				response.put("Mensagem", "Erro ao cadastrar o Nível: " + nivel.getDescricaoNivel());
				
				json = new JSONObject(response);
				
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Erro ao cadastrar o Nível: " + nivel.getDescricaoNivel());
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}

	@PUT
	@Path("/alterarNivel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarNivel(NivelVo nivel) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();

		Nivel nivelEntity = null;
		
		String descricaoNivel = null;
		
		try {
			
			nivelEntity = nivelRepository.getNivel(nivel.getIdNivel());
			
			if(nivelEntity == null) {
				
				throw new Exception();
			}
			
			descricaoNivel = nivelEntity.getDescricaoNivel();
			
			PropertyUtils.copyProperties(nivelEntity, nivel);
			
			boolean sucesso = nivelRepository.alterar(nivelEntity);
			
			if(sucesso) {
				
				response.put("Mensagem", "O Nível " + descricaoNivel + " foi alterado para " 
						+ nivelEntity.getDescricaoNivel() + ". URI Json: ../quizminado/rest/nivel/alterarNivel");
				
				json = new JSONObject(response);
				
				return Response.status(Response.Status.OK).entity(json).build();
				
			} else {
				
				response.put("Mensagem", "Erro ao alterar o Nível Id: " + nivel.getIdNivel());
				
				json = new JSONObject(response);
				
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Erro ao alterar o Nível Id: " + nivel.getIdNivel());
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}
	
	@DELETE
	@Path("/deletarNivel/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarNivelPorId(@PathParam("id") String id) {
		
		JSONObject json = null;
		
		Map<String, String> response = new HashMap<String, String>();
		
		String descricaoNivelRemovido = null;
		
		try {
			
			Nivel nivelEntity = nivelRepository.getNivel(Integer.parseInt(id));
			
			if(nivelEntity == null) {
				
				throw new Exception();
			}
			
			descricaoNivelRemovido = nivelEntity.getDescricaoNivel();
			
			boolean sucesso = nivelRepository.excluir(nivelEntity);
			
			if(sucesso) {
				
				response.put("Mensagem", "Nível " + descricaoNivelRemovido + " removido com sucesso.");
				
				json = new JSONObject(response);
				
				return Response.ok(json).build();
				
			} else {
				
				response.put("Mensagem", "Não foi possível remover o Nível Id: " + id);
				
				json = new JSONObject(response);
				
				return Response.status(Response.Status.NOT_FOUND).entity(json).build();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			response.put("Mensagem", "Nenhum Nível encontrado para o Id: " + id);
			
			json = new JSONObject(response);
			
			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}

}
