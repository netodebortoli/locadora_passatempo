package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
