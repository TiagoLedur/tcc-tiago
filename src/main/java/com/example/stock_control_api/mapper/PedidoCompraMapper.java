package com.example.stock_control_api.mapper;

import com.example.stock_control_api.dto.pedido.PedidoCompraRequestDTO;
import com.example.stock_control_api.dto.pedido.PedidoCompraResponseDTO;
import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.model.enums.StatusPedido;

public class PedidoCompraMapper{

    private PedidoCompraMapper() {}

    public static PedidoCompra toEntity(PedidoCompraRequestDTO dto, Fornecedor fornecedor) {
        return PedidoCompra.builder()
                .fornecedor(fornecedor)
                .status(StatusPedido.valueOf(dto.getStatus()))
                .dataPedido(dto.getDataPedido())
                .build();
    }

    public static PedidoCompraResponseDTO toDTO(PedidoCompra entity) {
        return PedidoCompraResponseDTO.builder()
                .id(entity.getId())
                .fornecedorId(entity.getFornecedor() != null ? entity.getFornecedor().getId() : null)
                .fornecedorNome(entity.getFornecedor() != null ? entity.getFornecedor().getNome() : null)
                .status(String.valueOf(entity.getStatus()))
                .dataPedido(entity.getDataPedido())
                .build();
    }
}
