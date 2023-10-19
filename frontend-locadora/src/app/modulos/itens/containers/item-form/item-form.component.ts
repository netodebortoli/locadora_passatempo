import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { catchError, finalize, forkJoin, of } from 'rxjs';
import { TipoItemService } from 'src/app/modulos/enums/tipoItem/tipo-item.service';
import { Titulo } from 'src/app/modulos/titulos/model/titulo';
import { TitulosService } from 'src/app/modulos/titulos/titulos.service';
import { BaseFormComponent } from 'src/app/shared/base/components/base-form/base-form.component';

import { ItensService } from '../../itens.service';
import { Item } from '../../model/item';

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
  titulosDisponiveis: Titulo[] = [];
  tiposDisponiveis: string[] = [];

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

  isLoading: boolean = false;

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    const item: Item = this.route.snapshot.data['item'];
    this.form.patchValue({
      _id: item._id,
      numSerie: item.numSerie,
      dataAquisicao: item.dataAquisicao,
      tipoItem: item.tipoItem,
      titulo: item.titulo,
    });

    this.isLoading = true;

    forkJoin({
      titulos: this.titulosService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar títulos.', erro);
          return of([]);
        })
      ),
      tipos: this.tipoItemService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar tipos de itens.', erro);
          return of([]);
        })
      ),
    })
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe((result) => {
        this.titulosDisponiveis = result.titulos;
        this.tiposDisponiveis = result.tipos;
      });
  }
}
