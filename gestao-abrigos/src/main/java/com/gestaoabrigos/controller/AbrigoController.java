package com.gestaoabrigos.controller;

import com.gestaoabrigos.model.Abrigo;
import com.gestaoabrigos.service.AbrigoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/abrigos")
@RequiredArgsConstructor
public class AbrigoController {
    private final AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listarTodos() {
        return ResponseEntity.ok(abrigoService.listarTodos());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Abrigo>> listarComCapacidadeDisponivel() {
        return ResponseEntity.ok(abrigoService.listarComCapacidadeDisponivel());
    }

    @PostMapping
    public ResponseEntity<Abrigo> criarAbrigo(@RequestBody Abrigo abrigo) {
        return ResponseEntity.ok(abrigoService.salvar(abrigo));
    }
}