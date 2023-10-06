import {Ator} from "../../atores/model/ator";
import {Diretor} from "../../diretores/model/diretor";
import {Classe} from "../../classes/model/classe";

export interface Titulo {
  _id: string;
  nome: string;
  ano: string;
  sinopse: string;
  categoria: string;
  classe: Classe;
  diretor: Diretor;
  atores: Ator[]
}
