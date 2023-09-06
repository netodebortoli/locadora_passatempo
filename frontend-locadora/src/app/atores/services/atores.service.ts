import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Ator } from '../model/ator';

@Injectable({
  providedIn: 'root',
})
export class AtoresService {
  private readonly API = '/api/atores';
  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Ator[]>(this.API).pipe(
      first(),
      delay(1500),
      tap((atores) => console.log(atores))
    );
  }

  save(record: Ator) {
    return this.httpClient.post<Ator>(this.API, record);
  }
}
