package com.example.stock_control_api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nome;
}
