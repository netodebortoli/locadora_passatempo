package com.locadora.backendlocadora.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Cliente;
import com.locadora.backendlocadora.domain.entity.ClienteEntity;
import com.locadora.backendlocadora.domain.mapper.ClienteMapper;
import com.locadora.backendlocadora.repository.ClienteRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteService extends GenericService<Cliente, Long, ClienteRepository, ClienteEntity, ClienteMapper> {

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Cliente");
    }

    public String gerarNumInscricao(Cliente c) {
        String ano = String.valueOf(LocalDate.now().getYear());
        String semestre = "01";
        if (LocalDate.now().getMonthValue() > 6)
            semestre = "02";
        String numRamString = String.valueOf((Math.random()) * 10).substring(0, 4);
        String diaNascimento = String.valueOf(c.getDataNascimento().toLocalDate().getDayOfMonth());
        String diaAtual = String.valueOf(LocalDate.now().getDayOfMonth());
        String mesNascimento = String.valueOf(c.getDataNascimento().toLocalDate().getMonthValue());
        String mesAtual = String.valueOf(LocalDate.now().getMonthValue());
        return (ano + semestre + numRamString + diaNascimento + diaAtual + mesNascimento + mesAtual).replace(".", "");
    }

    @Override
    public void validarSave(@NotNull @Valid Cliente model) throws RegistroNaoEncontradoException, NegocioException {
        return;
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {

        if (this.repository.isClientePossuiLocacoes(id)) {
            throw new NegocioException("Nao é possível excluir um cliente com locações em andamento.");
        }

        super.deletar(id);
    }

    public List<Cliente> buscarTodosClientesAtivos(){
        return repository.findAllActiveClients()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

}
