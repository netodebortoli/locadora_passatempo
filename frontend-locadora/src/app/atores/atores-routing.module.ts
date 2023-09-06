import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AtoresComponent } from './atores/atores.component';
import { AtorFormComponent } from './ator-form/ator-form.component';

const routes: Routes = [
  { path: '', component: AtoresComponent },
  { path: 'novo', component: AtorFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtoresRoutingModule { }
