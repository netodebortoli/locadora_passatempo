package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.entity.AtorEntity;

@Repository
public interface AtorRepository extends JpaRepository<AtorEntity, Long> {
}
