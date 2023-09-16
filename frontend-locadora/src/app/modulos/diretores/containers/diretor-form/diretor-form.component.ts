import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Diretor } from '../../model/diretor';
import { DiretoresService } from '../../diretores.service';

@Component({
  selector: 'app-diretor-form',
  templateUrl: './diretor-form.component.html',
  styleUrls: ['./diretor-form.component.scss'],
})
export class DiretorFormComponent implements OnInit {
  form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
  });

  constructor(
    private diretoresService: DiretoresService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const diretor: Diretor = this.route.snapshot.data['diretor'];
    this.form.setValue({
      _id: diretor._id,
      nome: diretor.nome,
    });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Diretor salvo com sucesso', '', { duration: 3500 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar Diretor.', '', { duration: 3500 });
  }

  onSubmit() {
    this.diretoresService.save(this.form.value as Diretor).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
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
