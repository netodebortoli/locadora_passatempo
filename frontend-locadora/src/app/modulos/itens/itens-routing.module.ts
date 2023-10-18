import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ItemFormComponent } from './containers/item-form/item-form.component';
import { ItensComponent } from './containers/itens/itens.component';
import { itemResolver } from './guards/item.resolver';

const routes: Routes = [
  { path: '', component: ItensComponent },
  {
    path: 'novo',
    component: ItemFormComponent,
    resolve: { item: itemResolver },
  },
  {
    path: 'editar/:id',
    component: ItemFormComponent,
    resolve: { item: itemResolver },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItensRoutingModule { }
