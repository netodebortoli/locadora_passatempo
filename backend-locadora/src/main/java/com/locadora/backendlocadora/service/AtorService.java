package com.locadora.backendlocadora.service;

import com.locadora.backendlocadora.entity.Ator;
import com.locadora.backendlocadora.repositories.AtorRepository;
import com.locadora.backendlocadora.repositories.TituloRepository;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    private AtorRepository atorRepository;

    private final TituloRepository tituloRepository;
    private String humanReadableName = "Ator";

    public AtorService(AtorRepository atorRepository, TituloRepository tituloRepository) {
        this.atorRepository = atorRepository;
        this.tituloRepository = tituloRepository;
    }

    public List<Ator> buscarTodos() {
        return atorRepository.findAll();
    }

    public Ator buscarPorId(@Positive @NotNull Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    public Ator criar(@Valid @NotNull Ator ator) {
        return atorRepository.save(ator);
    }

    public Ator atualizar(@Valid @NotNull Long id, @Valid @NotNull Ator ator) {
        return atorRepository.findById(id)
                .map(atorAtualizado -> {
                    atorAtualizado.setNome(ator.getNome());
                    return atorRepository.save(atorAtualizado);
                }).orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    //TODO: adicionar regra de negocio que nao permite excluir um que possui titulos associados
    public void deletar(@Valid @NotNull Long id) {
        atorRepository.delete(atorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id)));
    }

}
