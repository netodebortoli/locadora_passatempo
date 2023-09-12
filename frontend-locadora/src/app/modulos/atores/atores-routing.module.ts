import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AtorFormComponent } from './containers/ator-form/ator-form.component';
import { AtoresComponent } from './containers/atores/atores.component';
import { atorResolver } from './guards/ator.resolver';

const routes: Routes = [
  { path: '', component: AtoresComponent },
  {
    path: 'novo',
    component: AtorFormComponent,
    resolve: { ator: atorResolver },
  },
  {
    path: 'editar/:id',
    component: AtorFormComponent,
    resolve: { ator: atorResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AtoresRoutingModule {}
