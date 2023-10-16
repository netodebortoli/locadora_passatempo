package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.mapper.TituloMapper;
import com.locadora.backendlocadora.repository.TituloRepository;
import com.locadora.backendlocadora.service.TituloService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/titulos")
public class TituloController
        extends GenericController<Long, Titulo, TituloEntity, TituloMapper, TituloRepository, TituloService> {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Titulo criarTitulo(@RequestBody @Valid @NotNull Titulo titulo) throws NegocioException {
        return service.salvar(titulo);
    }

    @PutMapping("/{id}")
    public Titulo atualizarTitulo(@RequestBody @Valid @NotNull Titulo titulo, @PathVariable @Positive @NotNull Long id)
            throws NegocioException {
        service.buscarPorId(id);
        return service.salvar(
                new Titulo(id, titulo.nome(), titulo.ano(), titulo.sinopse(), titulo.categoria(), titulo.diretor(),
                        titulo.classe(), titulo.atores()));
    }

}
