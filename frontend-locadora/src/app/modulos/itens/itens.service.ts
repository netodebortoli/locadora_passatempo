import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/base/base.service';

import { Item } from './model/item';

@Injectable({
  providedIn: 'root',
})
export class ItensService extends BaseService<Item> {
  constructor(protected override httpClient: HttpClient) {
    super('/api/itens', httpClient);
  }
}
