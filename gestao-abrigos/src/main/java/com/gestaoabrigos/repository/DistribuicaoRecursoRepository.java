package com.gestaoabrigos.repository;

import com.gestaoabrigos.model.DistribuicaoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistribuicaoRecursoRepository extends JpaRepository<DistribuicaoRecurso, Long> {

    List<DistribuicaoRecurso> findByAbrigoId(Long abrigoId);

    List<DistribuicaoRecurso> findByRecursoId(Long recursoId);

    @Query("SELECT d FROM DistribuicaoRecurso d WHERE d.abrigo.id = :abrigoId AND d.recurso.id = :recursoId")
    List<DistribuicaoRecurso> findByAbrigoAndRecurso(
            @Param("abrigoId") Long abrigoId,
            @Param("recursoId") Long recursoId);

    @Query("SELECT SUM(d.quantidade) FROM DistribuicaoRecurso d WHERE d.recurso.id = :recursoId")
    Integer findTotalDistribuidoPorRecurso(@Param("recursoId") Long recursoId);

    void deleteByRecursoId(Long id);

    void deleteByAbrigoId(Long id);

    Integer sumQuantidadeByAbrigoId(Long abrigoId);

    Integer sumQuantidadeByRecursoId(Long id);
}