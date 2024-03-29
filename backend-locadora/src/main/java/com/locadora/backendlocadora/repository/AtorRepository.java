package com.locadora.backendlocadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.AtorEntity;

@Repository
public interface AtorRepository extends JpaRepository<AtorEntity, Long> {

    @Query("From AtorEntity ORDER BY nome")
    public List<AtorEntity> findAll();

    @Query(value = "SELECT * FROM atores a INNER JOIN titulo_ator ta ON"
            + " a.id = ta.id_ator WHERE a.id = :idAtor", nativeQuery = true)
    public AtorEntity findSeAtorTemTitulos(@Param("idAtor") Long idAtor);
}
