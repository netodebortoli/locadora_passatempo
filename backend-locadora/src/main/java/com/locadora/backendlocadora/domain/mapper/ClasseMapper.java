package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.Classe;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper extends GenericMapper<Classe, ClasseEntity> {

    @Override
    public Classe toDTO(ClasseEntity registro) {

        if (registro == null)
            return null;

        return new Classe(registro.getId(), registro.getNome(), registro.getValor(), registro.getPrazoDevolucao());
    }

    @Override
    public ClasseEntity toEntity(Classe registro) {

        if (registro == null)
            return null;

        ClasseEntity entity = new ClasseEntity();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNome(registro.nome());
        entity.setValor(registro.valor());
        entity.setPrazoDevolucao(registro.prazoDevolucao());

        return entity;
    }
}
