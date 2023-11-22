import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { catchError, delay, finalize, forkJoin, Observable, of } from 'rxjs';
import { ClassesService } from 'src/app/modulos/classes/classes.service';
import { Classe } from 'src/app/modulos/classes/model/classe';
import { CategoriaService } from 'src/app/modulos/enums/categoria/categoria.service';

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
    nome: ['', [Validators.required, Validators.maxLength(255)]],
    ano: ['', [Validators.required, Validators.min(1800), Validators.max(new Date().getFullYear())]],
    sinopse: ['', [Validators.required, Validators.maxLength(2000)]],
    categoria: ['', Validators.required],
    classe: [, Validators.required],
    diretor: [, Validators.required],
    atores: [[], Validators.required],
  });

  atoresDisponiveis: Ator[] = [];
  diretoresDisponiveis: Diretor[] = [];
  classesDisponiveis: Classe[] = [];
  categoriasDisponiveis: string[] = [];

  constructor(
    protected titulosService: TitulosService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private atoresService: AtoresService,
    private diretoresService: DiretoresService,
    private classesService: ClassesService,
    private categoriaService: CategoriaService
  ) {
    super('Título', titulosService, snackBar, location);
  }

  isLoading: boolean = false;

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    const titulo: Titulo = this.route.snapshot.data['titulo'];
    this.form.patchValue({
      _id: titulo._id,
      nome: titulo.nome,
      ano: titulo.ano,
      sinopse: titulo.sinopse,
      categoria: titulo.categoria,
      classe: titulo.classe,
      diretor: titulo.diretor,
      atores: titulo.atores,
    });
    this.isLoading = true;

    forkJoin({
      atores: this.atoresService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar atores.', erro);
          return of([]);
        })
      ),
      diretores: this.diretoresService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar diretores.', erro);
          return of([]);
        })
      ),
      classes: this.classesService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar classes.', erro);
          return of([]);
        })
      ),
      categorias: this.categoriaService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar categorias.', erro);
          return of([]);
        })
      ),
    })
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe((result) => {
        this.atoresDisponiveis = result.atores;
        this.diretoresDisponiveis = result.diretores;
        this.classesDisponiveis = result.classes;
        this.categoriasDisponiveis = result.categorias;
      });

  }
}
