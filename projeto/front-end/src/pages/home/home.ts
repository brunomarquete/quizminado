import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { AuthService } from '../../providers/auth/auth-service';
import { LoginPage } from '../login/login';
import { DisciplinaService } from '../../providers/disciplina/disciplina.service';
import { Disciplina } from '../../models/disciplina.model';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, 
              private authService: AuthService,
              private toastCtrl: ToastController,
              private disciplinaService : DisciplinaService) {}

 disciplinas : Array<Disciplina>;

  listarDisciplinas() {
     this.disciplinaService.listar().subscribe(
       disciplinas => this.disciplinas = disciplinas
     );

  }

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

  ngOnInit() {
   this.listarDisciplinas(); 
  }

}