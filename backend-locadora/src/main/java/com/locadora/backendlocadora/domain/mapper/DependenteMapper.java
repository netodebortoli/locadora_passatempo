package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.enums.TipoStatus;

@Component
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
        model.setStatus(entity.getStatus().getValor());

        return model;
    }

    public DependenteEntity toEntity(Dependente model) {

        if (model == null)
            return null;

        DependenteEntity entity = new DependenteEntity();

        if (model.getId() != null)
            entity.setId(model.getId());

        if (model.getStatus() != null)
            entity.setStatus(Stream.of(TipoStatus.values())
                    .filter(status -> status.getValor().equals(model.getStatus()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Status inválido: " + model.getStatus())));

        entity.setNome(model.getNome());
        entity.setDataNascimento(model.getDataNascimento());
        entity.setSexo(model.getSexo());
        entity.setNumInscricao(model.getNumInscricao());

        return entity;
    }

}
