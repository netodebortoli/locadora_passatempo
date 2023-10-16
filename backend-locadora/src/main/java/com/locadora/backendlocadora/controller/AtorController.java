package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.mapper.AtorMapper;
import com.locadora.backendlocadora.repository.AtorRepository;
import com.locadora.backendlocadora.service.AtorService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/atores")
public class AtorController extends GenericController<Long, Ator, AtorEntity, AtorMapper, AtorRepository, AtorService> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator criarAtor(@RequestBody @Valid @NotNull Ator ator)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(ator);
    }

    @PutMapping("/{id}")
    public Ator atualizarAtor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Ator ator)
            throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Ator(id, ator.nome()));
    }

}
