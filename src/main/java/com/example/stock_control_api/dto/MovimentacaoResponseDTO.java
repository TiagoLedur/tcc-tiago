package com.example.stock_control_api.dto;

import com.example.stock_control_api.model.enums.TipoMovimentacao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovimentacaoResponseDTO {

    private Long id;
    private Long ingredienteId;
    private String ingredienteNome;
    private TipoMovimentacao tipo;
    private Double quantidade;
    private LocalDate dataMovimentacao;
    private String observacao;
}

