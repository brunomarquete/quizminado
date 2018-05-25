import { Component, ViewChild } from '@angular/core';
import { IonicPage, NavController, ToastController } from 'ionic-angular';
import { NgForm } from '@angular/forms';
import { User } from '../../providers/auth/user';
import { AuthService } from '../../providers/auth/auth-service';
import { HomePage } from '../home/home';
import { CadastroEmailPage } from '../cadastro-email/cadastro-email';
import { LoginEmailPage } from '../login-email/login-email';
import { EventLoggerProvider } from '../../providers/event-logger/event-logger';

@IonicPage()
@Component({
  selector: 'page-signin',
  templateUrl: 'login.html',
})
export class LoginPage {
  user: User = new User();
  @ViewChild('form') form: NgForm;

  constructor(
    public navCtrl: NavController,
    private toastCtrl: ToastController,
    private authService: AuthService,
    public logger: EventLoggerProvider) {
  }

  ionViewDidEnter() {
    this.logger.log('abriu_tela_login', {})
  }

  createAccount() {
    this.navCtrl.push(CadastroEmailPage);
  }

  signInWithEmailPage() {
    this.navCtrl.push(LoginEmailPage);
  }

  signInWithGoogle() {
    this.authService.signInWithGoogle()
      .then(() => {
        this.navCtrl.setRoot(HomePage);
      })
      .catch((error) => {
        this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Erro ao efetuar o login' })
          .present();
      });
  }

  signInWithFacebook() {
    this.authService.signInWithFacebook()
      .then(() => {
        this.navCtrl.setRoot(HomePage);
      })
      .catch((error) => {
        this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Erro ao efetuar o login' })
          .present();
      });
  }

  signInWithTwitter() {
    this.authService.signInWithTwitter()
      .then(() => {
        this.navCtrl.setRoot(HomePage);
      })
      .catch((error) => {
        this.toastCtrl.create({ duration: 3000, position: 'bottom', message: 'Erro ao efetuar o login' })
          .present();
      });
  }

}
