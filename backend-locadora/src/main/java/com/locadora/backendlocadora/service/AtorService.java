package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.repositories.AtorRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class AtorService extends GenericService<Ator, Long, AtorRepository> {

    public AtorService(AtorRepository repository) {
        super(repository);
        this.setHumanReadableName("Ator");
    }

    @Override
    public void validarSave(@NotNull @Valid Ator model) throws RegistroNaoEncontradoException {
        return;
    }

    //TODO: adicionar regra de negocio que nao permite excluir um ator que possui titulos associados e sobrescrever o metodo
    @Override
    public void deletar(@Valid @NotNull Long id) throws NegocioException, RegistroNaoEncontradoException {
        super.deletar(id);
    }

}
