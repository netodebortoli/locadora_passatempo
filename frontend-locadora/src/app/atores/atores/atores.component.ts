import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { AtoresService } from '../services/atores.service';
import { Ator } from '../model/ator';

@Component({
  selector: 'app-atores',
  templateUrl: './atores.component.html',
  styleUrls: ['./atores.component.scss'],
})
export class AtoresComponent implements OnInit {
  atores$: Observable<Ator[]>;

  constructor(
    private atoresService: AtoresService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.atores$ = this.atoresService.list().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar atores.');
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, { data: errorMsg });
  }

  ngOnInit(): void {}

  onAdd() {
    this.router.navigate(['novo'], { relativeTo: this.route });
  }

  onEdit(ator: Ator) {
    this.router.navigate(['editar', ator._id], { relativeTo: this.route });
  }
}
