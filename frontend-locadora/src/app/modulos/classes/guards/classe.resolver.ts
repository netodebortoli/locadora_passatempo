import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Classe } from '../model/classe';
import { ClassesService } from '../services/classes.service';

export const classeResolver: ResolveFn<Observable<Classe>> = (
  route,
  state,
  classeService: ClassesService = inject(ClassesService)
) => {
  if (route.params?.['id']) {
    return classeService.loadById(route.params['id']);
  }
  return of({ _id: '', nome: '', valor: '', prazoDevolucao: '' });
};
