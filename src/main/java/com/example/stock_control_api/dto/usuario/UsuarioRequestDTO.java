package com.example.stock_control_api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private String cargo;
}
