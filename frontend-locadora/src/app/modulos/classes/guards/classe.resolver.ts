import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { ClassesService } from '../classes.service';
import { Classe } from './../model/classe';

export const classeResolver: ResolveFn<Observable<Classe>> = (
  route,
  _state,
  classeService: ClassesService = inject(ClassesService)
) => {
  if (route.params?.['id']) {
    return classeService.loadById(route.params['id']);
  }
  return of({ } as Classe);
};
