import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { Ng2OrderModule } from 'ng2-order-pipe';

import { AngularFireModule } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireDatabaseModule } from 'angularfire2/database';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';

import { AuthService } from '../providers/auth/auth-service';

import { GooglePlus } from '@ionic-native/google-plus';
import { Facebook } from '@ionic-native/facebook';
import { TwitterConnect } from '@ionic-native/twitter-connect';
import { FirebaseAnalytics } from '@ionic-native/firebase-analytics';

import { LoginPageModule } from '../pages/login/login.module';
import { LoginEmailPageModule } from '../pages/login-email/login-email.module';
import { CadastroEmailPageModule } from '../pages/cadastro-email/cadastro-email.module';
import { ResetarSenhaPageModule } from '../pages/resetar-senha/resetar-senha.module';
import { DisciplinaService } from '../providers/disciplina/disciplina.service';
import { HttpModule } from '@angular/http';
import { QuestaoService } from '../providers/questao/questao.service';
import { TabuleiroPageModule } from '../pages/tabuleiro/tabuleiro.module';
import { BombaPageModule } from '../pages/bomba/bomba.module';
import { Utils } from '../providers/utils/utils';
import { EventEmitterService } from '../providers/event-emitter/event-emitter.service';
import { NivelConcluidoPageModule } from '../pages/nivel-concluido/nivel-concluido.module';
import { EventLoggerProvider } from '../providers/event-logger/event-logger';
import { JogoConcluidoPageModule } from '../pages/jogo-concluido/jogo-concluido.module';
import { CreditosPageModule } from '../pages/creditos/creditos.module';

const firebaseConfig = {
  apiKey: "AIzaSyCcQMH4HuA9U0uXTXRhFriPK7ni_gvHVeA",
  authDomain: "quizminado.firebaseapp.com",
  databaseURL: "https://quizminado.firebaseio.com",
  projectId: "quizminado",
  storageBucket: "quizminado.appspot.com",
  messagingSenderId: "642248937925"
};

@NgModule({
  declarations: [
    MyApp,
    HomePage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFireAuthModule,
    AngularFireDatabaseModule,
    LoginPageModule,
    LoginEmailPageModule,
    CadastroEmailPageModule,
    ResetarSenhaPageModule,
    TabuleiroPageModule,
    NivelConcluidoPageModule,
    JogoConcluidoPageModule,
    BombaPageModule,
    CreditosPageModule,
    HttpModule,
    Ng2OrderModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthService,
    GooglePlus,
    Facebook,
    TwitterConnect,
    DisciplinaService,
    QuestaoService,
    Utils,
    EventEmitterService,
    FirebaseAnalytics,
    EventLoggerProvider
  ]
})
export class AppModule {}
