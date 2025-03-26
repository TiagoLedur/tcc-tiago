package com.example.stock_control_api.service;

import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.repository.PedidoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoCompraService {

    private final PedidoCompraRepository pedidoCompraRepository;

    public List<PedidoCompra> list() {
        return pedidoCompraRepository.findAll();
    }

    public Optional<PedidoCompra> findById(Long id) {
        return pedidoCompraRepository.findById(id);
    }

    public PedidoCompra save(PedidoCompra categoria) {
        return pedidoCompraRepository.save(categoria);
    }

    public void delete(Long id) {
        pedidoCompraRepository.deleteById(id);
    }

}
