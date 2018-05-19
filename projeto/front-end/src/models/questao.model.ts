import { Entidade } from './entidade.model';
import { List } from 'ionic-angular';
import { Resposta } from './resposta.model';
import { Disciplina } from './disciplina.model';
import { Nivel } from './nivel.model';

export class Questao extends Entidade{

    idQuestao : number;
	descricaoQuestao : string;
    idDisciplina : number;
    disciplina : Disciplina;
    idNivel : number;
    nivel: Nivel;
    respostas : Resposta[];

}