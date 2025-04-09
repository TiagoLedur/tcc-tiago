package com.example.stock_control_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoRequestDTO {

    @jakarta.validation.constraints.NotNull(message = "O ID do ingrediente é obrigatório.")
    private Long ingrediente_id;

    @NotNull(message = "A quantidade é obrigatória.")
    @DecimalMin(value = "0.01", message = "A quantidade deve ser maior que zero.")
    private BigDecimal quantidade;

    @NotBlank(message = "O tipo de movimentação é obrigatório.")
    @Pattern(regexp = "ENTRADA|SAIDA", message = "O tipo deve ser ENTRADA ou SAIDA.")
    private String tipo;

    private String observacao;

    @NotNull(message = "A data da movimentação é obrigatória.")
    private LocalDate dataMovimentacao;
}
