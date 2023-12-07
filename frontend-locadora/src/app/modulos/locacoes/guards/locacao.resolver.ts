import {ResolveFn} from '@angular/router';
import {LocacoesService} from "../locacoes.service";
import {inject} from "@angular/core";
import {of} from "rxjs";
import {Locacao} from "../model/locacao";

export const locacaoResolver: ResolveFn<Locacao> = (
    route,
    state,
    locacaoService: LocacoesService = inject(LocacoesService)) => {
    if (route.params?.['id']) {
        return locacaoService.loadById(route.params?.['id']);
    }
    return of({} as Locacao);
};
