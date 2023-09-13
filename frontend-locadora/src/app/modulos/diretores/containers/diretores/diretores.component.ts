import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Diretor } from '../../model/diretor';
import { DiretoresService } from './../../services/diretores.service';

@Component({
  selector: 'app-diretores',
  templateUrl: './diretores.component.html',
  styleUrls: ['./diretores.component.scss'],
})
export class DiretoresComponent implements OnInit {
  diretores$: Observable<Diretor[]> | null = null;

  constructor(
    private diretoresService: DiretoresService,
    private snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    this.refresh();
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.diretores$ = this.diretoresService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar diretores.');
        return of([]);
      })
    );
  }

  onError(mensagem: string) {
    this.dialog.open(ErrorDialogComponent, { data: mensagem });
  }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }

  onEdit(diretor: Diretor) {
    this.router.navigate(['editar', diretor._id], { relativeTo: this.route });
  }

  onDelete(diretor: Diretor) {
    this.diretoresService.delete(diretor._id).subscribe({
      error: () => this.onError('Erro ao tentar remover Diretor.'),
      complete: () => {
        this.refresh();
        this.snackBar.open('Diretor removido com sucesso.', 'X', {
          duration: 3500,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
    });
  }

}
