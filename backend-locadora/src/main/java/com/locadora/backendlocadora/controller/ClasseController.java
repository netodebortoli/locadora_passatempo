package com.locadora.backendlocadora.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Controladora de Classe", description = "Fornece serviços REST para acesso e manipulação de dados de classes.")
public class ClasseController
        extends GenericController<Long, Classe, ClasseEntity, ClasseMapper, ClasseRepository, ClasseService> {

    @Operation(description = "Cria um objeto do tipo Classe.", responses = {
            @ApiResponse(responseCode = "201", description = "Classe criada com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Classe.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Classe criarClasse(@RequestBody @NotNull @Valid Classe classe)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(classe);
    }

    @Operation(description = "Atualiza um objeto do tipo Classe pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Classe atualizada com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Classe.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Classe não encontrada.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Classe atualizarClasse(@NotNull @Positive @PathVariable Long id, @RequestBody @Valid @NotNull Classe classe)
            throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(
                new Classe(id, classe.nome(), classe.valor(), classe.prazoDevolucao()));
    }
}
