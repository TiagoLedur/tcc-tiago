package com.example.stock_control_api.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoRelatorioDTO {
    private Long id;
    private String tipo;
    private String ingrediente;
    private Double quantidade;
    private String fornecedor;
    private String usuario;
    private Date data;
}
