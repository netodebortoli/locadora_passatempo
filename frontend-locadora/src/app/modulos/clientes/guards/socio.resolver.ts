import {ResolveFn} from '@angular/router';
import {Observable, of} from "rxjs";
import {Cliente} from "../model/cliente";
import {SociosService} from "../socios.service";
import {inject} from "@angular/core";

export const socioResolver: ResolveFn<Observable<Cliente>> = (
  route,
  state,
  sociosService: SociosService = inject(SociosService)) => {
  if ((route.params?.['id'])) {
    return sociosService.loadById(route.params?.['id']);
  }
  return of({} as Cliente);
};
