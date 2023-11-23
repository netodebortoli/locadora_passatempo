package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.backendlocadora.domain.entity.DependenteEntity;

public interface DependenteRepository extends JpaRepository<DependenteEntity, Long> {

}
