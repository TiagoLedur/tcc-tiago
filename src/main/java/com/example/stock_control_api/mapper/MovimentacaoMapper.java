package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.MovimentacaoRequestDTO;
import com.example.stock_control_api.dto.MovimentacaoResponseDTO;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.Movimentacao;

public class MovimentacaoMapper {

    private MovimentacaoMapper() {
    }

    public static Movimentacao toEntity(MovimentacaoRequestDTO dto, Ingrediente ingrediente) {
        return Movimentacao.builder()
                .ingrediente(ingrediente)
                .tipo(dto.getTipo())
                .quantidade(dto.getQuantidade())
                .dataMovimentacao(dto.getDataMovimentacao())
                .observacao(dto.getObservacao())
                .build();
    }

    public static MovimentacaoResponseDTO toDTO(Movimentacao entity) {
        return MovimentacaoResponseDTO.builder()
                .id(entity.getId())
                .ingredienteId(entity.getIngrediente().getId())
                .ingredienteNome(entity.getIngrediente().getNome())
                .tipo(entity.getTipo())
                .quantidade(entity.getQuantidade())
                .dataMovimentacao(entity.getDataMovimentacao())
                .observacao(entity.getObservacao())
                .build();
    }
}
