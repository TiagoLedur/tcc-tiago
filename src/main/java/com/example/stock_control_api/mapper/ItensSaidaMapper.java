package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.itenssaida.ItensSaidaRequestDTO;
import com.example.stock_control_api.dto.itenssaida.ItensSaidaResponseDTO;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.ItensSaida;
import com.example.stock_control_api.model.Saida;

public final class ItensSaidaMapper {

    private ItensSaidaMapper() {}

    public static ItensSaida toEntity(ItensSaidaRequestDTO dto) {
        if (dto == null) return null;

        ItensSaida item = new ItensSaida();
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(dto.getPrecoUnitario());

        if (dto.getSaidaId() != null) {
            Saida saida = new Saida();
            saida.setId(dto.getSaidaId());
            item.setSaida(saida);
        }

        if (dto.getIngredienteId() != null) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(dto.getIngredienteId());
            item.setIngrediente(ingrediente);
        }

        return item;
    }

    public static ItensSaidaResponseDTO toResponseDTO(ItensSaida entity) {
        if (entity == null) return null;

        ItensSaidaResponseDTO dto = new ItensSaidaResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidade());
        dto.setPrecoUnitario(entity.getPrecoUnitario());
        dto.setSaidaId(entity.getSaida() != null ? entity.getSaida().getId() : null);
        dto.setIngredienteId(entity.getIngrediente() != null ? entity.getIngrediente().getId() : null);

        return dto;
    }
}

