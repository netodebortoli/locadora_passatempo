package com.locadora.backendlocadora.entity.enums.converter;

import com.locadora.backendlocadora.entity.enums.TipoItem;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;
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
