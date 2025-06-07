package com.gestaoabrigos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "GS_DISTRIBUICAORECURSO", schema = "RM553779")
public class DistribuicaoRecurso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "distribuicao_seq")
    @SequenceGenerator(name = "distribuicao_seq", sequenceName = "GS_DISTRIBUICAO_SEQ", allocationSize = 1)
    @Column(name = "ID_DISTRIBUICAO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ABRIGO", nullable = false)
    private Abrigo abrigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RECURSO", nullable = false)
    private Recurso recurso;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "DATA_DISTRIBUICAO", nullable = false)
    private LocalDate dataDistribuicao = LocalDate.now();

    @Column(name = "RESPONSAVEL", length = 100)
    private String responsavel;
}