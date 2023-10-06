package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.Diretor;
import org.springframework.stereotype.Component;

@Component
public class DiretorMapper extends GenericMapper<Diretor, DiretorEntity> {

    @Override
    public Diretor toDTO(DiretorEntity registro) {

        if (registro == null)
            return null;

        return new Diretor(registro.getId(), registro.getNome());
    }

    @Override
    public DiretorEntity toEntity(Diretor registro) {

        if (registro == null)
            return null;

        DiretorEntity entity = new DiretorEntity();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNome(registro.nome());

        return entity;
    }
}
