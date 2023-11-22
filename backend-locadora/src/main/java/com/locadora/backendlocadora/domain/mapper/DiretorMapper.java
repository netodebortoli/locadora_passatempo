package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;

@Component
public class DiretorMapper extends GenericMapper<Diretor, DiretorEntity> {

    @Override
    public Diretor toModel(DiretorEntity entity) {

        if (entity == null) {
            return null;
        }

        return new Diretor(entity.getId(), entity.getNome());
    }

    @Override
    public DiretorEntity toEntity(Diretor model) {

        if (model == null)
            return null;

        DiretorEntity entity = new DiretorEntity();

        if (model.id() != null)
            entity.setId(model.id());

        entity.setNome(model.nome());

        return entity;
    }
}
