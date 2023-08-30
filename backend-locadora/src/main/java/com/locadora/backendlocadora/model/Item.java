package com.locadora.backendlocadora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_serie", nullable = false)
    private String numSerie;

    @Column(name = "tipo_item", nullable = false)
    private String tipoItem;

    @Column(name = "data_aquisicao", nullable = false)
    private Date dataAquisicao;

    private Titulo titulo;
}
