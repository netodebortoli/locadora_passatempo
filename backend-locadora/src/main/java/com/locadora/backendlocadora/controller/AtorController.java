package com.locadora.backendlocadora.controller;

import com.locadora.backendlocadora.entity.Ator;
import com.locadora.backendlocadora.service.AtorService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public List<Ator> listarTodos() {
        return atorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Ator buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator criarAtor(@RequestBody @Valid @NotNull Ator ator) {
        return atorService.criar(ator);
    }

    @PutMapping("/{id}")
    public Ator atualizarAtor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Ator ator) {
        return atorService.atualizar(id, ator);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarAtor(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        atorService.deletar(id);
    }

}
