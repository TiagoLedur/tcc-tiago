package com.example.stock_control_api.dto.pedido;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoCompraRequestDTO {

    @NotNull(message = "O ID do fornecedor é obrigatório.")
    private Long fornecedorId;

    private String status; // PENDENTE, RECEBIDO, CANCELADO

    private LocalDateTime dataPedido; // opcional, pode ser gerado no backend também
}
