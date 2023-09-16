import { Component } from '@angular/core';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';

import { Ator } from '../../model/ator';

@Component({
  selector: 'app-atores-list',
  templateUrl: './atores-list.component.html',
  styleUrls: ['./atores-list.component.scss'],
})
export class AtoresListComponent extends BaseListComponent<Ator> {
  constructor() {
    super(['nome','acoes']);
  }
}
