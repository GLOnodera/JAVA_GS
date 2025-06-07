package com.gestaoabrigos.repository;

import com.gestaoabrigos.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {


    List<Abrigo> findByNomeContainingIgnoreCase(String nome);


    List<Abrigo> findByLocalizacaoContainingIgnoreCase(String localizacao);


    @Query("SELECT a FROM Abrigo a WHERE a.capacidade > (" +
            "SELECT COALESCE(SUM(d.quantidade), 0) " +
            "FROM DistribuicaoRecurso d " +
            "WHERE d.abrigo = a)")
    List<Abrigo> findAbrigosComCapacidadeDisponivel();


    @Query(value = "SELECT a.* FROM RM553779.GS_ABRIGO a " +
            "WHERE SQRT(POWER(a.latitude - :latitude, 2) + " +
            "POWER(a.longitude - :longitude, 2)) <= :raioKm " +
            "ORDER BY SQRT(POWER(a.latitude - :latitude, 2) + " +
            "POWER(a.longitude - :longitude, 2))",
            nativeQuery = true)
    List<Abrigo> findAbrigosProximos(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("raioKm") Double raioKm);


    @Query("SELECT DISTINCT a FROM Abrigo a " +
            "LEFT JOIN FETCH a.recursos " +
            "WHERE a.id = :id")
    Optional<Abrigo> findByIdWithRecursos(@Param("id") Long id);


    List<Abrigo> findByCapacidadeGreaterThanEqual(Integer capacidade);
}