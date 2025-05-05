package com.example.stock_control_api.dto.lote;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteIngredienteRequestDTO {

    @NotNull(message = "O ID do ingrediente é obrigatório.")
    private Long ingredienteId;

    @NotNull(message = "A quantidade é obrigatória.")
    @DecimalMin(value = "0.01", message = "A quantidade deve ser maior que zero.")
    private BigDecimal quantidade;

    @NotNull(message = "A data de validade é obrigatória.")
    private LocalDate dataValidade;
}
