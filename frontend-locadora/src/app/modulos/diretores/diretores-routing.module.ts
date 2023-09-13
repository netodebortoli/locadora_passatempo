import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DiretorFormComponent } from './containers/diretor-form/diretor-form.component';
import { DiretoresComponent } from './containers/diretores/diretores.component';
import { diretorResolver } from './guards/diretor.resolver';

const routes: Routes = [
  { path: '', component: DiretoresComponent },
  {
    path: 'novo',
    component: DiretorFormComponent,
    resolve: { diretor: diretorResolver },
  },
  {
    path: 'editar/:id',
    component: DiretorFormComponent,
    resolve: { diretor: diretorResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DiretoresRoutingModule {}
