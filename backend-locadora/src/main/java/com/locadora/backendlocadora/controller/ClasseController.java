package com.locadora.backendlocadora.controller;

import com.locadora.backendlocadora.domain.dto.ClasseDTO;
import com.locadora.backendlocadora.service.ClasseService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ClasseDTO criarClasse(@RequestBody @NotNull @Valid ClasseDTO classe){
        return classeService.salvar(classe);
    }

    @PutMapping("/{id}")
    public ClasseDTO atualizarClasse(@NotNull @Positive @PathVariable Long id, @RequestBody @Valid @NotNull ClasseDTO registro){
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
