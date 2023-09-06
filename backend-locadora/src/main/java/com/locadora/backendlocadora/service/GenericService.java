package com.locadora.backendlocadora.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Service
public abstract class GenericService<M, K, R extends JpaRepository<M, K>> {

    private R repository;

    private String humanReadableName;

    public GenericService(R repository) {
        this.repository = repository;
    }

    public List<M> listarTodos() {
        return repository.findAll();
    }

    public M buscarPorId(@Positive @NotNull K id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    public void deletar(@Valid @NotNull K id) throws NegocioException, RegistroNaoEncontradoException {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id)));
    }

    public abstract void validarSave(@NotNull @Valid M model) throws RegistroNaoEncontradoException;

    public M salvar(@Valid @NotNull M model) {
        validarSave(model);
        return this.repository.saveAndFlush(model);
    }

}
