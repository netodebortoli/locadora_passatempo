import { Location } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormGroup, UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { BaseModel } from '../../base.model';
import { BaseService } from '../../base.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-base-form',
  templateUrl: './base-form.component.html',
  styleUrls: ['./base-form.component.scss'],
})
export abstract class BaseFormComponent<Type extends BaseModel> {

  abstract form: UntypedFormGroup;

  constructor(
    @Inject(String) protected humanReadbleName: string,
    protected service: BaseService<Type>,
    protected snackBar: MatSnackBar,
    protected location: Location,
  ) { }

  compareObjetos(a: BaseModel, b: BaseModel): boolean {
    return a._id === b._id;
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value as Type).subscribe({
        next: () => this.onSuccess(),
        error: (erro) => this.onError(`Erro ao salvar o(a) ${this.humanReadbleName}.`, erro),
      });
    } else {
      this.validateAllFormFields(this.form)
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open(`${this.humanReadbleName} salvo(a) com sucesso.`, '', { duration: 3500 });
    this.onCancel();
  }

  protected onError(mensagem: string, err: HttpErrorResponse) {
    this.snackBar.open(mensagem + ` ${err.error.mensagem}`,  'OK'/* , { duration: 3500 } */);
  }

  private validateAllFormFields(formGroup: UntypedFormGroup | UntypedFormArray) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof UntypedFormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof UntypedFormGroup || control instanceof UntypedFormArray) {
        control.markAsTouched({ onlySelf: true });
        this.validateAllFormFields(control);
      }
    });
  }

  getErrorMessage(formGroup: UntypedFormGroup, fieldName: string) {
    const field = formGroup.get(fieldName) as UntypedFormControl;
    return this.getErrorMessageFromField(field);
  }

  getFormArrayFieldErrorMessage(formGroup: UntypedFormGroup, formArrayName: string, fieldName: string, index: number) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;
    const field = formArray.controls[index].get(fieldName) as UntypedFormControl;
    return this.getErrorMessageFromField(field);
  }

  private getErrorMessageFromField(field: UntypedFormControl) {

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 255;
      return `Tamanho máximo excedido de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('min')) {
      const requiredValue: number = field.errors ? field.errors['min']['min'] : 0;
      return `Valor mínimo é ${requiredValue}.`;
    }

    return 'Campo Inválido';
  }

  isFormArrayRequired(formGroup: UntypedFormGroup, formArrayName: string) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;
    return !formArray.valid && formArray.hasError('required') && formArray.touched;
  }
}
