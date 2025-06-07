package com.gestaoabrigos.exception;

public class RecursoNotFoundException extends RuntimeException {
    public RecursoNotFoundException(Long id) {
        super("Recurso não encontrado com ID: " + id);
    }
}