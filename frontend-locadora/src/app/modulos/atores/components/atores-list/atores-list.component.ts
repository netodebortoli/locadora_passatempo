import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Ator } from '../../model/ator';

@Component({
  selector: 'app-atores-list',
  templateUrl: './atores-list.component.html',
  styleUrls: ['./atores-list.component.scss'],
})
export class AtoresListComponent {
  @Input() atores: Ator[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  readonly displayedColumns = ['nome', 'acoes'];

  constructor() {}

  onAdd() {
    this.add.emit(true);
  }
  onEdit(ator: Ator) {
    this.edit.emit(ator);
  }
  onDelete(ator: Ator) {
    this.delete.emit(ator);
  }
}
