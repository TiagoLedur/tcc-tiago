package com.example.stock_control_api.dto.fornecedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String contato;
    private String cnpj;
    private LocalDateTime criadoEm;
}
