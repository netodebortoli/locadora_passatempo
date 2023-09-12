package com.locadora.backendlocadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.service.AtorService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public List<Ator> listarTodos() {
        return atorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Ator buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator criarAtor(@RequestBody @Valid @NotNull Ator ator) {
        return atorService.salvar(ator);
    }

    @PutMapping("/{id}")
    public Ator atualizarAtor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Ator ator) {
        atorService.buscarPorId(id);
        ator.setId(id);
        return atorService.salvar(ator);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarAtor(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        atorService.deletar(id);
    }

}
