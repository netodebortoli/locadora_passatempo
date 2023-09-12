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

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.service.DiretorService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/diretores")
public class DiretorController {

    private DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService; 
    }

    @GetMapping
    public List<Diretor> listarTodos() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Diretor buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return diretorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diretor criarDiretor(@RequestBody @Valid @NotNull Diretor diretor) {
        return diretorService.salvar(diretor);
    }

    @PutMapping("/{id}")
    public Diretor atualizarDiretor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Diretor diretor) {
        diretorService.buscarPorId(id);
        diretor.setId(id);
        return diretorService.salvar(diretor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarDiretor(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        diretorService.deletar(id);
    }

}
