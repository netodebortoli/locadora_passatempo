import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Uf} from "../../modulos/clientes/model/uf";
import {first} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UfService {

  constructor(protected httpClient: HttpClient) { }
  private apiUrl: string = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

  list() {
    return this.httpClient.get<Uf[]>(this.apiUrl).pipe(
        first()
    );
  }
}
