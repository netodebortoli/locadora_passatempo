package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.backendlocadora.domain.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
