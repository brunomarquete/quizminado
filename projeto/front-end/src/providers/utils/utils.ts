import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class Utils {

  constructor() {
   
  }

  gerarIntRandomico(min: number, max: number) : number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

}
