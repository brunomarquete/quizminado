/** 
												################################################
												###   Serviços REST - Projeto: quizminado 	###
												## 				../rest/	content	 			 ##
												################################################ 
 */

/*
	"projeto_quizminado": [

		// ******************************************************** REST SERVICES DISCIPLINA ********************************************************
		{
			"description": "Listar Disciplina por Id",
			"method": "GET",
			"href": "../quizminado/rest/disciplina/{id}",
			"rel": "find"
		},
		{
			"description": "Listar todas as Disciplinas",
			"method": "GET",
			"href": "../quizminado/rest/disciplina/listarDisciplinas",
			"rel": "findAll"
		},
		{
			"description": "Cadastrar Disciplina",
			"method": "POST",
			"href": "../quizminado/rest/disciplina/cadastrarDisciplina",
			"rel": "persist",
			"content-type": "application/json",
			"payload": "{"descricaoDisciplina":"descricao"}"
		},
		{
			"description": "Alterar Disciplina",
			"method": "PUT",
			"href": "../quizminado/rest/disciplina/alterarDisciplina",
			"rel": "merge",
			"content-type": "application/json",
			"payload": "{"idDisciplina":"id", "descricaoDisciplina":"descricao"}"
		},
		{
			"description": "Deletar Disciplina por Id",
			"method": "DELETE",
			"href": "../quizminado/rest/disciplina/deletarDisciplina/{id}",
			"rel": "delete"
		}
		
		// ******************************************************** REST SERVICES NIVEL ********************************************************
		{
			"description": "Listar Nível por Id",
			"method": "GET",
			"href": "../quizminado/rest/nivel/{id}",
			"rel": "find"
		},
		{
			"description": "Listar todos os Níveis",
			"method": "GET",
			"href": "../quizminado/rest/nivel/listarNiveis",
			"rel": "findAll"
		},
		{
			"description": "Cadastrar Nível",
			"method": "POST",
			"href": "../quizminado/rest/nivel/cadastrarNivel",
			"rel": "persist",
			"content-type": "application/json",
			"payload": "{"descricaoNivel":"descricao", "numeroDificuldade":"numero"}"
		},
		{
			"description": "Alterar Nível",
			"method": "PUT",
			"href": "../quizminado/rest/nivel/alterarNivel",
			"rel": "merge",
			"content-type": "application/json",
			"payload": "{"idNivel":"id", "descricaoNivel":"descricao", "numeroDificuldade":"numero"}"
		},
		{
			"description": "Deletar Nível por Id",
			"method": "DELETE",
			"href": "../quizminado/rest/nivel/deletarNivel/{id}",
			"rel": "delete"
		}
		
		// ******************************************************** REST SERVICES QUESTÃO ********************************************************
		{
			"description": "Listar Questão por Id",
			"method": "GET",
			"href": "../quizminado/rest/questao/{id}",
			"rel": "find"
		},
		{
			"description": "Listar todas as Questões",
			"method": "GET",
			"href": "../quizminado/rest/questao/listarQuestoes",
			"rel": "findAll"
		},
		{
			"description": "Buscar Questão por Disciplina e Nivel Aleatoriamente",
			"method": "GET",
			"href": "../quizminado/rest/questao/buscarQuestaoPorDisciplinaENivelRandom?idDisciplina={idDisciplina}&idNivel={idNivel}",
			"rel": "findAll",
			"queryParams": "?idDisciplina={idDisciplina}&idNivel={idNivel}"
		},
		{
			"description": "Buscar Questões por Disciplina e Nivel Aleatoriamente",
			"method": "GET",
			"href": "../quizminado/rest/questao/buscarQuestoesPorDisciplinaENivelRandom?idDisciplina={idDisciplina}&idNivel={idNivel}",
			"rel": "findAll",
			"queryParams": "?idDisciplina={idDisciplina}&idNivel={idNivel}"
		},
		{
			"description": "Buscar Questões por Usuario, Disciplina e Nivel Aleatoriamente",
			"method": "GET",
			"href": "../quizminado/rest/questao/buscarQuestoesPorUsuarioDisciplinaENivelRandom?idUsuario={idUsuario}&idDisciplina={idDisciplina}&idNivel={idNivel}",
			"rel": "findAll",
			"queryParams": "?idUsuario={idUsuario}&idDisciplina={idDisciplina}&idNivel={idNivel}"
		},
		{
			"description": "Buscar Questões por Usuario, Nivel e Quantidade de Questoes Aleatoriamente",
			"method": "GET",
			"href": "../quizminado/rest/questao/buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom?idUsuario={idUsuario}&idNivel={idNivel}&qtdQuestoes={qtdQuestoes}",
			"rel": "findAll",
			"queryParams": "?idUsuario={idUsuario}&idNivel={idNivel}&qtdQuestoes={qtdQuestoes}"
		},
		{
			"description": "Responder Questão por Disciplina e Nivel",
			"method": "GET",
			"href": "../quizminado/rest/questao/responderQuestao?idQuestao={idQuestao}&letraResposta={letraResposta}",
			"rel": "findAll",
			"queryParams": "?idQuestao={idQuestao}&letraResposta={letraResposta}"
		},
		{
			"description": "Limpar Questões Respondidas por Usuário",
			"method": "GET",
			"href": "../quizminado/rest/questao/limparQuestoesRespondidasPorUsuario?idUsuario={idUsuario}",
			"queryParams": "?idUsuario={idUsuario}"
		},
		{
			"description": "Limpar Todas as Questões",
			"method": "GET",
			"href": "../quizminado/rest/questao/limparTodasQuestoesRespondidas"
		},
		{
			"description": "Responder Questão por Usuario, Disciplina e Nivel",
			"method": "GET",
			"href": "../quizminado/rest/questao/responderQuestaoPorUsuario?idUsuario={idUsuario}&idQuestao={idQuestao}&letraResposta={letraResposta}",
			"rel": "findAll",
			"queryParams": "?idUsuario={idUsuario}&idQuestao={idQuestao}&letraResposta={letraResposta}"
		},
		{
			"description": "Cadastrar Questão",
			"method": "POST",
			"href": "../quizminado/rest/questao/cadastrarQuestao",
			"rel": "persist",
			"content-type": "application/json",
			"payload": "{"descricaoQuestao":"descricao", "idDisciplina":"id", "idNivel":"id"}"
		},
		{
			"description": "Alterar Questão",
			"method": "PUT",
			"href": "../quizminado/rest/questao/alterarQuestao",
			"rel": "merge",
			"content-type": "application/json",
			"payload": "{"idQuestao":"id", "descricaoQuestao":"descricao", "idDisciplina":"id", "idNivel":"id"}"
		},
		{
			"description": "Deletar Questão por Id",
			"method": "DELETE",
			"href": "../quizminado/rest/questao/deletarQuestao/{id}",
			"rel": "delete"
		}
		
		// ******************************************************** REST SERVICES RESPOSTA ********************************************************
		{
			"description": "Listar Resposta por Id",
			"method": "GET",
			"href": "../quizminado/rest/resposta/{id}",
			"rel": "find"
		},
		{
			"description": "Listar Resposta Certa por Questão",
			"method": "GET",
			"href": "../quizminado/rest/resposta/buscarRespostaCertaPorQuestao/{idQuestao}",
			"rel": "find"
		},
		{
			"description": "Listar Resposta por Questão",
			"method": "GET",
			"href": "../quizminado/rest/resposta/buscarRespostasPorQuestao/{idQuestao}",
			"rel": "find"
		},
		{
			"description": "Listar todas as Respostas",
			"method": "GET",
			"href": "../quizminado/rest/resposta/listarRespostas",
			"rel": "findAll"
		},
		{
			"description": "Cadastrar Resposta",
			"method": "POST",
			"href": "../quizminado/rest/resposta/cadastrarResposta",
			"rel": "persist",
			"content-type": "application/json",
			"payload": "{"descricaoResposta":"resposta", "situacaoResposta":"situacao", "letraResposta":"letra", "idQuestao":"id"}"
		},
		{
			"description": "Alterar Resposta",
			"method": "PUT",
			"href": "../quizminado/rest/resposta/alterarResposta",
			"rel": "merge",
			"content-type": "application/json",
			"payload": "{"idResposta":"id", "descricaoResposta":"descricao", "situacaoResposta":"situacao", "letraResposta":"letra", "idQuestao":"id"}"
		},
		{
			"description": "Deletar Resposta por Id",
			"method": "DELETE",
			"href": "../quizminado/rest/resposta/deletarResposta/{id}",
			"rel": "delete"
		}
		
	]
 */ 