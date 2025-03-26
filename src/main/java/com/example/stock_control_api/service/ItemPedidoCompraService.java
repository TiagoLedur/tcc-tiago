package com.example.stock_control_api.service;

import com.example.stock_control_api.model.ItemPedidoCompra;
import com.example.stock_control_api.repository.ItemPedidoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPedidoCompraService {

    private final ItemPedidoCompraRepository itemPedidoCompraRepository;

    public List<ItemPedidoCompra> list() {
        return itemPedidoCompraRepository.findAll();
    }

    public Optional<ItemPedidoCompra> findById(Long id) {
        return itemPedidoCompraRepository.findById(id);
    }

    public ItemPedidoCompra save(ItemPedidoCompra categoria) {
        return itemPedidoCompraRepository.save(categoria);
    }

    public void delete(Long id) {
        itemPedidoCompraRepository.deleteById(id);
    }

}
