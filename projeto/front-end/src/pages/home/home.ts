import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { AuthService } from '../../providers/auth/auth-service';
import { LoginPage } from '../login/login';
import { Disciplina } from '../../models/disciplina.model';
import { TabuleiroPage } from '../tabuleiro/tabuleiro';
import { CreditosPage } from '../creditos/creditos';
import { InAppBrowser, InAppBrowserOptions } from '@ionic-native/in-app-browser';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, 
              private authService: AuthService,
              private toastCtrl: ToastController) {}

 disciplinas : Array<Disciplina>;

  signOut() {
    this.authService.signOut()
      .then(() => {
        this.navCtrl.setRoot(LoginPage);
      })
      .catch((error) => {
        this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Erro ao tentar deslogar' })
          .present();
      });
  }

  jogar() {
    this.navCtrl.setRoot(TabuleiroPage, {"nivelAtual" : 1});
  }

  creditos() {
    this.navCtrl.setRoot(CreditosPage);
  }

  ngOnInit() {

  }

}