import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TitulosRoutingModule } from './titulos-routing.module';
import { TitulosComponent } from './titulos/titulos.component';


@NgModule({
  declarations: [
    TitulosComponent
  ],
  imports: [
    CommonModule,
    TitulosRoutingModule
  ]
})
export class TitulosModule { }
