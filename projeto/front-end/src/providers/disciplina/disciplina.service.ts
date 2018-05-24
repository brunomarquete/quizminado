import { Http } from "@angular/http";
import { Injectable } from "@angular/core";
import { Disciplina } from "../../models/disciplina.model";
import { BaseService } from "../base/base-service.service";

@Injectable()
export class DisciplinaService extends BaseService<Disciplina> {

    constructor(http: Http) {

        super("disciplina", http)
    }
}
