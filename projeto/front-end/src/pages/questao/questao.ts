import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ToastController } from 'ionic-angular';
import { Questao } from '../../models/questao.model';
import { QuestaoService } from '../../providers/questao/questao.service';
import { Tabuleiro2x2Page } from '../tabuleiro2x2/tabuleiro2x2';
import { Ng2OrderModule } from 'ng2-order-pipe';
import { HomePage } from '../home/home';
import { EventEmitterService } from '../../providers/event-emitter/event-emitter.service';


@IonicPage()
@Component({
  selector: 'page-questao',
  templateUrl: 'questao.html',
})
export class QuestaoPage {

  posicoesAcertadas : number[];
  posicaoAtual : number;
  questao : Questao;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              private questaoService: QuestaoService,
              private ng2OrderModule: Ng2OrderModule,
              private toastCtrl: ToastController) {

          this.posicoesAcertadas = this.navParams.get('posicoesAcertadas');
          this.posicaoAtual = this.navParams.get('posicaoAtual');

          let idQuestao = this.navParams.get('idQuestao');
          this.questao = new Questao();

          this.questaoService.obter(idQuestao).subscribe(
            (questao) => {
                this.questao = questao
                this.questao.respostas.sort((a, b) : number => {
                  if (a.letraResposta < b.letraResposta) return -1;
                  if (a.letraResposta > b.letraResposta) return 1;
                  return 0;
                })
            }
        );
  }

  responder(situacaoResposta: string) {

    if (situacaoResposta == 'C') {
     
      this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Parabéns! Você acertou!' })
      .present();
      this.navCtrl.setRoot(Tabuleiro2x2Page, {"posicoesAcertadas" : this.posicoesAcertadas, "posicaoAtual" : this.posicaoAtual});

    } else {

      this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Você errou! Game Over!' })
      .present();
      this.navCtrl.setRoot(HomePage);
    }

  }

  voltar() {
    this.navCtrl.setRoot(Tabuleiro2x2Page);
  }

  ionViewDidLoad() {
  
  }

}
