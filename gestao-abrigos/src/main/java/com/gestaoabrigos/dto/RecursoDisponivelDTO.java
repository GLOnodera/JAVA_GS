package com.gestaoabrigos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoDisponivelDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String unidade;
    private Integer quantidadeDisponivel;
    private String statusEstoque; // "SUFICIENTE", "ALERTA", "CRÍTICO"

    // Método para calcular o status do estoque
    public void calcularStatusEstoque() {
        if (quantidadeDisponivel == null) {
            statusEstoque = "INDEFINIDO";
        } else if (quantidadeDisponivel <= 0) {
            statusEstoque = "CRÍTICO";
        } else if (quantidadeDisponivel <= 10) {
            statusEstoque = "ALERTA";
        } else {
            statusEstoque = "SUFICIENTE";
        }
    }
}