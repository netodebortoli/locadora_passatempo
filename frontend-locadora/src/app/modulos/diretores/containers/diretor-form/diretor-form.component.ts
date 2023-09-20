import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { BaseFormComponent } from 'src/app/shared/base/components/base-form/base-form.component';

import { DiretoresService } from '../../diretores.service';
import { Diretor } from '../../model/diretor';

@Component({
  selector: 'app-diretor-form',
  templateUrl: './diretor-form.component.html',
  styleUrls: ['./diretor-form.component.scss'],
})
export class DiretorFormComponent extends BaseFormComponent<Diretor> implements OnInit {
  override form = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
  });

  constructor(
    protected diretoresService: DiretoresService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    super('Diretor', diretoresService, snackBar, location);
  }

  ngOnInit(): void {
    const diretor: Diretor = this.route.snapshot.data['diretor'];
    this.form.patchValue({
      _id: diretor._id,
      nome: diretor.nome,
    });
  }
}
