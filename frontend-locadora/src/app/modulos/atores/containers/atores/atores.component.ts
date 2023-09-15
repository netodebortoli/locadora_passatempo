import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Ator } from '../../model/ator';
import { AtoresService } from '../../services/atores.service';

@Component({
  selector: 'app-atores',
  templateUrl: './atores.component.html',
  styleUrls: ['./atores.component.scss'],
})
export class AtoresComponent implements OnInit {
  atores$: Observable<Ator[]> | null = null;

  constructor(
    private atoresService: AtoresService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.refresh();
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, { data: errorMsg });
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.atores$ = this.atoresService.list().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar atores.');
        return of([]);
      })
    );
  }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }

  onEdit(ator: Ator) {
    this.router.navigate(['editar', ator._id], { relativeTo: this.route });
  }

  onDelete(ator: Ator) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover este Ator?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.atoresService.delete(ator._id).subscribe({
          error: () => this.onError('Erro ao tentar remover Ator.'),
          complete: () => {
            this.refresh();
            this.snackBar.open('Ator removido com sucesso', 'X', {
              duration: 3500,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
        });
      }
    });
  }
}
