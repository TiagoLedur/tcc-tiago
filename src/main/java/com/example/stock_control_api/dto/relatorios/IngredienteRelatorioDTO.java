package com.example.stock_control_api.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteRelatorioDTO {

    private Long id;
    private String nome;
    private String categoria;
    private String unidadeMedida;
    private Double precoUnitario;
    private Double quantidadeTotal;
    private Date validade;

}
