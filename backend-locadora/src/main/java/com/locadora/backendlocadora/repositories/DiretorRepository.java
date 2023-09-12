package com.locadora.backendlocadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Diretor;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {
}
