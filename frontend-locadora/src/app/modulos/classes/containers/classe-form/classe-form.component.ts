import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Classe } from '../../model/classe';
import { ClassesService } from '../../services/classes.service';

@Component({
  selector: 'app-classe-form',
  templateUrl: './classe-form.component.html',
  styleUrls: ['./classe-form.component.scss'],
})
export class ClasseFormComponent implements OnInit {
  form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
    valor: ['', Validators.required],
    prazoDevolucao: ['', Validators.required],
  });

  constructor(
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    private classesService: ClassesService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    const classe: Classe = this.route.snapshot.data['classe'];
    this.form.setValue({
      _id: classe._id,
      nome: classe.nome,
      valor: classe.valor,
      prazoDevolucao: classe.prazoDevolucao,
    });
  }

  onSubmit() {
    this.classesService.save(this.form.value as Classe).subscribe({
      next: () => this.onSuccess(),
      error: () => this.onError(),
    });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Classe salva com sucesso.', '', { duration: 3500 });
    this.onCancel();
  }
  private onError() {
    this.snackBar.open('Erro ao salvar Classe.', '', { duration: 3500 });
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
