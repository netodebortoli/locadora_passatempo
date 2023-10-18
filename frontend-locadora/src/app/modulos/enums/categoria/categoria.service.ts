import { Injectable } from '@angular/core';
import {BaseEnumService} from "../../../shared/base/base-enum.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends BaseEnumService{

  constructor(protected override httpClient: HttpClient) {
    super("/api/enumeradores/categorias", httpClient);
  }
}
