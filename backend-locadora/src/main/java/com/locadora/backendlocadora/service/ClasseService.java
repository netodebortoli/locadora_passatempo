package com.locadora.backendlocadora.service;

import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.mapper.ClasseMapper;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ClasseService extends GenericService<Classe, Long, ClasseRepository, ClasseEntity, ClasseMapper> {

    public ClasseService(ClasseRepository repository, ClasseMapper classeMapper){
        super(repository, classeMapper);
        this.setHumanReadableName("Classe");
    }

    @Override
    public void validarSave(Classe model) throws RegistroNaoEncontradoException, NegocioException {
        
        ClasseEntity classeBanco = this.repository.findByNome(model.nome());

        if (classeBanco != null && !model.id().equals(classeBanco.getId())) {
            throw new NegocioException("Já existe a classe: " + model.nome().toUpperCase());
        }
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException {
        super.deletar(id);
    }
}
