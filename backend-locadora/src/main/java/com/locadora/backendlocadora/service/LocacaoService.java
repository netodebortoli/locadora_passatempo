package com.locadora.backendlocadora.service;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Locacao;
import com.locadora.backendlocadora.domain.entity.LocacaoEntity;
import com.locadora.backendlocadora.domain.mapper.LocacaoMapper;
import com.locadora.backendlocadora.repository.LocacaoRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class LocacaoService extends GenericService<Locacao, Long, LocacaoRepository, LocacaoEntity, LocacaoMapper> {

    public LocacaoService(LocacaoRepository repository, LocacaoMapper classeMapper) {
        super(repository, classeMapper);
        this.setHumanReadableName("Locação");
    }

    @Override
    public void validarSave(@NotNull @Valid Locacao model) {
        return;
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        super.deletar(id);
    }
}
