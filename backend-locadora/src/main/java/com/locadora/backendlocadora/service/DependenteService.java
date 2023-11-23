package com.locadora.backendlocadora.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.enums.TipoStatus;
import com.locadora.backendlocadora.domain.mapper.DependenteMapper;
import com.locadora.backendlocadora.repository.DependenteRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class DependenteService
        extends GenericService<Dependente, Long, DependenteRepository, DependenteEntity, DependenteMapper> {

    public DependenteService(DependenteRepository repository, DependenteMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Dependente");
    }

    @Override
    public void validarSave(@NotNull @Valid Dependente model) throws RegistroNaoEncontradoException, NegocioException {
        return;
    }

    @Transactional(rollbackOn = { Exception.class })
    public Dependente atualizarDependente(@NotNull @Valid Long id, @NotBlank @Valid String novoStatus) {

        DependenteEntity dependenteFromDB = this.repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(humanReadableName, id));

        dependenteFromDB.setStatus(Stream.of(TipoStatus.values())
                .filter(status -> status.getValor().equals(novoStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Status inválido: " + dependenteFromDB.getStatus())));

        return mapper.toModel(this.repository.save(dependenteFromDB));
    }

}
