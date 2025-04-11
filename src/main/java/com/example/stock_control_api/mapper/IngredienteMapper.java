package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.ingrediente.IngredienteRequestDTO;
import com.example.stock_control_api.dto.ingrediente.IngredienteResponseDTO;
import com.example.stock_control_api.model.Categoria;
import com.example.stock_control_api.model.Ingrediente;

public class IngredienteMapper {

    private IngredienteMapper() {
    }

    public static Ingrediente toEntity(IngredienteRequestDTO dto, Categoria categoria) {
        return Ingrediente.builder()
                .nome(dto.getNome())
                .unidadeMedida(dto.getUnidadeMedida())
                .preco(dto.getPreco())
                .quantidadeTotal(dto.getQuantidadeTotal())
                .categoria(categoria)
                .build();
    }

    public static IngredienteResponseDTO toDTO(Ingrediente entity) {
        return IngredienteResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .unidadeMedida(entity.getUnidadeMedida())
                .preco(entity.getPreco())
                .quantidadeTotal(entity.getQuantidadeTotal())
                .categoriaId(entity.getCategoria() != null ? entity.getCategoria().getId() : null)
                .criadoEm(entity.getCriadoEm())
                .build();
    }
}
