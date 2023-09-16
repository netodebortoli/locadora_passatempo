import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/base/base.service';

import { Diretor } from '../model/diretor';

@Injectable({
  providedIn: 'root',
})
export class DiretoresService extends BaseService<Diretor> {
  constructor(protected override httpClient: HttpClient) {
    super("/api/diretores", httpClient);
  }
}
