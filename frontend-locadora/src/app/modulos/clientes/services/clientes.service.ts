import {Injectable} from '@angular/core';
import {BaseService} from "../../../shared/base/base.service";
import {Cliente} from "../model/cliente";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ClientesService extends BaseService<Cliente> {

  constructor(protected override httpClient: HttpClient) {
    super('/api/socios', httpClient);
  }


}
