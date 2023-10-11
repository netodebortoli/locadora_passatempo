package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.domain.enums.TipoItem;
import com.locadora.backendlocadora.service.exception.NegocioException;

@Component
public class ItemMapper extends GenericMapper<Item, ItemEntity> {

    TituloMapper tituloMapper = new TituloMapper();

    @Override
    public Item toDTO(ItemEntity registro) {

        if (registro == null)
            return null;

        return new Item(
                registro.getId(),
                registro.getNumSerie(),
                registro.getTipoItem().getValor(),
                registro.getDataAquisicao(),
                tituloMapper.toDTO(registro.getTitulo()));
    }

    @Override
    public ItemEntity toEntity(Item registro) throws NegocioException {
        
        if (registro == null) {
            return null;
        }

        ItemEntity entity = new ItemEntity();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNumSerie(registro.numSerie());
        entity.setTipoItem(
                Stream.of(TipoItem.values())
                        .filter(tipoItem -> tipoItem.getValor().equals(registro.tipoItem()))
                        .findFirst()
                        .orElseThrow(() -> new NegocioException("Tipo de Item inválido!")));
        entity.setDataAquisicao(registro.dataAquisicao());
        entity.setTitulo(tituloMapper.toEntity(registro.titulo()));

        return entity;
    }

}
