package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.TituloEntity;

@Repository
public interface TituloRepository extends JpaRepository<TituloEntity, Long> {

    @Query("From TituloEntity where classe.id = :idClasse")
    public TituloEntity findTituloByClasse(@Param("idClasse") Long idClasse);

    @Query("From TituloEntity where diretor.id = :idDiretor and lower(nome) = lower(:nomeFilme)") 
    public TituloEntity findIfTituloExists(@Param("idDiretor") Long idDiretor, @Param("nomeFilme") String nomeFilme);

}