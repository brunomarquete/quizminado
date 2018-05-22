import { Entidade } from './entidade.model';
import { Questao } from './questao.model';

export class PosicaoQuestao extends Entidade{

    posicao : number;

    isBomba : boolean;

    questao: Questao;

    isAcertou: boolean;

}