import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';

import { BaseModel } from '../../base.model';

@Component({
  selector: 'app-base-list',
  templateUrl: './base-list.component.html',
  styleUrls: ['./base-list.component.scss']
})
export class BaseListComponent<Type extends BaseModel> {

  @Input() arrayModel: Type[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);

  constructor(
    @Inject(String) protected displayedColumns: string[]
  ) { }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(model: Type) {
    this.edit.emit(model);
  }

  onDelete(model: Type) {
    this.delete.emit(model);
  }

}

