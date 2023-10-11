package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.mapper.DiretorMapper;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class DiretorService extends GenericService<Diretor, Long, DiretorRepository, DiretorEntity, DiretorMapper> {
    
    public DiretorService(DiretorRepository repository, DiretorMapper diretorMapper) {
        super(repository, diretorMapper);
        this.setHumanReadableName("Diretor");
    }

    @Override
    public void validarSave(@NotNull @Valid Diretor model) throws RegistroNaoEncontradoException {
        return;
    }

    //TODO adicionar regra de negocio que nao permite excluir um diretor que possui titulos associados e sobrescrever o metodo
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        super.deletar(id);
    }

}
