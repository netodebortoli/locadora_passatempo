package com.locadora.backendlocadora.domain.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locações")
public class LocacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @Column(name = "data_locacao", nullable = false)
    private Date dataLocacao;

    @NotNull
    @Column(name = "data_devolucao_prevista", nullable = false)
    private Date dataDevolucaoPrevista;

    @Column(name = "data_devolucao_efetiva")
    private Date dataDevolucaoEfetiva;

    @NotNull
    @Column(name = "valor_cobrado", nullable = false)
    private Double valorCobrado;

    @Column(name = "multa_cobrada")
    private Double multaCobrada;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private ClienteEntity<?> cliente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    private ItemEntity item;

}
