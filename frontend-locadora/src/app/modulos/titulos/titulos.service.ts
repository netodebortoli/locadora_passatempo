import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Titulo } from './model/titulo';
import {BaseService} from "../../shared/base/base.service";


@Injectable({
  providedIn: 'root',
})
export class TitulosService extends BaseService<Titulo> {
  constructor(protected override httpClient: HttpClient) {
    super("/api/titulos", httpClient)
  }
}
