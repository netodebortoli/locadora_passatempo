package com.locadora.backendlocadora.domain.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

import com.locadora.backendlocadora.domain.enums.TipoItem;
@Converter(autoApply = true)
public class TipoItemConverter implements AttributeConverter<TipoItem, String> {

    @Override
    public String convertToDatabaseColumn(TipoItem tipoItem) {
        if (tipoItem == null) {
            return null;
        }
        return tipoItem.getValor();
    }

    @Override
    public TipoItem convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(TipoItem.values())
                .filter(tipoItem -> tipoItem.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
