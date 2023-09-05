package com.locadora.backendlocadora.service;

import com.locadora.backendlocadora.entity.Ator;
import com.locadora.backendlocadora.repositories.AtorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtorService {

    private AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public List<Ator> buscarTodos() {
        return atorRepository.findAll();
    }

    public Optional<Ator> buscarPorId(Long id) {
        return atorRepository.findById(id);
    }

}
