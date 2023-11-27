import {Component, EventEmitter, Output} from '@angular/core';
import {BaseListComponent} from "../../../../shared/base/components/base-list/base-list.component";
import {Locacao} from "../../model/locacao";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {registerLocaleData} from "@angular/common";

@Component({
  selector: 'app-locacoes-list',
  templateUrl: './locacoes-list.component.html',
  styleUrls: ['./locacoes-list.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class LocacoesListComponent extends BaseListComponent<Locacao> {

  @Output() returnLocation = new EventEmitter(false);

  constructor() {
    super(['item', 'cliente', 'dataLocacao', 'dataDevolucaoPrevista', 'dataDevolucaoEfetiva', 'acoes']);
  }

  expandedElement!: Locacao | null;
  colunasParaExibirAoExpandir = [...this.displayedColumns, 'expand'];

  onReturnLocation(registro: Locacao) {
    this.returnLocation.emit(registro);
  }

  protected getMensagemAtrasoDevolucao(registro: Locacao) {
    let currentDate = new Date();
    let expectedReturnDate = new Date(registro.dataDevolucaoPrevista!);
    let diasEmAtraso;
    let mensagem = `Aguardando devolução.`;
    if (registro.dataDevolucaoEfetiva == null) {
      if (currentDate.getTime() > expectedReturnDate.getTime()) {
        diasEmAtraso = currentDate.getDate() - expectedReturnDate.getDate();
        mensagem = `Devolução atrasada em ${diasEmAtraso} dia(s).`;
      }
    } else {
      let dataDevolucao = new Date(registro.dataDevolucaoEfetiva);
      if (dataDevolucao.getTime() > expectedReturnDate.getTime()) {
        diasEmAtraso = dataDevolucao.getDate() - expectedReturnDate.getDate();
        mensagem = `Item devolvido com ${diasEmAtraso} dia(s) de atraso.`;
      } else {
        mensagem = "Item devolvido sem atraso."
      }
    }
    return mensagem;
  }


}
