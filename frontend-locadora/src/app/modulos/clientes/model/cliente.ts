import {BaseModel} from "../../../shared/base/base.model";

export interface Cliente extends BaseModel{
  numInscricao: string,
  nome: string,
  dataNascimento: string,
  sexo: string,
  status: string
}
