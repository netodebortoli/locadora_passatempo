package com.locadora.backendlocadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.DiretorEntity;

@Repository
public interface DiretorRepository extends JpaRepository<DiretorEntity, Long> {

    @Query("From DiretorEntity d INNER JOIN TituloEntity t ON d.id = t.diretor.id WHERE d.id = :idDiretor")
    public DiretorEntity findSeDiretorTemTitulos(@Param("idDiretor") Long idDiretor);
}
