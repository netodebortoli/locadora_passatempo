package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.Ator;

@Component
public class AtorMapper extends GenericMapper<Ator, AtorEntity> {

    public Ator toDTO(AtorEntity registro) {

        if (registro == null) {
            return null;
        }

        return new Ator(registro.getId(), registro.getNome());
    }

    public AtorEntity toEntity(Ator registro) {

        if (registro == null) {
            return null;
        }

        AtorEntity entity = new AtorEntity();

        if (registro.id() != null) {
            entity.setId(registro.id());
        }
        
        entity.setNome(registro.nome());

        return entity;
    }

}
