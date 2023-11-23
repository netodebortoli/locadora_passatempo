import {Component} from '@angular/core';
import {BaseContainerComponent} from "../../../../shared/base/components/base-container/base-container.component";
import {SociosService} from "../../socios.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {Socio} from "../../model/socio";
import {Cliente} from "../../model/cliente";
import {Dependente} from "../../model/dependente";
import {
  ConfirmationDialogComponent
} from "../../../../shared/components/confirmation-dialog/confirmation-dialog.component";

@Component({
  selector: 'app-clientes',
  templateUrl: './socios.component.html',
  styleUrls: ['./socios.component.scss']
})
export class SociosComponent extends BaseContainerComponent<Socio> {

  constructor(
    protected sociosService: SociosService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar
  ) {
    super('Cliente', sociosService, router, route, dialog, snackBar);
  }

  onDeleteDependente(registro: Dependente) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: `Tem certeza que deseja remover este Dependente?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.sociosService.deleteDependente(registro._id).subscribe({
            error: (erro) => this.onError('Erro ao remover Dependente.', erro),
            complete: () => {
              this.refresh();
              this.snackBar.open('Dependente removido com sucesso.', 'X', {
                duration: 3500,
                verticalPosition: 'bottom',
                horizontalPosition: 'center',
              });
            },
          }
        )
      }
    });
  }

  mudarStatusSocio(registro: Socio) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: `Tem certeza que deseja alterar o status desse Socio?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        if (registro.status == 'Ativo') {
          this.sociosService.desativarSocio(registro._id).subscribe({
              error: (erro) => this.onError('Erro ao desativar Socio.', erro),
              complete: () => {
                this.refresh();
                this.snackBar.open('Socio desativado com sucesso.', 'X', {
                  duration: 3500,
                  verticalPosition: 'bottom',
                  horizontalPosition: 'center',
                });
              },
            }
          );
        } else {
          this.sociosService.ativarSocio(registro._id).subscribe({
              error: (erro) => this.onError('Erro ao ativar Socio.', erro),
              complete: () => {
                this.refresh();
                this.snackBar.open('Socio ativado com sucesso.', 'X', {
                  duration: 3500,
                  verticalPosition: 'bottom',
                  horizontalPosition: 'center',
                });
              },
            }
          );
        }
      }
    });
  }

  mudarStatusDependente(registro: Dependente) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: `Tem certeza que deseja alterar o status desse Dependente?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        if (registro.status == 'Ativo') {
          this.sociosService.desativarDependente(registro._id).subscribe({
              error: (erro) => this.onError('Erro ao desativar Dependente.', erro),
              complete: () => {
                this.refresh();
                this.snackBar.open('Dependente desativado com sucesso.', 'X', {
                  duration: 3500,
                  verticalPosition: 'bottom',
                  horizontalPosition: 'center',
                });
              },
            }
          );
        } else {
          this.sociosService.ativarDependente(registro._id).subscribe({
              error: (erro) => this.onError('Erro ao ativar Dependente.', erro),
              complete: () => {
                this.refresh();
                this.snackBar.open('Dependente ativado com sucesso.', 'X', {
                  duration: 3500,
                  verticalPosition: 'bottom',
                  horizontalPosition: 'center',
                });
              },
            }
          );
        }
      }
    });
  }
}
