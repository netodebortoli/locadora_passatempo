package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.mapper.ClasseMapper;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.service.ClasseService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/classes")
public class ClasseController
        extends GenericController<Long, Classe, ClasseEntity, ClasseMapper, ClasseRepository, ClasseService> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Classe criarClasse(@RequestBody @NotNull @Valid Classe classe)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(classe);
    }

    @PutMapping("/{id}")
    public Classe atualizarClasse(@NotNull @Positive @PathVariable Long id, @RequestBody @Valid @NotNull Classe classe)
            throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(
                new Classe(id, classe.nome(), classe.valor(), classe.prazoDevolucao()));
    }
}
