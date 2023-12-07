import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LocacoesRoutingModule} from './locacoes-routing.module';
import {LocacoesComponent} from './containers/locacoes/locacoes.component';
import {LocacoesListComponent} from './components/locacoes-list/locacoes-list.component';
import {LocacaoFormComponent} from './containers/locacao-form/locacao-form.component';
import {SharedModule} from "../../shared/shared.module";
import {AppMaterialModule} from "../../shared/app-material/app-material.module";

@NgModule({
    declarations: [
        LocacoesComponent,
        LocacoesListComponent,
        LocacaoFormComponent
    ],
    imports: [
        CommonModule,
        LocacoesRoutingModule,
        AppMaterialModule,
        SharedModule
    ]
})
export class LocacoesModule {
}
