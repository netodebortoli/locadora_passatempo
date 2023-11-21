package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.domain.enums.TipoItem;
import com.locadora.backendlocadora.service.exception.NegocioException;

@Component
public class ItemMapper extends GenericMapper<Item, ItemEntity> {

    private TituloMapper tituloMapper = new TituloMapper();

    @Override
    public Item toModel(ItemEntity entity) {

        if (entity == null)
            return null;

        return new Item(
                entity.getId(),
                entity.getNumSerie(),
                entity.getTipoItem().getValor(),
                entity.getDataAquisicao(),
                tituloMapper.toModel(entity.getTitulo()));
    }

    @Override
    public ItemEntity toEntity(Item model) throws NegocioException {
        
        if (model == null) {
            return null;
        }

        ItemEntity entity = new ItemEntity();

        if (model.id() != null)
            entity.setId(model.id());

        entity.setNumSerie(model.numSerie());
        entity.setTipoItem(
                Stream.of(TipoItem.values())
                        .filter(tipoItem -> tipoItem.getValor().equals(model.tipoItem()))
                        .findFirst()
                        .orElseThrow(() -> new NegocioException("Tipo de Item inválido!")));
        entity.setDataAquisicao(model.dataAquisicao());
        entity.setTitulo(tituloMapper.toEntity(model.titulo()));

        return entity;
    }

}
