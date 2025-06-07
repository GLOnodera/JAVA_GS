package com.gestaoabrigos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbrigoNotFoundException extends RuntimeException {

    public AbrigoNotFoundException(Long id) {
        super("Abrigo não encontrado com ID: " + id);
    }

    public AbrigoNotFoundException(String nome) {
        super("Abrigo não encontrado com nome: " + nome);
    }

    public AbrigoNotFoundException(Double latitude, Double longitude) {
        super("Nenhum abrigo encontrado nas coordenadas: " + latitude + ", " + longitude);
    }
}