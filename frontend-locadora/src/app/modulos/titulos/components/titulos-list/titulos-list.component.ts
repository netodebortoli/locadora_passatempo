import { Component } from '@angular/core';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';

import { Titulo } from '../../model/titulo';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-titulos-list',
  templateUrl: './titulos-list.component.html',
  styleUrls: ['./titulos-list.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class TitulosListComponent extends BaseListComponent<Titulo>{

  constructor(){
    super(['nome', 'diretor', 'ano', 'categoria', 'classe', 'acoes']);
  }

  expandedElement!: Titulo | null;
  colunasParaExibirAoExpandir = [...this.displayedColumns, 'expand'];
}
