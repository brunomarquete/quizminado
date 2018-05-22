import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BombaPage } from './bomba';

@NgModule({
  declarations: [
    BombaPage,
  ],
  imports: [
    IonicPageModule.forChild(BombaPage),
  ],
})
export class BombaPageModule {}
