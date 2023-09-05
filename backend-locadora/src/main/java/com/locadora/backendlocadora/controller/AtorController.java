package com.locadora.backendlocadora.controller;

import com.locadora.backendlocadora.entity.Ator;
import com.locadora.backendlocadora.repositories.AtorRepository;
import com.locadora.backendlocadora.service.AtorService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atores")
public class AtorController {

    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public ResponseEntity<List<Ator>> listarTodos() {
        return ResponseEntity.ok().body(atorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public Optional<Ator> buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return atorService.buscarPorId(id);
    }


}
