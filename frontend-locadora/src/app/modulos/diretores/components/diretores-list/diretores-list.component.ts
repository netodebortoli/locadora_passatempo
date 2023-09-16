import { Component } from '@angular/core';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';

import { Diretor } from '../../model/diretor';

@Component({
  selector: 'app-diretores-list',
  templateUrl: './diretores-list.component.html',
  styleUrls: ['./diretores-list.component.scss'],
})
export class DiretoresListComponent extends BaseListComponent<Diretor> {
  constructor(){
    super(['nome','acoes']);
  }
}
