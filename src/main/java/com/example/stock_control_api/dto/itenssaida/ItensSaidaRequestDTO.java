package com.example.stock_control_api.dto.itenssaida;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItensSaidaRequestDTO {

    @NotNull(message = "O ID da saída é obrigatório")
    private Long saidaId;

    @NotNull(message = "O ID do ingrediente é obrigatório")
    private Long ingredienteId;

    @NotNull(message = "A quantidade é obrigatória")
    @DecimalMin(value = "0.01", message = "A quantidade deve ser maior que zero")
    private BigDecimal quantidade;

}
