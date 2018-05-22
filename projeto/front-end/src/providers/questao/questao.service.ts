import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BaseService } from '../base/base-service.service';
import { Questao } from '../../models/questao.model';
import { Observable } from 'rxjs/Observable';

/*
  Generated class for the QuestaoSerivce provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class QuestaoService extends BaseService<Questao>{

    constructor(http: Http) {

        super("questao", http)
    }

    buscarQuestaoPorDisciplinaENivelRandom(idDisciplina: string, idNivel: string): Observable<Questao> {
            
      return this.http.get(this.url('/buscarQuestaoPorDisciplinaENivelRandom'), this.config()).map(this.mapper)
    }

    buscarQuestoesPorDisciplinaENivelRandom(idDisciplina: string, idNivel: string): Observable<Questao[]> {
            
      return this.http.get(this.url('/buscarQuestoesPorDisciplinaENivelRandom'), this.config()).map(this.mapper)
    }

    buscarQuestoesPorUsuarioDisciplinaENivelRandom(idUsuario: string, idDisciplina: string, idNivel: string): Observable<Questao[]> {
            
      return this.http.get(this.url('/buscarQuestoesPorUsuarioDisciplinaENivelRandom'), this.config()).map(this.mapper)
    }

    responderQuestao(idQuestao: string, letraResposta: string): Observable<any> {
            
      return this.http.get(this.url('/responderQuestao'), this.config()).map(this.mapper)
    }

    responderQuestaoPorUsuario(idUsuario: string, idQuestao: string, letraResposta: string): Observable<any> {
            
      return this.http.get(this.url('/responderQuestaoPorUsuario'), this.config()).map(this.mapper)
    }

    buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom(idUsuario: string, idNivel: string, qtdQuestoes: string): Observable<any> {
            
      return this.http.get(this.url(`/buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom?idUsuario=${idUsuario}&idNivel=${idNivel}&qtdQuestoes=${qtdQuestoes}`), this.config()).map(this.mapper)
    }

    
}
