package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.backendlocadora.domain.entity.LocacaoEntity;

public interface LocacaoRepository extends JpaRepository<LocacaoEntity, Long> {
    
    // TODO: fazer busca de locaçoes para descobrir se o cliente está em débito
}
