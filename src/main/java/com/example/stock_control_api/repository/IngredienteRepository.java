package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    List<Ingrediente> findByQuantidadeTotalLessThanEqual(BigDecimal quantidadeMinima);
    List<Ingrediente> findByValidadeLessThanEqual(LocalDate dataLimite);
    List<Ingrediente> findByValidadeBefore(LocalDate hoje);

    List<Ingrediente> findByValidadeBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Ingrediente> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT i FROM Ingrediente i WHERE (:categoriaId IS NULL OR i.categoria.id = :categoriaId)")
    List<Ingrediente> filtrarPorCategoria(@Param("categoriaId") Long categoriaId);

    @Query("""
        SELECT i FROM Ingrediente i
        WHERE (:nome IS NULL OR :nome = '' OR LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
        AND (:categoriaId IS NULL OR i.categoria.id = :categoriaId)
        AND (:dataInicio IS NULL OR i.validade >= :dataInicio)
        AND (:dataFim IS NULL OR i.validade <= :dataFim)
    """)
    List<Ingrediente> filtrarIngredientes(
            @Param("nome") String nome,
            @Param("categoriaId") Long categoriaId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );


}