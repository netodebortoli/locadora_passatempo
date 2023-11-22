import {Component} from '@angular/core';
import {BaseListComponent} from "../../../../shared/base/components/base-list/base-list.component";
import {Cliente} from "../../model/cliente";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-socios-list',
  templateUrl: './socios-list.component.html',
  styleUrls: ['./socios-list.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SociosListComponent extends BaseListComponent<Cliente> {

  constructor() {
    super(['numInscricao', 'nome', 'dataNascimento', 'sexo', 'acoes']);
  }

  expandedElement!: Cliente | null;
  colunasParaExibirAoExpandir = [...this.displayedColumns, 'expand'];
}
