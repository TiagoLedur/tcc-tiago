package com.example.stock_control_api.dto.saida;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SaidaRequestDTO {
    private Long usuarioId;
    private LocalDateTime dataSaida;
}
