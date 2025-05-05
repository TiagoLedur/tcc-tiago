package com.example.stock_control_api.dto.lote;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteIngredienteResponseDTO {

    private Long id;
    private Long ingredienteId;
    private String ingredienteNome;
    private BigDecimal quantidade;
    private LocalDate dataValidade;
    private LocalDateTime criadoEm;
}
