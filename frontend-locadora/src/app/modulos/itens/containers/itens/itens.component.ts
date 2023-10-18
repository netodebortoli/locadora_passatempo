import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseContainerComponent } from 'src/app/shared/base/components/base-container/base-container.component';

import { ItensService } from '../../itens.service';
import { Item } from '../../model/item';

@Component({
  selector: 'app-itens',
  templateUrl: './itens.component.html',
  styleUrls: ['./itens.component.scss']
})
export class ItensComponent extends BaseContainerComponent<Item>{

  constructor(
    protected itensService: ItensService,
    protected override router: Router,
    protected override route: ActivatedRoute,
    protected override snackBar: MatSnackBar,
    protected override dialog: MatDialog
  ) {
    super('Item', itensService, router, route, dialog, snackBar);
  }

}
