package com.example.stock_control_api.dto.itenssaida;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItensSaidaResponseDTO {

    private Long id;
    private Long saidaId;
    private Long ingredienteId;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
}
