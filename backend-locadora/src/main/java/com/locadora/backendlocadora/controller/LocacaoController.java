package com.locadora.backendlocadora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.backendlocadora.domain.Locacao;
import com.locadora.backendlocadora.domain.entity.LocacaoEntity;
import com.locadora.backendlocadora.domain.mapper.LocacaoMapper;
import com.locadora.backendlocadora.repository.LocacaoRepository;
import com.locadora.backendlocadora.service.LocacaoService;
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
@RequestMapping("/api/locacoes")
@Tag(name = "Controladora de Locações", description = "Fornece serviços REST para acesso e manipulação de dados de Locações.")
public class LocacaoController
        extends GenericController<Long, Locacao, LocacaoEntity, LocacaoMapper, LocacaoRepository, LocacaoService> {

    @Operation(description = "Cria um objeto do tipo Locação", responses = {
            @ApiResponse(responseCode = "201", description = "Locação criado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Locação.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Locacao criarLocacao(
            @RequestBody @Valid @NotNull Locacao locacao) throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(locacao);
    }

    @Operation(description = "Atualiza um objeto do tipo Locação pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Locação atualizada com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Locação.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Locação não encontrada.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Locacao atualizarLocacao(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid @NotNull Locacao locacao) throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(new Locacao(
                id,
                locacao.dataLocacao(),
                locacao.dataDevolucaoPrevista(),
                locacao.dataDevolucaoEfetiva(),
                locacao.valorCobrado(),
                locacao.multaCobrada(),
                locacao.cliente(),
                locacao.item()));
    }

}
