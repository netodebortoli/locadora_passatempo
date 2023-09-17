import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { BaseFormComponent } from 'src/app/shared/base/containers/base-form/base-form.component';

import { ClassesService } from '../../classes.service';
import { Classe } from '../../model/classe';

@Component({
  selector: 'app-classe-form',
  templateUrl: './classe-form.component.html',
  styleUrls: ['./classe-form.component.scss'],
})
export class ClasseFormComponent extends BaseFormComponent<Classe> implements OnInit {
  override form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
    valor: ['', Validators.required],
    prazoDevolucao: ['', Validators.required],
  });

  constructor(
    protected classesService: ClassesService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    super(classesService, snackBar, location);
  }

  ngOnInit(): void {
    const classe: Classe = this.route.snapshot.data['classe'];
    this.form.patchValue({
      _id: classe._id,
      nome: classe.nome,
      valor: classe.valor,
      prazoDevolucao: classe.prazoDevolucao,
    });
  }
}
