package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.Classe;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper extends GenericMapper<Classe, ClasseEntity> {

    @Override
    public Classe toModel(ClasseEntity entity) {

        if (entity == null)
            return null;

        return new Classe(entity.getId(), entity.getNome(), entity.getValor(), entity.getPrazoDevolucao());
    }

    @Override
    public ClasseEntity toEntity(Classe model) {

        if (model == null)
            return null;

        ClasseEntity entity = new ClasseEntity();

        if (model.id() != null)
            entity.setId(model.id());

        entity.setNome(model.nome());
        entity.setValor(model.valor());
        entity.setPrazoDevolucao(model.prazoDevolucao());

        return entity;
    }
}
