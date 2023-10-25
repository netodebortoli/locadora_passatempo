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

import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.mapper.ItemMapper;
import com.locadora.backendlocadora.repository.ItemRepository;
import com.locadora.backendlocadora.service.ItemService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "Controladora de Item", description = "Fornece serviços REST para acesso e manipulação de dados de itens.")
public class ItemController extends GenericController<Long, Item, ItemEntity, ItemMapper, ItemRepository, ItemService> {

    @Operation(description = "Cria um objeto do tipo Item", responses = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao criar Item.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item criarItem(@RequestBody @Valid @NotNull Item item)
            throws RegistroNaoEncontradoException, NegocioException {
        return service.salvar(item);
    }

    @Operation(description = "Atualiza um objeto do tipo Item pelo ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar Item.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Item não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{id}")
    public Item atualizarItem(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Item item)
            throws RegistroNaoEncontradoException, NegocioException {
        service.buscarPorId(id);
        return service.salvar(
                new Item(id, item.numSerie(), item.tipoItem(), item.dataAquisicao(), item.titulo()));
    }

}
