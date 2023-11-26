package com.locadora.backendlocadora.domain.entity;

import java.sql.Date;

import com.locadora.backendlocadora.domain.enums.TipoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @NotNull
    @Column(name = "numero_inscricao", nullable = false, updatable = false)
    protected String numInscricao;

    @NotBlank
    @Column(nullable = false)
    protected String nome;

    @NotNull
    @Column(nullable = false, name = "data_nascimento")
    protected Date dataNascimento;

    @NotBlank
    @Column(nullable = false, length = 1)
    @Pattern(regexp = "M|m|F|f")
    protected String sexo;

    @NotNull
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    protected TipoStatus status = TipoStatus.ATIVO;

}
