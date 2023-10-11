import { Component } from '@angular/core';
import { Titulo } from '../../model/titulo';
import { BaseListComponent } from 'src/app/shared/base/components/base-list/base-list.component';

@Component({
  selector: 'app-titulos-list',
  templateUrl: './titulos-list.component.html',
  styleUrls: ['./titulos-list.component.scss']
})
export class TitulosListComponent extends BaseListComponent<Titulo>{
  constructor(){
    super(['nome','categoria', 'ano', 'sinopse', 'diretor', 'atores', 'acoes']);
  }
}
