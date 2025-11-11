package com.example.stock_control_api.dto.usuario;

import com.example.stock_control_api.model.enums.Cargo;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Cargo cargo;
    private LocalDateTime criadoEm;
}
