package com.locadora.backendlocadora.domain.entity;

import java.sql.Date;

import com.locadora.backendlocadora.domain.enums.TipoItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "num_serie", nullable = false)
    private String numSerie;

    @NotNull
    @Column(name = "tipo_item", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoItem tipoItem;

    @NotNull
    @Column(name = "data_aquisicao", nullable = false)
    private Date dataAquisicao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_titulo", referencedColumnName = "id")
    private TituloEntity titulo;

}
