import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TabuleiroPage } from '../tabuleiro/tabuleiro';
import { HomePage } from '../home/home';
import { EventLoggerProvider } from '../../providers/event-logger/event-logger';

@IonicPage()
@Component({
  selector: 'page-jogo-concluido',
  templateUrl: 'jogo-concluido.html',
})
export class JogoConcluidoPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, public logger: EventLoggerProvider) {
  }

  jogarNovamente(){
    this.navCtrl.setRoot(TabuleiroPage, {"nivelAtual": 1});
  }

  menu() {
    this.navCtrl.setRoot(HomePage);
  }

  ionViewDidLoad() {
    this.logger.log('jogo_concluido', {})
  }

}
