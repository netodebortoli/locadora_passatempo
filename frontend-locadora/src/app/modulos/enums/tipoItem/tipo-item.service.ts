import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseEnumService} from "../../../shared/base/base-enum.service";

@Injectable({
  providedIn: 'root'
})
export class TipoItemService extends BaseEnumService {

  constructor(protected override httpClient: HttpClient) {
    super("/api/enumeradores/tipos-item", httpClient);
  }
}
