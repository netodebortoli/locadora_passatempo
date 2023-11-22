package com.locadora.backendlocadora.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.enums.Categoria;

@Component
public class TituloMapper extends GenericMapper<Titulo, TituloEntity> {

    private AtorMapper atorMapper = new AtorMapper();
    private DiretorMapper diretorMapper = new DiretorMapper();
    private ClasseMapper classeMapper = new ClasseMapper();

    @Override
    public Titulo toModel(TituloEntity registro) {

        if (registro == null) {
            return null;
        }

        List<Ator> atores = registro.getAtores()
                .stream()
                .map(atorMapper::toModel)
                .collect(Collectors.toList());

        return new Titulo(
                registro.getId(),
                registro.getNome(),
                registro.getAno(),
                registro.getSinopse(),
                registro.getCategoria().getValor(),
                diretorMapper.toModel(registro.getDiretor()),
                classeMapper.toModel(registro.getClasse()),
                atores);
    }

    @Override
    public TituloEntity toEntity(Titulo model) {

        if (model == null) {
            return null;
        }

        TituloEntity entity = new TituloEntity();

        if (model.id() != null)
            entity.setId(model.id());

        entity.setNome(model.nome());
        entity.setAno(model.ano());
        entity.setSinopse(model.sinopse());
        entity.setCategoria(Stream.of(Categoria.values())
                .filter(categoria -> categoria.getValor().equals(model.categoria()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Categoria inválida: " + model.categoria())));
        entity.setClasse(classeMapper.toEntity(model.classe()));
        entity.setDiretor(diretorMapper.toEntity(model.diretor()));
        entity.setAtores(model.atores()
                .stream()
                .map(atorMapper::toEntity)
                .collect(Collectors.toList()));

        return entity;
    }
}
