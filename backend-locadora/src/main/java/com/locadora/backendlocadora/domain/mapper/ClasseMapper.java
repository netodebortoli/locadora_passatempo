package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.dto.ClasseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper extends GenericMapper<ClasseDTO, Classe> {

    @Override
    public ClasseDTO toDTO(Classe registro) {

        if (registro == null)
            return null;

        return new ClasseDTO(registro.getId(), registro.getNome(), registro.getValor(), registro.getPrazoDevolucao());
    }

    @Override
    public Classe toEntity(ClasseDTO registro) {

        if (registro == null)
            return null;

        Classe entity = new Classe();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNome(registro.nome());
        entity.setValor(registro.valor());
        entity.setPrazoDevolucao(registro.prazoDevolucao());

        return entity;
    }
}
