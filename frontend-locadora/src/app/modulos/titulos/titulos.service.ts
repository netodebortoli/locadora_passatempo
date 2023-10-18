import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { BaseService } from '../../shared/base/base.service';
import { Titulo } from './model/titulo';


@Injectable({
  providedIn: 'root',
})
export class TitulosService extends BaseService<Titulo> {
  constructor(protected override httpClient: HttpClient) {
    super("/api/titulos", httpClient)
  }
}
