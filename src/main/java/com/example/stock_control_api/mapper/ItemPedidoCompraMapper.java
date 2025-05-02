package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraRequestDTO;
import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraResponseDTO;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.ItemPedidoCompra;
import com.example.stock_control_api.model.PedidoCompra;

public class ItemPedidoCompraMapper {

    private ItemPedidoCompraMapper() {}

    public static ItemPedidoCompra toEntity(ItemPedidoCompraRequestDTO dto, PedidoCompra pedido, Ingrediente ingrediente) {
        return ItemPedidoCompra.builder()
                .pedido(pedido)
                .ingrediente(ingrediente)
                .quantidade(dto.getQuantidade())
                .precoUnitario(dto.getPrecoUnitario())
                .build();
    }


    public static ItemPedidoCompraResponseDTO toDTO(ItemPedidoCompra entity) {
        return ItemPedidoCompraResponseDTO.builder()
                .id(entity.getId())
                .ingredienteId(entity.getIngrediente() != null ? entity.getIngrediente().getId() : null)
                .ingredienteNome(entity.getIngrediente() != null ? entity.getIngrediente().getNome() : null)
                .quantidade(entity.getQuantidade())
                .precoUnitario(entity.getPrecoUnitario())
                .build();
    }
}
