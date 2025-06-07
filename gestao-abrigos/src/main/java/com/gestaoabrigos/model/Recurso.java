package com.gestaoabrigos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "GS_RECURSO", schema = "RM553779")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recurso_seq")
    @SequenceGenerator(name = "recurso_seq", sequenceName = "GS_RECURSO_SEQ", allocationSize = 1)
    @Column(name = "ID_RECURSO")
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "TIPO", length = 20, nullable = false)
    private String tipo;

    @Column(name = "UNIDADE", length = 20, nullable = false)
    private String unidade;

    @Column(name = "QUANTIDADE_TOTAL", nullable = false)
    private Integer quantidadeTotal = 0;

    @Column(name = "ESTOQUE_MINIMO", nullable = false)
    private Integer estoqueMinimo = 10;

    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL)
    private List<DistribuicaoRecurso> distribuicoes;
}