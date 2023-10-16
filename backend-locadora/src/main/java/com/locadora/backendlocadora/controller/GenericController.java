package com.locadora.backendlocadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locadora.backendlocadora.domain.mapper.GenericMapper;
import com.locadora.backendlocadora.service.GenericService;
import com.locadora.backendlocadora.service.exception.NegocioException;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Controller
public abstract class GenericController<K, M, E, MP extends GenericMapper<M, E>, R extends JpaRepository<E, K>, S extends GenericService<M, K, R, E, MP>> {

    @Autowired
    protected S service;

    @GetMapping
    public List<M> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public M buscarPorId(@PathVariable @NotNull @Positive K id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable @Positive @NotNull K id) throws NegocioException {
        service.deletar(id);
    }

}
