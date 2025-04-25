package com.example.stock_control_api.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PedidoCompraResponseDTO {
    private Long id;
    private Long fornecedorId;
    private String fornecedorNome;
    private String status;
    private LocalDateTime dataPedido;
}
