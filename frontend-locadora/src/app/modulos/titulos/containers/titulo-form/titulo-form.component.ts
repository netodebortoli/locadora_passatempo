import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
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
    ano: ['', [Validators.required, Validators.min(1800)]],
    sinopse: ['', [Validators.required,  Validators.maxLength(2000)]],
    categoria: ['', Validators.required],
    classe: [, Validators.required],
    diretor: [, Validators.required],
    atores: [[], Validators.required],
  });

  atoresDisponiveis$: Observable<Ator[]> | null = null;
  diretoresDisponiveis$: Observable<Diretor[]> | null = null;
  classesDisponiveis$: Observable<Classe[]> | null = null;
  categoriasDisponiveis$: Observable<string[]> | null = null;
  // categoriasDisponiveis$: string[] = ['ROMANCE', 'COMEDIA', 'DRAMA'];

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

  ngOnInit(): void {
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
    this.loadOptionsForSelect();
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
    this.classesDisponiveis$ = this.classesService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar classes.');
        return of([]);
      })
    );
    this.categoriasDisponiveis$ = this.categoriaService.list().pipe(
      catchError(() => {
        this.onError('Erro ao carregar categorias.');
        return of([]);
      })
    );
  }
}
