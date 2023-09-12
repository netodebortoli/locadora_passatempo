package com.locadora.backendlocadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
}
