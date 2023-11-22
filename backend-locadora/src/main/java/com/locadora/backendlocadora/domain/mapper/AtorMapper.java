package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.Ator;

@Component
public class AtorMapper extends GenericMapper<Ator, AtorEntity> {

    public Ator toModel(AtorEntity entity) {

        if (entity == null) {
            return null;
        }

        return new Ator(entity.getId(), entity.getNome());
    }

    public AtorEntity toEntity(Ator model) {

        if (model == null) {
            return null;
        }

        AtorEntity entity = new AtorEntity();

        if (model.id() != null) {
            entity.setId(model.id());
        }
        
        entity.setNome(model.nome());

        return entity;
    }

}
