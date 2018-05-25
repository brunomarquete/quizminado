import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TabuleiroPage } from '../tabuleiro/tabuleiro';
import { EventLoggerProvider } from '../../providers/event-logger/event-logger';

@IonicPage()
@Component({
  selector: 'page-nivel-concluido',
  templateUrl: 'nivel-concluido.html',
})
export class NivelConcluidoPage {

  nivelAtual : number;

  constructor(public navCtrl: NavController, public navParams: NavParams, public logger: EventLoggerProvider) {
  }

  proximoNivel() {

    if (this.nivelAtual) {
      this.navCtrl.setRoot(TabuleiroPage, {"nivelAtual" : this.nivelAtual + 1});
    } 
    
  }

  ionViewDidLoad() {
      this.nivelAtual = this.navParams.get('nivelAtual');
      console.log("Modal nível concluído pegou o nivel: " + this.nivelAtual);
      this.logger.log(`nivel_${this.nivelAtual}`, {})
  }

}
