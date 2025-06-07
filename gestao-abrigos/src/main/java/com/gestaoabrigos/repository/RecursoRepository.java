package com.gestaoabrigos.repository;

import com.gestaoabrigos.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {


    List<Recurso> findByNomeContainingIgnoreCaseOrTipoContainingIgnoreCase(String nome, String tipo);


    @Query("""
        SELECT r FROM Recurso r 
        WHERE r.quantidadeTotal < r.estoqueMinimo 
        OR r.quantidadeTotal < (SELECT COALESCE(SUM(d.quantidade), 0) 
                               FROM DistribuicaoRecurso d 
                               WHERE d.recurso = r)""")
    List<Recurso> findRecursosComEstoqueCritico();


    @Query("""
        SELECT r.quantidadeTotal - COALESCE(SUM(d.quantidade), 0) 
        FROM Recurso r LEFT JOIN DistribuicaoRecurso d ON d.recurso = r 
        WHERE r.id = :recursoId 
        GROUP BY r.id""")
    Integer findQuantidadeDisponivel(@Param("recursoId") Long recursoId);


    @Query("SELECT r FROM Recurso r LEFT JOIN FETCH r.distribuicoes WHERE r.id = :id")
    Optional<Recurso> findByIdWithDistribuicoes(@Param("id") Long id);
}