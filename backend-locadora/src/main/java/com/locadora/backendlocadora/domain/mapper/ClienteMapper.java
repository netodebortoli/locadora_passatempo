package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Cliente;
import com.locadora.backendlocadora.domain.entity.ClienteEntity;
import com.locadora.backendlocadora.domain.enums.TipoStatus;

@Component
public class ClienteMapper extends GenericMapper<Cliente, ClienteEntity> {

    @Override
    public Cliente toModel(ClienteEntity entity) {

        if (entity == null) {
            return null;
        }

        return new Cliente(
                entity.getId(),
                entity.getNumInscricao(),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getSexo(),
                entity.getStatus().getValor());
    }

    @Override
    public ClienteEntity toEntity(Cliente model) {

        if (model == null) {
            return null;
        }

        ClienteEntity entity = new ClienteEntity();

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
