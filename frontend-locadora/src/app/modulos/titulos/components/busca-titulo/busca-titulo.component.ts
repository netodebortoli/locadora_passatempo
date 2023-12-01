import {Component, DestroyRef, inject, OnInit} from '@angular/core';
import {catchError, debounce, debounceTime, Observable, of} from "rxjs";
import {Titulo} from "../../model/titulo";
import {TitulosService} from "../../titulos.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorDialogComponent} from "../../../../shared/components/error-dialog/error-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {FormControl} from "@angular/forms";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-busca-titulo',
  templateUrl: './busca-titulo.component.html',
  styleUrls: ['./busca-titulo.component.scss']
})
export class BuscaTituloComponent implements OnInit {

  titulosDisponiveis: Titulo[] = [];
  titulosFiltrados: Titulo[] = [];
  opcoesFiltradas!: Observable<Titulo[]>;
  opcoesDeBusca: string[] = ['Nome', 'Ator', 'Categoria', 'Diretor'];
  filtroControl: FormControl = new FormControl();
  buscaTituloControl: FormControl = new FormControl();
  private destroyRef = inject(DestroyRef);

  constructor(
    protected service: TitulosService,
    protected dialog: MatDialog
  ) {
    this.refresh();
  }

  displayNomeTitulo(titulo: Titulo) {
    return titulo?.nome ?? '';
  }

  ngOnInit() {
    this.refresh();

    this.filtroControl.setValue('Nome');

    this.filtroControl.valueChanges
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        debounceTime(200)
      )
      .subscribe(() => {
        this.buscaTituloControl.setValue('');
      });

    this.buscaTituloControl.valueChanges
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        debounceTime(200)
      )
      .subscribe(termoDaBusca => {
        if (!termoDaBusca) {
          this.titulosFiltrados = this.titulosDisponiveis;
        } else {
          termoDaBusca = termoDaBusca.toLowerCase();
          this.titulosFiltrados = this.titulosDisponiveis.filter(titulo => {
             const tipoDeBusca = this.filtroControl.value;
             switch (tipoDeBusca){
               case 'Ator': return titulo.atores.some(ator=> ator.nome.toLowerCase().includes(termoDaBusca));
               case 'Categoria': return titulo.categoria.toLowerCase().includes(termoDaBusca);
               case 'Diretor': return titulo.diretor.nome.toLowerCase().includes(termoDaBusca);
               default:  return titulo.nome.toLowerCase().includes(termoDaBusca);
             }
          });
        }
      })
  }

  refresh() {
    this.service.list().pipe(
      takeUntilDestroyed(this.destroyRef),
      catchError((erro) => {
        this.onError('Erro ao carregar títulos', erro);
        return of([]);
      })
    ).subscribe(list => {
      this.titulosDisponiveis = list;
      this.titulosFiltrados = list
    });
  }

  onError(mensagem: string, err: HttpErrorResponse) {
    this.dialog.open(ErrorDialogComponent, {data: {mensagemDaOperacao: mensagem, serverResponse: err.error}});
  }

}
