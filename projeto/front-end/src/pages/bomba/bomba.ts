import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { HomePage } from '../home/home';
import { TabuleiroPage } from '../tabuleiro/tabuleiro';

@IonicPage()
@Component({
  selector: 'page-bomba',
  templateUrl: 'bomba.html',
})
export class BombaPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  jogarNovamente(){
    this.navCtrl.setRoot(TabuleiroPage, {"nivelAtual": 1});
  }

  menu() {
    this.navCtrl.setRoot(HomePage);
  }

  ionViewDidLoad() {
    
  }

}
