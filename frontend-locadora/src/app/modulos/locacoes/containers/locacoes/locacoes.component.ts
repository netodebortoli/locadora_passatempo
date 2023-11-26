import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {BaseContainerComponent} from "../../../../shared/base/components/base-container/base-container.component";
import {Locacao} from "../../model/locacao";
import {LocacoesService} from "../../locacoes.service";
import {
  ConfirmationDialogComponent
} from "../../../../shared/components/confirmation-dialog/confirmation-dialog.component";

@Component({
  selector: 'app-locacoes',
  templateUrl: './locacoes.component.html',
  styleUrls: ['./locacoes.component.scss']
})
export class LocacoesComponent extends BaseContainerComponent<Locacao> {
  constructor(
    protected locacoesService: LocacoesService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar,
  ) {
    super('Locação', locacoesService, router, route, dialog, snackBar);
  }

  onReturnLocation(registro: Locacao){
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: `Deseja realizar a devolução deste item?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.locacoesService.realizarDevolucao(registro).subscribe({
          error: (erro) => this.onError(`Erro ao tentar realizar devolução do item ${registro.item.numSerie}.`, erro),
          complete: () => {
            this.refresh();
            this.snackBar.open(`Item ${registro.item.numSerie} devolvido com sucesso.`, 'X', {
              duration: 3500,
              verticalPosition: 'bottom',
              horizontalPosition: 'center',
            });
          },
        });
      }});
  }
}
