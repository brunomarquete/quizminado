import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-fase-concluida',
  templateUrl: 'fase-concluida.html',
})
export class FaseConcluidaPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  proximoNivel() {
    
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad FaseConcluidaPage');
  }

}
