package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    @Query("SELECT DISTINCT e FROM Entrada e " +
            "LEFT JOIN e.itensEntrada ie " +
            "WHERE (:id IS NULL OR e.id = :id) " +
            "AND (:fornecedorId IS NULL OR e.fornecedor.id = :fornecedorId) " +
            "AND (:ingredienteId IS NULL OR ie.ingrediente.id = :ingredienteId) " +
            "AND (CAST(:dataInicio AS timestamp) IS NULL OR e.dataEntrada >= :dataInicio) " +
            "AND (CAST(:dataFim AS timestamp) IS NULL OR e.dataEntrada <= :dataFim)")
    List<Entrada> filtrarEntradas(
            @Param("id") Long id,
            @Param("fornecedorId") Long fornecedorId,
            @Param("ingredienteId") Long ingredienteId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim
    );
}

