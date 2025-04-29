package com.example.stock_control_api.dto.itempedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ItemPedidoCompraResponseDTO {
    private Long id;
    private Long ingredienteId;
    private String ingredienteNome;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
}
