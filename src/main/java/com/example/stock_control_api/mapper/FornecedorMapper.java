package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.fornecedor.FornecedorRequestDTO;
import com.example.stock_control_api.dto.fornecedor.FornecedorResponseDTO;
import com.example.stock_control_api.model.Fornecedor;

public class FornecedorMapper {

    private FornecedorMapper() {
    }

    public static Fornecedor toEntity(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setContato(dto.getContato());
        fornecedor.setCnpj(dto.getCnpj());
        return fornecedor;
    }

    public static FornecedorResponseDTO toDTO(Fornecedor entity) {
        return FornecedorResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .contato(entity.getContato())
                .cnpj(entity.getCnpj())
                .criadoEm(entity.getCriadoEm())
                .build();
    }
}
