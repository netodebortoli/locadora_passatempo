package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.dto.DiretorDTO;
import org.springframework.stereotype.Component;

@Component
public class DiretorMapper extends GenericMapper<DiretorDTO, Diretor> {

    @Override
    public DiretorDTO toDTO(Diretor registro) {

        if (registro == null)
            return null;

        return new DiretorDTO(registro.getId(), registro.getNome());
    }

    @Override
    public Diretor toEntity(DiretorDTO registro) {

        if (registro == null)
            return null;

        Diretor entity = new Diretor();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNome(registro.nome());

        return entity;
    }
}
