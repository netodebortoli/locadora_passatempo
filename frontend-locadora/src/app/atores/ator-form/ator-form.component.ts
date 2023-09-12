import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Ator } from '../model/ator';
import { AtoresService } from '../services/atores.service';

@Component({
  selector: 'app-ator-form',
  templateUrl: './ator-form.component.html',
  styleUrls: ['./ator-form.component.scss'],
})
export class AtorFormComponent implements OnInit {
  form = this.formBuilder.group({
    _id: [''],
    nome: [''],
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
}
