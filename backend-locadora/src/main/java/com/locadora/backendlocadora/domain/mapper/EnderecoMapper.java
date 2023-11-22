package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Endereco;
import com.locadora.backendlocadora.domain.entity.EnderecoEntity;

@Component
public class EnderecoMapper extends GenericMapper<Endereco, EnderecoEntity> {

    public Endereco toModel(EnderecoEntity entity) {

        if (entity == null) {
            return null;
        }

        return new Endereco(
                entity.getId(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCep(),
                entity.getLocalidade(),
                entity.getUf());
    }

    public EnderecoEntity toEntity(Endereco model) {

        if (model == null) {
            return null;
        }

        EnderecoEntity entity = new EnderecoEntity();

        if (model.id() != null) {
            entity.setId(model.id());
        }

        entity.setLogradouro(model.logradouro());
        entity.setNumero(model.numero());
        entity.setBairro(model.bairro());
        entity.setCep(model.cep());
        entity.setLocalidade(model.localidade());
        entity.setUf(model.uf());

        return entity;
    }

}
