import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { AtorFormComponent } from './ator-form/ator-form.component';
import { AtoresRoutingModule } from './atores-routing.module';
import { AtoresComponent } from './atores/atores.component';
import { AtoresListComponent } from './atores-list/atores-list.component';

@NgModule({
  declarations: [
    AtoresComponent,
    AtorFormComponent,
    AtoresListComponent
  ],
  imports: [
    CommonModule,
    AtoresRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class AtoresModule { }
