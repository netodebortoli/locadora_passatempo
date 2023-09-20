import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseContainerComponent } from 'src/app/shared/base/components/base-container/base-container.component';

import { AtoresService } from '../../atores.service';
import { Ator } from '../../model/ator';

@Component({
  selector: 'app-atores',
  templateUrl: './atores.component.html',
  styleUrls: ['./atores.component.scss'],
})
export class AtoresComponent extends BaseContainerComponent<Ator> {

  constructor(
    protected atoresService: AtoresService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar,
  ) {
    super('Ator', atoresService, router, route, dialog, snackBar);
  }
}
