package com.locadora.backendlocadora.domain.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

import com.locadora.backendlocadora.domain.enums.Categoria;
@Converter(autoApply = true)
public class CategoriaConverter implements AttributeConverter<Categoria, String> {
    @Override
    public String convertToDatabaseColumn(Categoria categoria) {
        if(categoria == null){
            return Categoria.OUTROS.getValor();
        }
        return categoria.getValor();
    }

    @Override
    public Categoria convertToEntityAttribute(String valor) {
       return Stream.of(Categoria.values())
               .filter(c -> c.getValor().equals(valor))
               .findFirst()
               .orElseThrow(IllegalArgumentException::new);
    }
}
