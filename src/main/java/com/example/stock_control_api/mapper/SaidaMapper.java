package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.saida.SaidaRequestDTO;
import com.example.stock_control_api.dto.saida.SaidaResponseDTO;
import com.example.stock_control_api.model.Saida;
import com.example.stock_control_api.model.Usuario;

public final class SaidaMapper {

    private SaidaMapper() {}

    public static Saida toEntity(SaidaRequestDTO dto) {
        if (dto == null) return null;

        Saida saida = new Saida();
        
        if (dto.getUsuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(dto.getUsuarioId());
            saida.setUsuario(u);
        }

        return saida;
    }

    public static SaidaResponseDTO toResponseDTO(Saida entity) {
        if (entity == null) return null;

        SaidaResponseDTO dto = new SaidaResponseDTO();
        dto.setId(entity.getId());
        dto.setDataSaida(entity.getDataSaida());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        dto.setNomeUsuario(entity.getUsuario() != null ? entity.getUsuario().getNome() : null);

        return dto;
    }
}
