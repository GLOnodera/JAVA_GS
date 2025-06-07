package com.gestaoabrigos.util;

import com.gestaoabrigos.model.Recurso;
import java.util.List;

public class ResourceInventoryUtils {

    public static String calculateStockStatus(Recurso recurso, int distributedQuantity) {
        int available = recurso.getQuantidadeTotal() - distributedQuantity;
        double percentage = (double) available / recurso.getQuantidadeTotal();

        if (available <= 0) {
            return "ESGOTADO";
        } else if (percentage < 0.2) {
            return "CRÍTICO";
        } else if (percentage < 0.5) {
            return "ALERTA";
        } else {
            return "NORMAL";
        }
    }

    public static int calculateTotalDistributed(List<Integer> distributions) {
        return distributions.stream().mapToInt(Integer::intValue).sum();
    }
}