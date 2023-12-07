import {Cliente} from "./cliente";
import {Endereco} from "./endereco";
import {Dependente} from "./dependente";

export interface Socio extends Cliente {
  cpf: string,
  telefone: string,
  endereco: Endereco,
  dependentes: Dependente[]
}
