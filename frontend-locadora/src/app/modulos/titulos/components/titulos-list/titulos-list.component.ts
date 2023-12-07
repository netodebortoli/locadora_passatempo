import {Component, Input, OnInit} from '@angular/core';
import {BaseListComponent} from 'src/app/shared/base/components/base-list/base-list.component';

import {Titulo} from '../../model/titulo';
import {trigger, state, style, transition, animate} from '@angular/animations';

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
export class TitulosListComponent extends BaseListComponent<Titulo> implements OnInit {

  @Input() isViewOnly?: boolean;
  isViewOnlyColumns: string[] = ['nome', 'diretor', 'ano', 'categoria', 'classe'];
  expandedElement!: Titulo | null;
  colunasParaExibirAoExpandir!: string[];

  constructor() {
    super(['nome', 'diretor', 'ano', 'categoria', 'classe', 'acoes']);
  }

  ngOnInit() {
    this.colunasParaExibirAoExpandir = [...this.isViewOnly ? this.isViewOnlyColumns : this.displayedColumns, 'expand'];
  }

}
