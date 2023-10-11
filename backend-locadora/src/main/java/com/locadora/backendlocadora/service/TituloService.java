package com.locadora.backendlocadora.service;

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

        // Verificando se a tupla (diretor/nome de filme) existe
        Titulo tituloBanco = this.getMapper().toDTO(
                repository.findIfTituloExists(model.diretor().id(), model.nome()));

        if (tituloBanco != null && !tituloBanco.id().equals(model.id())) {
            throw new NegocioException("O título " + model.nome().toUpperCase() + ", do diretor "
                    + model.diretor().nome().toUpperCase() + " já foi cadastrado.");
        }

        // Verificando se um mesmo ator foi cadastrado duas vezes no mesmo título
        
    }

    // TODO: adicionar regra de negocio que nao permite excluir um titulo com item's associados
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {
        super.deletar(id);
    }

}
