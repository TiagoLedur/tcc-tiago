package com.example.stock_control_api.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CategoriaResponseDTO {
    private Long id;
    private String nome;
}
