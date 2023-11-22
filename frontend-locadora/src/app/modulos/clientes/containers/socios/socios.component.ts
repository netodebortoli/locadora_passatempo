import {Component} from '@angular/core';
import {BaseContainerComponent} from "../../../../shared/base/components/base-container/base-container.component";
import {SociosService} from "../../socios.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {Socio} from "../../model/socio";

@Component({
  selector: 'app-clientes',
  templateUrl: './socios.component.html',
  styleUrls: ['./socios.component.scss']
})
export class SociosComponent extends BaseContainerComponent<Socio> {

  constructor(
    protected sociosService: SociosService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override dialog: MatDialog,
    protected override snackBar: MatSnackBar
  ) {
    super('Cliente', sociosService, router, route, dialog, snackBar);
  }
}
