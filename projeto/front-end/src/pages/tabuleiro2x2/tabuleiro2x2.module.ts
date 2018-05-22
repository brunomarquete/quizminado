import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { Tabuleiro2x2Page } from './tabuleiro2x2';

@NgModule({
  declarations: [
    Tabuleiro2x2Page,
  ],
  imports: [
    IonicPageModule.forChild(Tabuleiro2x2Page),
  ],
  exports: [
    Tabuleiro2x2Page
  ]
})
export class Tabuleiro2x2PageModule {}
