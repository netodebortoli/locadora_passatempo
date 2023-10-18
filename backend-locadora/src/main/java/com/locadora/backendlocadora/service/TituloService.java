package com.locadora.backendlocadora.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.mapper.TituloMapper;
import com.locadora.backendlocadora.repository.TituloRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class TituloService extends GenericService<Titulo, Long, TituloRepository, TituloEntity, TituloMapper> {

    @Autowired
    private AtorService atorService;

    @Autowired
    private DiretorService diretorService;

    @Autowired
    private ClasseService classeService;

    public TituloService(TituloRepository repository, TituloMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Título");
    }

    @Override
    public void validarSave(@NotNull @Valid Titulo model) throws RegistroNaoEncontradoException, NegocioException {

        // Verificando se os relacionamentos existem
        diretorService.buscarPorId(model.diretor().id());
        classeService.buscarPorId(model.classe().id());
        model.atores().forEach(ator -> atorService.buscarPorId(ator.id()));

        // Verificando se a tupla (diretor/nome de filme) já existe
        Titulo tituloBanco = this.getMapper().toDTO(
                repository.findSeTituloExiste(model.diretor().id(), model.nome()));

        if (tituloBanco != null && model.id() != tituloBanco.id()) {
            throw new NegocioException("O título " + model.nome().toUpperCase() + ", do diretor "
                    + model.diretor().nome().toUpperCase() + " já foi cadastrado.");
        }

        // Verificando se um mesmo ator já foi cadastrado no mesmo título
        Set<Object> set = new HashSet<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        model.atores().forEach(ator -> {
            if (!set.add(ator))
                map.put(ator.nome(), ator);
        });
        if (!map.isEmpty()) {
            throw new NegocioException(
                    String.format("Não é permitido cadastrar um Título com atores repetidos: %s", map.keySet()));
        }

    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {

        if (this.repository.findSeTituloPossuiItens(id) != null) {
            throw new NegocioException("Não é permitido excluir um Título com Itens associados.");
        }

        super.deletar(id);
    }

}
