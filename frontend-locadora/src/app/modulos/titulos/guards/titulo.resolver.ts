import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Titulo } from '../model/titulo';
import { TitulosService } from '../titulos.service';

export const tituloResolver: ResolveFn<Observable<Titulo>> = (
  route,
  _state,
  titulosService: TitulosService = inject(TitulosService)) => {
  if (route.params?.['id']){
    return titulosService.loadById(route.params['id']);
  };
  return of({} as Titulo);
};
