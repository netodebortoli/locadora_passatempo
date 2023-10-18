import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TituloFormComponent } from './containers/titulo-form/titulo-form.component';
import { TitulosComponent } from './containers/titulos/titulos.component';
import { tituloResolver } from './guards/titulo.resolver';

const routes: Routes = [
  { path: '', component: TitulosComponent },
  {
    path: 'novo',
    component: TituloFormComponent,
    resolve: { titulo: tituloResolver },
  },
  {
    path: 'editar/:id',
    component: TituloFormComponent,
    resolve: { titulo: tituloResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TitulosRoutingModule {}
