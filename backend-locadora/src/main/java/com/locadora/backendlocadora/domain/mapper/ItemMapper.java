package com.locadora.backendlocadora.domain.mapper;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Item;
import com.locadora.backendlocadora.domain.dto.ItemDTO;
import com.locadora.backendlocadora.domain.enums.TipoItem;
import com.locadora.backendlocadora.service.exception.NegocioException;

@Component
public class ItemMapper extends GenericMapper<ItemDTO, Item> {

    @Override
    public ItemDTO toDTO(Item registro) {

        if (registro == null)
            return null;

        return new ItemDTO(
                registro.getId(),
                registro.getNumSerie(),
                registro.getTipoItem().getValor(),
                registro.getDataAquisicao());
    }

    @Override
    public Item toEntity(ItemDTO registro) throws NegocioException {
        
        if (registro == null) {
            return null;
        }

        Item entity = new Item();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNumSerie(registro.numSerie());
        entity.setTipoItem(
                Stream.of(TipoItem.values())
                        .filter(tipoItem -> tipoItem.getValor().equals(registro.tipoItem()))
                        .findFirst()
                        .orElseThrow(() -> new NegocioException("Tipo de Item inválido!")));
        entity.setDataAquisicao(registro.dataAquisicao());

        return entity;
    }

}
