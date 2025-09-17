package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.relatorio.RelatorioRequestDTO;
import com.example.stock_control_api.dto.relatorio.RelatorioResponseDTO;
import com.example.stock_control_api.model.Relatorio;
import com.example.stock_control_api.model.Usuario;

public final class RelatorioMapper {

    private RelatorioMapper() {}

    public static Relatorio toEntity(RelatorioRequestDTO dto) {
        if (dto == null) return null;

        Relatorio relatorio = new Relatorio();
        relatorio.setTipo(dto.getTipo());
        relatorio.setCriadoEm(dto.getCriadoEm());

        if (dto.getUsuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(dto.getUsuarioId());
            relatorio.setUsuario(u);
        }

        return relatorio;
    }

    public static RelatorioResponseDTO toResponseDTO(Relatorio entity) {
        if (entity == null) return null;

        RelatorioResponseDTO dto = new RelatorioResponseDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);

        return dto;
    }
}

