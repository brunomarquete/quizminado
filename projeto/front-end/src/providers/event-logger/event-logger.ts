import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FirebaseAnalytics } from '@ionic-native/firebase-analytics';

@Injectable()
export class EventLoggerProvider {

  constructor(public firebaseAnalytics: FirebaseAnalytics) {
    console.log('Hello EventLoggerProvider Provider');
  }
 
  log(name:string,value:any){
    this.firebaseAnalytics.logEvent(name, { pram:value })
  }

}
