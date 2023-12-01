import {Location} from "@angular/common";
import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {Observable, catchError, finalize, forkJoin, map, of, retry, startWith} from "rxjs";
import {BaseFormComponent} from "../../../../shared/base/components/base-form/base-form.component";
import {Cliente} from "../../../clientes/model/cliente";
import {ClientesService} from "../../../clientes/services/clientes.service";
import {ItensService} from "../../../itens/itens.service";
import {Item} from "../../../itens/model/item";
import {LocacoesService} from "../../locacoes.service";
import {Locacao} from './../../model/locacao';

@Component({
  selector: 'app-locacao-form',
  templateUrl: './locacao-form.component.html',
  styleUrls: ['./locacao-form.component.scss']
})
export class LocacaoFormComponent extends BaseFormComponent<Locacao> {

  formEnable: boolean = true;

  itensDisponiveis: Item[] = [];
  clientesDisponiveis: Cliente[] = [];
  protected currentDate: Date;
  protected isLoading: boolean = false;
  filteredItensOptions!: Observable<Item[]>;
  filteredClientsOptions!: Observable<Cliente[]>;

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
    this.filteredItensOptions = this.form.get('item')!.valueChanges.pipe(
      startWith(''),
      map(value => {
        if (!value || typeof value === 'string') {
          return this._filterItens(value || '');
        }
        return this._filterItens('');
      }),
    );
    this.filteredClientsOptions = this.form.get('cliente')!.valueChanges.pipe(
      startWith(''),
      map(value => {
        if (!value || typeof value === 'string') {
          return this._filterClients(value || '');
        }
        return this._filterClients('');
      }),
    );
  }

  check(control: string) {
    setTimeout(() => {
      const value = this.form.get(control)!.value;
      if (!value || typeof value === 'string') {
        this.form.get(control)!.setValue(null);
      }
    });
  }

  private _filterItens(value: string): Item[] {
    const filterValue = value.toLowerCase();
    return this.itensDisponiveis.filter(item => item.numSerie.toLowerCase().includes(filterValue));
  }

  private _filterClients(value: string): Cliente[] {
    const filterValue = value.toLowerCase();
    return this.clientesDisponiveis.filter(cliente => cliente.nome.toLowerCase().includes(filterValue));
  }

  itemClick(event: any) {
    this.form.get('item')?.setValue(event.option.value);
    this.getDataPrevistaEValorPeloItem();
  }

  displayItemSeriesNumber(item: Item) {
    return item?.numSerie ?? '';
  }

  displayClientName(client: Cliente) {
    return client?.nome ?? '';
  }

  clientClick(event: any) {
    this.form.get('cliente')?.setValue(event.option.value);
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

    if (locacao.dataDevolucaoEfetiva != null) {
      this.form.disable();
      this.form.updateValueAndValidity();
      this.formEnable = false;
      return;
    }

    this.isLoading = true;

    forkJoin({
      itens: this.itemService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar itens.', erro);
          return of([]);
        })
      ),
      clientes: this.clienteService.listAllActiveClients().pipe(
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

  protected readonly origin = origin;
}
