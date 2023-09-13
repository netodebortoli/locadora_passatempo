package com.locadora.backendlocadora.controller;

import java.util.List;

import com.locadora.backendlocadora.domain.dto.DiretorDTO;
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

import com.locadora.backendlocadora.service.DiretorService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

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
    public List<DiretorDTO> listarTodos() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public DiretorDTO buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return diretorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiretorDTO criarDiretor(@RequestBody @Valid @NotNull DiretorDTO diretor) throws RegistroNaoEncontradoException, NegocioException {
        return diretorService.salvar(diretor);
    }

    @PutMapping("/{id}")
    public DiretorDTO atualizarDiretor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull DiretorDTO diretor) throws RegistroNaoEncontradoException, NegocioException {
        diretorService.buscarPorId(id);
        return diretorService.salvar(new DiretorDTO(id, diretor.nome()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarDiretor(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        diretorService.deletar(id);
    }

}
