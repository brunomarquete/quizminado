import { Injectable } from '@angular/core';
import { FirebaseAnalytics } from '@ionic-native/firebase-analytics';
import { Platform } from 'ionic-angular';

@Injectable()
export class EventLoggerProvider {

  constructor(public firebaseAnalytics: FirebaseAnalytics, private platform: Platform) {
  }
 
  log(name:string,value:any){
    if (this.platform.is('cordova')) {
      this.firebaseAnalytics.logEvent(name, { pram:value })
      .then((res: any) => console.log(res))
      .catch((error: any) => console.error(error));
    }
  }

}
