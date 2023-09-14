import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClasseFormComponent } from './containers/classe-form/classe-form.component';
import { ClassesComponent } from './containers/classes/classes.component';
import { classeResolver } from './guards/classe.resolver';

const routes: Routes = [
  { path: '', component: ClassesComponent },
  {
    path: 'novo',
    component: ClasseFormComponent,
    resolve: { classe: classeResolver },
  },
  {
    path: 'editar/:id',
    component: ClasseFormComponent,
    resolve: { classe: classeResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClassesRoutingModule {}
