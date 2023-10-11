import {Component} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';

import {Titulo} from '../../model/titulo';
import {TitulosService} from '../../titulos.service';
import {BaseContainerComponent} from "../../../../shared/base/components/base-container/base-container.component";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-titulos',
  templateUrl: './titulos.component.html',
  styleUrls: ['./titulos.component.scss'],
})
export class TitulosComponent extends BaseContainerComponent<Titulo>{
  constructor(
    protected titulosService: TitulosService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar,
  ) {
    super('Título', titulosService, router, route, dialog, snackBar);
  }
}
