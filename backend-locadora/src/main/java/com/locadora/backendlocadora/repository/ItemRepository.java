package com.locadora.backendlocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.backendlocadora.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
