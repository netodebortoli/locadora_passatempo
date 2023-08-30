package com.locadora.backendlocadora.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
}
