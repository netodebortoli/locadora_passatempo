import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/base/base.service';

import { Classe } from './model/classe';

@Injectable({
  providedIn: 'root',
})
export class ClassesService extends BaseService<Classe> {
  constructor(protected override httpClient: HttpClient) {
    super("/api/classes", httpClient);
  }
}
