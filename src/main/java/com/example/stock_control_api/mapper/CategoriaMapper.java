package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.categoria.CategoriaRequestDTO;
import com.example.stock_control_api.dto.categoria.CategoriaResponseDTO;
import com.example.stock_control_api.model.Categoria;

import java.util.Optional;

public class CategoriaMapper {

    private CategoriaMapper() {
    }

    public static Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return categoria;
    }

    public static CategoriaResponseDTO toDTO(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}
