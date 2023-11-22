import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BaseFormComponent} from "../../../../shared/base/components/base-form/base-form.component";
import {Socio} from "../../model/socio";
import {SociosService} from "../../socios.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, UntypedFormArray, Validators} from "@angular/forms";
import {BuscaCepService} from "../../../../shared/services/busca-cep.service";
import {catchError, finalize, forkJoin, of} from "rxjs";
import {UfService} from "../../../../shared/services/uf.service";
import {Uf} from "../../model/uf";
import {Endereco} from "../../model/endereco";
import {Dependente} from "../../model/dependente";

@Component({
  selector: 'app-socio-form',
  templateUrl: './socio-form.component.html',
  styleUrls: ['./socio-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SocioFormComponent extends BaseFormComponent<Socio> implements OnInit {

  isLoading: boolean = false;
  dataFutura: Date;
  ufsDisponiveis!: Uf[];
  sexos: string[] = ['F', 'M'];

  constructor(
    protected clientesService: SociosService,
    protected override snackBar: MatSnackBar,
    protected override location: Location,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private enderecoService: BuscaCepService,
    private ufService: UfService
  ) {
    super('Cliente', clientesService, snackBar, location);
    this.dataFutura = new Date();
  }

  ngOnInit(): void {
    this.loadData();
  }

  override form: FormGroup = this.formBuilder.group({
    _id: [''],
    nome: ["dfadfsa", [Validators.required, Validators.maxLength(255)]],
    dataNascimento: [null, Validators.required],
    sexo: ['', Validators.required],
    cpf: ['', Validators.required],
    telefone: ['', Validators.required],
    endereco: this.formBuilder.group({
      _id: [''],
      logradouro: ['', [Validators.required, Validators.maxLength(255)]],
      bairro: ['', [Validators.required, Validators.maxLength(255)]],
      numero: ['', [Validators.maxLength(10)]],
      localidade: ['', [Validators.required, Validators.maxLength(255)]],
      uf: ['', [Validators.required, Validators.maxLength(255)]],
      cep: ['', [Validators.required]]
    }),
    dependentes: this.formBuilder.array([])
  });

  private loadData() {
    const socio: Socio = this.route.snapshot.data['socio'];
    this.form.patchValue({
      _id: socio._id,
      nome: socio.nome,
      dataNascimento: socio.dataNascimento,
      sexo: socio.sexo,
      cpf: socio.cpf,
      telefone: socio.telefone,
      endereco: {
        _id: socio.endereco?._id,
        logradouro: socio.endereco?.logradouro,
        bairro: socio.endereco?.bairro,
        numero: socio.endereco?.numero,
        localidade: socio.endereco?.localidade,
        uf: socio.endereco?.uf,
        cep: socio.endereco?.cep
      },
      dependentes: this.carregarDependentes(socio)
    });

    this.isLoading = true;

    forkJoin({
      ufs: this.ufService.list().pipe(
        catchError((erro) => {
          this.onError('Erro ao carregar os Estados.', erro);
          return of([]);
        })
      ),
    }).pipe(finalize(() => this.isLoading = false)).subscribe(
      (result) => {
        this.ufsDisponiveis = result.ufs;
      }
    )
  }

  protected adicionarDependente(dependente: Dependente = {
    _id: '',
    nome: '',
    dataNascimento: '',
    sexo: '',
    numInscricao: '',
    status: ''
  }) {

    const formDependente = this.form.get('dependentes') as UntypedFormArray;

    let form = this.formBuilder.group({
      _id: [dependente._id],
      nome: [dependente.nome, [Validators.required, Validators.maxLength(255)]],
      dataNascimento: [dependente.dataNascimento, Validators.required],
      sexo: [dependente.sexo, Validators.required]
    });

    formDependente.push(form);
  }

  protected removerDependente(indice: number){
    const dependentes = this.form.get('dependentes') as UntypedFormArray;
    dependentes.removeAt(indice);
  }

  private carregarDependentes(socio: Socio) {
    const dependentes = [];
    if (socio?.dependentes) {
      socio.dependentes.forEach(dependente => dependentes.push(this.adicionarDependente(dependente)));
    } else {
      dependentes.push(this.adicionarDependente());
    }
    return dependentes;
  }

  getDependentesFormArray() {
    return (<UntypedFormArray>this.form.get('dependentes')).controls;
  }

  getDependenteErrorMessage(campo: string, indice: number){
    return this.getFormArrayFieldErrorMessage(this.form, 'dependentes', campo, indice);
  }

// Métodos de busca de endereço
  private cleanFieldsFormEndereco() {
    this.form.get('endereco.logradouro')?.patchValue('');
    this.form.get('endereco.bairro')?.patchValue('');
    this.form.get('endereco.numero')?.patchValue('');
    this.form.get('endereco.localidade')?.patchValue('');
    this.form.get('endereco.uf')?.patchValue('');
  }

  private setarValoresEndereco(endereco: Endereco) {
    this.form.get('endereco.logradouro')?.patchValue(endereco.logradouro);
    this.form.get('endereco.bairro')?.patchValue(endereco.bairro);
    this.form.get('endereco.localidade')?.patchValue(endereco.localidade);
    this.form.get('endereco.uf')?.patchValue(endereco.uf);
  }

  protected buscarCep() {
    if (this.form.get('endereco.cep')?.valid) {
      let cep = this.form.get('endereco.cep')?.value;
      this.enderecoService.buscarCep(cep).subscribe({
        next: (response: Endereco) => {
          if (Object.keys(response).length !== 10) {
            this.snackBar.open("O CEP informado não possui nenhum endereço associado.", 'OK', {
              duration: 3000
            })
          } else {
            this.cleanFieldsFormEndereco();
            this.setarValoresEndereco(response);
          }
        },
      });
    }
  }

}
