import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseContainerComponent } from 'src/app/shared/base/components/base-container/base-container.component';

import { DiretoresService } from '../../diretores.service';
import { Diretor } from '../../model/diretor';

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
