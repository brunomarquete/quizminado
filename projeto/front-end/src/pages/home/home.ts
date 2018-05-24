import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { AuthService } from '../../providers/auth/auth-service';
import { LoginPage } from '../login/login';
import { Disciplina } from '../../models/disciplina.model';
import { Tabuleiro2x2Page } from '../tabuleiro2x2/tabuleiro2x2';

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
    this.navCtrl.setRoot(Tabuleiro2x2Page);
  }

  ngOnInit() {

  }

}