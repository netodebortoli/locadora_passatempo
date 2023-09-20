import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Diretor } from '../../model/diretor';
import { DiretoresService } from '../../diretores.service';
import { BaseContainerComponent } from 'src/app/shared/base/components/base-container/base-container.component';

@Component({
  selector: 'app-diretores',
  templateUrl: './diretores.component.html',
  styleUrls: ['./diretores.component.scss'],
})
export class DiretoresComponent extends BaseContainerComponent<Diretor> {
  constructor(
    protected diretoresService: DiretoresService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar
  ) {
    super('Diretor', diretoresService, router, route, dialog, snackBar);
  }

}
