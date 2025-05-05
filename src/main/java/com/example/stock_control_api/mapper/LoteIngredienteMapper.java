package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.lote.*;
import com.example.stock_control_api.model.*;

public class LoteIngredienteMapper {

    private LoteIngredienteMapper() {}

    public static LoteIngrediente toEntity(LoteIngredienteRequestDTO dto, Ingrediente ingrediente) {
        return LoteIngrediente.builder()
                .ingrediente(ingrediente)
                .quantidade(dto.getQuantidade())
                .dataValidade(dto.getDataValidade())
                .build();
    }

    public static LoteIngredienteResponseDTO toDTO(LoteIngrediente entity) {
        return LoteIngredienteResponseDTO.builder()
                .id(entity.getId())
                .ingredienteId(entity.getIngrediente().getId())
                .ingredienteNome(entity.getIngrediente().getNome())
                .quantidade(entity.getQuantidade())
                .dataValidade(entity.getDataValidade())
                .criadoEm(entity.getCriadoEm())
                .build();
    }
}
