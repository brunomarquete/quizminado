import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CadastroEmailPage } from './cadastro-email';

@NgModule({
  declarations: [
    CadastroEmailPage,
  ],
  imports: [
    IonicPageModule.forChild(CadastroEmailPage),
  ],
  exports: [
    CadastroEmailPage
  ]
})
export class CadastroEmailPageModule {}