import {BaseModel} from "../../../shared/base/base.model";

export interface Endereco extends BaseModel {
  logradouro: string;
  bairro: string;
  numero: string;
  localidade: string;
  uf: string;
  cep: string;
}
