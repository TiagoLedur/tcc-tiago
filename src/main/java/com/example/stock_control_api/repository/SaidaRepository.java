package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {

    @Query("SELECT DISTINCT s FROM Saida s " +
            "LEFT JOIN s.itensSaida isd " +
            "WHERE (:id IS NULL OR s.id = :id) " +
            "AND (:usuarioId IS NULL OR s.usuario.id = :usuarioId) " +
            "AND (:ingredienteId IS NULL OR isd.ingrediente.id = :ingredienteId) " +
            "AND (CAST(:dataInicio AS timestamp) IS NULL OR s.dataSaida >= :dataInicio) " +
            "AND (CAST(:dataFim AS timestamp) IS NULL OR s.dataSaida <= :dataFim)")
    List<Saida> filtrarSaidas(
            @Param("id") Long id,
            @Param("usuarioId") Long usuarioId,
            @Param("ingredienteId") Long ingredienteId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim
    );
}