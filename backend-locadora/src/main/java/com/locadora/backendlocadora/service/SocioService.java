package com.locadora.backendlocadora.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.mapper.SocioMapper;
import com.locadora.backendlocadora.repository.SocioRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class SocioService extends GenericService<Socio, Long, SocioRepository, SocioEntity, SocioMapper> {

    public SocioService(SocioRepository repository, SocioMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Sócio");
    }

    @Override
    public void validarSave(@NotNull @Valid Socio model) throws RegistroNaoEncontradoException, NegocioException {

        Socio socioFromDB = this.getMapper().toModel(this.repository.findByCpf(model.getCpf()));

        if (socioFromDB != null && model.getId() != socioFromDB.getId()) {
            throw new NegocioException("O número de CPF informado já está cadastrado.");
        }

        if (model.getId() != null) {
            socioFromDB = this.buscarPorId(model.getId());
            if (socioFromDB != null && !model.getCpf().equals(socioFromDB.getCpf())) {
                throw new NegocioException("Não é permitido alterar o número de CPF.");
            }
        }

        if (model.getDataNascimento().after(Date.valueOf(LocalDate.now()))) {
            throw new NegocioException("A data de nascimento não pode ser superior a data atual.");
        }

    }

    /*
     * TODO: Não é permitida a exclusão de um cliente que tenha locações
     * Na exclusão de um cliente, devem ser excluídas tambémas suas reservas.
     */
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        super.deletar(id);
    }

}
