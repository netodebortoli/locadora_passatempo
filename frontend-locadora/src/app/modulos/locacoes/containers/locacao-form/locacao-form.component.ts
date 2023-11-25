import {Component} from '@angular/core';
import {BaseFormComponent} from "../../../../shared/base/components/base-form/base-form.component";
import {Locacao} from "../../model/locacao";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {LocacoesService} from "../../locacoes.service";
import {Item} from "../../../itens/model/item";
import {Cliente} from "../../../clientes/model/cliente";
import {catchError, finalize, forkJoin, map, Observable, of, startWith} from "rxjs";
import {ItensService} from "../../../itens/itens.service";
import {ClientesService} from "../../../clientes/services/clientes.service";

@Component({
  selector: 'app-locacao-form',
  templateUrl: './locacao-form.component.html',
  styleUrls: ['./locacao-form.component.scss']
})
export class LocacaoFormComponent extends BaseFormComponent<Locacao> {

  itensDisponiveis: Item[] = [];
  clientesDisponiveis: Cliente[] = [];
  protected currentDate: Date;
  protected isLoading: boolean = false;

  override form: FormGroup = this.formBuilder.group({
    _id: [''],
    item: ['', Validators.required],
    cliente: ['', Validators.required],
    dataLocacao: [{value: '', disabled: true}, Validators.required,],
    dataDevolucaoPrevista: ['', Validators.required],
    valorCobrado: ['', Validators.required]
  });

  constructor(
    protected locacoesService: LocacoesService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private itemService: ItensService,
    private clienteService: ClientesService
  ) {
    super('Locação', locacoesService, snackBar, location);
    this.currentDate = new Date();
  }

  ngOnInit() {
    this.loadData();
  }

  protected getDataPrevistaEValorPeloItem() {
    const item: Item = this.form.get('item')?.value;
    if (item) {
      let diaDevolucaoPrevista = new Date().getDate() + 3;
      let dataPrevisao = new Date().setDate(diaDevolucaoPrevista);
      this.form.get('dataDevolucaoPrevista')?.patchValue(new Date(dataPrevisao));
      this.form.get('valorCobrado')?.patchValue(item.titulo.classe.valor);
    }
    this.form.get('dataLocacao')?.patchValue(new Date());
  }

  private loadData() {
    const locacao: Locacao = this.route.snapshot.data['locacao'];
    this.form.patchValue({
      _id: locacao._id,
      item: locacao.item,
      cliente: locacao.cliente,
      dataLocacao: locacao.dataLocacao,
      dataDevolucaoPrevista: locacao.dataDevolucaoPrevista,
      valorCobrado: locacao.valorCobrado
    });

    this.isLoading = true;

    forkJoin({
      itens: this.itemService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar itens.', erro);
          return of([]);
        })
      ),
      clientes: this.clienteService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar clientes.', erro);
          return of([]);
        })
      )
    }).pipe(finalize(() => (this.isLoading = false)))
      .subscribe((result) => {
        this.itensDisponiveis = result.itens;
        this.clientesDisponiveis = result.clientes;
      });
  }
}
