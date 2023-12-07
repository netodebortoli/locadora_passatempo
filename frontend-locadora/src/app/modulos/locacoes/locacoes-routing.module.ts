import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LocacoesComponent} from "./containers/locacoes/locacoes.component";
import {LocacaoFormComponent} from "./containers/locacao-form/locacao-form.component";
import {locacaoResolver} from "./guards/locacao.resolver";

const routes: Routes = [
  {path: '', component: LocacoesComponent},
  {
    path: 'novo',
    component: LocacaoFormComponent,
    resolve: {locacao: locacaoResolver}
  },
  {
    path: 'editar/:id',
    component: LocacaoFormComponent,
    resolve: {locacao: locacaoResolver}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocacoesRoutingModule {
}
