import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AtoresService } from '../services/atores.service';

@Component({
  selector: 'app-ator-form',
  templateUrl: './ator-form.component.html',
  styleUrls: ['./ator-form.component.scss'],
})
export class AtorFormComponent {
  form: FormGroup;

  constructor(
    private atoresService: AtoresService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      nome: [null],
    });
  }
  onSubmit() {
    this.atoresService.save(this.form.value).subscribe(
      result => console.log(result),
      error => this.onError()
    );
  }
  onCancel() {
    throw new Error('Method not implemented.');
  }

  private onError() {
    this.snackBar.open('Erro ao salvar Ator.', '', { duration: 3500 });
  }
}
