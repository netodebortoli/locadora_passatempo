import { Component } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AtoresService } from '../services/atores.service';

@Component({
  selector: 'app-ator-form',
  templateUrl: './ator-form.component.html',
  styleUrls: ['./ator-form.component.scss'],
})
export class AtorFormComponent {
  form = this.formBuilder.group({
    nome: new FormControl<string>('', { nonNullable: true }),
  });

  constructor(
    private atoresService: AtoresService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
  ) {}

  onSubmit() {
    this.atoresService.save(this.form.value).subscribe(
      (result) => console.log(result),
      (error) => this.onError()
    );
  }
  onCancel() {}

  private onError() {
    this.snackBar.open('Erro ao salvar Ator.', '', { duration: 3500 });
  }
}
