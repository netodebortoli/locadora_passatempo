import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Ator } from '../model/ator';
import { AtoresService } from '../atores.service';

export const atorResolver: ResolveFn<Observable<Ator>> = (
  route,
  _state,
  service: AtoresService = inject(AtoresService)
) => {
  if (route.params?.['id']) {
    return service.loadById(route.params['id']);
  }
  return of({ } as Ator);
};
