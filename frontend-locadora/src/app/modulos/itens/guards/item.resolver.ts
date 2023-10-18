import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { ItensService } from '../itens.service';
import { Item } from '../model/item';

export const itemResolver: ResolveFn<Observable<Item>> = (
  route,
  _state,
  itensService: ItensService = inject(ItensService)) => {
  if(route.params?.['id']){
    return itensService.loadById(route.params['id']);
  }
  return of({} as Item)
};
