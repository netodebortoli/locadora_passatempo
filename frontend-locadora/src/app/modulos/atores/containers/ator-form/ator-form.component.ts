import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Ator } from '../../model/ator';
import { AtoresService } from '../../atores.service';

@Component({
  selector: 'app-ator-form',
  templateUrl: './ator-form.component.html',
  styleUrls: ['./ator-form.component.scss'],
})
export class AtorFormComponent implements OnInit {
  form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
  });

  constructor(
    private atoresService: AtoresService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const ator: Ator = this.route.snapshot.data['ator'];
    this.form.setValue({
      _id: ator._id,
      nome: ator.nome,
    });
  }

  onSubmit() {
    this.atoresService.save(this.form.value as Ator).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Ator salvo com sucesso', '', { duration: 3500 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar Ator.', '', { duration: 3500 });
  }

  getErrorMessage(nomeDoCampo: string) {
    const campo = this.form.get(nomeDoCampo);
    if (campo?.hasError('required')) {
      return 'Campo obrigatório.';
    }
    if (campo?.hasError('maxlength')) {
      const requiredLength: number = campo.errors
        ? campo.errors['maxlength']['requiredLength']
        : 255;
      return `O número máximo de caracteres é ${requiredLength}`;
    }
    return 'Campo inválido.';
  }
}
