import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ClientesRoutingModule} from './clientes-routing.module';
import {AppMaterialModule} from "../../shared/app-material/app-material.module";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {SociosComponent} from './containers/socios/socios.component';
import {SociosListComponent} from './components/socios-list/socios-list.component';
import {SocioFormComponent} from './containers/socio-form/socio-form.component';
import {MatTooltipModule} from "@angular/material/tooltip";

@NgModule({
    declarations: [
        SociosComponent,
        SociosListComponent,
        SocioFormComponent
    ],
    exports: [
        SociosListComponent
    ],
    imports: [
        CommonModule,
        ClientesRoutingModule,
        AppMaterialModule,
        SharedModule,
        ReactiveFormsModule,
        MatTooltipModule
    ]
})
export class ClientesModule {
}
