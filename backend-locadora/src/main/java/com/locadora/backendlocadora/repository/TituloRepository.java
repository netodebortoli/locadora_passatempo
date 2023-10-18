package com.locadora.backendlocadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.TituloEntity;

@Repository
public interface TituloRepository extends JpaRepository<TituloEntity, Long> {

    @Query("From TituloEntity WHERE diretor.id = :idDiretor"
            + " AND lower(nome) = lower(:nomeFilme)")
    public TituloEntity findSeTituloExiste(@Param("idDiretor") Long idDiretor, @Param("nomeFilme") String nomeFilme);

    @Query("From TituloEntity t, ItemEntity i WHERE t.id = i.titulo.id"
            + " AND t.id = :idTitulo")
    public List<TituloEntity> findSeTituloPossuiItens(@Param("idTitulo") Long idTitulo);
}