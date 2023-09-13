import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './shared/components/page-not-found/page-not-found.component';
import { HomeComponent } from './shared/components/home/home.component';

const routes: Routes = [
  //{ path: '', pathMatch: 'full', redirectTo: 'titulos' },
  { path: '', pathMatch: 'full', component: HomeComponent},
  {
    path: 'titulos',
    loadChildren: () =>
      import('./modulos/titulos/titulos.module').then((m) => m.TitulosModule),
  },
  {
    path: 'atores',
    loadChildren: () =>
      import('./modulos/atores/atores.module').then((m) => m.AtoresModule),
  },
  {
    path: 'diretores',
    loadChildren: () =>
      import('./modulos/diretores/diretores.module').then(
        (m) => m.DiretoresModule
      ),
  },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
