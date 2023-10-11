package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.mapper.ClasseMapper;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ClasseService extends GenericService<Classe, Long, ClasseRepository, ClasseEntity, ClasseMapper> {

    public ClasseService(ClasseRepository repository, ClasseMapper classeMapper) {
        super(repository, classeMapper);
        this.setHumanReadableName("Classe");
    }

    @Override
    public void validarSave(@NotNull @Valid Classe model) throws RegistroNaoEncontradoException, NegocioException {

        Classe classeBanco = this.getMapper().toDTO(
                this.repository.findByNome(model.nome()));

        if (classeBanco != null && !model.id().equals(classeBanco.id())) {
            throw new NegocioException("Já existe a classe: " + model.nome().toUpperCase());
        }
    }

    // TODO: nao é permitido excluir uma classe relacionada a titulos
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        super.deletar(id);
    }
}
