import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Classe } from '../model/classe';

@Injectable({
  providedIn: 'root',
})
export class ClassesService {
  private readonly API = '/api/classes';
  constructor(private httpClient: HttpClient) {}

  list() {
    return this.httpClient.get<Classe[]>(this.API).pipe(
      first(),
      delay(1000),
      tap((classes) => console.log(classes))
    );
  }

  save(record: Partial<Classe>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  loadById(id: string) {
    return this.httpClient.get<Classe>(`${this.API}/${id}`).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete<Classe>(`${this.API}/${id}`);
  }

  private create(record: Partial<Classe>) {
    return this.httpClient.post<Classe>(this.API, record);
  }

  private update(record: Partial<Classe>) {
    return this.httpClient.put<Classe>(`${this.API}/${record._id}`, record);
  }
}
