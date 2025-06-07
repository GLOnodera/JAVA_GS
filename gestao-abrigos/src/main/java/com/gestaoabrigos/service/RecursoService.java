package com.gestaoabrigos.service;

import com.gestaoabrigos.dto.RecursoDisponivelDTO;
import com.gestaoabrigos.exception.RecursoNotFoundException;
import com.gestaoabrigos.model.Recurso;
import com.gestaoabrigos.repository.DistribuicaoRecursoRepository;
import com.gestaoabrigos.repository.RecursoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecursoService {

    private final RecursoRepository recursoRepository;
    private final DistribuicaoRecursoRepository distribuicaoRepository;

    @Transactional(readOnly = true)
    public List<Recurso> listarTodos() {
        return recursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Recurso buscarPorId(Long id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNotFoundException(id));
    }

    @Transactional
    public Recurso criarRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    @Transactional
    public Recurso atualizarRecurso(Long id, Recurso recursoAtualizado) {
        Recurso recurso = buscarPorId(id);
        recurso.setNome(recursoAtualizado.getNome());
        recurso.setTipo(recursoAtualizado.getTipo());
        recurso.setUnidade(recursoAtualizado.getUnidade());
        recurso.setQuantidadeTotal(recursoAtualizado.getQuantidadeTotal());
        recurso.setEstoqueMinimo(recursoAtualizado.getEstoqueMinimo());
        return recursoRepository.save(recurso);
    }

    @Transactional
    public void excluirRecurso(Long id) {

    }

    @Transactional(readOnly = true)
    public List<RecursoDisponivelDTO> listarRecursosDisponiveis() {
        return recursoRepository.findAll().stream()
                .map(recurso -> {
                    Integer distribuido = distribuicaoRepository.sumQuantidadeByRecursoId(recurso.getId());
                    Integer disponivel = recurso.getQuantidadeTotal() - (distribuido != null ? distribuido : 0);
                    return new RecursoDisponivelDTO(
                            recurso.getId(),
                            recurso.getNome(),
                            recurso.getTipo(),
                            recurso.getUnidade(),
                            disponivel,
                            calcularStatusEstoque(disponivel, recurso.getEstoqueMinimo())
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void distribuirRecurso(Long abrigoId, Long recursoId, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }

        Integer disponivel = calcularDisponibilidade(recursoId);
        if (disponivel < quantidade) {
            throw new IllegalStateException("Quantidade indisponível em estoque");
        }

        // Lógica de distribuição (implementar conforme necessidade)
    }

    private String calcularStatusEstoque(Integer disponivel, Integer estoqueMinimo) {
        if (disponivel <= 0) return "CRÍTICO";
        if (disponivel < estoqueMinimo) return "ALERTA";
        return "NORMAL";
    }

    @Transactional(readOnly = true)
    public Integer calcularDisponibilidade(Long recursoId) {
        Recurso recurso = buscarPorId(recursoId);
        Integer distribuido = distribuicaoRepository.sumQuantidadeByRecursoId(recursoId);
        return recurso.getQuantidadeTotal() - (distribuido != null ? distribuido : 0);
    }

    public void salvar(@Valid Recurso recurso) {
    }
}