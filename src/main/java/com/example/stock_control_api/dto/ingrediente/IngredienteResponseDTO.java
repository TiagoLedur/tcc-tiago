package com.example.stock_control_api.dto.ingrediente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteResponseDTO {
    private Long id;
    private String nome;
    private Long categoriaId;
    private String unidadeMedida;
    private BigDecimal preco;
    private BigDecimal quantidadeTotal;
    private BigDecimal quantidadeMinima;
    private LocalDate validade;
    private LocalDateTime criadoEm;
}
