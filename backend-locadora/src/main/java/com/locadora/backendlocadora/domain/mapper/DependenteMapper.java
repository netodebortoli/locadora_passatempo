package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;

public class DependenteMapper extends GenericMapper<Dependente, DependenteEntity> {

    @Override
    public Dependente toModel(DependenteEntity entity) {

        if (entity == null) {
            return null;
        }

        Dependente model = new Dependente();
        model.setId(entity.getId());
        model.setNome(entity.getNome());
        model.setDataNascimento(entity.getDataNascimento());
        model.setSexo(entity.getSexo());
        model.setNumInscricao(entity.getNumInscricao());

        return model;
    }

    public DependenteEntity toEntity(Dependente model) {

        if (model == null)
            return null;

        DependenteEntity entity = new DependenteEntity();

        entity.setId(model.getId());
        entity.setNome(model.getNome());
        entity.setDataNascimento(model.getDataNascimento());
        entity.setSexo(model.getSexo());
        entity.setNumInscricao(model.getNumInscricao());

        return entity;
    }

}
