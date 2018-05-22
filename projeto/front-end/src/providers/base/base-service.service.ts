import { Injectable, Injector, Inject, ReflectiveInjector } from "@angular/core";
import { Http, Headers, Response, RequestOptionsArgs } from "@angular/http"
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Observable } from "rxjs/Observable";
import { Entidade } from "../../models/entidade.model";
import { Environment } from "../../environments/environment";

@Injectable()
export abstract class BaseService<T extends Entidade> {

    protected api: string

    path: string

    http: Http;

    constructor(path: string, http: Http) {

        this.api = Environment.api

        this.path = path

        this.http = http
    }

    protected config(): RequestOptionsArgs {

        return {

            headers: this.headers()
        }
    }

    protected headers(): Headers {

        return new Headers({

            'Content-Type': 'application/json',

            'Accept': 'application/json'
        });
    }

    protected url(op: string): string {

        return this.api + this.path + op
    }

    protected mapper(resp: Response) {

        return resp.json() || {};
    }

    listar(): Observable<T[]> {
        
        return this.http.get(this.url('/listar'), this.config()).map(this.mapper)
    }

    obter(id: number): Observable<T> {

        return this.http.get(this.url(`/obter/${id}`), this.config()).map(this.mapper)
    }

    excluir(id: number): Observable<any> {

        return this.http.post(this.url(`/excluir/${id}`), null, this.config()).map(this.mapper)
    }

    salvar(entidade: T): Observable<any> {

        return this.http.post(this.url('/salvar'), JSON.stringify(entidade), this.config()).map(this.mapper)
    }

    alterar(entidade: T): Observable<any> {

        return this.http.post(this.url('/alterar'), JSON.stringify(entidade), this.config()).map(this.mapper)
    }
}