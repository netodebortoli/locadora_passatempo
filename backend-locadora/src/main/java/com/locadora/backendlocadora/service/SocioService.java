package com.locadora.backendlocadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Dependente;
import com.locadora.backendlocadora.domain.Socio;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.mapper.SocioMapper;
import com.locadora.backendlocadora.repository.SocioRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class SocioService extends GenericService<Socio, Long, SocioRepository, SocioEntity, SocioMapper> {

    @Autowired
    private DependenteService dependenteService;

    @Autowired
    private ClienteService clienteService;

    public SocioService(SocioRepository repository, SocioMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Sócio");
    }

    @Transactional(rollbackOn = { Exception.class })
    @Override
    public Socio salvar(@Valid @NotNull Socio model) throws RegistroNaoEncontradoException, NegocioException {

        validarSave(model);

        if (model.getId() == null) {
            model.setNumInscricao(clienteService.gerarNumInscricao(model));
        } else {
            model.setNumInscricao(buscarPorId(model.getId()).getNumInscricao());
        }

        if (model.getDependentes() != null && !model.getDependentes().isEmpty()) {
            model.getDependentes().forEach(dependente -> {
                if (dependente.getId() == null) {
                    dependente.setNumInscricao(clienteService.gerarNumInscricao(dependente));
                } else {
                    dependente.setNumInscricao(
                            dependenteService.buscarPorId(
                                    dependente.getId())
                                    .getNumInscricao());
                }
            });
        }

        return mapper.toModel(this.repository.saveAndFlush(mapper.toEntity(model)));
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
    }

    @Transactional(rollbackOn = { Exception.class })
    public Socio atualizarSocio(@NotNull @Valid Long id, @NotBlank @Valid String novoStatus) {

        Socio socioFromDB = buscarPorId(id);

        socioFromDB.setStatus(novoStatus);

        if (socioFromDB.getDependentes() != null && !socioFromDB.getDependentes().isEmpty()) {
            socioFromDB.getDependentes().forEach(d -> d.setStatus(novoStatus));
        }

        return mapper.toModel(this.repository.saveAndFlush(mapper.toEntity(socioFromDB)));
    }

    public Dependente atualizarDependente(@NotNull @Valid Long id, @NotBlank @Valid String novoStatus)
            throws NegocioException {
        return this.dependenteService.atualizarDependente(id, novoStatus);
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        clienteService.deletar(id);
    }

}
