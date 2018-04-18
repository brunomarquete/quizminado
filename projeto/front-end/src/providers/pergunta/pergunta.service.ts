import { Http, Response, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Pergunta } from "../../models/pergunta.model";
import { DatePipe } from '@angular/common';
import { BaseService } from "../base/base-service.service";

@Injectable()
export class PerguntaService extends BaseService<Pergunta> {

    constructor(http: Http) {

        super("pergunta", http)
    }
}
