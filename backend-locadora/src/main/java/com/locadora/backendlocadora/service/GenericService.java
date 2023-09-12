package com.locadora.backendlocadora.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.mapper.GenericMapper;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Service
public abstract class GenericService<M, K, R extends JpaRepository<E, K>, E, MP extends GenericMapper<M, E>> {

    private R repository;

    private String humanReadableName;

    private MP mapper;

    public GenericService(R repository, MP mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<M> listarTodos() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.toDTO(entity))
                .collect(Collectors.toList());
    }

    public M buscarPorId(@Positive @NotNull K id) {
        return repository.findById(id)
                .map(entity -> mapper.toDTO(entity))
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    public void deletar(@Valid @NotNull K id) throws NegocioException, RegistroNaoEncontradoException {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id)));
    }

    public abstract void validarSave(@NotNull @Valid M model) throws RegistroNaoEncontradoException;

    public M salvar(@Valid @NotNull M model) {
        validarSave(model);
        return mapper.toDTO(this.repository.saveAndFlush(mapper.toEntity(model)));
    }

}
