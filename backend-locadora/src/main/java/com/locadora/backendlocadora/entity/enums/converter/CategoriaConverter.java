package com.locadora.backendlocadora.entity.enums.converter;

import com.locadora.backendlocadora.entity.enums.Categoria;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;
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
