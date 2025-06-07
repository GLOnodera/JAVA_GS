package com.gestaoabrigos.util;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationUtils {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue alertQueue;

    public void sendLowStockAlert(String resourceName, int remainingQuantity) {
        String message = String.format(
                "ALERTA: Recurso %s está com estoque baixo. Quantidade restante: %d",
                resourceName,
                remainingQuantity
        );
        rabbitTemplate.convertAndSend(alertQueue.getName(), message);
    }

    public void sendNewShelterNotification(String shelterName, String location) {
        String message = String.format(
                "NOVO ABRIGO: %s localizado em %s foi cadastrado no sistema",
                shelterName,
                location
        );
        rabbitTemplate.convertAndSend(alertQueue.getName(), message);
    }
}