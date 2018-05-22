import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DisciplinaService } from '../../providers/disciplina/disciplina.service';
import { Disciplina } from '../../models/disciplina.model';
import { PosicaoQuestao } from '../../models/posicao-questao.model';
import { QuestaoService } from '../../providers/questao/questao.service';
import { User } from '../../providers/auth/user';
import { Questao } from '../../models/questao.model';
import { AngularFireAuth } from 'angularfire2/auth';
import { QuestaoPage } from '../questao/questao';
import { HomePage } from '../home/home';
import { BombaPage } from '../bomba/bomba';
import { Utils } from '../../providers/utils/utils';
import { EventEmitterService } from '../../providers/event-emitter/event-emitter.service';


@IonicPage()
@Component({
  selector: 'page-tabuleiro2x2',
  templateUrl: 'tabuleiro2x2.html',
})
export class Tabuleiro2x2Page {

  questoes : Array<Questao>;
  posicaoBomba : number;
  posicoesAcertadas : Array<boolean> = [false, false, false, false];
  
  constructor(private angularFireAuth: AngularFireAuth,
              public navCtrl: NavController,
              public navParams: NavParams, 
              private questaoService: QuestaoService,
              private utils : Utils) {

  }


  buscarQuestoes() {
    this.questaoService.buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom(this.angularFireAuth.auth.currentUser.uid,"1","4").subscribe(
     (questoes) => {
       this.questoes = questoes.questao;
     }
    );

 }

 abrirQuestao(posicao: number) {
   if (posicao != this.posicaoBomba) {
    console.log(this.questoes[posicao-1]);
    this.navCtrl.setRoot(QuestaoPage, {"idQuestao" : this.questoes[posicao-1].idQuestao, "posicaoQuestao" : posicao});
   } else {
    this.navCtrl.setRoot(BombaPage);
   }
 }

  buscarPosicaoBomba() {
      this.posicaoBomba = this.utils.gerarIntRandomico(1,4)
  }

  desistir() {
    this.navCtrl.setRoot(HomePage);
  }

  ionViewDidLoad() {
    this.buscarQuestoes();
    this.buscarPosicaoBomba();
    console.log("Posição da bomba: " + this.posicaoBomba);
  }

}
