import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'titulos' },
  {
    path: 'titulos',
    loadChildren: () =>
      import('./titulos/titulos.module').then((m) => m.TitulosModule),
  },
  {
    path: 'atores',
    loadChildren: () =>
      import('./atores/atores.module').then((m) => m.AtoresModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
