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

import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.service.ItemService;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> listarTodos() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return itemService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item criarItem(@RequestBody @Valid @NotNull Item item)
            throws RegistroNaoEncontradoException, NegocioException {
        return itemService.salvar(item);
    }

    @PutMapping("/{id}")
    public Item atualizarItem(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull Item item)
            throws RegistroNaoEncontradoException, NegocioException {
        itemService.buscarPorId(id);
        return itemService.salvar(
                new Item(id, item.numSerie(), item.tipoItem(), item.dataAquisicao(), item.titulo())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarItem(@PathVariable @Positive @NotNull Long id) throws NegocioException {
        itemService.deletar(id);
    }

}
