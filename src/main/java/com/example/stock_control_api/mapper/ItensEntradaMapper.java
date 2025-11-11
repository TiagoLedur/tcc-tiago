package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.itensentrada.ItensEntradaRequestDTO;
import com.example.stock_control_api.dto.itensentrada.ItensEntradaResponseDTO;
import com.example.stock_control_api.model.Entrada;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.ItensEntrada;

public final class ItensEntradaMapper {

    private ItensEntradaMapper() {}

    public static ItensEntrada toEntity(ItensEntradaRequestDTO dto) {
        if (dto == null) return null;

        ItensEntrada item = new ItensEntrada();
        item.setQuantidade(dto.getQuantidade());

        if (dto.getEntradaId() != null) {
            Entrada entrada = new Entrada();
            entrada.setId(dto.getEntradaId());
            item.setEntrada(entrada);
        }

        if (dto.getIngredienteId() != null) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(dto.getIngredienteId());
            item.setIngrediente(ingrediente);
        }

        return item;
    }

    public static ItensEntradaResponseDTO toResponseDTO(ItensEntrada entity) {
        if (entity == null) return null;

        ItensEntradaResponseDTO dto = new ItensEntradaResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidade());
        dto.setEntradaId(entity.getEntrada() != null ? entity.getEntrada().getId() : null);
        dto.setIngredienteId(entity.getIngrediente() != null ? entity.getIngrediente().getId() : null);

        return dto;
    }
}

