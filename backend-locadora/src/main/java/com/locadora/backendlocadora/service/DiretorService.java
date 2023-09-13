package com.locadora.backendlocadora.service;

import com.locadora.backendlocadora.domain.dto.DiretorDTO;
import com.locadora.backendlocadora.domain.mapper.DiretorMapper;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class DiretorService extends GenericService<DiretorDTO, Long, DiretorRepository, Diretor, DiretorMapper> {
    
    public DiretorService(DiretorRepository repository, DiretorMapper diretorMapper) {
        super(repository, diretorMapper);
        this.setHumanReadableName("Diretor");
    }

    @Override
    public void validarSave(@NotNull @Valid DiretorDTO model) throws RegistroNaoEncontradoException {
        return;
    }

    //TODO: adicionar regra de negocio que nao permite excluir um diretor que possui titulos associados e sobrescrever o metodo
    @Override
    public void deletar(@Valid @NotNull Long id) throws NegocioException, RegistroNaoEncontradoException {
        super.deletar(id);
    }

}
