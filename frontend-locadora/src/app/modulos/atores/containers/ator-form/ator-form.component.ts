import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Ator } from '../../model/ator';
import { AtoresService } from '../../atores.service';
import { BaseFormComponent } from 'src/app/shared/base/components/base-form/base-form.component';

@Component({
  selector: 'app-ator-form',
  templateUrl: './ator-form.component.html',
  styleUrls: ['./ator-form.component.scss'],
})
export class AtorFormComponent extends BaseFormComponent<Ator> implements OnInit {

  override form: FormGroup = this.formBuilder.group({
    _id: [''],
    nome: ['', [Validators.required, Validators.maxLength(255)]],
  });

  constructor(
    protected atoresService: AtoresService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
  ) {
    super(atoresService, snackBar, location);
  }

  ngOnInit(): void {
    const ator: Ator = this.route.snapshot.data['ator'];
    this.form.patchValue({
      _id: ator._id,
      nome: ator.nome,
    });
  }
}
