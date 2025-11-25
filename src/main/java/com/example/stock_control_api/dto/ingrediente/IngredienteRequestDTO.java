package com.example.stock_control_api.dto.ingrediente;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IngredienteRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A unidade de medida é obrigatória.")
    private String unidadeMedida;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = true, message = "O preço deve ser maior ou igual a zero.")
    private BigDecimal preco;

    @NotNull(message = "A quantidade total é obrigatória.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A quantidade total deve ser maior ou igual a zero.")
    private BigDecimal quantidadeTotal;

    @NotNull(message = "A quantidade mínima é obrigatória.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A quantidade mínima deve ser maior ou igual a zero.")
    private BigDecimal quantidadeMinima;

    @NotNull(message = "A validade é obrigatória")
    private LocalDate validade;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;
}
