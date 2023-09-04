package com.locadora.backendlocadora.repositories;

import com.locadora.backendlocadora.entity.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
}
