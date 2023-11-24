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

import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.mapper.TituloMapper;
import com.locadora.backendlocadora.repository.TituloRepository;
import com.locadora.backendlocadora.service.TituloService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/titulos")
@Tag(name = "Controladora de Título", description = "Fornece serviços REST para acesso e manipulação de dados de títulos.")
public class TituloController
        extends GenericController<Long, Titulo, TituloEntity, TituloMapper, TituloRepository, TituloService> {

    @Operation(description = "Cria um objeto do tipo Título", responses = {
            @ApiResponse(responseCode = "201", description = "Título criado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Título.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Titulo criarTitulo(@RequestBody @Valid @NotNull Titulo titulo) throws NegocioException {
        return service.salvar(titulo);
    }

    @Operation(description = "Atualiza um objeto do tipo Título pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Título atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Título.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Título não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Titulo atualizarTitulo(@RequestBody @Valid @NotNull Titulo titulo, @PathVariable @Positive @NotNull Long id)
            throws NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Titulo(
                id,
                titulo.nome(),
                titulo.ano(),
                titulo.sinopse(),
                titulo.categoria(),
                titulo.diretor(),
                titulo.classe(),
                titulo.atores()));
    }

}
