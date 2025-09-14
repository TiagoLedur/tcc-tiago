package com.example.stock_control_api.dto.relatorio;

import lombok.Data;

@Data
public class RelatorioRequestDTO {
    private String tipo;
    private Long usuarioId;
}
