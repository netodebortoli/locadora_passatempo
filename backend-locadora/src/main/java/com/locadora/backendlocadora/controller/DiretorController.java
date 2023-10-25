package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.mapper.DiretorMapper;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.service.DiretorService;
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
@RequestMapping("/api/diretores")
@Tag(name = "Controladora de Diretor", description = "Fornece serviços REST para acesso e manipualação de dados de diretores")
public class DiretorController
        extends GenericController<Long, Diretor, DiretorEntity, DiretorMapper, DiretorRepository, DiretorService> {

    @Operation(description = "Cria um objeto do tipo Diretor", responses = {
            @ApiResponse(responseCode = "201", description = "Diretor criador com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Diretor.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diretor criarDiretor(
            @RequestBody @Valid @NotNull Diretor diretor)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(diretor);
    }

    @Operation(description = "Atualiza um objeto do tipo Diretor pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Diretor atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Diretor.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Diretor não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Diretor atualizarDiretor(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid @NotNull Diretor diretor) throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Diretor(id, diretor.nome()));
    }

}
