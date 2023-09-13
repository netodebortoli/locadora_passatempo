import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Diretor } from '../../model/diretor';

@Component({
  selector: 'app-diretores-list',
  templateUrl: './diretores-list.component.html',
  styleUrls: ['./diretores-list.component.scss'],
})
export class DiretoresListComponent {
  @Input() diretores: Diretor[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  readonly displayedColumns = ['nome', 'acoes'];

  constructor() {}

  onAdd() {
    this.add.emit(true);
  }
  onEdit(diretor: Diretor) {
    this.edit.emit(diretor);
  }
  onDelete(diretor: Diretor) {
    this.delete.emit(diretor);
  }
}
