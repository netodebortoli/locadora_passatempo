package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Query("from Classe where lower(nome) like lower(:nome)")
    public Classe findByNome(@Param("nome")String nome);
}
