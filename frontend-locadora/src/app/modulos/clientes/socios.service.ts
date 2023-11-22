import {Injectable} from '@angular/core';
import {BaseService} from "../../shared/base/base.service";
import {Cliente} from "./model/cliente";
import {HttpClient} from "@angular/common/http";
import {Socio} from "./model/socio";

@Injectable({
  providedIn: 'root'
})
export class SociosService extends BaseService<Socio> {

  constructor(protected override httpClient: HttpClient) {
    super("/api/socios", httpClient);
  }
}
