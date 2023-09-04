package com.locadora.backendlocadora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulos")
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

    @ManyToOne
    @JoinColumn(name = "id_diretor", referencedColumnName = "id")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "id_classe", referencedColumnName = "id")
    private Classe classe;

    @ManyToMany
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private List<Ator> atores;

}
