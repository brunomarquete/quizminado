import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { HomePage } from '../home/home';
import { Tabuleiro2x2Page } from '../tabuleiro2x2/tabuleiro2x2';

/**
 * Generated class for the BombaPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-bomba',
  templateUrl: 'bomba.html',
})
export class BombaPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  jogarNovamente(){
    this.navCtrl.setRoot(Tabuleiro2x2Page, {"nivelAtual": 1});
  }

  menu() {
    this.navCtrl.setRoot(HomePage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad BombaPage');
  }

}
