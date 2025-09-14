package com.example.stock_control_api.dto.itensentrada;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItensEntradaResponseDTO {

    private Long id;
    private Long entradaId;
    private Long ingredienteId;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
}
