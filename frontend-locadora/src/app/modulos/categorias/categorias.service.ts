import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/base/base.service';

import { Categoria } from './model/categoria';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CategoriasService extends BaseService<Categoria> {
  constructor(protected override httpClient: HttpClient) {
    super('/api/titulos/categorias', httpClient);
  }
}
