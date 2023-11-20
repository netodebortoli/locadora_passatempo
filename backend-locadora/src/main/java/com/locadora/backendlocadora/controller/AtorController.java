package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.mapper.AtorMapper;
import com.locadora.backendlocadora.repository.AtorRepository;
import com.locadora.backendlocadora.service.AtorService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/atores")
@Tag(name = "Controladora de Ator", description = "Fornece serviços REST para acesso e manipulação de dados de atores.")
public class AtorController extends GenericController<Long, Ator, AtorEntity, AtorMapper, AtorRepository, AtorService> {

    @Operation(description = "Cria um objeto do tipo Ator", responses = {
            @ApiResponse(responseCode = "201", description = "Ator criador com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Ator.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator criarAtor(@RequestBody @Valid @NotNull Ator ator)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(ator);
    }

    @Operation(description = "Atualiza um objeto do tipo Ator pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Ator atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Ator.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Ator não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Ator atualizarAtor(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Ator ator)
            throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Ator(id, ator.nome()));
    }

}
