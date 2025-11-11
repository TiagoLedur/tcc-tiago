package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.entrada.EntradaRequestDTO;
import com.example.stock_control_api.dto.entrada.EntradaResponseDTO;
import com.example.stock_control_api.model.Entrada;
import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.model.Usuario;

public final class EntradaMapper {

    private EntradaMapper() {}

    public static Entrada toEntity(EntradaRequestDTO dto) {
        if (dto == null) return null;

        Entrada entrada = new Entrada();

        if (dto.getFornecedorId() != null) {
            Fornecedor f = new Fornecedor();
            f.setId(dto.getFornecedorId());
            entrada.setFornecedor(f);
        }

        if (dto.getUsuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(dto.getUsuarioId());
            entrada.setUsuario(u);
        }

        return entrada;
    }

    public static EntradaResponseDTO toResponseDTO(Entrada entity) {
        if (entity == null) return null;

        EntradaResponseDTO dto = new EntradaResponseDTO();
        dto.setId(entity.getId());
        dto.setFornecedorId(entity.getFornecedor() != null ? entity.getFornecedor().getId() : null);
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);

        return dto;
    }
}