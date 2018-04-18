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

import br.com.ppcacws.model.Disciplina;
import br.com.ppcacws.repository.DisciplinaRepository;
import br.com.ppcacws.service.DisciplinaService;
import br.com.ppcacws.vo.DisciplinaVo;

@Path("/disciplina")
@RequestScoped
public class DisciplinaRest {

	@Inject
	private DisciplinaService disciplinaService;

	private final DisciplinaRepository disciplinaRepository = new DisciplinaRepository();


	/**
	 * EXEMPLOS SEM CDI
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarDisciplinaPorId(@PathParam("id") String id) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		DisciplinaVo disciplina = null;

		GenericEntity<DisciplinaVo> entity = null;

		try {

			Disciplina disciplinaEntity = disciplinaRepository.getDisciplina(Integer.parseInt(id));

			disciplina = DisciplinaVo.clone(disciplinaEntity);

			entity = new GenericEntity<DisciplinaVo>(disciplina) {};

			return Response.ok(entity).build();

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Nenhuma Disciplina encontrada para o Id: " + id);

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}

	@GET
	@Path("/listarDisciplinas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListarDisciplinas() {

		List<Disciplina> listaDisciplinas = disciplinaRepository.listarDisciplinas();

		List<DisciplinaVo> lista = DisciplinaVo.popularDisciplinas(listaDisciplinas);

		GenericEntity<List<DisciplinaVo>> entity = new GenericEntity<List<DisciplinaVo>>(lista) {};

		return Response.ok(entity).build();
	}

	@POST
	@Path("/cadastrarDisciplina")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarDisciplina(DisciplinaVo disciplina) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Disciplina disciplinaEntity = new Disciplina(disciplina.getDescricaoDisciplina());

		try {

			boolean sucesso = disciplinaRepository.salvar(disciplinaEntity);

			if(sucesso) {

				response.put("Mensagem", "A Disciplina " + disciplina.getDescricaoDisciplina() + " foi cadastrada com sucesso. "
						+ "URI Json: ../quizminado/rest/disciplina/cadastrarDisciplina");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao cadastrar a Disciplina: " + disciplina.getDescricaoDisciplina());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao cadastrar a Disciplina: " + disciplina.getDescricaoDisciplina());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}

	@PUT
	@Path("/alterarDisciplina")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarDisciplina(DisciplinaVo disciplina) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		Disciplina disciplinaEntity = null;

		String descricaoDisciplina = null;

		try {

			disciplinaEntity = disciplinaRepository.getDisciplina(disciplina.getIdDisciplina());

			if(disciplinaEntity == null) {

				throw new Exception();
			}

			descricaoDisciplina = disciplinaEntity.getDescricaoDisciplina();

			PropertyUtils.copyProperties(disciplinaEntity, disciplina);

			boolean sucesso = disciplinaRepository.alterar(disciplinaEntity);

			if(sucesso) {

				response.put("Mensagem", "A Disciplina " + descricaoDisciplina + " foi alterada para " 
						+ disciplinaEntity.getDescricaoDisciplina() + ". URI Json: ../quizminado/rest/disciplina/alterarDisciplina");

				json = new JSONObject(response);

				return Response.status(Response.Status.OK).entity(json).build();

			} else {

				response.put("Mensagem", "Erro ao alterar a Disciplina Id: " + disciplina.getIdDisciplina());

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Erro ao alterar a Disciplina Id: " + disciplina.getIdDisciplina());

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(json).build();
		}
	}

	@DELETE
	@Path("/deletarDisciplina/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarDisciplinaPorId(@PathParam("id") String id) {

		JSONObject json = null;

		Map<String, String> response = new HashMap<String, String>();

		String descricaoDisciplinaRemovida = null;

		try {

			Disciplina disciplinaEntity = disciplinaRepository.getDisciplina(Integer.parseInt(id));

			if(disciplinaEntity == null) {

				throw new Exception();
			}

			descricaoDisciplinaRemovida = disciplinaEntity.getDescricaoDisciplina();

			boolean sucesso = disciplinaRepository.excluir(disciplinaEntity);

			if(sucesso) {

				response.put("Mensagem", "Disciplina " + descricaoDisciplinaRemovida + " removida com sucesso.");

				json = new JSONObject(response);

				return Response.ok(json).build();

			} else {

				response.put("Mensagem", "Não foi possível remover a Disciplina Id: " + id);

				json = new JSONObject(response);

				return Response.status(Response.Status.NOT_FOUND).entity(json).build();
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.put("Mensagem", "Nenhuma Disciplina encontrada para o Id: " + id);

			json = new JSONObject(response);

			return Response.status(Response.Status.NOT_FOUND).entity(json).build();
		}
	}


	/**
	 * EXEMPLOS COM CDI
	 */
	@GET
	@Path("/getListaDisciplinas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaDisciplinas() {

		List<Disciplina> listaDisciplinas = disciplinaService.getListaDisciplinas();

		List<DisciplinaVo> lista = DisciplinaVo.popularDisciplinas(listaDisciplinas);

		GenericEntity<List<DisciplinaVo>> entity = new GenericEntity<List<DisciplinaVo>>(lista) {};

		return Response.ok(entity).build();
	}

	// NÃO SALVA NO BANCO
	@POST
	@Path("/salvarDisciplina/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarDisciplina(DisciplinaVo disciplina) {

		Disciplina disciplinaEntity = new Disciplina(disciplina.getDescricaoDisciplina());

		disciplinaService.salvar(disciplinaEntity);

		String output = "OK!";

		return Response.status(200).entity(output).build();
	}

}
