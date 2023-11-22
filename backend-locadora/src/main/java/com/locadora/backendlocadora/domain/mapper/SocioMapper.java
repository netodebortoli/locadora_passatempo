package com.locadora.backendlocadora.domain.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.enums.TipoStatus;

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
        model.setStatus(entity.getStatus().getValor());
        model.setDependentes(new ArrayList<>());

        if (entity.getDependentes() != null && !entity.getDependentes().isEmpty()) {
            List<Dependente> dependentes = entity.getDependentes().stream().map(dependente -> {
                var dp = dependenteMapper.toModel(dependente);
                return dp;
            }).collect(Collectors.toList());

            model.setDependentes(dependentes);
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

        if (model.getStatus() != null)
            entity.setStatus(Stream.of(TipoStatus.values())
                    .filter(status -> status.getValor().equals(model.getStatus()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Status inválido: " + model.getStatus())));

        entity.setNome(model.getNome());
        entity.setDataNascimento(model.getDataNascimento());
        entity.setSexo(model.getSexo());
        entity.setCpf(model.getCpf());
        entity.setNumInscricao(model.getNumInscricao());
        entity.setTelefone(model.getTelefone());
        entity.setEndereco(enderecoMapper.toEntity(model.getEndereco()));
        entity.setDependentes(new ArrayList<>());

        if (model.getDependentes() != null && !model.getDependentes().isEmpty()) {
            List<DependenteEntity> dependentes = model.getDependentes().stream().map(dependente -> {
                var dp = dependenteMapper.toEntity(dependente);
                dp.setSocio(entity);
                return dp;
            }).collect(Collectors.toList());

            entity.setDependentes(dependentes);
        }

        return entity;
    }

}
