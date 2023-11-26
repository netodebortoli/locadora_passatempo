import {BaseModel} from "../../../shared/base/base.model";
import {Item} from "../../itens/model/item";
import {Cliente} from "../../clientes/model/cliente";

export interface Locacao extends BaseModel {
  dataLocacao: string,
  dataDevolucaoPrevista: string,
  dataDevolucaoEfetiva: string,
  valorCobrado: string,
  multaCobrada: string,
  cliente: Cliente,
  item: Item
}
