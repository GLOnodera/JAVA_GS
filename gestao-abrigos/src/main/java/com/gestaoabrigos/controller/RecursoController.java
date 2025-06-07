package com.gestaoabrigos.controller;

import com.gestaoabrigos.model.Recurso;
import com.gestaoabrigos.service.RecursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/recursos")
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService recursoService;

    @GetMapping
    public String listarRecursos(Model model) {
        model.addAttribute("recursos", recursoService.listarRecursosDisponiveis());
        return "recursos/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("recurso", new Recurso());
        return "recursos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarRecurso(@Valid @ModelAttribute("recurso") Recurso recurso,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "recursos/formulario";
        }
        recursoService.salvar(recurso);
        attributes.addFlashAttribute("mensagem", "Recurso cadastrado com sucesso!");
        return "redirect:/recursos";
    }
}