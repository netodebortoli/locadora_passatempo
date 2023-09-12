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

  save(record: Partial<Ator>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  loadById(id: string) {
    return this.httpClient.get<Ator>(`${this.API}/${id}`).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete<Ator>(`${this.API}/${id}`);
  }

  private create(record: Partial<Ator>) {
    return this.httpClient.post<Ator>(this.API, record);
  }

  private update(record: Partial<Ator>) {
    return this.httpClient.put<Ator>(`${this.API}/${record._id}`, record);
  }
}
