package com.example.stock_control_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoResponseDTO {
    private Long id;
    private String nomeIngrediente;
    private BigDecimal quantidade;
    private String tipo;
    private String observacao;
    private LocalDate dataMovimentacao;
}

