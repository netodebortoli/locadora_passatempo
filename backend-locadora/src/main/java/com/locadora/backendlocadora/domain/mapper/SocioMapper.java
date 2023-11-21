package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.SocioEntity;

@Component
public class SocioMapper extends GenericMapper<Socio, SocioEntity> {

    EnderecoMapper enderecoMapper = new EnderecoMapper();
    DependenteMapper dependenteMapper = new DependenteMapper();

    public Socio toModel(SocioEntity entity) {

        if (entity == null) {
            return null;
        }

        Socio model = new Socio();

        model.setId(entity.getId());
        model.setNome(entity.getNome());
        model.setDataNascimento(entity.getDataNascimento());
        model.setSexo(entity.getSexo());
        model.setNumInscricao(entity.getNumInscricao());
        model.setCpf(entity.getCpf());
        model.setTelefone(entity.getTelefone());
        model.setEndereco(enderecoMapper.toModel(entity.getEndereco()));

        if (entity.getDependentes() != null) {
            model.setDependentes(
                    entity.getDependentes()
                            .stream()
                            .map(dependenteMapper::toModel)
                            .collect(Collectors.toList()));
        }

        return model;
    }

    public SocioEntity toEntity(Socio model) {

        if (model == null) {
            return null;
        }

        SocioEntity entity = new SocioEntity();

        if (model.getId() != null)
            entity.setId(model.getId());

        entity.setNome(model.getNome());
        entity.setDataNascimento(model.getDataNascimento());
        entity.setNumInscricao(model.getNumInscricao());
        entity.setSexo(model.getSexo());
        entity.setCpf(model.getCpf());
        entity.setTelefone(model.getTelefone());
        entity.setEndereco(enderecoMapper.toEntity(model.getEndereco()));

        if (model.getDependentes() != null) {
            entity.setDependentes(
                    model.getDependentes()
                            .stream()
                            .map(dependenteMapper::toEntity)
                            .collect(Collectors.toList()));
        }

        return entity;
    }

}
