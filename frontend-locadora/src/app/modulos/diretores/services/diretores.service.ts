import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Diretor } from '../model/diretor';

@Injectable({
  providedIn: 'root',
})
export class DiretoresService {
  private readonly API = '/api/diretores';
  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Diretor[]>(this.API).pipe(
      first(),
      delay(1000),
      tap((diretor) => console.log(diretor))
    );
  }

  save(record: Partial<Diretor>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  loadById(id: string) {
    return this.httpClient.get<Diretor>(`${this.API}/${id}`).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete<Diretor>(`${this.API}/${id}`);
  }

  private create(record: Partial<Diretor>) {
    return this.httpClient.post<Diretor>(this.API, record);
  }

  private update(record: Partial<Diretor>) {
    return this.httpClient.put<Diretor>(`${this.API}/${record._id}`, record);
  }
}
