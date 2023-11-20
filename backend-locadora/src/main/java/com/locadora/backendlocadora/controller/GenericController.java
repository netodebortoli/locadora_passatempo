package com.locadora.backendlocadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locadora.backendlocadora.domain.Paginacao;
import com.locadora.backendlocadora.domain.mapper.GenericMapper;
import com.locadora.backendlocadora.service.GenericService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Controller
public abstract class GenericController<K, M, E, MP extends GenericMapper<M, E>, R extends JpaRepository<E, K>, S extends GenericService<M, K, R, E, MP>> {

    @Autowired
    protected S service;

    @Operation(description = "Obtem uma lista de todos os objetos desta entidade.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista dos objetos desta entidade.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao obter lista de objetos da entidade.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping
    public Paginacao<M> listarTodos(
            @RequestParam(defaultValue = "0", name = "page") @PositiveOrZero int pageNumber,
            @RequestParam(defaultValue = "10", name = "size") @Positive @Max(50) int pageSize) {
        return service.listarTodos(pageNumber, pageSize);
    }

    @Operation(description = "Obtem um objeto desta entidade buscando pelo ID passado na requisição.", responses = {
            @ApiResponse(responseCode = "200", description = "Objeto encontrado.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar os dados da entidade.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Objeto não encontrado.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/{id}")
    public M buscarPorId(@PathVariable @NotNull @Positive K id) {
        return service.buscarPorId(id);
    }

    @Operation(description = "Exclui o objeto desta entidade pelo ID passado na requisição.", responses = {
            @ApiResponse(responseCode = "204", description = "Objeto excluido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao excluir."),
            @ApiResponse(responseCode = "404", description = "Objeto não encontrado.")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable @Positive @NotNull K id) throws NegocioException {
        service.deletar(id);
    }

}
