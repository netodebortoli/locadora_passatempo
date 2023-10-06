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

import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.service.ClasseService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public List<Classe> listarTodos() {
        return classeService.listarTodos();
    }

    @GetMapping("/{id}")
    public Classe buscarPorId(@NotNull @Positive @PathVariable Long id) {
        return classeService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Classe criarClasse(@RequestBody @NotNull @Valid Classe classe) throws RegistroNaoEncontradoException, NegocioException {
        return classeService.salvar(classe);
    }

    @PutMapping("/{id}")
    public Classe atualizarClasse(@NotNull @Positive @PathVariable Long id, @RequestBody @Valid @NotNull Classe classe) throws RegistroNaoEncontradoException, NegocioException {
        classeService.buscarPorId(id);
        return classeService.salvar(
                new Classe(id, classe.nome(), classe.valor(), classe.prazoDevolucao())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarClasse(@PathVariable @NotNull @Positive Long id) throws NegocioException {
        classeService.deletar(id);
    }
}
