import { Component, Inject, OnInit } from '@angular/core';
import { BaseModel } from '../../base.model';
import { Observable, catchError, of } from 'rxjs';
import { BaseService } from '../../base.service';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-base-container',
  templateUrl: './base-container.component.html',
  styleUrls: ['./base-container.component.scss'],
})
export class BaseContainerComponent<Type extends BaseModel> implements OnInit {
  registros$: Observable<Type[]> | null = null;

  constructor(
    @Inject(String) protected humanReadbleName: string,
    protected service: BaseService<Type>,
    protected router: Router,
    protected route: ActivatedRoute,
    protected dialog: MatDialog,
    protected snackBar: MatSnackBar,
  ) {
    this.refresh();
    console.log(this.registros$);
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.registros$ = this.service.list().pipe(
      catchError(() => {
        this.onError(`Erro ao carregar ${this.humanReadbleName}`);
        return of([]);
      })
    );
  }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }
  onEdit(registro: Type) {
    this.router.navigate(['editar', registro._id], { relativeTo: this.route });
  }

  onDelete(registro: Type) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: `Tem certeza que deseja remover este(a) ${this.humanReadbleName}?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.service.delete(registro._id).subscribe({
          error: () => this.onError(`Erro ao tentar remover ${this.humanReadbleName}.`),
          complete: () => {
            this.refresh();
            this.snackBar.open(`${this.humanReadbleName} removido(a) com sucesso.`, 'X', {
              duration: 3500,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
        });
      }
    });
  }

  onError(mensagem: string) {
    this.dialog.open(ErrorDialogComponent, { data: mensagem });
  }
}
