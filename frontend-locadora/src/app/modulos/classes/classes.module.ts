import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClassesRoutingModule } from './classes-routing.module';
import { ClassesComponent } from './containers/classes/classes.component';
import { ClasseFormComponent } from './containers/classe-form/classe-form.component';
import { ClassesListComponent } from './components/classes-list/classes-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import {MatTooltipModule} from "@angular/material/tooltip";


@NgModule({
  declarations: [
    ClassesComponent,
    ClasseFormComponent,
    ClassesListComponent
  ],
    imports: [
        CommonModule,
        ClassesRoutingModule,
        SharedModule,
        AppMaterialModule,
        MatTooltipModule
    ]
})
export class ClassesModule { }
