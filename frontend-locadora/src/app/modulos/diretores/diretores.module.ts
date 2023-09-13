import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { SharedModule } from 'src/app/shared/shared.module';

import { DiretoresListComponent } from './components/diretores-list/diretores-list.component';
import { DiretoresComponent } from './containers/diretores/diretores.component';
import { DiretoresRoutingModule } from './diretores-routing.module';
import { DiretorFormComponent } from './containers/diretor-form/diretor-form.component';

@NgModule({
  declarations: [
    DiretoresListComponent,
    DiretoresComponent,
    DiretorFormComponent
  ],
  imports: [
    CommonModule,
    DiretoresRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ],
})
export class DiretoresModule {}
