package com.gestaoabrigos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoAbrigoDTO {
    private Long recursoId;
    private String recursoNome;
    private String recursoTipo;
    private String recursoUnidade;
    private Long abrigoId;
    private String abrigoNome;
    private Integer quantidade;
    private LocalDate dataDistribuicao;
    private String ultimaDistribuicao; // Formato amigável

    // Método para formatar a data
    public void formatarData() {
        if (dataDistribuicao != null) {
            ultimaDistribuicao = dataDistribuicao.getDayOfMonth() + "/" +
                    dataDistribuicao.getMonthValue() + "/" +
                    dataDistribuicao.getYear();
        } else {
            ultimaDistribuicao = "Nunca distribuído";
        }
    }
}