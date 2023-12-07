import { Location } from '@angular/common';
import { Component, Inject, ViewChild } from '@angular/core';
import { FormGroupDirective, UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { HttpErrorResponse } from '@angular/common/http';
import { BaseModel } from '../../base.model';
import { BaseService } from '../../base.service';

@Component({
  selector: 'app-base-form',
  templateUrl: './base-form.component.html',
  styleUrls: ['./base-form.component.scss'],
})
export abstract class BaseFormComponent<Type extends BaseModel> {

  abstract form: UntypedFormGroup;
  @ViewChild(FormGroupDirective) formDirective?: FormGroupDirective;

  constructor(
    @Inject(String) protected humanReadbleName: string,
    protected service: BaseService<Type>,
    protected snackBar: MatSnackBar,
    protected location: Location,
  ) { }

  compareObjetos(a: BaseModel, b: BaseModel): boolean {
    return a?._id === b?._id;
  }

  onSubmit() {
    this.formDirective?.onSubmit(null as any);
    if (this.form.valid) {
      this.service.save(this.form.getRawValue() as Type).subscribe({
        next: () => this.onSuccess(),
        error: (erro) => this.onError(`Erro ao salvar registro de ${this.humanReadbleName}.`, erro),
      });
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open(`Registro de ${this.humanReadbleName} salvo com sucesso.`, '', { duration: 3500 });
    this.onCancel();
  }

  protected onError(mensagem: string, err: HttpErrorResponse) {
    this.snackBar.open(mensagem + ` ${err.error.mensagem}`, 'OK');
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

    if (field?.hasError('max')) {
      const requiredValue: number = field.errors ? field.errors['max']['max'] : 0;
      return `Valor máximo é ${requiredValue}.`;
    }

    if (field?.hasError('mask')) {
      return `Formato do campo inválido.`;
    }

    return 'Campo Inválido';
  }

  isFormArrayRequired(formGroup: UntypedFormGroup, formArrayName: string) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;
    return !formArray.valid && formArray.hasError('required') && formArray.touched;
  }
}
