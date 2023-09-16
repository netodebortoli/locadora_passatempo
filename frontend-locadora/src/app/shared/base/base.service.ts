import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { delay, first } from 'rxjs';

import { BaseModel } from './base.model';

@Injectable({
  providedIn: 'root'
})
export class BaseService<Type extends BaseModel> {

  constructor(
    @Inject(String) protected apiUrl: string,
    protected httpClient: HttpClient
  ) { }

  list() {
    return this.httpClient.get<[]>(this.apiUrl).pipe(
      first(),
      delay(1000)
    );
  }

  save(record: Partial<Type>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  loadById(id: string) {
    return this.httpClient.get<Type>(`${this.apiUrl}/${id}`).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete<Type>(`${this.apiUrl}/${id}`);
  }

  private create(record: Partial<Type>) {
    return this.httpClient.post<Type>(this.apiUrl, record);
  }

  private update(record: Partial<Type>) {
    return this.httpClient.put<Type>(`${this.apiUrl}/${record._id}`, record);
  }
}

