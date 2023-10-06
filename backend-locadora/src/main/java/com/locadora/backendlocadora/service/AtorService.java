package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.mapper.AtorMapper;
import com.locadora.backendlocadora.repository.AtorRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AtorService extends GenericService<Ator, Long, AtorRepository, AtorEntity, AtorMapper> {

    public AtorService(AtorRepository repository, AtorMapper atorMapper) {
        super(repository, atorMapper);
        this.setHumanReadableName("Ator");
    }

    @Override
    public void validarSave(@NotNull @Valid Ator model) throws RegistroNaoEncontradoException {
        return;
    }

    //TODO: adicionar regra de negocio que nao permite excluir um ator que possui titulos associados e sobrescrever o metodo
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException {
        super.deletar(id);
    }

}
