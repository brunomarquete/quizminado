import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TabuleiroPage } from './tabuleiro';

@NgModule({
  declarations: [
    TabuleiroPage,
  ],
  imports: [
    IonicPageModule.forChild(TabuleiroPage),
  ],
  exports: [
    TabuleiroPage
  ]
})
export class TabuleiroPageModule {}
