import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { Titulo } from 'src/app/modulos/titulos/model/titulo';
import { TitulosService } from 'src/app/modulos/titulos/titulos.service';
import { BaseFormComponent } from 'src/app/shared/base/components/base-form/base-form.component';

import { ItensService } from '../../itens.service';
import { Item } from '../../model/item';
import { TipoItemService } from 'src/app/modulos/enums/tipoItem/tipo-item.service';

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss'],
})
export class ItemFormComponent
  extends BaseFormComponent<Item>
  implements OnInit
{
  override form: FormGroup = this.formBuilder.group({
    _id: [''],
    numSerie: ['', [Validators.required, Validators.maxLength(255)]],
    dataAquisicao: ['', Validators.required],
    tipoItem: ['', Validators.required],
    titulo: [, Validators.required],
  });

  dataMaxAquisicao: Date;
  titulosDisponiveis$: Observable<Titulo[]> | null = null;
  tiposDisponiveis$: Observable<string[]> | null = null;

  constructor(
    protected itensService: ItensService,
    protected titulosService: TitulosService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private tipoItemService: TipoItemService
  ) {
    super('Item', itensService, snackBar, location);
    this.dataMaxAquisicao = new Date();
  }

  ngOnInit(): void {
    const item: Item = this.route.snapshot.data['item'];
    this.form.patchValue({
      _id: item._id,
      numSerie: item.numSerie,
      dataAquisicao: item.dataAquisicao,
      tipoItem: item.tipoItem,
      titulo: item.titulo,
    });
    this.loadOptionsForSelect();
  }

  loadOptionsForSelect() {
    this.titulosDisponiveis$ = this.titulosService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar títulos.');
        return of([]);
      })
    );
    this.tiposDisponiveis$ = this.tipoItemService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar tipos de itens.');
        return of([]);
      })
    );
  }

}
