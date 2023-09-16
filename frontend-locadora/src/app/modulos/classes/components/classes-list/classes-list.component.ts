import { Component } from '@angular/core';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';

import { Classe } from '../../model/classe';

@Component({
  selector: 'app-classes-list',
  templateUrl: './classes-list.component.html',
  styleUrls: ['./classes-list.component.scss'],
})
export class ClassesListComponent extends BaseListComponent<Classe> {
  constructor() {
    super(['nome', 'valor', 'prazoDevolucao', 'acoes']);
  }
}
