package com.gestaoabrigos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "GS_ABRIGO", schema = "RM553779")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abrigo_seq")
    @SequenceGenerator(name = "abrigo_seq", sequenceName = "GS_ABRIGO_SEQ", allocationSize = 1)
    @Column(name = "ID_ABRIGO")
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "CAPACIDADE", nullable = false)
    private Integer capacidade;

    @Column(name = "LOCALIZACAO", length = 200, nullable = false)
    private String localizacao;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DistribuicaoRecurso> recursos;
}