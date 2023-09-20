import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { BaseModel } from '../../base.model';
import { BaseService } from '../../base.service';

@Component({
  selector: 'app-base-form',
  templateUrl: './base-form.component.html',
  styleUrls: ['./base-form.component.scss'],
})
export abstract class BaseFormComponent<Type extends BaseModel> {

  abstract form: FormGroup;

  constructor(
    protected service: BaseService<Type>,
    protected snackBar: MatSnackBar,
    protected location: Location
  ) { }

  onSubmit() {
    this.service.save(this.form.value as Type).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Registro salvo com sucesso', '', { duration: 3500 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar o registro.', '', { duration: 3500 });
  }

  getErrorMessage(nomeDoCampo: string) {

    const campo = this.form.get(nomeDoCampo);

    if (campo?.hasError('required')) {
      return 'Campo obrigatório.';
    }

    if (campo?.hasError('maxlength')) {
      const requiredLength: number = campo.errors ? campo.errors['maxlength']['requiredLength'] : 255;
      return `O número máximo de caracteres é ${requiredLength}`;
    }

    return 'Campo inválido.';
  }
}
