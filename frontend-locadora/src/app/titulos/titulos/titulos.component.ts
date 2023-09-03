import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';

import { Titulo } from '../model/titulo';
import { TitulosService } from '../services/titulos.service';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-titulos',
  templateUrl: './titulos.component.html',
  styleUrls: ['./titulos.component.scss'],
})
export class TitulosComponent implements OnInit {
  titulos$: Observable<Titulo[]>;
  displayedColumns = ['nome', 'ano', 'sinopse', 'categoria'];

  constructor(
    private titulosService: TitulosService,
    public dialog: MatDialog
  ) {
    this.titulos$ = this.titulosService.list().pipe(
      catchError((error) => {
        this.onError('Erro ao carregar títulos.');
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, { data: errorMsg });
  }

  ngOnInit(): void {}
}
