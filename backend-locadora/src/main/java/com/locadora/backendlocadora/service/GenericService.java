package com.locadora.backendlocadora.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.backendlocadora.domain.mapper.GenericMapper;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public abstract class GenericService<M, K, R extends JpaRepository<E, K>, E, MP extends GenericMapper<M, E>> {

    protected R repository;

    protected String humanReadableName;

    protected MP mapper;

    protected GenericService(R repository, MP mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // public Paginacao<M> listarTodos(int pageNumber, int pageSize) {
    //     Page<E> page = this.repository.findAll(PageRequest.of(pageNumber, pageSize));
    //     List<M> result = page.get().map(mapper::toModel).collect(Collectors.toList());
    //     return new Paginacao<M>(result, page.getTotalElements(), page.getTotalPages());
    // }

    public List<M> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    public M buscarPorId(@Positive @NotNull K id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    public void deletar(@Valid @NotNull K id) throws RegistroNaoEncontradoException, NegocioException {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id)));
    }

    public abstract void validarSave(@NotNull @Valid M model) throws RegistroNaoEncontradoException, NegocioException;

    @Transactional(rollbackOn = { Exception.class })
    public M salvar(@Valid @NotNull M model) throws RegistroNaoEncontradoException, NegocioException {
        validarSave(model);
        return mapper.toModel(this.repository.saveAndFlush(mapper.toEntity(model)));
    }

}
