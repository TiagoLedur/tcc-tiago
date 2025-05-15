package com.example.stock_control_api.dto.movimentacao;

import com.example.stock_control_api.model.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoResponseDTO {

    private Long id;
    private Long ingredienteId;
    private String ingredienteNome;
    private TipoMovimentacao tipo;
    private Double quantidade;
    private LocalDateTime dataMovimentacao;
    private String observacao;
}

