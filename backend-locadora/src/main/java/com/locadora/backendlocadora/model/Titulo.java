package com.locadora.backendlocadora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String ano;

    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private String categoria;

    private Diretor diretor;

    private Classe classe;

    private Ator ator;

    private Item item;
}
