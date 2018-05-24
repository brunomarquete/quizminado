import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BaseService } from '../base/base-service.service';
import { Questao } from '../../models/questao.model';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class QuestaoService extends BaseService<Questao>{

    constructor(http: Http) {

        super("questao", http)
    }

    buscarQuestaoPorDisciplinaENivelRandom(idDisciplina: string, idNivel: string): Observable<Questao> {
            
      return this.http.get(this.url(`/buscarQuestaoPorDisciplinaENivelRandom?idDisciplina=${idDisciplina}&idNivel=${idNivel}`), this.config()).map(this.mapper)
    }

    buscarQuestoesPorDisciplinaENivelRandom(idDisciplina: string, idNivel: string): Observable<Questao[]> {
            
      return this.http.get(this.url(`/buscarQuestoesPorDisciplinaENivelRandom?idDisciplina=${idDisciplina}&idNivel=${idNivel}`), this.config()).map(this.mapper)
    }

    buscarQuestoesPorUsuarioDisciplinaENivelRandom(idUsuario: string, idDisciplina: string, idNivel: string): Observable<Questao[]> {
            
      return this.http.get(this.url(`/buscarQuestoesPorUsuarioDisciplinaENivelRandom?idUsuario=${idUsuario}&idDisciplina=${idDisciplina}&idNivel=${idNivel}`), this.config()).map(this.mapper)
    }

    buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom(idUsuario: string, idNivel: string, qtdQuestoes: string): Observable<any> {
            
      return this.http.get(this.url(`/buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom?idUsuario=${idUsuario}&idNivel=${idNivel}&qtdQuestoes=${qtdQuestoes}`), this.config()).map(this.mapper)
    }
    
    responderQuestao(idQuestao: string, letraResposta: string): Observable<any> {
            
      return this.http.get(this.url(`/responderQuestao?idQuestao=${idQuestao}&letraResposta=${letraResposta}`), this.config()).map(this.mapper)
    }

    responderQuestaoPorUsuario(idUsuario: string, idQuestao: string, letraResposta: string): Observable<any> {
            
      return this.http.get(this.url(`/responderQuestaoPorUsuario?idUsuario=${idUsuario}&idQuestao=${idQuestao}&letraResposta=${letraResposta}`), this.config()).map(this.mapper)
    }
    
}
