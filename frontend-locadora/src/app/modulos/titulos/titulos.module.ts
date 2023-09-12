import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TitulosRoutingModule } from './titulos-routing.module';
import { TitulosComponent } from './titulos/titulos.component';
import { SharedModule } from '../../shared/shared.module';
import { AppMaterialModule } from '../../shared/app-material/app-material.module';

@NgModule({
  declarations: [TitulosComponent],
  imports: [
    CommonModule,
    TitulosRoutingModule,
    AppMaterialModule,
    SharedModule,
  ],
})
export class TitulosModule {}
