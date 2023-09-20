import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

import { ClassesService } from '../../classes.service';
import { Classe } from '../../model/classe';
import { BaseContainerComponent } from './../../../../shared/base/components/base-container/base-container.component';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.scss'],
})
export class ClassesComponent extends BaseContainerComponent<Classe> {
  constructor(
    protected classesService: ClassesService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override snackBar: MatSnackBar,
    protected override dialog: MatDialog
  ) {
    super('Classe', classesService, router, route, dialog, snackBar);
  }
}
