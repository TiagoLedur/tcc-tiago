package com.example.stock_control_api.dto.entrada;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntradaResponseDTO {
    private Long id;
    private Long fornecedorId;
    private Long usuarioId;
    private String usuarioNome;
    private LocalDateTime dataEntrada;
}
