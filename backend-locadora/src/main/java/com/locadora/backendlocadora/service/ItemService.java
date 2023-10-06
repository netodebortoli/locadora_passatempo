package com.locadora.backendlocadora.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.domain.mapper.ItemMapper;
import com.locadora.backendlocadora.repository.ItemRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ItemService extends GenericService<Item, Long, ItemRepository, ItemEntity, ItemMapper> {

    public ItemService(ItemRepository repository, ItemMapper mapper) {
        super(repository, mapper);
        this.setHumanReadableName("Item");
    }

    @Override
    public void validarSave(@NotNull @Valid Item model) throws RegistroNaoEncontradoException, NegocioException {

        Item item = this.getMapper().toDTO(
                repository.findItemByNumSerie(model.numSerie()));

        if (model.dataAquisicao().after(Date.valueOf(LocalDate.now()))) {
            throw new NegocioException("A Data de Aquisição não pode ser posterior ao dia de hoje.");
        }

        if (item != null && !model.id().equals(item.id())) {
            throw new NegocioException(
                    "O Número de Série " + model.numSerie() + " já foi registrado e não é aceito novamente.");
        }
    }

    // TODO nao permitir excluir um item com locações associada
    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException {
        super.deletar(id);
    }

}
