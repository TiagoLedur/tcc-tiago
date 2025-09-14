package com.example.stock_control_api.dto.usuario;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private LocalDateTime criadoEm;
}
