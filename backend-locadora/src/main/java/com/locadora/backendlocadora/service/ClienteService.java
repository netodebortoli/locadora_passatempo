package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Cliente;
import com.locadora.backendlocadora.domain.entity.ClienteEntity;
import com.locadora.backendlocadora.domain.mapper.ClienteMapper;
import com.locadora.backendlocadora.repository.ClienteRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteService extends GenericService<Cliente, Long, ClienteRepository, ClienteEntity, ClienteMapper> {

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Cliente");
    }

    @Override
    public void validarSave(@NotNull @Valid Cliente model) throws RegistroNaoEncontradoException, NegocioException {
        return;
    }

}
