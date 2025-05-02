package com.example.stock_control_api.dto.itempedido;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoCompraRequestDTO {

    @NotNull(message = "O ID do pedido é obrigatório.")
    private Long pedidoId;

    @NotNull(message = "O ID do ingrediente é obrigatório.")
    private Long ingredienteId;

    @NotNull(message = "A quantidade é obrigatória.")
    private BigDecimal quantidade;

    @NotNull(message = "O preço unitário é obrigatório.")
    private BigDecimal precoUnitario;
}
