import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ResetarSenhaPage } from './resetar-senha';

@NgModule({
  declarations: [
    ResetarSenhaPage,
  ],
  imports: [
    IonicPageModule.forChild(ResetarSenhaPage),
  ],
  exports: [
    ResetarSenhaPage
  ]
})
export class ResetarSenhaPageModule {}