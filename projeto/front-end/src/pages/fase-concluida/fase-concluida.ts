import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { TabuleiroPage } from '../tabuleiro/tabuleiro';

@IonicPage()
@Component({
  selector: 'page-fase-concluida',
  templateUrl: 'fase-concluida.html',
})
export class FaseConcluidaPage {

  nivelAtual : number;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  proximoNivel() {

    if (this.nivelAtual) {
      this.navCtrl.setRoot(TabuleiroPage, {"nivelAtual" : this.nivelAtual + 1});
    } 
    
  }

  ionViewDidLoad() {
      this.nivelAtual = this.navParams.get('nivelAtual');
      console.log("Modal fase concluída pegou o nivel: " + this.nivelAtual);
  }

}
