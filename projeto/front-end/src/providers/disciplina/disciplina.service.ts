import { Http, Response, Headers } from "@angular/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Disciplina } from "../../models/disciplina.model";
import { DatePipe } from '@angular/common';
import { BaseService } from "../base/base-service.service";

@Injectable()
export class DisciplinaService extends BaseService<Disciplina> {

    constructor(http: Http) {

        super("disciplina", http)
    }
}
