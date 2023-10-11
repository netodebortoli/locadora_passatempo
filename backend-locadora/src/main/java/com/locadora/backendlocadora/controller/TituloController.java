package com.locadora.backendlocadora.controller;

import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.service.TituloService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titulos")
public class TituloController {

    private TituloService tituloService;

    public TituloController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping
    public List<Titulo> listarTodos() {
        return tituloService.listarTodos();
    }

    @GetMapping("/{id}")
    public Titulo buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return tituloService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Titulo criarTitulo(@RequestBody @Valid @NotNull Titulo titulo) throws NegocioException {
        return tituloService.salvar(titulo);
    }

    @PutMapping("/{id}")
    public Titulo atualizarTitulo(@RequestBody @Valid @NotNull Titulo titulo, @PathVariable @Positive @NotNull Long id) throws NegocioException {
        tituloService.buscarPorId(id);
        return tituloService.salvar(
                new Titulo(id, titulo.nome(), titulo.ano(), titulo.sinopse(), titulo.categoria(), titulo.diretor(), titulo.classe(), titulo.atores())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarTitulo(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        tituloService.deletar(id);
    }

}
