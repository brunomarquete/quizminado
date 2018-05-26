import { Environment } from '../../environments/environment';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ToastController, ModalController, LoadingController } from 'ionic-angular';
import { QuestaoService } from '../../providers/questao/questao.service';
import { Questao } from '../../models/questao.model';
import { AngularFireAuth } from 'angularfire2/auth';
import { HomePage } from '../home/home';
import { BombaPage } from '../bomba/bomba';
import { Utils } from '../../providers/utils/utils';
import { NivelConcluidoPage } from '../nivel-concluido/nivel-concluido';
import { JogoConcluidoPage } from '../jogo-concluido/jogo-concluido';
import { EventLoggerProvider } from '../../providers/event-logger/event-logger';

@IonicPage()
@Component({
  selector: 'page-tabuleiro',
  templateUrl: 'tabuleiro.html',
})
export class TabuleiroPage {

  nivelAtual: number = 1;
  bombas: number;
  posicoes: number;
  posicoesVitoria: number;
  dificuldade: number;

  questoes : Array<Questao>;
  posicoesBomba : number[] = [];
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
              public modalCtrl: ModalController,
              public loadingCtrl: LoadingController,
              public logger: EventLoggerProvider)  {
        
  }

  ionViewDidLoad() {

    this.nivelAtual = this.navParams.get('nivelAtual');

    let nivel = Environment.niveis.filter((nivel) => nivel.numero == this.nivelAtual)[0];

    this.posicoes = nivel.posicoes
    this.bombas = nivel.bombas
    this.posicoesVitoria = nivel.posicoesVitoria
    this.dificuldade = nivel.dificuldade

    console.log("Nível: " + nivel.numero + " Bombas: " + nivel.bombas + " Dificuldade: " + nivel.dificuldade);

    this.buscarQuestoes();
    this.sortearBomba();
  }

  buscarQuestoes() {

    let loading = this.loadingCtrl.create({content:'Sorteando questões...'});
    loading.present(loading);
    
    this.questaoService.buscarQuestoesPorUsuarioNivelEQtdQuestoesRandom(
                                this.angularFireAuth.auth.currentUser.email,
                                this.dificuldade.toString(), 
                                this.posicoes.toString())
     .subscribe(
        (questoes) => {
          this.questoes = questoes.questao;
          if (this.questoes) {
              console.log("Vieram " + this.questoes.length + " questões do serviço!");
              loading.dismiss();
          } else {
              console.log(" Não vieram questões do serviço!");
          }
        }
    );
  }

  sortearBomba() {

    if (this.bombas > 0) {

      let i;
      for (i = 1; i <= this.bombas;) {
        
        let posicaoBomba = this.utils.gerarIntRandomico(1, this.posicoes);

        if (this.posicoesBomba.indexOf(posicaoBomba) < 0) {
          this.posicoesBomba.push(posicaoBomba);
          console.log("Posição da bomba " + i + ": "  + posicaoBomba);
          i++;
        }

      }

      
      
    } else {
      console.log("Não tem bomba!");
    }

  }

  abrirQuestao(posicao: number) {

    if (this.posicoesAcertadas && this.posicoesAcertadas.indexOf(posicao) >= 0) {

      this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Você já acertou esta questão!' })
      .present();

    } else {

      this.posicao = posicao;

      if (this.posicoesBomba.indexOf(posicao) < 0) {

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

    this.logger.log("respondeu", {});

    let loading = this.loadingCtrl.create({content:'Respondendo questão...'});
    loading.present(loading);

    let resultado;

    this.questaoService.responderQuestaoPorUsuario(this.angularFireAuth.auth.currentUser.email, 
                                                 this.questao.idQuestao.toString(), 
                                                 letraResposta)
                                                .subscribe(
    (resposta) => {

          resultado = resposta.Mensagem;

          console.log(resultado);

          loading.dismiss();

          if (resultado == 'Certo') {

            this.logger.log("acertou", {});
    
            this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Parabéns! Você acertou!' })
            .present();
            this.isExibirQuestao = false;
            this.isExibirTabuleiro = true;
    
            if (this.posicao > 0) {
              this.posicoesAcertadas.push(this.posicao);
            }
    
            if (this.posicoesAcertadas.length == this.posicoesVitoria) {

              if (this.nivelAtual == Environment.ultimo_nivel) {

                this.logger.log("jogo_concluido", {});
                this.navCtrl.setRoot(JogoConcluidoPage)

              } else {
                this.nivelConcluido();
              }
            }
    
        } else {

            this.logger.log("errou", {});
    
            this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Você errou! Game Over!' })
            .present();
            this.limparQuestoesRespondidasUsuario();
            this.navCtrl.setRoot(HomePage);
        }

    });
  

}

  bomba() {

    this.logger.log("bomba", {});

    let modal = this.modalCtrl.create(BombaPage);
    modal.present();
    this.limparQuestoesRespondidasUsuario()
  }

  nivelConcluido() {

    let descLog : string = "nivel" + this.nivelAtual + 1;
    this.logger.log(descLog, {});

    let modal = this.modalCtrl.create(NivelConcluidoPage, {"nivelAtual" : this.nivelAtual});
    modal.present();
  }

  desistir() {

    this.logger.log("desistiu", {});

    this.posicao = 0;
    this.posicoesAcertadas = [];
    this.limparQuestoesRespondidasUsuario();
    this.navCtrl.setRoot(HomePage);
  }

  limparQuestoesRespondidasUsuario() {

    this.questaoService.limparQuestoesRespondidasPorUsuario(this.angularFireAuth.auth.currentUser.email).subscribe(
      () => {
        console.log("Questões limpadas.");
      }
    );

  }

}
