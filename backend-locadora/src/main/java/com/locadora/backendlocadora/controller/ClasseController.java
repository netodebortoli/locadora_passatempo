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

import com.locadora.backendlocadora.domain.dto.ClasseDTO;
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
    public List<ClasseDTO> listarTodos(){
        return classeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ClasseDTO buscarPorId(@NotNull @Positive @PathVariable Long id){ return classeService.buscarPorId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClasseDTO criarClasse(@RequestBody @NotNull @Valid ClasseDTO classe) throws RegistroNaoEncontradoException, NegocioException{
        return classeService.salvar(classe);
    }

    @PutMapping("/{id}")
    public ClasseDTO atualizarClasse(@NotNull @Positive @PathVariable Long id, @RequestBody @Valid @NotNull ClasseDTO registro) throws RegistroNaoEncontradoException, NegocioException{
        classeService.buscarPorId(id);
        ClasseDTO classeDTO = new ClasseDTO(id, registro.nome(), registro.valor(), registro.prazoDevolucao());
        return classeService.salvar(classeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarClasse(@PathVariable @NotNull @Positive Long id) throws NegocioException {
        classeService.deletar(id);
    }
}
