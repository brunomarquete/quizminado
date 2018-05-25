import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { HomePage } from '../home/home';

@IonicPage()
@Component({
  selector: 'page-creditos',
  templateUrl: 'creditos.html',
})
export class CreditosPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    
  }

  voltar() {
    this.navCtrl.setRoot(HomePage)
  }

}
