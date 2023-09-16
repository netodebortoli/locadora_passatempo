import { BaseModel } from "src/app/shared/base/base.model";

export interface Classe extends BaseModel {
  nome: string;
  valor: string;
  prazoDevolucao: string;
}
