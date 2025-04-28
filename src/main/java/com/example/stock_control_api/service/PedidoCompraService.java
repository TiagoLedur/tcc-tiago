package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.pedido.PedidoCompraRequestDTO;
import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.model.enums.StatusPedido;
import com.example.stock_control_api.repository.FornecedorRepository;
import com.example.stock_control_api.repository.PedidoCompraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoCompraService {

    private final PedidoCompraRepository pedidoCompraRepository;
    private final FornecedorRepository fornecedorRepository;

    public PedidoCompra criarPedido(PedidoCompraRequestDTO pedidoDTO) {
        PedidoCompra pedido = new PedidoCompra();
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setFornecedor(fornecedorRepository.findById(pedidoDTO.getFornecedorId())
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado")));
        return pedidoCompraRepository.save(pedido);
    }

    public List<PedidoCompra> listarPedidos() {
        return pedidoCompraRepository.findAll();
    }

    public PedidoCompra buscarPorId(Long id) {
        return pedidoCompraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido de compra não encontrado"));
    }

    public PedidoCompra atualizarStatus(Long id, String novoStatus) {
        PedidoCompra pedido = buscarPorId(id);
        pedido.setStatus(StatusPedido.valueOf(novoStatus.toUpperCase()));
        return pedidoCompraRepository.save(pedido);
    }
}
