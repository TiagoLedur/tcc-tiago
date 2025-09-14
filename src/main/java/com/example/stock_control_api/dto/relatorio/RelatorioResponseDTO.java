package com.example.stock_control_api.dto.relatorio;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RelatorioResponseDTO {
    private Long id;
    private String tipo;
    private Long usuarioId;
    private LocalDateTime criadoEm;
}
