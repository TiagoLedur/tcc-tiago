package com.example.stock_control_api.dto.saida;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SaidaResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nomeUsuario;
    private Long ingredienteId;
    private String nomeIngrediente;
    private BigDecimal quantidade;
    private LocalDateTime dataSaida;
}
