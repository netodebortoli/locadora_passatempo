import { ResolveFn } from '@angular/router';
import {Observable, of} from "rxjs";
import {Titulo} from "../model/titulo";
import {TitulosService} from "../titulos.service";
import {Inject} from "@angular/core";

export const tituloResolver: ResolveFn<Observable<Titulo>> = (
  route,
  _state,
  service: TitulosService = Inject(TitulosService)) => {
  if (route.params?.['id']){
    return service.loadById(route.params['id']);
  };
  return of({} as Titulo);
};
