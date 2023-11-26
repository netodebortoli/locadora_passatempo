import {Injectable} from '@angular/core';
import {BaseService} from "../../shared/base/base.service";
import {Locacao} from "./model/locacao";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class LocacoesService extends BaseService<Locacao> {

    constructor(protected override httpClient: HttpClient) {
        super("/api/locacoes", httpClient)
    }

    realizarDevolucao(record: Partial<Locacao>) {
        let currentDate = new Date();
        let expectedReturnDate = new Date(record.dataDevolucaoPrevista!);
        if(currentDate.getTime() > expectedReturnDate.getTime()) {
            let diasEmAtraso = currentDate.getDate() - expectedReturnDate.getDate();
            let multa = parseInt(record.item!.titulo.classe.valor) * diasEmAtraso;
            record.multaCobrada = multa.toString();
        } else {
            record.multaCobrada = '0';
        }

        record.dataDevolucaoEfetiva = currentDate.toISOString();
        return this.httpClient.put<Locacao>(`${this.apiUrl}/devolucao/${record._id}`, record);
    }
}
