package com.gestaoabrigos.controller;

import com.gestaoabrigos.service.AbrigoService;
import com.gestaoabrigos.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final AbrigoService abrigoService;
    private final RecursoService recursoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("abrigos", abrigoService.listarTodos());
        model.addAttribute("recursos", recursoService.listarRecursosDisponiveis());
        return "dashboard";
    }
}