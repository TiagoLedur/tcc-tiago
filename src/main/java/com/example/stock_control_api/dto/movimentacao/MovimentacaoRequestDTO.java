package com.example.stock_control_api.dto.movimentacao;

import com.example.stock_control_api.model.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoRequestDTO {

    @NotNull(message = "O ID do ingrediente é obrigatório.")
    private Long ingredienteId;

    @NotNull(message = "O tipo de movimentação é obrigatório.")
    private TipoMovimentacao tipo;

    @NotNull(message = "A quantidade é obrigatória.")
    private Double quantidade;

    @NotNull(message = "A data da movimentação é obrigatória.")
    private LocalDateTime dataMovimentacao;

    private String observacao;
}
