import { BaseModel } from "src/app/shared/base/base.model";
import { Ator } from "../../atores/model/ator";
import { Classe } from "../../classes/model/classe";
import { Diretor } from "../../diretores/model/diretor";

export interface Titulo extends BaseModel {
  nome: string;
  ano: string;
  sinopse: string;
  categoria: string;
  classe: Classe;
  diretor: Diretor;
  atores: Ator[]
}
