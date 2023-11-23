import {Component, EventEmitter, Output} from '@angular/core';
import {BaseListComponent} from "../../../../shared/base/components/base-list/base-list.component";
import {Cliente} from "../../model/cliente";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Socio} from "../../model/socio";
import {Dependente} from "../../model/dependente";

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
export class SociosListComponent extends BaseListComponent<Socio> {

  @Output() changeStatusSocio = new EventEmitter(false);
  @Output() changeStatusDependente = new EventEmitter(false);
  @Output() deleteDependente = new EventEmitter(false);

  constructor() {
    super(['numInscricao', 'nome', 'telefone', 'status', 'acoes']);
  }

  expandedElement!: Cliente | null;
  colunasParaExibirAoExpandir = [...this.displayedColumns, 'expand'];

  onChangeStatusSocio(registro: Socio) {
    this.changeStatusSocio.emit(registro);
  }

  onChangeStatusDependente(registro: Dependente) {
    this.changeStatusDependente.emit(registro);
  }

  onDeleteDependente(registro: Dependente) {
    this.deleteDependente.emit(registro);
  }
}
