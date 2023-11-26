import {Injectable} from '@angular/core';
import {BaseService} from "../../../shared/base/base.service";
import {Cliente} from "../model/cliente";
import {HttpClient} from "@angular/common/http";
import {Socio} from "../model/socio";
import {Dependente} from "../model/dependente";

@Injectable({
  providedIn: 'root'
})
export class SociosService extends BaseService<Socio> {

  constructor(protected override httpClient: HttpClient) {
    super("/api/socios", httpClient);
  }

  ativarSocio(id: string) {
    return this.httpClient.patch<Socio>(`${this.apiUrl}/${id}/status`, 'Ativo');
  }

  ativarDependente(id: string) {
    return this.httpClient.patch<Dependente>(`${this.apiUrl}/dependentes/${id}/status`, 'Ativo');
  }

  desativarSocio(id: string) {
    return this.httpClient.patch<Socio>(`${this.apiUrl}/${id}/status`, 'Inativo');
  }

  desativarDependente(id: string) {
    return this.httpClient.patch<Dependente>(`${this.apiUrl}/dependentes/${id}/status`, 'Inativo');
  }

}
