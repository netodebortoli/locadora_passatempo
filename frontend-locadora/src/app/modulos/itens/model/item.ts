import { Titulo } from '../../titulos/model/titulo';
import { BaseModel } from "src/app/shared/base/base.model";

export interface Item extends BaseModel {
  numSerie: string;
  dataAquisicao: string;
  tipoItem: string;
  titulo: Titulo
}
