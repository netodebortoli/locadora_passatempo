import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TitulosComponent } from './titulos/titulos.component';

const routes: Routes = [
  { path: '', component: TitulosComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TitulosRoutingModule { }
