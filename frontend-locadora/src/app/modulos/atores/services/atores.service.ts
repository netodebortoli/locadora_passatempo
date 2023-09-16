import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/base/base.service';

import { Ator } from '../model/ator';

@Injectable({
  providedIn: 'root',
})
export class AtoresService extends BaseService<Ator> {
  constructor(protected override httpClient: HttpClient) {
    super("/api/atores", httpClient);
  }
}
