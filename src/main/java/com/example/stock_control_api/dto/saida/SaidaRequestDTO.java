package com.example.stock_control_api.dto.saida;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaidaRequestDTO {
    private Long usuarioId;
    private Long ingredienteId;
    private BigDecimal quantidade;
}
