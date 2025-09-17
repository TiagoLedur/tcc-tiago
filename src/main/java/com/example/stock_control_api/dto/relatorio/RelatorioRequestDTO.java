package com.example.stock_control_api.dto.relatorio;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RelatorioRequestDTO {
    private String tipo;
    private Long usuarioId;
    private LocalDateTime criadoEm;
}
