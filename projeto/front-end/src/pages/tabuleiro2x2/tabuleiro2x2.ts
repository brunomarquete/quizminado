import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ToastController, ModalController } from 'ionic-angular';
import { QuestaoService } from '../../providers/questao/questao.service';
import { Questao } from '../../models/questao.model';
import { AngularFireAuth } from 'angularfire2/auth';
import { HomePage } from '../home/home';
import { BombaPage } from '../bomba/bomba';
import { Utils } from '../../providers/utils/utils';
import { FaseConcluidaPage } from '../fase-concluida/fase-concluida';


@IonicPage()
@Component({
  selector: 'page-tabuleiro2x2',
  templateUrl: 'tabuleiro2x2.html',
})
export class Tabuleiro2x2Page {

  questoes : Array<Questao>;
  posicaoBomba : number;
  idQuestao : number;
  questao : Questao;
  isExibirTabuleiro : boolean = true;
  isExibirQuestao: boolean = false;
  posicao : number;
  posicoesAcertadas: number[] = [];
  
  constructor(private angularFireAuth: AngularFireAuth,
              public navCtrl: NavController,
              public navParams: NavParams, 
              private questaoService: QuestaoService,
              private utils : Utils,
              private toastCtrl: ToastController,
              public modalCtrl: ModalController)  {
        
  }

  ionViewDidLoad() {
    this.buscarQuestoes();
    this.buscarPosicaoBomba();
  }

  buscarQuestoes() {
    this.questaoService.buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom(this.angularFireAuth.auth.currentUser.email,"1","4").subscribe(
     (questoes) => {
       this.questoes = questoes.questao;
       console.log("Vieram " + this.questoes.length + " questões do serviço!");
     }
    );
  }

  buscarPosicaoBomba() {
    this.posicaoBomba = this.utils.gerarIntRandomico(1,4);
    console.log(this.posicaoBomba);
  }

  abrirQuestao(posicao: number) {

    if (this.posicoesAcertadas && this.posicoesAcertadas.indexOf(posicao) >= 0) {

      this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Você já acertou esta questão!' })
      .present();

    } else {

      this.posicao = posicao;

      if (posicao != this.posicaoBomba) {

          this.questao = this.questoes[posicao-1];
          this.questao.respostas.sort((a, b) : number => {
            if (a.letraResposta < b.letraResposta) return -1;
            if (a.letraResposta > b.letraResposta) return 1;
            return 0;
          })
          this.isExibirQuestao = true;
          this.isExibirTabuleiro = false;

      } else {
          this.bomba()
      }

    }

  }

  obterQuestao(idQuestao : number)  {
    
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

 responder(situacaoResposta: string, letraResposta: string) {

    let resultado;

    this.questaoService.responderQuestaoPorUsuario(this.angularFireAuth.auth.currentUser.email, 
                                                 this.questao.idQuestao.toString(), 
                                                 letraResposta)
                                                .subscribe(
    (resposta) => {

          resultado = resposta.Mensagem;

          console.log(resultado);

          if (resultado == 'Certo') {
    
            this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Parabéns! Você acertou!' })
            .present();
            this.isExibirQuestao = false;
            this.isExibirTabuleiro = true;
    
            if (this.posicao > 0) {
              this.posicoesAcertadas.push(this.posicao);
            }
    
            if (this.posicoesAcertadas.length == 3) {
              this.faseConcluida();
            }
    
        } else {
    
            this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Você errou! Game Over!' })
            .present();
            this.navCtrl.setRoot(HomePage);
        }

    });
  

}

  bomba() {
    let modal = this.modalCtrl.create(BombaPage);
    modal.present();
  }

  faseConcluida() {
    let modal = this.modalCtrl.create(FaseConcluidaPage);
    modal.present();
  }

  voltar() {
    this.isExibirQuestao = false;
    this.isExibirTabuleiro = true;
  }

  desistir() {
    this.posicao = 0;
    this.posicoesAcertadas = [];
    this.navCtrl.setRoot(HomePage);
  }

}
