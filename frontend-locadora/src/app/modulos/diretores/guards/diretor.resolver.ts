import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Diretor } from '../model/diretor';
import { DiretoresService } from '../diretores.service';

export const diretorResolver: ResolveFn<Observable<Diretor>> = (
  route,
  _state,
  service: DiretoresService = inject(DiretoresService)
) => {
  if (route.params?.['id']) {
    return service.loadById(route.params['id']);
  }
  return of({ } as Diretor);
};
