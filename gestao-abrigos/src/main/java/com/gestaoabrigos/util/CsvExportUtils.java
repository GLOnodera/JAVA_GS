package com.gestaoabrigos.util;

import com.gestaoabrigos.dto.RecursoDisponivelDTO;
import java.io.PrintWriter;
import java.util.List;

public class CsvExportUtils {

    public static void exportRecursosToCsv(List<RecursoDisponivelDTO> recursos,
                                           PrintWriter writer) {
        writer.println("ID,Nome,Tipo,Unidade,Quantidade Disponivel,Status");

        recursos.forEach(r -> writer.println(
                r.getId() + "," +
                        escapeCsv(r.getNome()) + "," +
                        escapeCsv(r.getTipo()) + "," +
                        escapeCsv(r.getUnidade()) + "," +
                        r.getQuantidadeDisponivel() + "," +
                        escapeCsv(r.getStatusEstoque())
        ));
    }

    private static String escapeCsv(String input) {
        return input == null ? "" : "\"" + input.replace("\"", "\"\"") + "\"";
    }
}