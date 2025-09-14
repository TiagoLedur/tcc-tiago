package com.example.stock_control_api.dto.entrada;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntradaRequestDTO {
    private Long fornecedorId;
    private Long usuarioId;
    private LocalDateTime dataPedido;
}
