package com.example.stock_control_api.dto.fornecedor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorRequestDTO {

    @NotBlank(message = "O nome do fornecedor é obrigatório.")
    private String nome;

    private String contato;

    private String cnpj;
}
