import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Classe } from '../../model/classe';
import { ClassesService } from '../../services/classes.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.scss'],
})
export class ClassesComponent implements OnInit {
  classes$: Observable<Classe[]> | null = null;

  constructor(
    private classesService: ClassesService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
    this.refresh();
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.classes$ = this.classesService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar Classes.');
        return of([]);
      })
    );
  }

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }
  onEdit(classe: Classe) {
    this.router.navigate(['editar', classe._id], { relativeTo: this.route });
  }
  onDelete(classe: Classe) {
    this.classesService.delete(classe._id).subscribe({
      error: () => this.onError('Erro ao tentar remover Classe.'),
      complete: () => {
        this.refresh();
        this.snackBar.open('Classe removida com sucesso.', 'X', {
          duration: 3500,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      },
    });
  }

  onError(mensagem: string) {
    this.dialog.open(ErrorDialogComponent, { data: mensagem });
  }
}
