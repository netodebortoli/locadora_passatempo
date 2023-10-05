package com.locadora.backendlocadora.domain.mapper;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.dto.AtorDTO;

@Component
public class AtorMapper extends GenericMapper<AtorDTO, Ator> {

    public AtorDTO toDTO(Ator registro) {

        if (registro == null) {
            return null;
        }

        return new AtorDTO(registro.getId(), registro.getNome());
    }

    public Ator toEntity(AtorDTO registro) {

        if (registro == null) {
            return null;
        }

        Ator entity = new Ator();

        if (registro.id() != null) {
            entity.setId(registro.id());
        }
        
        entity.setNome(registro.nome());

        return entity;
    }

}
