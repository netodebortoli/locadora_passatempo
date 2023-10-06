import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { CategoriasService } from 'src/app/modulos/categorias/categorias.service';

import { BaseFormComponent } from '../../../../shared/base/components/base-form/base-form.component';
import { AtoresService } from '../../../atores/atores.service';
import { Ator } from '../../../atores/model/ator';
import { DiretoresService } from '../../../diretores/diretores.service';
import { Diretor } from '../../../diretores/model/diretor';
import { Titulo } from '../../model/titulo';
import { TitulosService } from '../../titulos.service';

@Component({
  selector: 'app-titulo-form',
  templateUrl: './titulo-form.component.html',
  styleUrls: ['./titulo-form.component.scss'],
})
export class TituloFormComponent
  extends BaseFormComponent<Titulo>
  implements OnInit
{
  override form: FormGroup = this.formBuilder.group({
    _id: [''],
    nome: [''],
    ano: [''],
    sinopse: [''],
    categoria: [''],
    diretor: [],
    atores: [[]],
  });

  atoresDisponiveis$: Observable<Ator[]> | null = null;
  diretoresDisponiveis$: Observable<Diretor[]> | null = null;
  //categoriasDisponiveis$: Observable<Categoria[]> | null = null;
  categoriasDisponiveis$: string[] = ['A', 'B', 'C'];

  constructor(
    protected titulosService: TitulosService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private atoresService: AtoresService,
    private diretoresService: DiretoresService,
    private categoriaService: CategoriasService
  ) {
    super('Título', titulosService, snackBar, location);
  }

  ngOnInit(): void {
    const titulo: Titulo = this.route.snapshot.data['titulo'];
    this.form.patchValue({
      _id: titulo._id,
      nome: titulo.nome,
      ano: titulo.ano,
      sinopse: titulo.sinopse,
      categoria: titulo.categoria,
      diretor: titulo.diretor,
      atores: titulo.atores,
    });
    this.loadOptionsForSelect();
  }

  compareAtores(a: Ator, b: Ator) {
    return a._id === b._id;
  }

  loadOptionsForSelect() {
    this.atoresDisponiveis$ = this.atoresService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar atores.');
        return of([]);
      })
    );
    this.diretoresDisponiveis$ = this.diretoresService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar diretores.');
        return of([]);
      })
    );
    /* this.categoriasDisponiveis$ = this.categoriaService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar categorias.');
        return of([]);
      })
    ); */
  }
}
