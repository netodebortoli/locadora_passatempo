import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../../shared/app-material/app-material.module';
import { SharedModule } from '../../shared/shared.module';
import { TitulosListComponent } from './components/titulos-list/titulos-list.component';
import { TituloFormComponent } from './containers/titulo-form/titulo-form.component';
import { TitulosComponent } from './containers/titulos/titulos.component';
import { TitulosRoutingModule } from './titulos-routing.module';
import {MatTooltipModule} from "@angular/material/tooltip";
import { BuscaTituloComponent } from './components/busca-titulo/busca-titulo.component';

@NgModule({
  declarations: [
    TitulosComponent,
    TitulosListComponent,
    TituloFormComponent,
    BuscaTituloComponent
  ],
    imports: [
        CommonModule,
        TitulosRoutingModule,
        AppMaterialModule,
        SharedModule,
        ReactiveFormsModule,
        MatTooltipModule
    ],
})
export class TitulosModule {}
