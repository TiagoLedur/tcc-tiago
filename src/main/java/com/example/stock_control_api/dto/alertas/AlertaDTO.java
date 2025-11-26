package com.example.stock_control_api.dto.alertas;

import com.example.stock_control_api.enums.AlertaTipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDTO {
    private Long ingredienteId;
    private String nomeIngrediente;
    private AlertaTipo tipo;
    private String mensagem;
}
