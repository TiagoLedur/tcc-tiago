package com.example.stock_control_api.dto.saida;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SaidaResponseDTO {
    private Long id;
    private Long usuarioId; // novo campo
    private LocalDateTime dataSaida;
}
