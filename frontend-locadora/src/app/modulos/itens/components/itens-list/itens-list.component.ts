import { Component } from '@angular/core';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';
import { Item } from '../../model/item';

@Component({
  selector: 'app-itens-list',
  templateUrl: './itens-list.component.html',
  styleUrls: ['./itens-list.component.scss']
})
export class ItensListComponent extends BaseListComponent<Item> {
  constructor() {
    super(['numSerie', 'dataAquisicao', 'tipoItem', 'titulo', 'acoes']);
  }
}
