package com.example.stock_control_api.service;

import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.repository.PedidoCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoCompraService {

    private final PedidoCompraRepository pedidoCompraRepository;

    public PedidoCompraService(PedidoCompraRepository pedidoCompraRepository) {
        this.pedidoCompraRepository = pedidoCompraRepository;
    }

    public List<PedidoCompra> listar() {
        return pedidoCompraRepository.findAll();
    }

    public Optional<PedidoCompra> buscarPorId(Long id) {
        return pedidoCompraRepository.findById(id);
    }

    public PedidoCompra salvar(PedidoCompra categoria) {
        return pedidoCompraRepository.save(categoria);
    }

    public void deletar(Long id) {
        pedidoCompraRepository.deleteById(id);
    }

}
