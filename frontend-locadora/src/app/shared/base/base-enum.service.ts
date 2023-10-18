import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {delay, first} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BaseEnumService {

  constructor(
    @Inject(String) protected apiUrl: string,
    protected httpClient: HttpClient
  ) {
  }

  list() {
    return this.httpClient.get<[]>(this.apiUrl).pipe(
      first(),
      delay(1500)
    );
  }
}
