import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Titulo } from './model/titulo';


@Injectable({
  providedIn: 'root',
})
export class TitulosService {

  private readonly API = '/assets/titulos.json';
  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Titulo[]>(this.API).pipe(
      first(),
      delay(1500),
      tap(titulos => console.log(titulos))
    );
  }
}
