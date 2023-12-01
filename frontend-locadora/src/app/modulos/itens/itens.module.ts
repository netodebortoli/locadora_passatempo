import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { SharedModule } from 'src/app/shared/shared.module';

import { ItensListComponent } from './components/itens-list/itens-list.component';
import { ItemFormComponent } from './containers/item-form/item-form.component';
import { ItensComponent } from './containers/itens/itens.component';
import { ItensRoutingModule } from './itens-routing.module';
import {MatTooltipModule} from "@angular/material/tooltip";


@NgModule({
  declarations: [
    ItensListComponent,
    ItemFormComponent,
    ItensComponent
  ],
    imports: [
        CommonModule,
        ItensRoutingModule,
        SharedModule,
        AppMaterialModule,
        MatTooltipModule
    ]
})
export class ItensModule { }
