import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Classe } from '../../model/classe';

@Component({
  selector: 'app-classes-list',
  templateUrl: './classes-list.component.html',
  styleUrls: ['./classes-list.component.scss'],
})
export class ClassesListComponent {
  @Input() classes: Classe[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  readonly displayedColumns = ['nome', 'valor', 'prazoDevolucao', 'acoes'];

  constructor() {}

  onAdd() {
    this.add.emit(true);
  }

  onEdit(classe: Classe) {
    this.edit.emit(classe);
  }

  onDelete(classe: Classe) {
    this.delete.emit(classe);
  }
}
