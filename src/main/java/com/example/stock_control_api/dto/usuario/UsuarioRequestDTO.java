package com.example.stock_control_api.dto.usuario;

import com.example.stock_control_api.model.enums.Cargo;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private Cargo cargo;
}
