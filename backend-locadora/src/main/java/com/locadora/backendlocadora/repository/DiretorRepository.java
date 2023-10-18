package com.locadora.backendlocadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.DiretorEntity;

@Repository
public interface DiretorRepository extends JpaRepository<DiretorEntity, Long> {

    @Query("From DiretorEntity d, TituloEntity t "
            + "WHERE d.id = t.diretor.id AND d.id = :idDiretor")
    public List<DiretorEntity> findSeDiretorTemTitulos(@Param("idDiretor") Long idDiretor);
}
