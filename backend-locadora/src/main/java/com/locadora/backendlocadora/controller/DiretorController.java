package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.mapper.DiretorMapper;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.service.DiretorService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/diretores")
public class DiretorController
        extends GenericController<Long, Diretor, DiretorEntity, DiretorMapper, DiretorRepository, DiretorService> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diretor criarDiretor(@RequestBody @Valid @NotNull Diretor diretor)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(diretor);
    }

    @PutMapping("/{id}")
    public Diretor atualizarDiretor(@PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid @NotNull Diretor diretor) throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Diretor(id, diretor.nome()));
    }

}
