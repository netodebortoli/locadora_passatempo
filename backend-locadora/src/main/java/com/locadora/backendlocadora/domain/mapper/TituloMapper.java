package com.locadora.backendlocadora.domain.mapper;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.service.exception.NegocioException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TituloMapper extends GenericMapper<Titulo, TituloEntity> {

    private AtorMapper atorMapper = new AtorMapper();
    private DiretorMapper diretorMapper = new DiretorMapper();
    private ClasseMapper classeMapper = new ClasseMapper();

    @Override
    public Titulo toDTO(TituloEntity registro) {
        if (registro == null)
            return null;

        List<Ator> atores = registro.getAtores()
                .stream()
                .map(atorMapper::toDTO)
                .collect(Collectors.toList());

        return new Titulo(
                registro.getId(),
                registro.getNome(),
                registro.getAno(),
                registro.getSinopse(),
                registro.getCategoria().getValor(),
                diretorMapper.toDTO(registro.getDiretor()),
                classeMapper.toDTO(registro.getClasse()),
                atores
        );
    }

    @Override
    public TituloEntity toEntity(Titulo registro) throws NegocioException {
        if (registro == null)
            return null;

        TituloEntity entity = new TituloEntity();

        if (registro.id() != null)
            entity.setId(registro.id());

        entity.setNome(registro.nome());
        entity.setAno(registro.ano());
        entity.setSinopse(registro.sinopse());
        entity.setCategoria(
                Stream.of(Categoria.values())
                        .filter(categoria -> categoria.getValor().equals(registro.categoria()))
                        .findFirst()
                        .orElseThrow(() -> new NegocioException("Categoria inválida!")));
        entity.setClasse(classeMapper.toEntity(registro.classe()));
        entity.setDiretor(diretorMapper.toEntity(registro.diretor()));
        entity.setAtores(
                registro.atores()
                        .stream()
                        .map(atorMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return entity;
    }
}
