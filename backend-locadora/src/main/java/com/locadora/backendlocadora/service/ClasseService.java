package com.locadora.backendlocadora.service;

import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.dto.ClasseDTO;
import com.locadora.backendlocadora.domain.mapper.ClasseMapper;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ClasseService extends GenericService<ClasseDTO, Long, ClasseRepository, Classe, ClasseMapper> {

    public ClasseService(ClasseRepository repository, ClasseMapper classeMapper){
        super(repository, classeMapper);
        this.setHumanReadableName("Classe");
    }

    @Override
    public void validarSave(ClasseDTO model) throws RegistroNaoEncontradoException {
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws NegocioException, RegistroNaoEncontradoException {
        super.deletar(id);
    }
}
