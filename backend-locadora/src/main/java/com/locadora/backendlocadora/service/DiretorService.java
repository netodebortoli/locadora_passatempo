package com.locadora.backendlocadora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.entity.Diretor;
import com.locadora.backendlocadora.repositories.DiretorRepository;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class DiretorService {
    
    private DiretorRepository diretorRepository;

    private String humanReadableName = "Diretor";

    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    public List<Diretor> buscarTodos() {
        return this.diretorRepository.findAll();
    }
    
    
    public Diretor buscarPorId(@Positive @NotNull Long id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    public Diretor criar(@Valid @NotNull Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    public Diretor atualizar(@Valid @NotNull Long id, @Valid @NotNull Diretor diretor) {
        return diretorRepository.findById(id)
                .map(atorAtualizado -> {
                    atorAtualizado.setNome(diretor.getNome());
                    return diretorRepository.save(atorAtualizado);
                }).orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));
    }

    //TODO: adicionar regra de negocio que nao permite excluir um diretor que possui titulos associados
    public void deletar(@Valid @NotNull Long id) {
        diretorRepository.delete(diretorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id)));
    }

}
